package hr.fer.progi.backend.enums;

public enum Status {
    POTVRDENO("Potvrđeno"),
    REALIZIRANO("Realizirano"),
    OTKAZANO("Otkazano");

    private final String opis;

    Status(String opis) {
        this.opis = opis;
    }

    public String getOpis() {
        return opis;
    }
}
