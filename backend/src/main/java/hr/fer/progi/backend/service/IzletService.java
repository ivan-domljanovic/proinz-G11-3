package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.request.IzletCreateRequest;
import hr.fer.progi.backend.dto.response.IzletDetailResponse;
import hr.fer.progi.backend.dto.response.TerminResponse;

import java.util.List;

public interface IzletService {

    IzletDetailResponse dohvatiIzlet(Long id);

    List<IzletDetailResponse> dohvatiSveIzlete();

    IzletDetailResponse kreirajIzlet(IzletCreateRequest request);

    void obrisiIzlet(Long id);

    IzletDetailResponse azurirajIzlet(Long id);

    List<TerminResponse> dohvatiTermineIzleta(Long id);

    TerminResponse dodajTerminIzletu(Long id);
}
