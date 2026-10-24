package hr.fer.progi.backend.dto;

public record IzletCreateRequest(String naziv, String opis,
                                 String kategorijaAktivnosti,
                                 Integer trajanjeMinuta,
                                 String lokacija,
                                 String mjestoPolaska,
                                 Double cijena,
                                 Integer kapacitet,
                                 Integer potrebanBrojVodica,
                                 Long[] atrakcije,
                                 Long[] dodatneUsluge) {
}
