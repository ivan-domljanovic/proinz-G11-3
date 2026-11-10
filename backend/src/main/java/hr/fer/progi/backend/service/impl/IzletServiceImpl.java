package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.request.IzletCreateRequest;
import hr.fer.progi.backend.dto.request.IzletSearchRequest;
import hr.fer.progi.backend.dto.request.IzletUpdateRequest;
import hr.fer.progi.backend.enums.Status;
import hr.fer.progi.backend.exception.ResourceNotFoundException;
import hr.fer.progi.backend.model.Izlet;
import hr.fer.progi.backend.model.Termin;
import hr.fer.progi.backend.repository.AtrakcijaRepository;
import hr.fer.progi.backend.repository.DodatnaUslugaRepository;
import hr.fer.progi.backend.repository.IzletRepository;
import hr.fer.progi.backend.repository.TerminRepository;
import hr.fer.progi.backend.service.IzletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IzletServiceImpl implements IzletService {

    private final IzletRepository izletRepository;
    private final AtrakcijaRepository atrakcijaRepository;
    private final DodatnaUslugaRepository dodatnaUslugaRepository;
    private final TerminRepository terminRepository;

    @Override
    public Izlet getIzlet(Long id) {
        return izletRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Izlet s tim id ne postoji"));
    }

    @Override
    public List<Izlet> dohvatiSveIzlete() {
        return izletRepository.findAll();
    }

    @Override
    @Transactional
    public Izlet kreirajIzlet(IzletCreateRequest request) {
        var atrakcije = atrakcijaRepository.findAllById(request.atrakcije());
        var dodatneUsluge = dodatnaUslugaRepository.findAllById(request.dodatneUsluge());

        if (atrakcije.size() != request.atrakcije().stream().distinct().count()) {
            throw new ResourceNotFoundException("Jedna ili više atrakcija ne postoji");
        }

        if (dodatneUsluge.size() != request.dodatneUsluge().stream().distinct().count()) {
            throw new ResourceNotFoundException("Jedna ili više dodatnih usluga ne postoji");
        }

        var izlet = new Izlet(request.naziv(), request.opis(), request.kategorijaAktivnosti(),
                request.lokacija(), atrakcije,
                dodatneUsluge);

        return izletRepository.save(izlet);
    }

    @Override
    @Transactional
    public void obrisiIzlet(Long id) {
        if (!izletRepository.existsById(id))
            throw new ResourceNotFoundException("Izlet s tim id ne postoji");
        izletRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Izlet azurirajIzlet(
            Long id, IzletUpdateRequest request) {

        var izlet = getIzlet(id);

        izlet.setNaziv(request.naziv());
        izlet.setOpis(request.opis());
        izlet.setKategorijaAktivnosti(request.kategorijaAktivnosti());
        izlet.setLokacija(request.lokacija());

        if (request.atrakcije() != null) {
            var atrakcije = atrakcijaRepository.findAllById(request.atrakcije());
            if (atrakcije.size() != request.atrakcije().size())
                throw new ResourceNotFoundException("Jedna ili vise atrakcija ne postoji");
            izlet.setAtrakcije(atrakcije);
        }
        if (request.dodatneUsluge() != null) {
            var usluge = dodatnaUslugaRepository.findAllById(request.dodatneUsluge());
            if (usluge.size() != request.dodatneUsluge().size())
                throw new ResourceNotFoundException("Jedna ili vise dodatna usluga ne postoji");
            izlet.setDodatneUsluge(usluge);
        }

        return izlet;
    }

    @Override
    public List<Termin> dohvatiTermineIzleta(Long id) {
        if (!izletRepository.existsById(id))
            throw new ResourceNotFoundException("Izlet s tim id ne postoji");

        return terminRepository.findAllByIzlet_Id(id);
    }

    @Override
    @Transactional
    public Map<Long, List<Termin>> pretraziIzlete(
            IzletSearchRequest request) {

        var sada = LocalDateTime.now();
        var od = request.datumOd() == null
                ? sada
                : request.datumOd().atStartOfDay();

        if (od.isBefore(sada)) {
            od = sada;
        }

        var doIskljucivo = request.datumDo() == null
                ? sada.plusYears(100)
                : request.datumDo().plusDays(1).atStartOfDay();

        if (!od.isBefore(doIskljucivo)) {
            return Collections.emptyMap();
        }

        var lokacija = request.lokacija();
        var kategorija = request.kategorija();
        var termini = terminRepository.pretrazi(
                lokacija == null || lokacija.isBlank() ? null : lokacija.trim(),
                kategorija == null || kategorija.isBlank() ? null : kategorija.trim(),
                od,
                doIskljucivo,
                request.trajanjeOd() == null ? 0 : request.trajanjeOd(),
                request.trajanjeDo() == null
                        ? Integer.MAX_VALUE : request.trajanjeDo(),
                request.cijenaOd() == null ? 0.0 : request.cijenaOd(),
                request.cijenaDo() == null
                        ? Double.MAX_VALUE : request.cijenaDo(),
                request.brojOsoba() == null ? 1 : request.brojOsoba(),
                Status.OTKAZANO
        );

        return termini.stream()
                .collect(Collectors.groupingBy(
                        termin -> termin.getIzlet().getId(),
                        LinkedHashMap::new,
                        Collectors.toList()
                ));
    }
}
