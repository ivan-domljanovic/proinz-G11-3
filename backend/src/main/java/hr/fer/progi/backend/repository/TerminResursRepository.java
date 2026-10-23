package hr.fer.progi.backend.repository;

import hr.fer.progi.backend.model.TerminResurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminResursRepository extends JpaRepository<TerminResurs, Long> {
}
