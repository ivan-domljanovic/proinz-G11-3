package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.model.Rezervacija;
import hr.fer.progi.backend.service.MailService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MailServiceImpl implements MailService {
    @Override
    public void posaljiPotvrduRezervacije(Rezervacija rezervacija) {
        // todo: implementirati slanje maila
    }

    @Override
    public void posaljiPotvrdeRezervacija(Collection<Rezervacija> rezervacije) {
        // todo: implementirati slanje maila za više rezervacija
    }

    @Override
    public void posaljiOtkazivanjeRezervacije(Rezervacija rezervacija) {
        // todo: implementirati slanje maila za otkazivanje rezervacije
    }
}
