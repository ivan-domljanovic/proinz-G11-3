package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.request.RVodicaCreateRequest;
import hr.fer.progi.backend.dto.request.RVodicaUpdateRequest;
import hr.fer.progi.backend.enums.Status;
import hr.fer.progi.backend.enums.Uloga;
import hr.fer.progi.backend.exception.ForbiddenOperationException;
import hr.fer.progi.backend.exception.RecenzijaAlreadyExistsException;
import hr.fer.progi.backend.exception.ResourceNotFoundException;
import hr.fer.progi.backend.model.RecenzijaVodica;
import hr.fer.progi.backend.repository.KorisnikRepository;
import hr.fer.progi.backend.repository.RecenzijaVodicaRepository;
import hr.fer.progi.backend.repository.RezervacijaRepository;
import hr.fer.progi.backend.service.RecenzijaVodicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecenzijaVodicaServiceImpl implements RecenzijaVodicaService {

    private final RecenzijaVodicaRepository recenzijaVodicaRepository;
    private final RezervacijaRepository rezervacijaRepository;
    private final KorisnikRepository korisnikRepository;

    @Override
    public RecenzijaVodica dohvatiRecenziju(Long id) {
        return recenzijaVodicaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recenzija s tim id ne postoji"));
    }

    @Override
    public List<RecenzijaVodica> dohvatiSveRecenzijeVodica(Long vodicId) {
        return recenzijaVodicaRepository.findAllByVodic_Id(vodicId);
    }

    @Override
    @Transactional
    public RecenzijaVodica azurirajRecenzijuVodica(Long id, Long autorId,
                                                   RVodicaUpdateRequest request) {
        var recenzija = recenzijaVodicaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recenzija s tim id ne postoji"));

        if (!recenzija.getRezervacija().getKorisnik().getId().equals(autorId)) {
            throw new ForbiddenOperationException(
                    "Nije moguce urediti recenziju za drugog korisnika");
        }

        recenzija.setDatumZadnjeIzmjene(LocalDateTime.now());
        recenzija.setKomentar(request.komentar());
        recenzija.setOcjena(request.ocjena());

        return recenzijaVodicaRepository.save(recenzija);
    }

    @Override
    @Transactional
    public RecenzijaVodica dodajRecenzijuVodica(Long rezervacijaId, Long autorId,
                                                RVodicaCreateRequest request) {
        var rezervacija = rezervacijaRepository.findById(rezervacijaId).orElseThrow(
                () -> new ResourceNotFoundException("Rezervacija s tim id ne postoji"));

        if (rezervacija.getStatus() != Status.REALIZIRANO) {
            throw new IllegalArgumentException("Taj termin izleta se nije jos odrzao");
        }

        if (!rezervacija.getKorisnik().getId().equals(autorId)) {
            throw new ForbiddenOperationException(
                    "Nije moguce ostaviti recenziju za drugog korisnika");
        }

        if (recenzijaVodicaRepository.existsByRezervacija_IdAndVodic_Id(rezervacijaId,
                request.vodicId())) {
            throw new RecenzijaAlreadyExistsException(
                    "Recenzija vodica za ovu rezervaciju vec postoji");
        }

        var vodic = korisnikRepository.findById(request.vodicId()).orElseThrow(
                () -> new ResourceNotFoundException("Vodic s tim id ne postoji"));

        if (!vodic.getUloge().contains(Uloga.VODIC)) {
            throw new IllegalArgumentException("Taj korisnik nije vodic.");
        }

        var vodioTermin = rezervacija.getTermin().getVodici().stream()
                .anyMatch(v -> v.getId().equals(request.vodicId()));

        if (!vodioTermin) {
            throw new IllegalArgumentException("Vodic nije vodio taj termin");
        }

        var recenzija = new RecenzijaVodica();

        recenzija.setRezervacija(rezervacija);
        recenzija.setVodic(vodic);

        var sad = LocalDateTime.now();
        recenzija.setDatumRecenzije(sad);
        recenzija.setDatumZadnjeIzmjene(sad);

        recenzija.setKomentar(request.komentar());
        recenzija.setOcjena(request.ocjena());

        return recenzijaVodicaRepository.save(recenzija);
    }

    @Override
    @Transactional
    public void obrisiRecenzijuVodica(Long id, Long autorId) {
        var recenzija = recenzijaVodicaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recenzija s tim id ne postoji"));

        if (!recenzija.getRezervacija().getKorisnik().getId().equals(autorId)) {
            throw new ForbiddenOperationException(
                    "Nije moguce obrisati recenziju za drugog korisnika");
        }

        recenzijaVodicaRepository.deleteById(id);
    }
}
