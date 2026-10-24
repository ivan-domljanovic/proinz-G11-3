package hr.fer.progi.backend.model;


import hr.fer.progi.backend.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "rezervacije")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer brojMjesta;

    private LocalDateTime vrijemeRezervacije;

    @ManyToMany
    private List<DodatnaUsluga> dodatneUsluge;

    @ManyToOne
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;

    @ManyToOne
    @JoinColumn(name = "termin_id", nullable = false)
    private Termin termin;

    public Rezervacija(Status status, Integer brojMjesta, LocalDateTime vrijemeRezervacije,
                       List<DodatnaUsluga> dodatneUsluge, Korisnik korisnik, Termin termin) {
        this.status = status;
        this.brojMjesta = brojMjesta;
        this.vrijemeRezervacije = vrijemeRezervacije;
        this.dodatneUsluge = dodatneUsluge;
        this.korisnik = korisnik;
        this.termin = termin;
    }
}
