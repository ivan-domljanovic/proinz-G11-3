package hr.fer.progi.backend.dto.response;

import java.time.LocalDateTime;

public record RecenzijaVodicaResponse(
        Long id, String komentar, Integer ocjena, LocalDateTime datumRecenzije,
        LocalDateTime datumZadnjeIzmjene, Long vodicId, AutorRecenzijeResponse autor
) {
}
