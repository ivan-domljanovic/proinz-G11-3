package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.request.AtrakcijaCreateRequest;
import hr.fer.progi.backend.dto.request.AtrakcijaUpdateRequest;
import hr.fer.progi.backend.dto.response.AtrakcijaResponse;

import java.util.List;

public interface AtrakcijaService {
    AtrakcijaResponse dohvatiAtrakciju(Long id);

    List<AtrakcijaResponse> dohvatiSveAtrakcije();

    AtrakcijaResponse kreirajAtrakciju(AtrakcijaCreateRequest request);

    AtrakcijaResponse azurirajAtrakciju(Long id, AtrakcijaUpdateRequest request);

    void obrisiAtrakciju(Long id);
}
