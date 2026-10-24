package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.IzletDetailResponse;
import hr.fer.progi.backend.exception.ResourceNotFoundException;
import hr.fer.progi.backend.mapper.IzletMapper;
import hr.fer.progi.backend.repository.IzletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IzletServiceImpl implements IzletService {

    private final IzletRepository izletRepository;
    private final IzletMapper izletMapper;

    public IzletServiceImpl(@Autowired IzletRepository izletRepository, IzletMapper izletMapper) {
        this.izletRepository = izletRepository;
        this.izletMapper = izletMapper;
    }

    public IzletDetailResponse dohvatiIzlet(Long id) {
        var izlet = izletRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Izlet s tim id ne postoji"));
        var izletdto = izletMapper.toResponse(izlet);
        return izletdto;
    }
}
