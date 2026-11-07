package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.request.IzletCreateRequest;
import hr.fer.progi.backend.dto.request.IzletSearchRequest;
import hr.fer.progi.backend.dto.request.IzletUpdateRequest;
import hr.fer.progi.backend.model.Izlet;
import hr.fer.progi.backend.model.Termin;

import java.util.List;
import java.util.Map;

public interface IzletService {

    Izlet getIzlet(Long id);

    List<Izlet> dohvatiSveIzlete();

    Izlet kreirajIzlet(IzletCreateRequest request);

    void obrisiIzlet(Long id);

    Izlet azurirajIzlet(Long id, IzletUpdateRequest request);

    List<Termin> dohvatiTermineIzleta(Long id);

    Map<Long, List<Termin>> pretraziIzlete(IzletSearchRequest request);
}
