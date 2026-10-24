package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.model.Termin;
import hr.fer.progi.backend.repository.TerminRepository;
import hr.fer.progi.backend.service.TerminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TerminServiceImpl implements TerminService {
    private final TerminRepository terminRepository;

    @Override
    public Termin dohvatiTermin(Long id) {
        return terminRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Termin ne postoji"));
    }

    @Override
    public Integer dohvatiBrojSlobodnihMjesta(Termin termin) {
        return terminRepository.getNumberOfAvailableSpots(termin);
    }
}
