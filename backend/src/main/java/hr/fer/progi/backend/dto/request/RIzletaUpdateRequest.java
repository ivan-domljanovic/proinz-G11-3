package hr.fer.progi.backend.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RIzletaUpdateRequest(
        @NotNull @Min(1) @Max(5) Integer ocjena,
        @NotNull @Size(max = 500) String komentar
) {
}
