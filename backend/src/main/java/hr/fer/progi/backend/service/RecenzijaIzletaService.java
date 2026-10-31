package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.request.RIzletaCreateRequest;
import hr.fer.progi.backend.dto.request.RIzletaUpdateRequest;
import hr.fer.progi.backend.model.RecenzijaIzleta;

import java.util.List;

public interface RecenzijaIzletaService {

    RecenzijaIzleta dohvatiRecenziju(Long id);

    List<RecenzijaIzleta> dohvatiSveRecenzijeIzleta(Long izletId);

    RecenzijaIzleta azurirajRecenzijuIzleta(Long id, Long autorId, RIzletaUpdateRequest request);

    RecenzijaIzleta dodajRecenzijuIzleta(Long rezervacijaId, Long autorId,
                                         RIzletaCreateRequest request);

    void obrisiRecenzijuIzleta(Long id, Long autorId);
}
