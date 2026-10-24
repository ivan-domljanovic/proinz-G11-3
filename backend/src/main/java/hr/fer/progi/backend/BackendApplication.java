package hr.fer.progi.backend;

import hr.fer.progi.backend.enums.Status;
import hr.fer.progi.backend.enums.Uloga;
import hr.fer.progi.backend.model.*;
import hr.fer.progi.backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class BackendApplication {

    static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Profile("dev")
    @Bean
    CommandLineRunner privremenoPopuniBazu(
            KorisnikRepository korisnikRepository,
            AtrakcijaRepository atrakcijaRepository,
            DodatnaUslugaRepository dodatnaUslugaRepository,
            ResursRepository resursRepository,
            IzletRepository izletRepository,
            TerminRepository terminRepository,
            RezervacijaRepository rezervacijaRepository,
            TerminResursRepository terminResursRepository
    ) {
        return args -> {
            if (korisnikRepository.count() > 0 ||
                    atrakcijaRepository.count() > 0 ||
                    dodatnaUslugaRepository.count() > 0 ||
                    resursRepository.count() > 0 ||
                    izletRepository.count() > 0 ||
                    terminRepository.count() > 0 ||
                    rezervacijaRepository.count() > 0 ||
                    terminResursRepository.count() > 0) {
                System.out.println("VEC IMA NESTO U BAZI");
                return;
            }

            Korisnik korisnik = new Korisnik();
            korisnik.setId(1001L);
            korisnik.setIme("Ana Anić");
            korisnik.setEmail("ana@example.com");
            korisnik.setUloge(Set.of(Uloga.KORISNIK));
            korisnik = korisnikRepository.save(korisnik);

            Korisnik vodic = new Korisnik();
            vodic.setId(1002L);
            vodic.setIme("Marko Marković");
            vodic.setEmail("marko@example.com");
            vodic.setUloge(Set.of(Uloga.KORISNIK, Uloga.VODIC));
            vodic = korisnikRepository.save(vodic);

            Korisnik administrator = new Korisnik();
            administrator.setId(1003L);
            administrator.setIme("Iva Ivić");
            administrator.setEmail("iva@example.com");
            administrator.setUloge(Set.of(
                    Uloga.KORISNIK,
                    Uloga.ORGANIZATOR,
                    Uloga.ADMINISTRATOR
            ));
            administrator = korisnikRepository.save(administrator);

            // ATRAKCIJE
            Atrakcija sljeme = new Atrakcija();
            sljeme.setNaziv("Sljeme");
            sljeme.setOpis("Vrh Medvednice i pogled na Zagreb.");
            sljeme.setLatitude(45.8990);
            sljeme.setLongitude(15.9480);
            sljeme = atrakcijaRepository.save(sljeme);

            Atrakcija medvedgrad = new Atrakcija();
            medvedgrad.setNaziv("Medvedgrad");
            medvedgrad.setOpis("Srednjovjekovna utvrda na Medvednici.");
            medvedgrad.setLatitude(45.8690);
            medvedgrad.setLongitude(15.9410);
            medvedgrad = atrakcijaRepository.save(medvedgrad);

            Atrakcija plitvice = new Atrakcija();
            plitvice.setNaziv("Plitvička jezera");
            plitvice.setOpis("Šetnja uz jezera i slapove.");
            plitvice.setLatitude(44.8800);
            plitvice.setLongitude(15.6160);
            plitvice = atrakcijaRepository.save(plitvice);

            DodatnaUsluga rucak = new DodatnaUsluga();
            rucak.setNaziv("Ručak");
            rucak.setOpis("Ručak u restoranu tijekom izleta.");
            rucak.setCijena(15.0);
            rucak = dodatnaUslugaRepository.save(rucak);

            DodatnaUsluga prijevoz = new DodatnaUsluga();
            prijevoz.setNaziv("Prijevoz");
            prijevoz.setOpis("Povratni prijevoz autobusom.");
            prijevoz.setCijena(20.0);
            prijevoz = dodatnaUslugaRepository.save(prijevoz);

            Resurs stapovi = new Resurs();
            stapovi.setNaziv("Planinarski štapovi");
            stapovi.setKolicina(30);
            stapovi = resursRepository.save(stapovi);

            Resurs audioVodici = new Resurs();
            audioVodici.setNaziv("Audio vodiči");
            audioVodici.setKolicina(40);
            audioVodici = resursRepository.save(audioVodici);

            Izlet medvednicaIzlet = new Izlet(
                    "Planinarenje Medvednicom",
                    "Obilazak Medvedgrada i uspon na Sljeme.",
                    "PLANINARENJE",
                    300,
                    "Medvednica",
                    "Šestine, Zagreb",
                    25.0,
                    20,
                    1,
                    List.of(sljeme, medvedgrad),
                    List.of(rucak)
            );
            medvednicaIzlet = izletRepository.save(medvednicaIzlet);

            Izlet plitviceIzlet = new Izlet(
                    "Jednodnevni izlet na Plitvice",
                    "Vođeni obilazak jezera i slapova.",
                    "PRIRODA",
                    600,
                    "Plitvička jezera",
                    "Autobusni kolodvor Zagreb",
                    60.0,
                    30,
                    1,
                    List.of(plitvice),
                    List.of(rucak, prijevoz)
            );
            plitviceIzlet = izletRepository.save(plitviceIzlet);

            LocalDateTime sada = LocalDateTime.now();

            Termin medvednicaTermin = new Termin();
            medvednicaTermin.setDatumVrijeme(
                    sada.toLocalDate().plusDays(7).atTime(9, 0)
            );
            medvednicaTermin.setIzlet(medvednicaIzlet);
            medvednicaTermin.setVodici(Set.of(vodic));
            medvednicaTermin = terminRepository.save(medvednicaTermin);

            Termin plitviceTermin = new Termin();
            plitviceTermin.setDatumVrijeme(
                    sada.toLocalDate().plusDays(14).atTime(7, 0)
            );
            plitviceTermin.setIzlet(plitviceIzlet);
            plitviceTermin.setVodici(Set.of(vodic));
            plitviceTermin = terminRepository.save(plitviceTermin);

            Termin prosliTermin = new Termin();
            prosliTermin.setDatumVrijeme(
                    sada.toLocalDate().minusDays(7).atTime(9, 0)
            );
            prosliTermin.setIzlet(medvednicaIzlet);
            prosliTermin.setVodici(Set.of(vodic));
            prosliTermin = terminRepository.save(prosliTermin);

            TerminResurs stapoviZaTermin = new TerminResurs();
            stapoviZaTermin.setTermin(medvednicaTermin);
            stapoviZaTermin.setResurs(stapovi);
            stapoviZaTermin.setKolicina(20);
            terminResursRepository.save(stapoviZaTermin);

            TerminResurs audioZaTermin = new TerminResurs();
            audioZaTermin.setTermin(plitviceTermin);
            audioZaTermin.setResurs(audioVodici);
            audioZaTermin.setKolicina(30);
            terminResursRepository.save(audioZaTermin);

            Rezervacija potvrdena = new Rezervacija();
            potvrdena.setStatus(Status.POTVRDENO);
            potvrdena.setBrojMjesta(2);
            potvrdena.setVrijemeRezervacije(sada.minusDays(1));
            potvrdena.setKorisnik(korisnik);
            potvrdena.setTermin(medvednicaTermin);
            potvrdena.setDodatneUsluge(List.of(rucak));
            rezervacijaRepository.save(potvrdena);

            Rezervacija otkazana = new Rezervacija();
            otkazana.setStatus(Status.OTKAZANO);
            otkazana.setBrojMjesta(1);
            otkazana.setVrijemeRezervacije(sada.minusDays(2));
            otkazana.setKorisnik(korisnik);
            otkazana.setTermin(plitviceTermin);
            otkazana.setDodatneUsluge(List.of(prijevoz));
            rezervacijaRepository.save(otkazana);

            Rezervacija realizirana = new Rezervacija();
            realizirana.setStatus(Status.REALIZIRANO);
            realizirana.setBrojMjesta(3);
            realizirana.setVrijemeRezervacije(sada.minusDays(14));
            realizirana.setKorisnik(korisnik);
            realizirana.setTermin(prosliTermin);
            realizirana.setDodatneUsluge(List.of(rucak));
            rezervacijaRepository.save(realizirana);

            System.out.println("Dummy podaci uspješno spremljeni.");
        };
    }
}
