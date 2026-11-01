package hr.fer.progi.backend.service;

import hr.fer.progi.backend.model.Rezervacija;

import java.util.Collection;

public interface MailService {
    void posaljiPotvrduRezervacije(Rezervacija rezervacija);

    void posaljiPotvrdeRezervacija(Collection<Rezervacija> rezervacije);

    void posaljiOtkazivanjeRezervacije(Rezervacija rezervacija);
}
