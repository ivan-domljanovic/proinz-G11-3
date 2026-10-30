package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.request.RezervacijaRequest;
import hr.fer.progi.backend.enums.Status;
import hr.fer.progi.backend.model.DodatnaUsluga;
import hr.fer.progi.backend.model.Rezervacija;
import hr.fer.progi.backend.repository.RezervacijaRepository;
import hr.fer.progi.backend.service.DodatneUslugeService;
import hr.fer.progi.backend.service.KorisnikService;
import hr.fer.progi.backend.service.RezervacijaService;
import hr.fer.progi.backend.service.TerminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RezervacijaServiceImpl implements RezervacijaService {
    private final DodatneUslugeService dodatneUslugeService;
    private final KorisnikService korisnikService;
    private final TerminService terminService;
    private final RezervacijaRepository rezervacijaRepository;

    @Override
    public Rezervacija rezerviraj(RezervacijaRequest request, String sub) {
        var termin = terminService.dohvatiTermin(request.terminId());
        if (request.brojMjesta() > terminService.dohvatiBrojSlobodnihMjesta(termin)) {
            throw new IllegalStateException("Nema dovoljno slobodnih mjesta za odabrani termin");
        }
        var dodatneUsluge = new ArrayList<DodatnaUsluga>();
        for (Long dodatnaUslugaId : request.dodatneUslugeId()) {
            dodatneUsluge.add(dodatneUslugeService.dohvatiDodatnuUslugu(dodatnaUslugaId));
        }
        var korisnik = korisnikService.dohvatiKorisnika(sub);
        var rezervacija = new Rezervacija(Status.ZAHTJEV, request.brojMjesta(), LocalDateTime.now(), dodatneUsluge, korisnik, termin);
        return rezervacijaRepository.save(rezervacija);
    }

    @Override
    public void otkazi(Long id, String sub) {
        var rezervacija = rezervacijaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Rezervacija ne postoji"));
        var korisnik = korisnikService.dohvatiKorisnika(sub);
        if (!rezervacija.getKorisnik().equals(korisnik)) {
            throw new IllegalArgumentException("Rezervacija ne pripada korisniku");
        }
        if (rezervacija.getStatus().equals(Status.OTKAZANO)) {
            throw new IllegalStateException("Rezervacija je već otkazana");
        }
        if (rezervacija.getStatus().equals(Status.REALIZIRANO)) {
            throw new IllegalStateException("Rezervacija je već realizirana");
        }
        rezervacijaRepository.otkazi(rezervacija);
    }

    @Override
    public Rezervacija dohvatiRezervaciju(Long id, String sub) {
        var rezervacija = rezervacijaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Rezervacija ne postoji"));
        var korisnik = korisnikService.dohvatiKorisnika(sub);
        if (!rezervacija.getKorisnik().equals(korisnik)) {
            throw new IllegalArgumentException("Rezervacija ne pripada korisniku");
        }
        return rezervacija;
    }

    @Override
    public List<Rezervacija> dohvatiSveKorisnikoveRezervacije(String sub) {
        var korisnik = korisnikService.dohvatiKorisnika(sub);
        return rezervacijaRepository.findByKorisnik(korisnik);
    }
}
