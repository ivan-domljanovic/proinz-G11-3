package hr.fer.progi.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dodatne_usluge")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DodatnaUsluga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    private String opis;

    private Double cijena;


    public DodatnaUsluga(String naziv, String opis, Double cijena) {
        this.naziv = naziv;
        this.opis = opis;
        this.cijena = cijena;
    }
}