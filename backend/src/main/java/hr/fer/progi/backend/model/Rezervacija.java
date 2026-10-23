package hr.fer.progi.backend.model;


import hr.fer.progi.backend.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "rezervacije")
public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer brojMjesta;

    private LocalDateTime datumRezervacije;

    @ManyToMany
    private List<DodatnaUsluga> dodatneUsluge;

    @ManyToOne
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;

    @ManyToOne
    @JoinColumn(name = "termin_id", nullable = false)
    private Termin termin;
}
