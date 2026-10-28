package hr.fer.progi.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TerminResursUpdateRequest(@NotNull @Positive Integer kolicina) {
}
