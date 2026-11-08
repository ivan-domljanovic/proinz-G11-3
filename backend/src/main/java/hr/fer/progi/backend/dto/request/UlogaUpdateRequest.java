package hr.fer.progi.backend.dto.request;

import hr.fer.progi.backend.enums.Uloga;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UlogaUpdateRequest(@NotNull Set<@NotNull Uloga> uloge) {
}
