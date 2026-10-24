package hr.fer.progi.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "termini")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Termin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime datumVrijeme;

    private Integer kapacitet;

    private String mjestoPolaska;

    private Double cijena;

    private Integer potrebanBrojVodica;

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

    public Termin(LocalDateTime datumVrijeme, Integer kapacitet, Izlet izlet, Set<Korisnik> vodici) {
        this.datumVrijeme = datumVrijeme;
        this.kapacitet = kapacitet;
        this.izlet = izlet;
        this.vodici = vodici;
    }
}
