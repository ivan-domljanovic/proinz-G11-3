package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.KorisnikResponse;
import hr.fer.progi.backend.model.Korisnik;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KorisnikMapper {
    KorisnikResponse toResponse(Korisnik korisnik);
}
