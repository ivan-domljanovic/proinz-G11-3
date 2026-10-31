package hr.fer.progi.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "recenzije_vodica",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_recenzija_vodica_rezervacija_vodic",
                columnNames = {"rezervacija_id", "vodic_id"}
        ))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecenzijaVodica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ocjena;

    @Column(length = 500)
    private String komentar;

    private LocalDateTime datumRecenzije;

    private LocalDateTime datumZadnjeIzmjene;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rezervacija_id")
    private Rezervacija rezervacija;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vodic_id")
    private Korisnik vodic;
}
