package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.request.AtrakcijaCreateRequest;
import hr.fer.progi.backend.dto.request.AtrakcijaUpdateRequest;
import hr.fer.progi.backend.dto.response.AtrakcijaResponse;
import hr.fer.progi.backend.exception.ResourceNotFoundException;
import hr.fer.progi.backend.mapper.AtrakcijaMapper;
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
    private final AtrakcijaMapper atrakcijaMapper;

    @Override
    public AtrakcijaResponse dohvatiAtrakciju(Long id) {
        var data = atrakcijaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Atrakcija s tim id ne postoji"));
        return atrakcijaMapper.toResponse(data);
    }

    @Override
    public List<AtrakcijaResponse> dohvatiSveAtrakcije() {
        return atrakcijaRepository.findAll().stream().map(atrakcijaMapper::toResponse).toList();
    }

    @Override
    @Transactional
    public AtrakcijaResponse kreirajAtrakciju(AtrakcijaCreateRequest request) {
        var atrakcija = new Atrakcija(request.naziv(), request.opis(), request.latitude(),
                request.longitude());
        var atrakcijaDb = atrakcijaRepository.save(atrakcija);
        return atrakcijaMapper.toResponse(atrakcijaDb);
    }

    @Override
    @Transactional
    public AtrakcijaResponse azurirajAtrakciju(Long id, AtrakcijaUpdateRequest request) {
        var atrakcija = atrakcijaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Atrakcija s tim id ne postoji"));

        atrakcija.setLatitude(request.latitude());
        atrakcija.setLongitude(request.longitude());
        atrakcija.setNaziv(request.naziv());
        atrakcija.setOpis(request.opis());

        var atrakcijaDb = atrakcijaRepository.save(atrakcija);

        return atrakcijaMapper.toResponse(atrakcijaDb);
    }

    @Override
    @Transactional
    public void obrisiAtrakciju(Long id) {
        var atrakcija = atrakcijaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Ne postoji atrakcija s tim id"));
        atrakcijaRepository.delete(atrakcija);
    }
}
