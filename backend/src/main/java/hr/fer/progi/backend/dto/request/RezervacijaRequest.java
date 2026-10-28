package hr.fer.progi.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record RezervacijaRequest(@NotNull @PositiveOrZero Long terminId, @NotNull @PositiveOrZero Integer brojMjesta,
                                 List<@NotNull @PositiveOrZero Long> dodatneUslugeId) {
}
