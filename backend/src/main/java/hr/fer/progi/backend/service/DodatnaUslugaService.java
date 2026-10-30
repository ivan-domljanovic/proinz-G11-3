package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.request.DUslugeCreateRequest;
import hr.fer.progi.backend.dto.request.DUslugeUpdateRequest;
import hr.fer.progi.backend.model.DodatnaUsluga;

import java.util.List;

public interface DodatnaUslugaService {

    List<DodatnaUsluga> dohvatiSveDodatneUsluge();

    DodatnaUsluga dohvatiDodatnuUslugu(Long id);

    DodatnaUsluga kreirajDodatnuUslugu(DUslugeCreateRequest request);

    DodatnaUsluga azurirajDodatnuUslugu(Long id, DUslugeUpdateRequest request);

    void obrisiDodatnuUslugu(Long id);
}
