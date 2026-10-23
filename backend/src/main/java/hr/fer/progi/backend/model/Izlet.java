package hr.fer.progi.backend.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
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
    @Column(columnDefinition = "INTERVAL")
    private Duration trajanje;
    private String lokacija;
    private String mjestoPolaska;
    private Double cijena;
    private Integer kapacitet;
    private Integer potrebanBrojVodica;
    private List<String> dodatneUsluge;
    private List<LocalDateTime> termini;

    @ManyToMany
    private List<Atrakcija> atrakcije;
}
