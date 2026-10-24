package hr.fer.progi.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "atrakcije")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Atrakcija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    private String opis;
    private Double latitude;
    private Double longitude;


    public Atrakcija(String naziv, String opis, Double latitude, Double longitude) {
        this.naziv = naziv;
        this.opis = opis;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
