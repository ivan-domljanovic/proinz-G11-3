package hr.fer.progi.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record IzletCreateRequest(@NotNull String naziv, @NotNull String opis,
                                 @NotNull String kategorijaAktivnosti,
                                 @NotNull String lokacija,
                                 List<@NotNull @Positive Long> atrakcije,
                                 List<@NotNull @Positive Long> dodatneUsluge) {
    public IzletCreateRequest {
        atrakcije = atrakcije == null ? List.of() : atrakcije;
        dodatneUsluge = dodatneUsluge == null
                ? List.of()
                : dodatneUsluge;
    }
}
