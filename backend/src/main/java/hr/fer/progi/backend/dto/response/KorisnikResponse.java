package hr.fer.progi.backend.dto.response;

import hr.fer.progi.backend.enums.Uloga;

import java.util.List;

public record KorisnikResponse(String sub, String ime, String prezime, String email,
                               String avatarUrl, List<Uloga> uloge) {
}