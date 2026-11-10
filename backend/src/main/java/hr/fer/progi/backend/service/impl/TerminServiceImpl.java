package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.request.*;
import hr.fer.progi.backend.enums.Uloga;
import hr.fer.progi.backend.exception.ResourceNotFoundException;
import hr.fer.progi.backend.exception.ResursAlreadyAllocatedException;
import hr.fer.progi.backend.model.Korisnik;
import hr.fer.progi.backend.model.Resurs;
import hr.fer.progi.backend.model.Termin;
import hr.fer.progi.backend.model.TerminResurs;
import hr.fer.progi.backend.repository.TerminRepository;
import hr.fer.progi.backend.repository.TerminResursRepository;
import hr.fer.progi.backend.service.IzletService;
import hr.fer.progi.backend.service.KorisnikService;
import hr.fer.progi.backend.service.ResursService;
import hr.fer.progi.backend.service.TerminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TerminServiceImpl implements TerminService {

    private final TerminRepository terminRepository;
    private final TerminResursRepository terminResursRepository;
    private final IzletService izletService;
    private final ResursService resursService;
    private final KorisnikService korisnikService;

    @Override
    public Integer dohvatiBrojSlobodnihMjesta(Termin termin) {
        return terminRepository.getNumberOfAvailableSpots(termin);
    }

    @Override
    public Termin getTermin(Long id) {
        return terminRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Termin s tim id ne postoji"));
    }

    @Override
    public List<Termin> dohvatiSveTermine() {
        return terminRepository.findAll();
    }

    @Override
    @Transactional
    public Termin kreirajTermin(TerminCreateRequest request) {
        var izlet = izletService.getIzlet(request.izletId());

        Set<Korisnik> vodici = null;
        if (!request.vodici().isEmpty()) {
            var ids = new HashSet<>(request.vodici());
            vodici = korisnikService.dohvatiSveKorisnikePoId(ids);
            if (vodici.size() != ids.size())
                throw new ResourceNotFoundException("Jedan ili vise vodica ne postoji");

            if (!jesuSviVodici(List.copyOf(vodici))) {
                throw new IllegalArgumentException("Jedan ili vise korisnika nisu vodici");
            }
        }

        var termin = new Termin(request.datumVrijeme(), request.kapacitet(),
                request.mjestoPolaska(), request.cijena(), request.potrebanBrojVodica(),
                request.trajanjeMinuta(), izlet);

        if (vodici != null)
            termin.setVodici(vodici);

        return terminRepository.save(termin);
    }

    @Override
    @Transactional
    public Termin azurirajTermin(Long id, TerminUpdateRequest request) {
        var termin = terminRepository.findByIdForUpdate(id)
                .orElseThrow(() -> new ResourceNotFoundException("Termin s tim id ne postoji"));

        int zauzetaMjesta = termin.getKapacitet() - dohvatiBrojSlobodnihMjesta(termin);
        if (request.kapacitet() < zauzetaMjesta) {
            throw new IllegalArgumentException(
                    "Kapacitet ne može biti manji od broja zauzetih mjesta"
            );
        }

        termin.setCijena(request.cijena());
        termin.setDatumVrijeme(request.datumVrijeme());
        termin.setKapacitet(request.kapacitet());
        termin.setMjestoPolaska(request.mjestoPolaska());
        termin.setTrajanjeMinuta(request.trajanjeMinuta());
        termin.setPotrebanBrojVodica(request.potrebanBrojVodica());

        if (request.vodici() != null) {
            var ids = new HashSet<>(request.vodici());

            var korisnici =
                    korisnikService.dohvatiSveKorisnikePoId(ids);
            if (korisnici.size() != ids.size())
                throw new ResourceNotFoundException("Jedan ili vise vodica ne postoji");

            if (!jesuSviVodici(korisnici))
                throw new IllegalArgumentException("Jedan ili vise korisnika nisu vodici");

            termin.setVodici(new HashSet<>(korisnici));
        }

        return termin;
    }

    @Override
    @Transactional
    public void obrisiTermin(Long id) {
        if (!terminRepository.existsById(id)) {
            throw new ResourceNotFoundException("Termin s tim id ne postoji");
        }
        terminRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Termin postaviVodice(Long id, TerminSetVodiciRequest request) {
        if (request.vodici() == null)
            throw new IllegalArgumentException("Nema polja za vodice");

        var termin = getTermin(id);

        var ids = new HashSet<>(request.vodici());

        if (ids.size() > termin.getPotrebanBrojVodica()) {
            throw new IllegalArgumentException(
                    "Broj dodijeljenih vodiča ne može biti veći od potrebnog broja"
            );
        }

        var korisnici = korisnikService.dohvatiSveKorisnikePoId(ids);

        if (korisnici.size() != ids.size()) {
            throw new ResourceNotFoundException("Jedan ili vise vodica ne postoji");
        }
        if (!jesuSviVodici(korisnici))
            throw new IllegalArgumentException("Jedan ili vise korisnika nisu vodici");

        termin.setVodici(new HashSet<>(korisnici));

        return termin;
    }

    @Override
    public List<TerminResurs> dohvatiSveResurseTermina(Long id) {
        var termin = getTermin(id);

        return terminResursRepository.findAllByTermin_Id(termin.getId());
    }

    @Override
    @Transactional
    public void makniResursTerminu(Long terminId, Long resursId) {
        var terminResurs = terminResursRepository
                .findByTermin_IdAndResurs_Id(terminId, resursId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Taj resurs nije dodijeljen terminu"));
        terminResursRepository.delete(terminResurs);
    }

    @Override
    @Transactional
    public TerminResurs dodijeliResursTerminu(Long id, TerminResursSetRequest request) {
        Long resursId = request.resursId();
        if (!terminRepository.existsById(id))
            throw new ResourceNotFoundException("Termin s tim id ne postoji");

        var resurs = resursService.getResurs(resursId);
        var termin = terminRepository.getReferenceById(id);

        if (terminResursRepository
                .findByTermin_IdAndResurs_Id(id, resursId)
                .isPresent()) {
            throw new ResursAlreadyAllocatedException(
                    "Resurs je vec dodijeljen tom terminu");
        }

        provjeriRaspolozivostResursa(termin, resurs, request.kolicina());

        var terminResurs = new TerminResurs(termin, resurs, request.kolicina());
        return (terminResursRepository.save(terminResurs));
    }

    @Override
    @Transactional
    public TerminResurs azurirajKolicinuResursaTerminu(
            Long terminId,
            Long resursId,
            TerminResursUpdateRequest request) {

        var terminResurs = terminResursRepository
                .findByTermin_IdAndResurs_Id(terminId, resursId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Taj resurs nije dodijeljen tom terminu"));

        if (request.kolicina() > terminResurs.getResurs().getKolicina()) {
            throw new IllegalArgumentException(
                    "Količina dodjele ne može biti veća od ukupne zalihe resursa"
            );
        }

        provjeriRaspolozivostResursa(terminResurs.getTermin(), terminResurs.getResurs(),
                request.kolicina());

        terminResurs.setKolicina(request.kolicina());

        return terminResurs;
    }

    private boolean jesuSviVodici(Collection<Korisnik> korisnici) {
        return korisnici.stream().allMatch(vodic -> vodic.getUloge().contains(Uloga.VODIC));
    }

    private void provjeriRaspolozivostResursa(Termin termin, Resurs resurs, int kolicina) {
        if (kolicina > resurs.getKolicina()) {
            throw new IllegalArgumentException("Količina dodjele prelazi ukupnu zalihu resursa");
        }

        var pocetak = termin.getDatumVrijeme();
        var kraj = pocetak.plusMinutes(termin.getTrajanjeMinuta());
        var promjene = new TreeMap<LocalDateTime, Long>();

        var dodjele = terminResursRepository.findAllByResurs_IdAndTermin_IdNot(
                resurs.getId(), termin.getId()
        );

        for (var dodjela : dodjele) {
            var drugi = dodjela.getTermin();
            var drugiPocetak = drugi.getDatumVrijeme();
            var drugiKraj = drugiPocetak.plusMinutes(drugi.getTrajanjeMinuta());

            var od = pocetak.isAfter(drugiPocetak) ? pocetak : drugiPocetak;
            var doKada = kraj.isBefore(drugiKraj) ? kraj : drugiKraj;

            if (od.isBefore(doKada)) {
                promjene.merge(od, dodjela.getKolicina().longValue(), Long::sum);
                promjene.merge(doKada, -dodjela.getKolicina().longValue(), Long::sum);
            }
        }

        long zauzeto = kolicina;
        for (long promjena : promjene.values()) {
            zauzeto += promjena;
            if (zauzeto > resurs.getKolicina()) {
                throw new IllegalArgumentException(
                        "Nema dovoljno raspoloživih resursa za taj termin"
                );
            }
        }
    }
}
