package hr.fer.progi.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "resursi")
public class Resurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    private Integer kolicina;

    @OneToMany(mappedBy = "resurs")
    private List<TerminResurs> termini;

    public Resurs() {
    }

    public Resurs(String naziv, Integer kolicina) {
        this.naziv = naziv;
        this.kolicina = kolicina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public List<TerminResurs> getTermini() {
        return termini;
    }

    public void setTermini(List<TerminResurs> termini) {
        this.termini = termini;
    }
}
