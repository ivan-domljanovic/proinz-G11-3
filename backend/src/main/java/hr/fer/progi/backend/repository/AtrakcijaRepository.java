package hr.fer.progi.backend.repository;

import hr.fer.progi.backend.model.Atrakcija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtrakcijaRepository extends JpaRepository<Atrakcija, Long> {
}
