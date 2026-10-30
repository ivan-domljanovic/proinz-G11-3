package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.response.IzletDetailResponse;
import hr.fer.progi.backend.model.Izlet;
import hr.fer.progi.backend.model.Termin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TerminMapper.class, AtrakcijaMapper.class, DodatnaUslugaMapper.class})
public interface IzletMapper {
    IzletDetailResponse toResponse(Izlet izlet);

    @Mapping(target = "termini", source = "odabraniTermini")
    IzletDetailResponse toSearchResponse(
            Izlet izlet,
            List<Termin> odabraniTermini);
}