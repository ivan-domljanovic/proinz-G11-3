package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.IzletDetailResponse;
import hr.fer.progi.backend.model.Izlet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TerminMapper.class, AtrakcijeMapper.class, DodatneUslugeMapper.class})
public interface IzletMapper {
    IzletDetailResponse toResponse(Izlet izlet);
}