package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.model.Korisnik;
import hr.fer.progi.backend.repository.KorisnikRepository;
import hr.fer.progi.backend.service.KorisnikService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KorisnikServiceImpl implements KorisnikService {
    private final KorisnikRepository korisnikRepository;

    @Override
    public Korisnik dohvatiKorisnika(Long id) {
        return korisnikRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Korisnik ne postoji"));
    }

    @Override
    public Korisnik dohvatiKorisnika(String sub) {
        return korisnikRepository.findBySub(sub).orElseThrow(() -> new IllegalArgumentException("Korisnik ne postoji"));
    }
}
