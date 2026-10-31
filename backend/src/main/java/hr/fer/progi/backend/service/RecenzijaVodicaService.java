package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.request.RVodicaCreateRequest;
import hr.fer.progi.backend.dto.request.RVodicaUpdateRequest;
import hr.fer.progi.backend.model.RecenzijaVodica;

import java.util.List;

public interface RecenzijaVodicaService {

    RecenzijaVodica dohvatiRecenziju(Long id);

    List<RecenzijaVodica> dohvatiSveRecenzijeVodica(Long vodicId);

    RecenzijaVodica azurirajRecenzijuVodica(Long id, Long autorId, RVodicaUpdateRequest request);

    RecenzijaVodica dodajRecenzijuVodica(Long rezervacijaId, Long autorId,
                                         RVodicaCreateRequest request);

    void obrisiRecenzijuVodica(Long id, Long autorId);
}
