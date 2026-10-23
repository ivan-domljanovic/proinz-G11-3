package hr.fer.progi.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "termin_resurs")
public class TerminResurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "termin_id", nullable = false)
    private Termin termin;

    @ManyToOne
    @JoinColumn(name = "resurs_id", nullable = false)
    private Resurs resurs;

    private Integer kolicina;
}
