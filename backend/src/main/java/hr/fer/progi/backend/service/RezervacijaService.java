package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.request.RezervacijaRequest;
import hr.fer.progi.backend.model.Rezervacija;

import java.util.List;

public interface RezervacijaService {
    Rezervacija rezerviraj(RezervacijaRequest request, Long korisnikId);

    Rezervacija azurirajRezervaciju(Long id, RezervacijaRequest request, Long korisnikId);

    void otkazi(Long id, Long korisnikId);

    void potvrdiZahtjeve(Long terminId, Integer brojMjesta);

    Rezervacija getRezervacija(Long id);

    Rezervacija dohvatiRezervaciju(Long id, Long korisnikId);

    List<Rezervacija> dohvatiSveKorisnikoveRezervacije(Long korisnikId);
}
