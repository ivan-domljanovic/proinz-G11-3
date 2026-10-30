package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.response.DodatnaUslugaResponse;
import hr.fer.progi.backend.model.DodatnaUsluga;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DodatnaUslugaMapper {
    DodatnaUslugaResponse toResponse(DodatnaUsluga dodatnaUsluga);
}
