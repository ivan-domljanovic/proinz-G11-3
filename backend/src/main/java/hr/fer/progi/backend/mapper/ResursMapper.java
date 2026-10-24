package hr.fer.progi.backend.mapper;


import hr.fer.progi.backend.dto.response.ResursDetailsResponse;
import hr.fer.progi.backend.model.Resurs;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" )
public interface ResursMapper {

    ResursDetailsResponse toResponse(Resurs resurs);
}
