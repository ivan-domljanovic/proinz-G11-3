package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.IzletDetailResponse;

import java.util.List;

public interface IzletService {

    IzletDetailResponse dohvatiIzlet(Long id);

    List<IzletDetailResponse> dohvatiSveIzlete();

    default void kreirajIzlet() {
        throw new RuntimeException("Potrebno implementirat");
    }

    default void azurirajIzlet(Long id) {
        throw new RuntimeException("Potrebno implementirat");
    }

    default void obrisiIzlet(Long id) {
        throw new RuntimeException("Potrebno implementirat");
    }

    default void dohvatiTermineIzleta(Long id) {
        throw new RuntimeException("Potrebno implementirat");
    }

    default void dohvatiAtrakcijeIzleta(Long id) {
        throw new RuntimeException("Potrebno implementirat");
    }

    default void dohvatiUslugeIzleta(Long id) {
        throw new RuntimeException("Potrebno implementirat");
    }

    default void postaviTermineIzleta(Long id) {
        throw new RuntimeException("Potrebno implementirat");
    }

    default void postaviAtrakcijeIzleta(Long id) {
        throw new RuntimeException("Potrebno implementirat");
    }

    default void postaviDodatneUslugeIzleta(Long id) {
        throw new RuntimeException("Potrebno implementirat");
    }
}
