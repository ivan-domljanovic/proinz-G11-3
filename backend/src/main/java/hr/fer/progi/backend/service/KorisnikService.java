package hr.fer.progi.backend.service;

import hr.fer.progi.backend.model.Korisnik;

public interface KorisnikService {
    Korisnik dohvatiKorisnika(Long id);

    Korisnik dohvatiKorisnika(String sub);
}
