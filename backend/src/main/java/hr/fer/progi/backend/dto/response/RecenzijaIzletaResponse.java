package hr.fer.progi.backend.dto.response;

import java.time.LocalDateTime;

public record RecenzijaIzletaResponse(
        Long id, String komentar, Integer ocjena, LocalDateTime datumRecenzije,
        LocalDateTime datumZadnjeIzmjene, AutorRecenzijeResponse autor
) {
}
