package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.request.ResursCreateRequest;
import hr.fer.progi.backend.dto.request.ResursUpdateRequest;
import hr.fer.progi.backend.model.Resurs;

import java.util.List;

public interface ResursService {

    List<Resurs> dohvatiSveResurse();

    Resurs dohvatiResurs(Long id);

    Resurs kreirajResurs(ResursCreateRequest request);

    Resurs azurirajResurs(Long id, ResursUpdateRequest request);

    void obrisiResurs(Long id);
}
