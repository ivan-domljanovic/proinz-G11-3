package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.request.AtrakcijaCreateRequest;
import hr.fer.progi.backend.dto.request.AtrakcijaUpdateRequest;
import hr.fer.progi.backend.model.Atrakcija;

import java.util.List;

public interface AtrakcijaService {
    Atrakcija dohvatiAtrakciju(Long id);

    List<Atrakcija> dohvatiSveAtrakcije();

    Atrakcija kreirajAtrakciju(AtrakcijaCreateRequest request);

    Atrakcija azurirajAtrakciju(Long id, AtrakcijaUpdateRequest request);

    void obrisiAtrakciju(Long id);
}
