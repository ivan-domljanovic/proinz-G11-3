package hr.fer.progi.backend.mapper;

import hr.fer.progi.backend.dto.IzletDetailResponse;
import hr.fer.progi.backend.model.Izlet;
import org.springframework.stereotype.Component;

@Component
public class IzletMapper {

    public static IzletDetailResponse toResponse(Izlet izlet) {
        return new IzletDetailResponse(izlet.getId(), izlet.getNaziv(), izlet.getOpis(),
                izlet.getKategorijaAktivnosti(), izlet.getTrajanjeMinuta(), izlet.getLokacija(),
                izlet.getMjestoPolaska(), izlet.getCijena(), izlet.getKapacitet(),
                izlet.getPotrebanBrojVodica(),
                izlet.getTermini().stream().map(TerminMapper::toResponse).toList(),
                izlet.getAtrakcije().stream().map(AtrakcijeMapper::toResponse).toList(),
                izlet.getDodatneUsluge().stream().map(DodatneUslugeMapper::toResponse).toList());
    }
}
