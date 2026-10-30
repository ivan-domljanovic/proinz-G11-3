package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.request.IzletCreateRequest;
import hr.fer.progi.backend.dto.request.IzletUpdateRequest;
import hr.fer.progi.backend.exception.ResourceNotFoundException;
import hr.fer.progi.backend.mapper.IzletMapper;
import hr.fer.progi.backend.mapper.TerminMapper;
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

import java.util.List;

@Service
@RequiredArgsConstructor
public class IzletServiceImpl implements IzletService {

    private final IzletRepository izletRepository;
    private final IzletMapper izletMapper;
    private final AtrakcijaRepository atrakcijaRepository;
    private final DodatnaUslugaRepository dodatnaUslugaRepository;
    private final TerminRepository terminRepository;
    private final TerminMapper terminMapper;

    @Override
    public Izlet dohvatiIzlet(Long id) {
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

        var izlet = izletRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Izlet s tim id ne postoji"));

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
}
