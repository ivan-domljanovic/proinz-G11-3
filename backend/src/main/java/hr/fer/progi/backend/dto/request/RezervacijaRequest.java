package hr.fer.progi.backend.dto.request;

import java.util.List;

public record RezervacijaRequest(Long korisnikId, Long terminId, Integer brojMjesta, List<Long> dodatneUslugeId) {
}
