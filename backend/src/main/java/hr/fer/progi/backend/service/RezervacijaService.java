package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.request.RezervacijaRequest;
import hr.fer.progi.backend.model.Rezervacija;

import java.util.List;

public interface RezervacijaService {
    Rezervacija rezerviraj(RezervacijaRequest request, String sub);

    void otkazi(Long id, String sub);

    Rezervacija dohvatiRezervaciju(Long id, String sub);

    List<Rezervacija> dohvatiSveKorisnikoveRezervacije(String sub);
}
