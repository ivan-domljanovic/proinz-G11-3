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

    public TerminResurs() {
    }

    public TerminResurs(Termin termin, Resurs resurs, Integer kolicina) {
        this.termin = termin;
        this.resurs = resurs;
        this.kolicina = kolicina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }

    public Resurs getResurs() {
        return resurs;
    }

    public void setResurs(Resurs resurs) {
        this.resurs = resurs;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }
}
