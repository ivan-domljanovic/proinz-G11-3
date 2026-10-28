package hr.fer.progi.backend.dto.request;

import java.time.LocalDateTime;

public record TerminCreateRequest(Long izletId, Double cijena, Integer kapacitet,
                                  Integer potrebanBrojVodica, Integer trajanjeMinuta,
                                  LocalDateTime datumVrijeme, String mjestoPolaska, Long[] vodici) {

    public TerminCreateRequest {
        vodici = vodici == null ? new Long[0] : vodici;
    }


}
