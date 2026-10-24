package hr.fer.progi.backend.dto.response;

import java.time.LocalDateTime;

public record TerminResponse(Long id, LocalDateTime datumVrijeme, String mjestoPolaska, Double cijena,
                             Integer brojSlobodnihMjesta) {
}
