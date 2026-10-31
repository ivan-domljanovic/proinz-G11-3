package hr.fer.progi.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "recenzije_izleta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecenzijaIzleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ocjena;

    @Column(length = 500)
    private String komentar;

    private LocalDateTime datumRecenzije;

    private LocalDateTime datumZadnjeIzmjene;

    @OneToOne
    @JoinColumn(name = "rezervacija_id", unique = true)
    private Rezervacija rezervacija;

    public RecenzijaIzleta(Integer ocjena, String komentar, LocalDateTime datumRecenzije,
                           LocalDateTime datumZadnjeIzmjene, Rezervacija rezervacija) {
        this.ocjena = ocjena;
        this.komentar = komentar;
        this.datumRecenzije = datumRecenzije;
        this.datumZadnjeIzmjene = datumZadnjeIzmjene;
        this.rezervacija = rezervacija;
    }
}
