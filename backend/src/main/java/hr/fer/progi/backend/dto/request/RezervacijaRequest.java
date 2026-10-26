package hr.fer.progi.backend.dto.request;

import java.util.List;

public record RezervacijaRequest(Long terminId, Integer brojMjesta, List<Long> dodatneUslugeId) {
}
