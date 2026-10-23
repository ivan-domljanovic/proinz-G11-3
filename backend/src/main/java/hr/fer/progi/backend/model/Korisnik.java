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
}
