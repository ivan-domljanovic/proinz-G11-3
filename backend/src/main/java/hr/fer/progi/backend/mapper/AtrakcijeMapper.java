package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.AtrakcijaResponse;
import hr.fer.progi.backend.model.Atrakcija;
import org.springframework.stereotype.Component;

@Component
public class AtrakcijeMapper {

    public static AtrakcijaResponse toResponse(Atrakcija atrakcija) {
        return new AtrakcijaResponse(atrakcija.getId(), atrakcija.getNaziv(), atrakcija.getOpis(),
                atrakcija.getLatitude(), atrakcija.getLongitude());
    }
}
