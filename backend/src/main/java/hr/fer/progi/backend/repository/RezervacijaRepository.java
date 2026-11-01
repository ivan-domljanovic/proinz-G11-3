package hr.fer.progi.backend.repository;

import hr.fer.progi.backend.model.Korisnik;
import hr.fer.progi.backend.model.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {

    @Modifying
    @Query("UPDATE Rezervacija r SET r.status = 'OTKAZANO' WHERE r = :rezervacija")
    void otkazi(Rezervacija rezervacija);

    List<Rezervacija> findByKorisnik(Korisnik korisnik);

    @Modifying
    @Query("UPDATE Rezervacija r SET r.status = 'POTVRDENO' WHERE r = :rezervacija")
    void potvrdi(Rezervacija rezervacija);

    @Query("SELECT r FROM Rezervacija r WHERE r.status = 'ZAHTJEV' ORDER BY r.vrijemeRezervacije ASC")
    List<Rezervacija> dohvatiZahtjeveSortiranoPoVremenuRezervacije();
}
