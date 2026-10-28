package hr.fer.progi.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record TerminSetVodiciRequest(@NotNull List<@NotNull @PositiveOrZero Long> vodici) {
}
