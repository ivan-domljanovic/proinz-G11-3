package hr.fer.progi.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record TerminResursSetRequest(@NotNull @PositiveOrZero Long resursId,
                                     @NotNull @Positive Integer kolicina) {
}
