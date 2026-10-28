package hr.fer.progi.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ResursCreateRequest(@NotNull String naziv, @NotNull @PositiveOrZero Integer kolicina) {
}
