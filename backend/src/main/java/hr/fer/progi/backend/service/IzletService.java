package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.request.IzletCreateRequest;
import hr.fer.progi.backend.dto.request.IzletUpdateRequest;
import hr.fer.progi.backend.model.Izlet;
import hr.fer.progi.backend.model.Termin;

import java.util.List;

public interface IzletService {

    Izlet dohvatiIzlet(Long id);

    List<Izlet> dohvatiSveIzlete();

    Izlet kreirajIzlet(IzletCreateRequest request);

    void obrisiIzlet(Long id);

    Izlet azurirajIzlet(Long id, IzletUpdateRequest request);

    List<Termin> dohvatiTermineIzleta(Long id);
}
