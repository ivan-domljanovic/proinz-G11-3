package hr.fer.progi.backend.model;


import hr.fer.progi.backend.enums.Uloga;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "korisnici")
public class Korisnik {
    @Id
    private Long id;

    private String ime;
    private String email;
    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Uloga.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "korisnik_uloga")
    private Set<Uloga> uloge;

    @OneToMany(mappedBy = "korisnik")
    private Set<Rezervacija> rezervacije;

    public Korisnik() {
    }

    public Korisnik(String ime, String email, String avatarUrl, Set<Uloga> uloge) {
        this.ime = ime;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.uloge = uloge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Set<Uloga> getUloge() {
        return uloge;
    }

    public void setUloge(Set<Uloga> uloge) {
        this.uloge = uloge;
    }

    public Set<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void setRezervacije(Set<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }
}
