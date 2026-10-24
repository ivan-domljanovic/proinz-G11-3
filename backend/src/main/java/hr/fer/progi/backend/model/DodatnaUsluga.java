package hr.fer.progi.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dodatne_usluge")
public class DodatnaUsluga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    private String opis;

    private Double cijena;

    public DodatnaUsluga() {
    }

    public DodatnaUsluga(String naziv, String opis, Double cijena) {
        this.naziv = naziv;
        this.opis = opis;
        this.cijena = cijena;
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Double getCijena() {
        return cijena;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }
}