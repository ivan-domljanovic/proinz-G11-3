package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.request.IzletCreateRequest;
import hr.fer.progi.backend.dto.request.IzletUpdateRequest;
import hr.fer.progi.backend.dto.response.IzletDetailResponse;
import hr.fer.progi.backend.dto.response.TerminResponse;
import hr.fer.progi.backend.exception.ResourceNotFoundException;
import hr.fer.progi.backend.mapper.IzletMapper;
import hr.fer.progi.backend.mapper.TerminMapper;
import hr.fer.progi.backend.model.Izlet;
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
    public IzletDetailResponse dohvatiIzlet(Long id) {
        var izlet = izletRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Izlet s tim id ne postoji"));
        return izletMapper.toResponse(izlet);
    }

    @Override
    public List<IzletDetailResponse> dohvatiSveIzlete() {
        return izletRepository.findAll().stream().map(izletMapper::toResponse).toList();
    }

    @Override
    @Transactional
    public IzletDetailResponse kreirajIzlet(IzletCreateRequest request) {
        var atrakcije = atrakcijaRepository.findAllById(List.of(request.atrakcije()));
        var dodatneUsluge = dodatnaUslugaRepository.findAllById(List.of(request.dodatneUsluge()));

        var izlet = new Izlet(request.naziv(), request.opis(), request.kategorijaAktivnosti(),
                request.lokacija(), atrakcije,
                dodatneUsluge);

        var izletDb = izletRepository.save(izlet);

        return izletMapper.toResponse(izletDb);
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
    public IzletDetailResponse azurirajIzlet(
            Long id, IzletUpdateRequest request) {

        var izlet = izletRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Izlet s tim id ne postoji"));

        izlet.setNaziv(request.naziv());
        izlet.setOpis(request.opis());
        izlet.setKategorijaAktivnosti(request.kategorijaAktivnosti());
        izlet.setLokacija(request.lokacija());

        if (request.atrakcije() != null) {
            var atrakcije = atrakcijaRepository.findAllById(List.of(request.atrakcije()));
            if (atrakcije.size() != request.atrakcije().length)
                throw new ResourceNotFoundException("Jedna ili vise atrakcija ne postoji");
            izlet.setAtrakcije(atrakcije);
        }
        if (request.dodatneUsluge() != null) {
            var usluge = dodatnaUslugaRepository.findAllById(List.of(request.dodatneUsluge()));
            if (usluge.size() != request.dodatneUsluge().length)
                throw new ResourceNotFoundException("Jedna ili vise dodatna usluga ne postoji");
            izlet.setDodatneUsluge(usluge);
        }

        return izletMapper.toResponse(izlet);
    }

    @Override
    public List<TerminResponse> dohvatiTermineIzleta(Long id) {
        if (!izletRepository.existsById(id))
            throw new ResourceNotFoundException("Izlet s tim id ne postoji");

        return terminRepository.findAllByIzlet_Id(id).stream().map(
                terminMapper::toResponse).toList();
    }
}
