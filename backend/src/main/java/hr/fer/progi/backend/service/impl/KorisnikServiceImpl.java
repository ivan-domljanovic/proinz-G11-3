package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.request.UlogaUpdateRequest;
import hr.fer.progi.backend.enums.Uloga;
import hr.fer.progi.backend.exception.ResourceNotFoundException;
import hr.fer.progi.backend.model.Korisnik;
import hr.fer.progi.backend.repository.KorisnikRepository;
import hr.fer.progi.backend.service.KorisnikService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class KorisnikServiceImpl implements KorisnikService {
    private final KorisnikRepository korisnikRepository;

    @Override
    public List<Korisnik> dohvatiSveKorisnike() {
        return korisnikRepository.findAll();
    }

    @Override
    public Korisnik getKorisnik(Long id) {
        return korisnikRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Korisnik ne postoji"));
    }

    @Override
    public Set<Korisnik> dohvatiSveKorisnikePoId(Collection<Long> ids) {
        return korisnikRepository.findAllByIdIn(ids);
    }

    @Override
    @Transactional
    public Korisnik azurirajUlogeKorisniku(Long id, UlogaUpdateRequest request) {
        var korisnik = korisnikRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Korisnik ne postoji"));

        Set<Uloga> uloge = new HashSet<>(request.uloge());
        uloge.add(Uloga.KORISNIK);
        korisnik.setUloge(uloge);

        return korisnikRepository.save(korisnik);
    }
}
