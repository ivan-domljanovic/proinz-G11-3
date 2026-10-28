package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.request.TerminCreateRequest;
import hr.fer.progi.backend.dto.request.TerminSetVodiciRequest;
import hr.fer.progi.backend.dto.request.TerminUpdateRequest;
import hr.fer.progi.backend.dto.response.TerminResponse;
import hr.fer.progi.backend.model.Termin;

import java.util.List;

public interface TerminService {

    Termin dohvatiTerminEntitet(Long id);

    Integer dohvatiBrojSlobodnihMjesta(Termin termin);

    TerminResponse dohvatiTermin(Long id);

    List<TerminResponse> dohvatiSveTermine();

    TerminResponse kreirajTermin(TerminCreateRequest request);

    TerminResponse azurirajTermin(Long id, TerminUpdateRequest request);

    void obrisiTermin(Long id);

    TerminResponse postaviVodice(Long id, TerminSetVodiciRequest request);
}
