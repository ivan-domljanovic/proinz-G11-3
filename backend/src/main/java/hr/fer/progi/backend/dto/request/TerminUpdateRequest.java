package hr.fer.progi.backend.dto.request;

import java.time.LocalDateTime;

public record TerminUpdateRequest(Double cijena, Integer kapacitet,
                                  Integer potrebanBrojVodica, Integer trajanjeMinuta,
                                  LocalDateTime datumVrijeme, String mjestoPolaska, Long[] vodici) {
}
