package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.request.RezervacijaRequest;
import hr.fer.progi.backend.enums.Status;
import hr.fer.progi.backend.model.DodatnaUsluga;
import hr.fer.progi.backend.model.Rezervacija;
import hr.fer.progi.backend.repository.RezervacijaRepository;
import hr.fer.progi.backend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RezervacijaServiceImpl implements RezervacijaService {
    private final DodatnaUslugaService dodatnaUslugaService;
    private final KorisnikService korisnikService;
    private final TerminService terminService;
    private final RezervacijaRepository rezervacijaRepository;
    private final MailService mailService;

    @Override
    public Rezervacija rezerviraj(RezervacijaRequest request, Long korisnikId) {
        Rezervacija rezervacija;
        var korisnik = korisnikService.getKorisnik(korisnikId);
        var termin = terminService.getTermin(request.terminId());
        var dodatneUsluge = new ArrayList<DodatnaUsluga>();
        for (Long dodatnaUslugaId : request.dodatneUslugeId()) {
            dodatneUsluge.add(dodatnaUslugaService.getDodatnaUsluga(dodatnaUslugaId));
        }
        if (request.brojMjesta() > terminService.dohvatiBrojSlobodnihMjesta(termin)) {
            rezervacija = new Rezervacija(Status.ZAHTJEV, request.brojMjesta(), LocalDateTime.now(),
                    dodatneUsluge, korisnik, termin);
            return rezervacijaRepository.save(rezervacija);
        }
        rezervacija = new Rezervacija(Status.POTVRDENO, request.brojMjesta(), LocalDateTime.now(),
                dodatneUsluge, korisnik, termin);
        rezervacija = rezervacijaRepository.save(rezervacija);
        mailService.posaljiPotvrduRezervacije(rezervacija);
        return rezervacija;
    }

    @Override
    @Transactional
    public Rezervacija azurirajRezervaciju(Long id, RezervacijaRequest request, Long korisnikId) {
        var trenutnaRezervacija = getRezervacija(id);
        var korisnik = korisnikService.getKorisnik(korisnikId);
        var termin = terminService.getTermin(request.terminId());
        if (!trenutnaRezervacija.getKorisnik().equals(korisnik)) {
            throw new IllegalArgumentException("Rezervacija ne pripada korisniku");
        }
        if (!trenutnaRezervacija.getTermin().equals(termin)) {
            throw new IllegalArgumentException("Ne možete mijenjati termin rezervacije");
        }
        if (trenutnaRezervacija.getStatus().equals(
                Status.OTKAZANO) || trenutnaRezervacija.getStatus().equals(Status.REALIZIRANO)) {
            throw new IllegalStateException(
                    "Ne možete mijenjati rezervaciju koja je otkazana ili realizirana");
        }
        var noviBrojPotrebnihMjesta = trenutnaRezervacija.getStatus().equals(
                Status.POTVRDENO) ? request.brojMjesta() - trenutnaRezervacija.getBrojMjesta() : request.brojMjesta();
        var dodatneUsluge = new ArrayList<DodatnaUsluga>();
        for (Long dodatnaUslugaId : request.dodatneUslugeId()) {
            dodatneUsluge.add(dodatnaUslugaService.getDodatnaUsluga(dodatnaUslugaId));
        }
        trenutnaRezervacija.setBrojMjesta(request.brojMjesta());
        trenutnaRezervacija.setDodatneUsluge(dodatneUsluge);
        var brojSlobodnihMjesta = terminService.dohvatiBrojSlobodnihMjesta(termin);
        if (noviBrojPotrebnihMjesta > brojSlobodnihMjesta) {
            trenutnaRezervacija.setStatus(Status.ZAHTJEV);
            return rezervacijaRepository.save(trenutnaRezervacija);
        }
        trenutnaRezervacija.setStatus(Status.POTVRDENO);
        trenutnaRezervacija = rezervacijaRepository.save(trenutnaRezervacija);
        potvrdiZahtjeve(brojSlobodnihMjesta - noviBrojPotrebnihMjesta);
        mailService.posaljiPotvrduRezervacije(trenutnaRezervacija);
        return trenutnaRezervacija;
    }

    @Override
    @Transactional
    public void otkazi(Long id, Long korisnikId) {
        var rezervacija = getRezervacija(id);
        var korisnik = korisnikService.getKorisnik(korisnikId);
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
        potvrdiZahtjeve(rezervacija.getBrojMjesta());
        mailService.posaljiOtkazivanjeRezervacije(rezervacija);
    }

    @Override
    @Transactional
    public void potvrdiZahtjeve(Integer brojMjesta) {
        List<Rezervacija> rezervacije = rezervacijaRepository.dohvatiZahtjeveSortiranoPoVremenuRezervacije();
        List<Rezervacija> potvrdeneRezervacije = new ArrayList<>();
        for (Rezervacija rezervacija : rezervacije) {
            if (rezervacija.getBrojMjesta() <= brojMjesta) {
                rezervacijaRepository.potvrdi(rezervacija);
                potvrdeneRezervacije.add(rezervacija);
                brojMjesta -= rezervacija.getBrojMjesta();
            }
            if (brojMjesta <= 0) {
                break;
            }
        }
        mailService.posaljiPotvrdeRezervacija(potvrdeneRezervacije);
    }

    @Override
    public Rezervacija getRezervacija(Long id) {
        return rezervacijaRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Rezervacija ne postoji"));
    }

    @Override
    public Rezervacija dohvatiRezervaciju(Long id, Long korisnikId) {
        var rezervacija = getRezervacija(id);
        var korisnik = korisnikService.getKorisnik(korisnikId);
        if (!rezervacija.getKorisnik().equals(korisnik)) {
            throw new IllegalArgumentException("Rezervacija ne pripada korisniku");
        }
        return rezervacija;
    }

    @Override
    public List<Rezervacija> dohvatiSveKorisnikoveRezervacije(Long korisnikId) {
        var korisnik = korisnikService.getKorisnik(korisnikId);
        return rezervacijaRepository.findByKorisnik(korisnik);
    }
}
