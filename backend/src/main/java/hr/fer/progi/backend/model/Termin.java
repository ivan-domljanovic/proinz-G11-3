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

    public Termin() {
    }

    public Termin(LocalDateTime datumVrijeme, Izlet izlet, Set<Korisnik> vodici) {
        this.datumVrijeme = datumVrijeme;
        this.izlet = izlet;
        this.vodici = vodici;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatumVrijeme() {
        return datumVrijeme;
    }

    public void setDatumVrijeme(LocalDateTime datumVrijeme) {
        this.datumVrijeme = datumVrijeme;
    }

    public Izlet getIzlet() {
        return izlet;
    }

    public void setIzlet(Izlet izlet) {
        this.izlet = izlet;
    }

    public Set<Korisnik> getVodici() {
        return vodici;
    }

    public void setVodici(Set<Korisnik> vodici) {
        this.vodici = vodici;
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void setRezervacije(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }

    public List<TerminResurs> getResursi() {
        return resursi;
    }

    public void setResursi(List<TerminResurs> resursi) {
        this.resursi = resursi;
    }
}
