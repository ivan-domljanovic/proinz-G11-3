package hr.fer.progi.backend.enums;

import lombok.Getter;

@Getter
public enum Uloga {
    KORISNIK("Korisnik"),
    VODIC("Vodič"),
    ORGANIZATOR("Organizator"),
    ADMINISTRATOR("Administrator");

    private final String uloga;

    Uloga(String uloga) {
        this.uloga = uloga;
    }

}