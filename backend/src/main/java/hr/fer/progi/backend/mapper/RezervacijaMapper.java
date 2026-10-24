package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.response.RezervacijaResponse;
import hr.fer.progi.backend.model.Rezervacija;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DodatneUslugeMapper.class, KorisnikMapper.class, TerminMapper.class})
public interface RezervacijaMapper {
    RezervacijaResponse toResponse(Rezervacija rezervacija);
}
