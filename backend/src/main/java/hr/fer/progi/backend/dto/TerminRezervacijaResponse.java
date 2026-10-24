package hr.fer.progi.backend.dto;

import java.time.LocalDateTime;

public record TerminRezervacijaResponse(Long id, LocalDateTime datumVrijeme, String mjestoPolaska, Double cijena) {
}
