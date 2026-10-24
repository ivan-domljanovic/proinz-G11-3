package hr.fer.progi.backend.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record RezervacijaResponse(Long id, String status, Integer brojMjesta, LocalDateTime vrijemeRezervacije,
                                  List<DodatnaUslugaResponse> dodatneUsluge, KorisnikResponse korisnik,
                                  TerminRezervacijaResponse termin) {
}
