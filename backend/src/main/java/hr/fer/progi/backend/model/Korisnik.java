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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ime;

    private String prezime;

    private String email;

    @Column(nullable = false, unique = true)
    private String sub;

    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Uloga.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "korisnik_uloga")
    private Set<Uloga> uloge;

    @OneToMany(mappedBy = "korisnik")
    private Set<Rezervacija> rezervacije;

}
