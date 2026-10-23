package hr.fer.progi.backend.repository;

import hr.fer.progi.backend.model.DodatnaUsluga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DodatnaUslugaRepository extends JpaRepository<DodatnaUsluga, Long> {
}
