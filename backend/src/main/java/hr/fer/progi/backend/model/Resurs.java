package hr.fer.progi.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "resursi")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    private Integer kolicina;

    @OneToMany(mappedBy = "resurs")
    private List<TerminResurs> termini;

    public Resurs(String naziv, Integer kolicina) {
        this.naziv = naziv;
        this.kolicina = kolicina;
    }
}
