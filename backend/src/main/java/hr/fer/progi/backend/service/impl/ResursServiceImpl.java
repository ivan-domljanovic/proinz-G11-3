package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.request.ResursCreateRequest;
import hr.fer.progi.backend.dto.response.ResursDetailsResponse;
import hr.fer.progi.backend.dto.request.ResursUpdateRequest;
import hr.fer.progi.backend.exception.ResourceNotFoundException;
import hr.fer.progi.backend.mapper.ResursMapper;
import hr.fer.progi.backend.model.Resurs;
import hr.fer.progi.backend.repository.ResursRepository;
import hr.fer.progi.backend.service.ResursService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResursServiceImpl implements ResursService {

    private final ResursRepository resursRepository;
    private final ResursMapper resursMapper;

    @Override
    public List<ResursDetailsResponse> dohvatiSveResurse() {
        // todo: pagination logika
        var data = resursRepository.findAll().stream().map(resursMapper::toResponse).toList();
        return data;
    }

    @Override
    public ResursDetailsResponse dohvatiResurs(Long id) {
        var data = resursRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Ne postoji resurs s tim id"));
        return resursMapper.toResponse(data);
    }

    @Transactional
    @Override
    public ResursDetailsResponse kreirajResurs(ResursCreateRequest request) {
//        todo: validacija podataka
        var resurs = new Resurs(request.naziv(), request.kolicina());
        var resursDb = resursRepository.save(resurs);
        return resursMapper.toResponse(resursDb);
    }

    @Transactional
    @Override
    public ResursDetailsResponse azurirajResurs(Long id, ResursUpdateRequest request) {
//        todo: validacija podataka
        var resurs = resursRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Ne postoji resurs s tim id"));

        resurs.setNaziv(request.naziv());
        resurs.setKolicina(request.kolicina());

        var resursDb = resursRepository.save(resurs);
        return resursMapper.toResponse(resursDb);
    }

    @Transactional
    @Override
    public void obrisiResurs(Long id) {
        var resurs = resursRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Ne postoji resurs s tim id"));
        resursRepository.delete(resurs);
    }
}
