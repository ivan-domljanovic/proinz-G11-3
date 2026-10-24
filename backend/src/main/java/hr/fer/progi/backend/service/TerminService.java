package hr.fer.progi.backend.service;

import hr.fer.progi.backend.model.Termin;

public interface TerminService {
    Termin dohvatiTermin(Long id);

    Integer dohvatiBrojSlobodnihMjesta(Termin termin);
}
