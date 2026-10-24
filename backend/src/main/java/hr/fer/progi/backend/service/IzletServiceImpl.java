package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.IzletDetailResponse;
import hr.fer.progi.backend.exception.ResourceNotFoundException;
import hr.fer.progi.backend.mapper.IzletMapper;
import hr.fer.progi.backend.repository.IzletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        var izletdto = IzletMapper.toResponse(izlet);
        return izletdto;
    }

    public List<IzletDetailResponse> dohvatiSveIzlete() {
        var izleti = izletRepository.findAll().stream().map((IzletMapper::toResponse)).toList();
        return izleti;
    }
}
