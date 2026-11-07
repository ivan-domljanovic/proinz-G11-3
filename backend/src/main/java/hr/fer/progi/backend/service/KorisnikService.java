package hr.fer.progi.backend.service;

import hr.fer.progi.backend.model.Korisnik;

import java.util.Collection;
import java.util.Set;

public interface KorisnikService {
    Korisnik getKorisnik(Long id);

    Set<Korisnik> dohvatiSveKorisnikePoId(Collection<Long> ids);
}
