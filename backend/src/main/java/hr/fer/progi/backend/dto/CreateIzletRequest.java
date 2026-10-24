package hr.fer.progi.backend.dto;

import java.time.Duration;

public record CreateIzletRequest(String naziv, String opis, double cijena, int kapacitet, int potrebanBrojVodica,
                                 Integer trajanje, String kategorijaAktivnosti, String lokacija,
                                 String mjestoPolaska) {
}
