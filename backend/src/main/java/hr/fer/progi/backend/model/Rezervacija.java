package hr.fer.progi.backend.model;


import hr.fer.progi.backend.enums.Status;
import jakarta.persistence.*;

@Entity
@Table(name = "rezervacije")
public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Status status;
    private Integer brojMjesta;

    @ManyToOne
    private Korisnik korisnik;
}
