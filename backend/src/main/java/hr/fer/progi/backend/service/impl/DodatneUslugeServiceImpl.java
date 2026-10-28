package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.model.DodatnaUsluga;
import hr.fer.progi.backend.repository.DodatnaUslugaRepository;
import hr.fer.progi.backend.service.DodatneUslugeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DodatneUslugeServiceImpl implements DodatneUslugeService {
    private final DodatnaUslugaRepository dodatnaUslugaRepository;

    @Override
    public DodatnaUsluga dohvatiDodatnuUsluguEntitet(Long id) {
        return dodatnaUslugaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Dodatna usluga ne postoji"));
    }
}
