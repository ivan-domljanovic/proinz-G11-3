package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.response.TerminResursResponse;
import hr.fer.progi.backend.model.TerminResurs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TerminResursMapper {

    @Mapping(target = "resursId", source = "resurs.id")
    @Mapping(target = "naziv", source = "resurs.naziv")
    @Mapping(target = "kolicinaSkladiste", source = "resurs.kolicina")
    @Mapping(target = "kolicinaTermin", source = "kolicina")
    @Mapping(target = "terminId", source = "termin.id")
    TerminResursResponse toResponse(TerminResurs terminResurs);
}