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
}