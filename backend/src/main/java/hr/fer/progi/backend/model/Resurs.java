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
}
