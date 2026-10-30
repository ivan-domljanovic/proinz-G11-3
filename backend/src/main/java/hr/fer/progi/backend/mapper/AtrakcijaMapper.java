package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.response.AtrakcijaResponse;
import hr.fer.progi.backend.model.Atrakcija;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AtrakcijaMapper {
    AtrakcijaResponse toResponse(Atrakcija atrakcija);
}
