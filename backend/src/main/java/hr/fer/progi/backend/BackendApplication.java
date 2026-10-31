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
            TerminResursRepository terminResursRepository,
            RecenzijaIzletaRepository recenzijaIzletaRepository,
            RecenzijaVodicaRepository recenzijaVodicaRepository
    ) {
        return args -> {
            if (korisnikRepository.count() > 0 ||
                    atrakcijaRepository.count() > 0 ||
                    dodatnaUslugaRepository.count() > 0 ||
                    resursRepository.count() > 0 ||
                    izletRepository.count() > 0 ||
                    terminRepository.count() > 0 ||
                    rezervacijaRepository.count() > 0 ||
                    terminResursRepository.count() > 0 ||
                    recenzijaIzletaRepository.count() > 0 ||
                    recenzijaVodicaRepository.count() > 0) {
                System.out.println("VEC IMA NESTO U BAZI");
                return;
            }

            Korisnik korisnik = new Korisnik();
            korisnik.setIme("Ana");
            korisnik.setPrezime("Anić");
            korisnik.setEmail("ana@example.com");
            korisnik.setSub("116881078227036825705");
            korisnik.setUloge(Set.of(Uloga.KORISNIK));
            korisnik = korisnikRepository.save(korisnik);

            Korisnik vodic = new Korisnik();
            vodic.setIme("Marko");
            vodic.setPrezime("Marković");
            vodic.setEmail("marko@example.com");
            vodic.setSub("958881078227206825123");
            vodic.setUloge(Set.of(Uloga.KORISNIK, Uloga.VODIC));
            vodic = korisnikRepository.save(vodic);

            Korisnik administrator = new Korisnik();
            administrator.setIme("Iva");
            administrator.setPrezime("Ivić");
            administrator.setEmail("iva@example.com");
            administrator.setSub("746881062449336825123");
            administrator.setUloge(Set.of(
                    Uloga.KORISNIK,
                    Uloga.ORGANIZATOR,
                    Uloga.ADMINISTRATOR
            ));
            administrator = korisnikRepository.save(administrator);

            Korisnik drugiVodic = new Korisnik();
            drugiVodic.setIme("Petra");
            drugiVodic.setPrezime("Petrović");
            drugiVodic.setEmail("petra@example.com");
            drugiVodic.setSub("dummy-vodic-petra");
            drugiVodic.setUloge(Set.of(Uloga.KORISNIK, Uloga.VODIC));
            drugiVodic = korisnikRepository.save(drugiVodic);

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
                    "Medvednica",
                    List.of(sljeme, medvedgrad),
                    List.of(rucak)
            );
            medvednicaIzlet = izletRepository.save(medvednicaIzlet);

            Izlet plitviceIzlet = new Izlet(
                    "Jednodnevni izlet na Plitvice",
                    "Vođeni obilazak jezera i slapova.",
                    "PRIRODA",
                    "Plitvička jezera",
                    List.of(plitvice),
                    List.of(rucak, prijevoz)
            );
            plitviceIzlet = izletRepository.save(plitviceIzlet);

            LocalDateTime sada = LocalDateTime.now();

            Termin medvednicaTermin = new Termin();
            medvednicaTermin.setDatumVrijeme(
                    sada.toLocalDate().plusDays(7).atTime(9, 0)
            );
            medvednicaTermin.setKapacitet(20);
            medvednicaTermin.setPotrebanBrojVodica(1);
            medvednicaTermin.setTrajanjeMinuta(300);
            medvednicaTermin.setMjestoPolaska("Autobusni kolodvor Zagreb");
            medvednicaTermin.setCijena(50.0);
            medvednicaTermin.setIzlet(medvednicaIzlet);
            medvednicaTermin.setVodici(Set.of(vodic));
            medvednicaTermin = terminRepository.save(medvednicaTermin);

            Termin plitviceTermin = new Termin();
            plitviceTermin.setDatumVrijeme(
                    sada.toLocalDate().plusDays(14).atTime(7, 0)
            );
            plitviceTermin.setKapacitet(30);
            plitviceTermin.setPotrebanBrojVodica(1);
            plitviceTermin.setTrajanjeMinuta(600);
            plitviceTermin.setMjestoPolaska("Autobusni kolodvor Zagreb");
            plitviceTermin.setCijena(80.0);
            plitviceTermin.setIzlet(plitviceIzlet);
            plitviceTermin.setVodici(Set.of(vodic));
            plitviceTermin = terminRepository.save(plitviceTermin);

            // Nadolazeći: za dva dana
            Termin medvednicaUskoro = new Termin();
            medvednicaUskoro.setIzlet(medvednicaIzlet);
            medvednicaUskoro.setDatumVrijeme(
                    LocalDateTime.now().plusDays(2).withHour(9).withMinute(0)
                            .withSecond(0).withNano(0)
            );
            medvednicaUskoro.setKapacitet(20);
            medvednicaUskoro.setMjestoPolaska("Autobusni kolodvor Zagreb");
            medvednicaUskoro.setCijena(50.0);
            medvednicaUskoro.setPotrebanBrojVodica(1);
            medvednicaUskoro.setTrajanjeMinuta(300);
            medvednicaUskoro.setVodici(Set.of(vodic));
            terminRepository.save(medvednicaUskoro);

            // Nadolazeći: za tjedan dana
            Termin medvednicaKasnije = new Termin();
            medvednicaKasnije.setIzlet(medvednicaIzlet);
            medvednicaKasnije.setDatumVrijeme(
                    LocalDateTime.now().plusDays(7).withHour(8).withMinute(30)
                            .withSecond(0).withNano(0)
            );
            medvednicaKasnije.setKapacitet(15);
            medvednicaKasnije.setMjestoPolaska("Tunel Sljeme");
            medvednicaKasnije.setCijena(45.0);
            medvednicaKasnije.setPotrebanBrojVodica(1);
            medvednicaKasnije.setTrajanjeMinuta(300);
            medvednicaKasnije.setVodici(Set.of(vodic));
            terminRepository.save(medvednicaKasnije);

            // Prošli: prije tri dana
            Termin medvednicaProsli = new Termin();
            medvednicaProsli.setIzlet(medvednicaIzlet);
            medvednicaProsli.setDatumVrijeme(
                    LocalDateTime.now().minusDays(3).withHour(9).withMinute(0)
                            .withSecond(0).withNano(0)
            );
            medvednicaProsli.setKapacitet(20);
            medvednicaProsli.setMjestoPolaska("Lisinski");
            medvednicaProsli.setCijena(48.0);
            medvednicaProsli.setPotrebanBrojVodica(1);
            medvednicaProsli.setTrajanjeMinuta(300);
            medvednicaProsli.setVodici(Set.of(vodic));
            terminRepository.save(medvednicaProsli);

            Termin prosliTermin = new Termin();
            prosliTermin.setDatumVrijeme(
                    sada.toLocalDate().minusDays(7).atTime(9, 0)
            );
            prosliTermin.setKapacitet(20);
            prosliTermin.setPotrebanBrojVodica(2);
            prosliTermin.setTrajanjeMinuta(300);
            prosliTermin.setMjestoPolaska("Lisinski");
            prosliTermin.setCijena(50.0);
            prosliTermin.setIzlet(medvednicaIzlet);
            prosliTermin.setVodici(Set.of(vodic, drugiVodic));
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
            realizirana = rezervacijaRepository.save(realizirana);

            RecenzijaIzleta recenzijaIzleta = new RecenzijaIzleta();
            recenzijaIzleta.setRezervacija(realizirana);
            recenzijaIzleta.setOcjena(4);
            recenzijaIzleta.setKomentar("Lijepa ruta i odličan pogled sa Sljemena.");
            recenzijaIzleta.setDatumRecenzije(sada.minusDays(6));
            recenzijaIzleta.setDatumZadnjeIzmjene(sada.minusDays(6));
            recenzijaIzletaRepository.save(recenzijaIzleta);

            RecenzijaVodica recenzijaMarka = new RecenzijaVodica();
            recenzijaMarka.setRezervacija(realizirana);
            recenzijaMarka.setVodic(vodic);
            recenzijaMarka.setOcjena(5);
            recenzijaMarka.setKomentar("Marko je odlično objasnio povijest Medvedgrada.");
            recenzijaMarka.setDatumRecenzije(sada.minusDays(6));
            recenzijaMarka.setDatumZadnjeIzmjene(sada.minusDays(6));
            recenzijaVodicaRepository.save(recenzijaMarka);

            RecenzijaVodica recenzijaPetre = new RecenzijaVodica();
            recenzijaPetre.setRezervacija(realizirana);
            recenzijaPetre.setVodic(drugiVodic);
            recenzijaPetre.setOcjena(4);
            recenzijaPetre.setKomentar("Petra je pomagala grupi tijekom uspona.");
            recenzijaPetre.setDatumRecenzije(sada.minusDays(6));
            recenzijaPetre.setDatumZadnjeIzmjene(sada.minusDays(5));
            recenzijaVodicaRepository.save(recenzijaPetre);

            System.out.println("Dummy podaci uspješno spremljeni.");
        };
    }
}
