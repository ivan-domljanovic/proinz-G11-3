package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.request.RIzletaCreateRequest;
import hr.fer.progi.backend.dto.request.RIzletaUpdateRequest;
import hr.fer.progi.backend.enums.Status;
import hr.fer.progi.backend.exception.ForbiddenOperationException;
import hr.fer.progi.backend.exception.RecenzijaAlreadyExistsException;
import hr.fer.progi.backend.exception.ResourceNotFoundException;
import hr.fer.progi.backend.model.RecenzijaIzleta;
import hr.fer.progi.backend.repository.RecenzijaIzletaRepository;
import hr.fer.progi.backend.repository.RezervacijaRepository;
import hr.fer.progi.backend.service.RecenzijaIzletaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecenzijaIzletaServiceImpl implements RecenzijaIzletaService {

    private final RecenzijaIzletaRepository recenzijaIzletaRepository;
    private final RezervacijaRepository rezervacijaRepository;

    @Override
    public RecenzijaIzleta dohvatiRecenziju(Long id) {
        return recenzijaIzletaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recenzija s tim id ne postoji"));
    }

    @Override
    public List<RecenzijaIzleta> dohvatiSveRecenzijeIzleta(Long izletId) {
        return recenzijaIzletaRepository.findByRezervacija_Termin_Izlet_Id(izletId);
    }

    @Override
    @Transactional
    public RecenzijaIzleta azurirajRecenzijuIzleta(Long id, Long autorId,
                                                   RIzletaUpdateRequest request) {
        var recenzija = recenzijaIzletaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recenzija s tim id ne postoji"));


        if (!recenzija.getRezervacija().getKorisnik().getId().equals(autorId)) {
            throw new ForbiddenOperationException(
                    "Nije moguce urediti recenziju za drugog korisnika");
        }

        recenzija.setDatumZadnjeIzmjene(LocalDateTime.now());
        recenzija.setOcjena(request.ocjena());
        recenzija.setKomentar(request.komentar());

        return recenzijaIzletaRepository.save(recenzija);
    }

    @Override
    @Transactional
    public RecenzijaIzleta dodajRecenzijuIzleta(Long rezervacijaId, Long autorId,
                                                RIzletaCreateRequest request) {
        var rezervacija = rezervacijaRepository.findById(rezervacijaId).orElseThrow(
                () -> new ResourceNotFoundException("Rezervacija s tim id ne postoji"));

        if (rezervacija.getStatus() != Status.REALIZIRANO) {
            throw new IllegalArgumentException("Taj termin izleta se nije jos odrzao");
        }

        if (!rezervacija.getKorisnik().getId().equals(autorId)) {
            throw new ForbiddenOperationException(
                    "Nije moguce ostaviti recenziju za drugog korisnika");
        }

        if (recenzijaIzletaRepository.existsByRezervacija_Id(rezervacijaId)) {
            throw new RecenzijaAlreadyExistsException(
                    "Recenzija izleta za ovu rezervaciju vec postoji");
        }

        var recenzija = new RecenzijaIzleta();

        recenzija.setRezervacija(rezervacija);

        var sad = LocalDateTime.now();
        recenzija.setDatumZadnjeIzmjene(sad);
        recenzija.setDatumRecenzije(sad);

        recenzija.setKomentar(request.komentar());
        recenzija.setOcjena(request.ocjena());

        return recenzijaIzletaRepository.save(recenzija);
    }

    @Override
    @Transactional
    public void obrisiRecenzijuIzleta(Long id, Long autorId) {
        var recenzija = recenzijaIzletaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recenzija s tim id ne postoji"));

        if (!recenzija.getRezervacija().getKorisnik().getId().equals(autorId)) {
            throw new ForbiddenOperationException(
                    "Nije moguce obrisati recenziju za drugog korisnika");
        }

        recenzijaIzletaRepository.deleteById(id);
    }
}
