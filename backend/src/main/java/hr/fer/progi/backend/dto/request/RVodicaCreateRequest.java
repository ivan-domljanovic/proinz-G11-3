package hr.fer.progi.backend.dto.request;

import jakarta.validation.constraints.*;

public record RVodicaCreateRequest(
        @NotNull @PositiveOrZero Long vodicId,
        @NotNull @Min(1) @Max(5) Integer ocjena,
        @NotNull @Size(max = 500) String komentar
) {
}
