package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.RezervacijaRequest;
import hr.fer.progi.backend.model.Rezervacija;

public interface RezervacijaService {
    Rezervacija rezerviraj(RezervacijaRequest request);
}
