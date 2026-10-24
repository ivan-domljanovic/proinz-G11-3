package hr.fer.progi.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "termin_resurs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public TerminResurs(Termin termin, Resurs resurs, Integer kolicina) {
        this.termin = termin;
        this.resurs = resurs;
        this.kolicina = kolicina;
    }
}
