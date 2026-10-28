package hr.fer.progi.backend.dto.request;

public record IzletUpdateRequest(String naziv, String opis,
                                 String kategorijaAktivnosti,
                                 String lokacija,
                                 Long[] atrakcije,
                                 Long[] dodatneUsluge) {
}
