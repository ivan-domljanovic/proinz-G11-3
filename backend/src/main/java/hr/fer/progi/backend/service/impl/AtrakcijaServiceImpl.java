package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.request.AtrakcijaCreateRequest;
import hr.fer.progi.backend.dto.request.AtrakcijaUpdateRequest;
import hr.fer.progi.backend.exception.ResourceNotFoundException;
import hr.fer.progi.backend.model.Atrakcija;
import hr.fer.progi.backend.repository.AtrakcijaRepository;
import hr.fer.progi.backend.service.AtrakcijaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtrakcijaServiceImpl implements AtrakcijaService {

    private final AtrakcijaRepository atrakcijaRepository;

    @Override
    public Atrakcija dohvatiAtrakciju(Long id) {
        return atrakcijaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Atrakcija s tim id ne postoji"));
    }

    @Override
    public List<Atrakcija> dohvatiSveAtrakcije() {
        return atrakcijaRepository.findAll();
    }

    @Override
    @Transactional
    public Atrakcija kreirajAtrakciju(AtrakcijaCreateRequest request) {
        var atrakcija = new Atrakcija(request.naziv(), request.opis(), request.latitude(),
                request.longitude());
        return atrakcijaRepository.save(atrakcija);
    }

    @Override
    @Transactional
    public Atrakcija azurirajAtrakciju(Long id, AtrakcijaUpdateRequest request) {
        var atrakcija = atrakcijaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Atrakcija s tim id ne postoji"));

        atrakcija.setLatitude(request.latitude());
        atrakcija.setLongitude(request.longitude());
        atrakcija.setNaziv(request.naziv());
        atrakcija.setOpis(request.opis());

        return atrakcijaRepository.save(atrakcija);
    }

    @Override
    @Transactional
    public void obrisiAtrakciju(Long id) {
        var atrakcija = atrakcijaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Ne postoji atrakcija s tim id"));
        atrakcijaRepository.delete(atrakcija);
    }
}
