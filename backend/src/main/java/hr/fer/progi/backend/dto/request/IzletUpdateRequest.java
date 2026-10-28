package hr.fer.progi.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record IzletUpdateRequest(@NotNull String naziv, @NotNull String opis,
                                 @NotNull String kategorijaAktivnosti,
                                 @NotNull String lokacija,
                                 List<@NotNull @Positive Long> atrakcije,
                                 List<@NotNull @Positive Long> dodatneUsluge) {
}

