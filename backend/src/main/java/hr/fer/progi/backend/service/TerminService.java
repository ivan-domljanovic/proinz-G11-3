package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.request.*;
import hr.fer.progi.backend.dto.response.TerminResponse;
import hr.fer.progi.backend.dto.response.TerminResursResponse;
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


    List<TerminResursResponse> dohvatiSveResurseTermina(Long id);

    void makniResursTerminu(Long terminId, Long resursId);

    TerminResursResponse dodijeliResursTerminu(Long id, TerminResursSetRequest request);

    TerminResursResponse azurirajKolicinuResursaTerminu(Long terminId, Long resursId, TerminResursUpdateRequest request);

}
