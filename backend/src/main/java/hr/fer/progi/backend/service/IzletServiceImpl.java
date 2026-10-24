package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.IzletDetailResponse;
import hr.fer.progi.backend.exception.ResourceNotFoundException;

import hr.fer.progi.backend.mapper.IzletMapper;
import hr.fer.progi.backend.repository.IzletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IzletServiceImpl implements IzletService {

    private final IzletRepository izletRepository;
    private final IzletMapper izletMapper;

    public IzletDetailResponse dohvatiIzlet(Long id) {
        var izlet = izletRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Izlet s tim id ne postoji"));
        return izletMapper.toResponse(izlet);
    }

    public List<IzletDetailResponse> dohvatiSveIzlete() {
        return izletRepository.findAll().stream().map(izletMapper::toResponse).toList();
    }
}
