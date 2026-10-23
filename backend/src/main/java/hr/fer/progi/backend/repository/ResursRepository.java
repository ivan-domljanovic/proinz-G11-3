package hr.fer.progi.backend.repository;

import hr.fer.progi.backend.model.Resurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResursRepository extends JpaRepository<Resurs, Long> {
}
