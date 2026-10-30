package hr.fer.progi.backend.repository;

import hr.fer.progi.backend.enums.Status;
import hr.fer.progi.backend.model.Termin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TerminRepository extends JpaRepository<Termin, Long> {
    @Query("SELECT t.kapacitet - (SELECT COALESCE(SUM(r.brojMjesta), 0) FROM Rezervacija r WHERE r.termin = :termin AND r.status != 'OTKAZANO') FROM Termin t WHERE t = :termin")
    Integer getNumberOfAvailableSpots(Termin termin);

    List<Termin> findAllByIzlet_Id(Long izletId);

    @Query("""
            SELECT t FROM Termin t
            JOIN FETCH t.izlet i
            WHERE (CAST(:lokacija AS String) IS NULL
                OR LOWER(i.lokacija) LIKE
                   LOWER(CONCAT('%', CAST(:lokacija AS String), '%')))
              AND (:kategorija IS NULL OR i.kategorijaAktivnosti = :kategorija)
              AND t.datumVrijeme >= :od
              AND t.datumVrijeme < :doIskljucivo
              AND t.trajanjeMinuta >= :trajanjeOd
              AND t.trajanjeMinuta <= :trajanjeDo
              AND t.cijena >= :cijenaOd
              AND t.cijena <= :cijenaDo
              AND t.kapacitet - (
                  SELECT COALESCE(SUM(r.brojMjesta), 0)
                  FROM Rezervacija r
                  WHERE r.termin = t
                    AND r.status <> :otkazano
              ) >= :brojOsoba
            ORDER BY t.datumVrijeme, t.id
            """)
    List<Termin> pretrazi(
            @Param("lokacija") String lokacija,
            @Param("kategorija") String kategorija,
            @Param("od") LocalDateTime od,
            @Param("doIskljucivo") LocalDateTime doIskljucivo,
            @Param("trajanjeOd") Integer trajanjeOd,
            @Param("trajanjeDo") Integer trajanjeDo,
            @Param("cijenaOd") Double cijenaOd,
            @Param("cijenaDo") Double cijenaDo,
            @Param("brojOsoba") Integer brojOsoba,
            @Param("otkazano") Status otkazano);
}

