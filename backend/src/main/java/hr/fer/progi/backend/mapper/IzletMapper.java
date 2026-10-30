package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.response.IzletDetailResponse;
import hr.fer.progi.backend.model.Izlet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TerminMapper.class, AtrakcijaMapper.class, DodatnaUslugaMapper.class})
public interface IzletMapper {
    IzletDetailResponse toResponse(Izlet izlet);
}