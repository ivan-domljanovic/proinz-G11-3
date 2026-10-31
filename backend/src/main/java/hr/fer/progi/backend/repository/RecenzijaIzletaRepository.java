package hr.fer.progi.backend.repository;

import hr.fer.progi.backend.model.RecenzijaIzleta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecenzijaIzletaRepository extends JpaRepository<RecenzijaIzleta, Long> {
    List<RecenzijaIzleta> findByRezervacija_Termin_Izlet_Id(Long rezervacijaTerminIzletId);

    boolean existsByRezervacija_Id(Long rezervacijaId);
}
