package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.response.AutorRecenzijeResponse;
import hr.fer.progi.backend.dto.response.KorisnikResponse;
import hr.fer.progi.backend.model.Korisnik;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KorisnikMapper {
    KorisnikResponse toResponse(Korisnik korisnik);

    AutorRecenzijeResponse toAutorRecenzijeResponse(Korisnik korisnik);
}
