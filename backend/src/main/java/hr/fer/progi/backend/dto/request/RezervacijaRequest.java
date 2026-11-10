package hr.fer.progi.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record RezervacijaRequest(@NotNull @PositiveOrZero Long terminId,
                                 @NotNull @Positive Integer brojMjesta,
                                 List<@NotNull @PositiveOrZero Long> dodatneUslugeId) {
    public RezervacijaRequest {
        if (dodatneUslugeId == null) {
            dodatneUslugeId = List.of();
        }
    }
}
