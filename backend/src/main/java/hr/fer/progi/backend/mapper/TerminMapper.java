package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.TerminResponse;
import hr.fer.progi.backend.model.Termin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TerminMapper {
    TerminResponse toResponse(Termin termin);
}
