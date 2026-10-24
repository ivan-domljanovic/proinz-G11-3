package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.TerminResponse;
import hr.fer.progi.backend.model.Termin;
import org.springframework.stereotype.Component;

@Component
public class TerminMapper {

    public static TerminResponse toResponse(Termin termin) {
        return new TerminResponse(termin.getId(),termin.getDatumVrijeme());
    }
}
