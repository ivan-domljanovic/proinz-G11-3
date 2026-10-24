package hr.fer.progi.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "izleti")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Izlet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    private String opis;
    private String kategorijaAktivnosti;

    private Integer trajanjeMinuta;

    private String lokacija;
    private String mjestoPolaska;
    private Double cijena;
    private Integer kapacitet;
    private Integer potrebanBrojVodica;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Atrakcija> atrakcije;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<DodatnaUsluga> dodatneUsluge;

    @OneToMany(mappedBy = "izlet")
    private List<Termin> termini;

    public Izlet(String naziv, String opis, String kategorijaAktivnosti, Integer trajanjeMinuta, String lokacija, String mjestoPolaska, Double cijena, Integer kapacitet, Integer potrebanBrojVodica, List<Atrakcija> atrakcije, List<DodatnaUsluga> dodatneUsluge) {
        this.naziv = naziv;
        this.opis = opis;
        this.kategorijaAktivnosti = kategorijaAktivnosti;
        this.trajanjeMinuta = trajanjeMinuta;
        this.lokacija = lokacija;
        this.mjestoPolaska = mjestoPolaska;
        this.cijena = cijena;
        this.kapacitet = kapacitet;
        this.potrebanBrojVodica = potrebanBrojVodica;
        this.atrakcije = atrakcije;
        this.dodatneUsluge = dodatneUsluge;
    }
}
