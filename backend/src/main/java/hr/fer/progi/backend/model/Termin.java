package hr.fer.progi.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "termini")
public class Termin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime datumVrijeme;

    @ManyToOne
    @JoinColumn(name = "izlet_id", nullable = false)
    private Izlet izlet;

    @ManyToMany
    @JoinTable(
            name = "termin_vodic",
            joinColumns = @JoinColumn(name = "termin_id"),
            inverseJoinColumns = @JoinColumn(name = "vodic_id")
    )
    private Set<Korisnik> vodici;

    @OneToMany(mappedBy = "termin")
    private List<Rezervacija> rezervacije = new ArrayList<>();

    @OneToMany(mappedBy = "termin")
    private List<TerminResurs> resursi;
}
