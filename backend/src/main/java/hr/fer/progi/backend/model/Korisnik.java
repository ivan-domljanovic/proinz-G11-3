package hr.fer.progi.backend.model;


import hr.fer.progi.backend.enums.Uloga;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "korisnici")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public Korisnik(String ime, String email, String avatarUrl, Set<Uloga> uloge) {
        this.ime = ime;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.uloge = uloge;
    }
}
