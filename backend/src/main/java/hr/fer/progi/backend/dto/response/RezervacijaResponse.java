package hr.fer.progi.backend.dto.response;

import java.util.List;

public record RezervacijaResponse(Long id,
                                  String status,
                                  Integer brojMjesta,
                                  List<DodatnaUslugaResponse> dodatneUsluge,
                                  TerminRezervacijaResponse termin
) {
}