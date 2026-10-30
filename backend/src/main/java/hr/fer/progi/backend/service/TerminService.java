package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.request.*;
import hr.fer.progi.backend.model.Termin;
import hr.fer.progi.backend.model.TerminResurs;

import java.util.List;

public interface TerminService {

    Integer dohvatiBrojSlobodnihMjesta(Termin termin);

    Termin dohvatiTermin(Long id);

    List<Termin> dohvatiSveTermine();

    Termin kreirajTermin(TerminCreateRequest request);

    Termin azurirajTermin(Long id, TerminUpdateRequest request);

    void obrisiTermin(Long id);

    Termin postaviVodice(Long id, TerminSetVodiciRequest request);


    List<TerminResurs> dohvatiSveResurseTermina(Long id);

    void makniResursTerminu(Long terminId, Long resursId);

    TerminResurs dodijeliResursTerminu(Long id, TerminResursSetRequest request);

    TerminResurs azurirajKolicinuResursaTerminu(Long terminId, Long resursId, TerminResursUpdateRequest request);

}
