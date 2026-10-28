package hr.fer.progi.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;
import java.util.List;

public record TerminUpdateRequest(
        @NotNull @Positive Double cijena,
        @NotNull @Positive Integer kapacitet,
        @NotNull @PositiveOrZero Integer potrebanBrojVodica,
        @NotNull @PositiveOrZero Integer trajanjeMinuta,
        @NotNull LocalDateTime datumVrijeme,
        @NotNull String mjestoPolaska,
        List<@NotNull @PositiveOrZero Long> vodici) {
}
