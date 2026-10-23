package hr.fer.progi.backend.repository;

import hr.fer.progi.backend.model.Izlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IzletRepository extends JpaRepository<Izlet, Long> {
}
