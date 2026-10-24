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

    private LocalDateTime vrijemeRezervacije;

    @ManyToMany
    private List<DodatnaUsluga> dodatneUsluge;

    @ManyToOne
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;

    @ManyToOne
    @JoinColumn(name = "termin_id", nullable = false)
    private Termin termin;

    public Rezervacija() {
    }

    public Rezervacija(Status status, Integer brojMjesta, LocalDateTime vrijemeRezervacije,
                       List<DodatnaUsluga> dodatneUsluge, Korisnik korisnik, Termin termin) {
        this.status = status;
        this.brojMjesta = brojMjesta;
        this.vrijemeRezervacije = vrijemeRezervacije;
        this.dodatneUsluge = dodatneUsluge;
        this.korisnik = korisnik;
        this.termin = termin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getBrojMjesta() {
        return brojMjesta;
    }

    public void setBrojMjesta(Integer brojMjesta) {
        this.brojMjesta = brojMjesta;
    }

    public LocalDateTime getVrijemeRezervacije() {
        return vrijemeRezervacije;
    }

    public void setVrijemeRezervacije(LocalDateTime vrijemeRezervacije) {
        this.vrijemeRezervacije = vrijemeRezervacije;
    }

    public List<DodatnaUsluga> getDodatneUsluge() {
        return dodatneUsluge;
    }

    public void setDodatneUsluge(List<DodatnaUsluga> dodatneUsluge) {
        this.dodatneUsluge = dodatneUsluge;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }
}
