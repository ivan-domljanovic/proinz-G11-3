package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.AtrakcijaResponse;
import hr.fer.progi.backend.model.Atrakcija;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AtrakcijeMapper {
    AtrakcijaResponse toResponse(Atrakcija atrakcija);
}
