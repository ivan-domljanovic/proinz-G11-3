package hr.fer.progi.backend.service;

import hr.fer.progi.backend.model.DodatnaUsluga;
import hr.fer.progi.backend.repository.DodatnaUslugaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DodatneUslugeServiceImpl implements DodatneUslugeService {
    private final DodatnaUslugaRepository dodatnaUslugaRepository;

    @Override
    public DodatnaUsluga dohvatiDodatnuUslugu(Long id) {
        return dodatnaUslugaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Dodatna usluga ne postoji"));
    }
}
