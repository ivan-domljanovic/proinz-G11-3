package hr.fer.progi.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "izleti")
public class Izlet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    private String opis;
    private String kategorijaAktivnosti;

    private Integer trajanjeMinuta;

    private String lokacija;
    private String mjestoPolaska;
    private Double cijena;
    private Integer kapacitet;
    private Integer potrebanBrojVodica;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Atrakcija> atrakcije;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<DodatnaUsluga> dodatneUsluge;

    @OneToMany(mappedBy = "izlet")
    private List<Termin> termini;

    public Izlet() {
    }

    public Izlet(String naziv, String opis, String kategorijaAktivnosti, Integer trajanjeMinuta, String lokacija, String mjestoPolaska, Double cijena, Integer kapacitet, Integer potrebanBrojVodica, List<Atrakcija> atrakcije, List<DodatnaUsluga> dodatneUsluge) {
        this.naziv = naziv;
        this.opis = opis;
        this.kategorijaAktivnosti = kategorijaAktivnosti;
        this.trajanjeMinuta = trajanjeMinuta;
        this.lokacija = lokacija;
        this.mjestoPolaska = mjestoPolaska;
        this.cijena = cijena;
        this.kapacitet = kapacitet;
        this.potrebanBrojVodica = potrebanBrojVodica;
        this.atrakcije = atrakcije;
        this.dodatneUsluge = dodatneUsluge;
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

    public String getKategorijaAktivnosti() {
        return kategorijaAktivnosti;
    }

    public void setKategorijaAktivnosti(String kategorijaAktivnosti) {
        this.kategorijaAktivnosti = kategorijaAktivnosti;
    }

    public Integer getTrajanjeMinuta() {
        return trajanjeMinuta;
    }

    public void setTrajanjeMinuta(Integer trajanjeMinuta) {
        this.trajanjeMinuta = trajanjeMinuta;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public String getMjestoPolaska() {
        return mjestoPolaska;
    }

    public void setMjestoPolaska(String mjestoPolaska) {
        this.mjestoPolaska = mjestoPolaska;
    }

    public Double getCijena() {
        return cijena;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }

    public Integer getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(Integer kapacitet) {
        this.kapacitet = kapacitet;
    }

    public Integer getPotrebanBrojVodica() {
        return potrebanBrojVodica;
    }

    public void setPotrebanBrojVodica(Integer potrebanBrojVodica) {
        this.potrebanBrojVodica = potrebanBrojVodica;
    }

    public List<Atrakcija> getAtrakcije() {
        return atrakcije;
    }

    public void setAtrakcije(List<Atrakcija> atrakcije) {
        this.atrakcije = atrakcije;
    }

    public List<DodatnaUsluga> getDodatneUsluge() {
        return dodatneUsluge;
    }

    public void setDodatneUsluge(List<DodatnaUsluga> dodatneUsluge) {
        this.dodatneUsluge = dodatneUsluge;
    }

    public List<Termin> getTermini() {
        return termini;
    }

    public void setTermini(List<Termin> termini) {
        this.termini = termini;
    }
}
