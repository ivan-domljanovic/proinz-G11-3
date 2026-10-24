package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.DodatnaUslugaResponse;
import hr.fer.progi.backend.model.DodatnaUsluga;
import org.springframework.stereotype.Component;

@Component
public class DodatneUslugeMapper {

    public static DodatnaUslugaResponse toResponse(DodatnaUsluga dodatnaUsluga) {
        return new DodatnaUslugaResponse(dodatnaUsluga.getId(), dodatnaUsluga.getNaziv(),
                dodatnaUsluga.getOpis(), dodatnaUsluga.getCijena());
    }
}
