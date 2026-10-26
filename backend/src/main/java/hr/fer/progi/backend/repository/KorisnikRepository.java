package hr.fer.progi.backend.repository;

import hr.fer.progi.backend.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
    Optional<Korisnik> findBySub(String sub);
}