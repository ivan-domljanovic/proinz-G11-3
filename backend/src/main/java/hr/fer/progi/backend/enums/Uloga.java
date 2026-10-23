package hr.fer.progi.backend.enums;

public enum Uloga {
    KORISNIK("Korisnik"),
    VODIC("Vodič"),
    ORGANIZATOR("Organizator"),
    ADMINISTRATOR("Administrator");

    private final String uloga;

    Uloga(String uloga) {
        this.uloga = uloga;
    }

    public String getUloga() {
        return uloga;
    }
}
