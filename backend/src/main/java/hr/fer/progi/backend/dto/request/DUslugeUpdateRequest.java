package hr.fer.progi.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record DUslugeUpdateRequest(@NotBlank String naziv, @NotBlank String opis,
                                   @NotNull @PositiveOrZero Double cijena) {
}
