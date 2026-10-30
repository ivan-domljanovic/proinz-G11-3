package hr.fer.progi.backend.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtrakcijaCreateRequest(@NotNull @DecimalMin("-90") @DecimalMax("90")
                                     Double latitude,
                                     @NotNull @DecimalMin("-180") @DecimalMax("180")
                                     Double longitude,
                                     @NotBlank String naziv, @NotBlank String opis) {
}
