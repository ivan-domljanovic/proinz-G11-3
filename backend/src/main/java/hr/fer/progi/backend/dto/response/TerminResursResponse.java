package hr.fer.progi.backend.dto.response;

public record TerminResursResponse(Long resursId, Long terminId, String naziv,
                                   Integer kolicinaSkladiste, Integer kolicinaTermin) {
}
