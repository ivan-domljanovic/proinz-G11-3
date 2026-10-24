package hr.fer.progi.backend.dto;

import java.util.List;

public record IzletDetailResponse(
        Long id,
        String naziv,
        String opis,
        String kategorijaAktivnosti,
        Integer trajanjeMinuta,
        String lokacija,
        String mjestoPolaska,
        Double cijena,
        Integer kapacitet,
        Integer potrebanBrojVodica,

        List<TerminResponse> termini,
        List<AtrakcijaResponse> atrakcije,
        List<DodatnaUslugaResponse> dodatneUsluge
) {
}
