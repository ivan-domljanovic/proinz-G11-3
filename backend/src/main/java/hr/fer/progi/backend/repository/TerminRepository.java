package hr.fer.progi.backend.repository;

import hr.fer.progi.backend.model.Termin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminRepository extends JpaRepository<Termin, Long> {
    @Query("SELECT t.kapacitet - (SELECT SUM(r.brojMjesta) FROM Rezervacija r WHERE r.termin = :termin AND r.status != 'Otkazano') FROM Termin t WHERE t = :termin")
    Integer getNumberOfAvailableSpots(Termin termin);
}
