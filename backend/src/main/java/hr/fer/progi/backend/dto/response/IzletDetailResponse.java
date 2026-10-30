package hr.fer.progi.backend.dto.response;

import java.util.List;

public record IzletDetailResponse(
        Long id,
        String naziv,
        String opis,
        String kategorijaAktivnosti,
        String lokacija,
        List<TerminResponse> termini,
        List<AtrakcijaResponse> atrakcije,
        List<DodatnaUslugaResponse> dodatneUsluge
) {
}
