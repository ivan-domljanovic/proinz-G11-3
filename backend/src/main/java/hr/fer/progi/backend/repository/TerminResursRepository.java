package hr.fer.progi.backend.repository;

import hr.fer.progi.backend.model.TerminResurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TerminResursRepository extends JpaRepository<TerminResurs, Long> {

    List<TerminResurs> findAllByTermin_Id(Long terminId);

    boolean existsByTermin_Id(Long terminId);

    Optional<TerminResurs> findByTermin_IdAndResurs_Id(Long terminId, Long resursId);
}
