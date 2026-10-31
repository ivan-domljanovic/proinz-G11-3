package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.response.RecenzijaVodicaResponse;
import hr.fer.progi.backend.model.RecenzijaVodica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {KorisnikMapper.class})
public interface RecenzijaVodicaMapper {

    @Mapping(target = "vodicId", source = "vodic.id")
    @Mapping(target = "autor", source = "rezervacija.korisnik")
    RecenzijaVodicaResponse toResponse(RecenzijaVodica recenzija);
}
