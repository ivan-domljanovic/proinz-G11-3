package hr.fer.progi.backend.dto.request;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record IzletSearchRequest(
        String lokacija,
        String kategorija,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate datumOd,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate datumDo,

        @PositiveOrZero Integer trajanjeOd,
        @PositiveOrZero Integer trajanjeDo,

        @PositiveOrZero Double cijenaOd,
        @PositiveOrZero Double cijenaDo,

        @Positive Integer brojOsoba
) {}