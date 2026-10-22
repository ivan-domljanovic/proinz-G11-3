# 1. Opseg projekta

## 1.1 Problem i cilj projekta

Turističke agencije i organizatori izleta nude velik broj aktivnosti koje se održavaju na različitim lokacijama i u različitim terminima. Ako se rezervacije, kapaciteti i vodiči vode ručno, preko tablica, e-pošte ili telefona, organizator teško prati koliko je mjesta slobodno i koji su vodiči dostupni, a turist nema na jednom mjestu pregled dostupnih izleta, cijena i slobodnih mjesta.

Cilj projekta je razviti web aplikaciju u kojoj turisti na jednom mjestu mogu pretraživati i rezervirati izlete, a agencija upravljati ponudom, terminima, kapacitetima, vodičima i dodatnim uslugama. Tijekom izleta aplikacija na mobitelu radi kao digitalni vodič: prema GPS lokaciji korisniku prikazuje informacije o mjestima u blizini.

Očekivana korist: turist na jednom mjestu rezervira izlet i dobije potvrdu e-poštom; organizator uvijek vidi popunjenost i ne može se dogoditi da se rezervira više mjesta nego što ih ima; voditelj agencije vidi broj rezervacija, prihod i najtraženije izlete.

## 1.2 Ciljani korisnici i dionici

| Skupina korisnika ili dionika               | Potreba ili interes relevantan za ovaj projekt                                                                                                                                                                |
| ------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Neprijavljeni posjetitelj                   | Pregledati i pretražiti ponudu izleta prema terminu, lokaciji, vrsti aktivnosti, trajanju, cijeni i broju sudionika.                                                                                          |
| Turist (prijavljeni korisnik)               | Rezervirati izlet za određeni broj osoba, odabrati dodatne usluge, pratiti status rezervacije, pregledati podatke o rezervaciji i dobiti potvrdu e-poštom. Tijekom izleta koristiti digitalni vodič s kartom. |
| Administrator                               | Dodavati i mijenjati izlete, termine, cijene, kapacitete, dodatne usluge i točke interesa te upravljati rezervacijama.                                                                                        |
| Organizator                                 | Dodjeljivati vodiče izletima i pratiti raspoloživost resursa (prijevoz, oprema).                                                                                                                              |
| Voditelj turističke agencije                | Pregledati statistiku (broj rezervacija, popunjenost, prihod, najtraženije aktivnosti i usluge) i izvesti izvještaje.                                                                                         |
| Vodič (dionik)                              | Znati kojim je terminima izleta dodijeljen.                                                                                                                                                                   |
| Tijela za zaštitu osobnih podataka (dionik) | Zahtijevaju zaštitu osobnih podataka i lokacije korisnika u skladu s GDPR-om. Ne koriste aplikaciju, ali postavljaju ograničenja sustavu.                                                                     |

## 1.3 Granice projekta

**Uključeno:**

- pretraživanje izleta prema terminu, lokaciji, vrsti aktivnosti, trajanju, cijeni i broju osoba;
- rezervacija izleta za određeni broj osoba s odabirom dodatnih usluga, pri čemu sustav ne dopušta rezervaciju kad je izlet popunjen;
- statusi rezervacije (zahtjev, potvrđena, otkazana, realizirana), pregled podataka o rezervaciji i potvrda e-poštom;
- upravljanje izletima, terminima, cijenama, kapacitetima, dodatnim uslugama i rezervacijama;
- definiranje točaka interesa (naziv, opis, lokacija) za pojedini izlet;
- dodjela vodiča izletima i praćenje raspoloživosti resursa (prijevoz, oprema);
- statistika i izvoz izvještaja u CSV formatu;
- digitalni vodič na mobitelu koji prema GPS lokaciji prikazuje informacije o mjestima u blizini i kartu s položajem korisnika i točaka interesa;
- prijava putem vanjskog pružatelja identiteta i različite uloge korisnika s odgovarajućim ovlastima;
- responzivno sučelje za računala, tablete i mobitele te istovremeni rad većeg broja korisnika.

**Izvan opsega ili odgođeno:**

- **Online plaćanje ?? pitanje za tim** – rezervacija se potvrđuje u aplikaciji, a plaća se izvan nje (npr. na licu mjesta). Plaćanje bi uvelo dodatne sigurnosne zahtjeve i integraciju s bankom.
- **Ocjene i recenzije izleta** – korisnici ne mogu ocjenjivati izlete. Korisno je, ali nije dio zadatka, pa se ostavlja za buduće verzije.
- **Push obavijesti na mobitel** – korisnik ne dobiva obavijesti na mobitel (npr. podsjetnik dan prije izleta). Sve potrebne obavijesti šalju se e-poštom.
- **Preporuke izleta** – aplikacija ne preporučuje izlete prema prethodnim rezervacijama korisnika.
- **Lista čekanja** – kad je izlet popunjen, korisnik se ne može prijaviti na listu čekanja za slučaj da netko otkaže.

**Vanjske ovisnosti:**

- **Google prijava (OAuth 2.0 / OpenID Connect)** – korisnici se prijavljuju svojim Google računom. Ako usluga nije dostupna, prijava ne radi.
- **Usluga za slanje e-pošte** – šalje potvrdu rezervacije korisniku. Ako ne radi, rezervacija se i dalje spremi, ali potvrda ne stigne na e-mail.
- **OpenStreetMap** – daje kartu na kojoj se prikazuju korisnik i točke interesa. Ako nije dostupna, karta se ne prikazuje.
- **GPS lokacija uređaja** – preko preglednika na mobitelu određuje gdje se korisnik nalazi. Bez nje digitalni vodič ne može prikazati mjesta u blizini.
- **Platforma za postavljanje (npr. Render)** – na njoj se pokreću aplikacija i baza podataka. Ako ne radi, cijela aplikacija je nedostupna. <!-- TODO: potvrditi s timom koju platformu koristimo (Render, Azure...) i koji OAuth pružatelj -->

## 1.4 Postojeći pristupi i opravdanost projekta

Kao primjer tipičnog scenarija: manji organizator izleta prima upite i rezervacije e-poštom ili telefonom, a popunjenost termina i raspored vodiča vodi u zasebnoj tablici. Turist zato mora kontaktirati organizatora da bi saznao ima li slobodnih mjesta, a organizator ručno usklađuje rezervacije s kapacitetom i dostupnim vodičima. Naša aplikacija objedinjuje ponudu, rezervacije, kapacitete i vodiče na jednom mjestu te ih dopunjuje digitalnim vodičem za vrijeme izleta. Aplikacija ne zamjenjuje vodiča na terenu, nego korisniku daje dodatne informacije o mjestima koja posjećuje.
