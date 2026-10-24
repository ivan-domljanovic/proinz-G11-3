package hr.fer.progi.backend.enums;

import lombok.Getter;

@Getter
public enum Status {
    ZAHTJEV("Zahtjev"),
    POTVRDENO("Potvrđeno"),
    REALIZIRANO("Realizirano"),
    OTKAZANO("Otkazano");

    private final String opis;

    Status(String opis) {
        this.opis = opis;
    }

}
