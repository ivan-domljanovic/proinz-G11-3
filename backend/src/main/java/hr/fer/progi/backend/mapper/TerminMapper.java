package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.response.TerminResponse;
import hr.fer.progi.backend.dto.response.TerminRezervacijaResponse;
import hr.fer.progi.backend.mapper.helper.TerminMapperHelper;
import hr.fer.progi.backend.model.Termin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TerminMapperHelper.class})
public interface TerminMapper {

    @Mapping(target = "brojSlobodnihMjesta", source = "termin", qualifiedByName = "brojSlobodnihMjesta")
    TerminResponse toResponse(Termin termin);

    TerminRezervacijaResponse toRezervacijaResponse(Termin termin);
}

