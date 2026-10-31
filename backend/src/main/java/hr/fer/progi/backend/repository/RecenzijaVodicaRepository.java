package hr.fer.progi.backend.repository;

import hr.fer.progi.backend.model.RecenzijaVodica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecenzijaVodicaRepository extends JpaRepository<RecenzijaVodica, Long> {
    List<RecenzijaVodica> findAllByVodic_Id(Long vodicId);

    boolean existsByRezervacija_IdAndVodic_Id(Long rezervacijaId, Long vodicId);
}
