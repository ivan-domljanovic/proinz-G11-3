package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.response.RecenzijaIzletaResponse;
import hr.fer.progi.backend.model.RecenzijaIzleta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {KorisnikMapper.class})
public interface RecenzijaIzletaMapper {

    @Mapping(target = "autor", source = "rezervacija.korisnik")
    RecenzijaIzletaResponse toResponse(RecenzijaIzleta recenzija);
}
