package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.IzletDetailResponse;
import hr.fer.progi.backend.model.Izlet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IzletMapper {

    public IzletDetailResponse toResponse(Izlet izlet) {
        return new IzletDetailResponse(izlet.getId(), izlet.getNaziv(), izlet.getOpis(),
                izlet.getKategorijaAktivnosti(), izlet.getTrajanjeMinuta(), izlet.getLokacija(),
                izlet.getMjestoPolaska(), izlet.getCijena(), izlet.getKapacitet(),
                izlet.getPotrebanBrojVodica(), List.of(), List.of(), List.of());
        //izlet.getTermini(), izlet.getAtrakcije(),
        //izlet.getDodatneUsluge());
    }
}
