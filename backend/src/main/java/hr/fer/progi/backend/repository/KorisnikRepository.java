package hr.fer.progi.backend.repository;

import hr.fer.progi.backend.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
    Optional<Korisnik> findBySub(String sub);


    Set<Korisnik> findAllByIdIn(Collection<Long> ids);
}
