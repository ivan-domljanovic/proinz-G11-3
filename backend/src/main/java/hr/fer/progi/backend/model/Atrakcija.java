package hr.fer.progi.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "atrakcije")
public class Atrakcija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naziv;
    private String opis;
    private Double latitude;
    private Double longitude;
}
