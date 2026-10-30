package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.request.*;
import hr.fer.progi.backend.enums.Uloga;
import hr.fer.progi.backend.exception.ResourceNotFoundException;
import hr.fer.progi.backend.exception.ResursAlreadyAllocatedException;
import hr.fer.progi.backend.mapper.TerminMapper;
import hr.fer.progi.backend.mapper.TerminResursMapper;
import hr.fer.progi.backend.model.Korisnik;
import hr.fer.progi.backend.model.Termin;
import hr.fer.progi.backend.model.TerminResurs;
import hr.fer.progi.backend.repository.*;
import hr.fer.progi.backend.service.TerminService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TerminServiceImpl implements TerminService {

    private final TerminRepository terminRepository;
    private final TerminMapper terminMapper;
    private final IzletRepository izletRepository;
    private final KorisnikRepository korisnikRepository;
    private final TerminResursRepository terminResursRepository;
    private final TerminResursMapper terminResursMapper;
    private final ResursRepository resursRepository;


    public TerminServiceImpl(
            TerminRepository terminRepository,
            @Lazy TerminMapper terminMapper,
            IzletRepository izletRepository,
            KorisnikRepository korisnikRepository, TerminResursRepository terminResursRepository,
            TerminResursMapper terminResursMapper, ResursRepository resursRepository) {

        this.terminRepository = terminRepository;
        this.terminMapper = terminMapper;
        this.izletRepository = izletRepository;
        this.korisnikRepository = korisnikRepository;
        this.terminResursRepository = terminResursRepository;
        this.terminResursMapper = terminResursMapper;
        this.resursRepository = resursRepository;
    }

    @Override
    public Integer dohvatiBrojSlobodnihMjesta(Termin termin) {
        return terminRepository.getNumberOfAvailableSpots(termin);
    }

    @Override
    public Termin dohvatiTermin(Long id) {
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
        var izlet = izletRepository.findById(request.izletId()).orElseThrow(
                () -> new ResourceNotFoundException("Izlet s tim id ne postoji"));

        Set<Korisnik> vodici = null;
        if (!request.vodici().isEmpty()) {
            var ids = new HashSet<>(request.vodici());
            vodici = new HashSet<>(korisnikRepository.findAllById(ids));
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
        var termin = terminRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Termin s tim id ne postoji"));

        termin.setCijena(request.cijena());
        termin.setDatumVrijeme(request.datumVrijeme());
        termin.setKapacitet(request.kapacitet());
        termin.setMjestoPolaska(request.mjestoPolaska());
        termin.setTrajanjeMinuta(request.trajanjeMinuta());
        termin.setPotrebanBrojVodica(request.potrebanBrojVodica());

        if (request.vodici() != null) {
            var ids = new HashSet<>(request.vodici());

            var korisnici =
                    korisnikRepository.findAllById(ids);
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

        var termin = terminRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Termin s tim id ne postoji"));

        var ids = new HashSet<>(request.vodici());
        var korisnici = korisnikRepository.findAllById(ids);

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
        var termin = terminRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Termin s tim id ne postoji"));

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

        var resurs = resursRepository.findById(resursId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resurs s tim id ne postoji"));
        var termin = terminRepository.getReferenceById(id);

        if (terminResursRepository
                .findByTermin_IdAndResurs_Id(id, resursId)
                .isPresent()) {
            throw new ResursAlreadyAllocatedException(
                    "Resurs je vec dodijeljen tom terminu");
        }

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

        terminResurs.setKolicina(request.kolicina());

        return terminResurs;
    }

    private boolean jesuSviVodici(List<Korisnik> korisnici) {
        return korisnici.stream().allMatch(vodic -> vodic.getUloge().contains(Uloga.VODIC));
    }

}
