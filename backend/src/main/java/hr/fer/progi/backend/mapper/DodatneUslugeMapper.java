package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.response.DodatnaUslugaResponse;
import hr.fer.progi.backend.model.DodatnaUsluga;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DodatneUslugeMapper {
    DodatnaUslugaResponse toResponse(DodatnaUsluga dodatnaUsluga);
}
