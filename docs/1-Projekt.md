# 1. Opseg projekta

## 1.1 Problem i cilj projekta

Organizacija turističkih izleta zahtijeva usklađivanje rezervacija, slobodnih mjesta, termina i raspoloživih resursa. Kada se ti podaci vode odvojeno, turistima može biti otežano pronalaženje dostupnih izleta, dok organizatori moraju dodatno usklađivati rezervacije i raspoložive kapacitete (ručno, preko tablica, e-pošte ili telefona).

Cilj projekta je razviti web aplikaciju koja turistima omogućuje pregled i rezervaciju izleta na jednom mjestu, a organizatorima jednostavnije upravljanje ponudom, terminima, kapacitetima i potrebnim resursima. Aplikacija dodatno omogućuje korištenje digitalnog turističkog vodiča koji na temelju GPS lokacije korisniku prikazuje informacije o mjestima u blizini.

Očekivana korist: turist na jednom mjestu rezervira izlet i dobije potvrdu e-poštom nakon potvrđivanja rezervacije, organizator prati popunjenost, rezervacije, prihode i popularnost izleta, dok sustav sprječava prekoračenje kapaciteta rezervacija.

## 1.2 Ciljani korisnici i dionici

| Skupina korisnika ili dionika               | Potreba ili interes relevantan za ovaj projekt                                                                                                                                                                |
| ------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Neprijavljeni posjetitelj                   | Pregledati i pretražiti ponudu izleta prema terminu, lokaciji, vrsti aktivnosti, trajanju, cijeni i broju sudionika.                                                                                          |
| Korisnik (prijavljeni) | Rezervirati izlet za određeni broj osoba, odabrati dodatne usluge, pratiti status rezervacije, pregledati, izmijeniti i otkazati rezervaciju te dobiti potvrdu e-poštom. Ako nema slobodnih mjesta, rezervacija čeka na listi čekanja i automatski se potvrđuje kad se mjesta oslobode. Ocjenjivati izlete i vodiče te tijekom izleta koristiti digitalni vodič s kartom. |
| Administrator | Dodavati i mijenjati izlete, termine, cijene, kapacitete, dodatne usluge, resurse i točke interesa, upravljati rezervacijama te korisnicima dodjeljivati uloge. |
| Organizator | Objavljivati i uređivati izlete i njihove termine, dodjeljivati vodiče terminima i pratiti raspoloživost resursa (prijevoz, oprema) te pregledavati statistiku rezervacija, popunjenosti, prihoda i popularnosti aktivnosti i dodatnih usluga. |
| Vodič (dionik) | Biti dodijeljen terminima izleta i dobivati ocjene i recenzije korisnika koji su sudjelovali na izletu. |


## 1.3 Granice projekta

**Uključeno:**

- pregled i pretraživanje izleta prema terminu, lokaciji, vrsti aktivnosti, trajanju, cijeni i broju sudionika;
- rezervacija izleta za određeni broj osoba s odabirom dodatnih usluga, pri čemu sustav sprječava prekoračenje raspoloživog kapaciteta;
- statusi rezervacije (zahtjev, potvrđena, otkazana, realizirana), pregled, izmjena i otkazivanje vlastite rezervacije te potvrda e-poštom;
- upravljanje izletima, terminima, cijenama, kapacitetima, dodatnim uslugama i rezervacijama;
- definiranje točaka interesa (naziv, opis, lokacija) za pojedini izlet;
- dodjela vodiča terminima izleta i praćenje raspoloživosti resursa (prijevoz, oprema);
- pregled statistike rezervacija, popunjenosti, prihoda, najtraženijih aktivnosti i dodatnih usluga te izvoz izvještaja u CSV formatu;
- digitalni vodič na mobitelu koji prema GPS lokaciji prikazuje informacije o unaprijed definiranim točkama interesa u blizini korisnika te kartu s položajem korisnika i točaka interesa;
- prijava putem vanjskog pružatelja identiteta i različite uloge korisnika s odgovarajućim ovlastima;
- responzivno sučelje za računala, tablete i mobitele te istovremeni rad većeg broja korisnika;
- ocjenjivanje i recenziranje izleta i turističkih vodiča;
- prijava na listu čekanja za popunjene termine izleta.
  
**Izvan opsega ili odgođeno:**
- **Push obavijesti na mobitel** – aplikacija ne šalje mobilne obavijesti ni automatske podsjetnike o nadolazećim izletima. Potvrda rezervacije šalje se e-poštom.
- **Preporuke izleta** – aplikacija ne preporučuje izlete prema prethodnim rezervacijama korisnika.
- **Online plaćanje** – korisnici mogu rezervirati izlet, ali ga ne mogu platiti putem aplikacije.
  
**Vanjske ovisnosti:**

- **Google prijava (OAuth 2.0 / OpenID Connect)** – korisnici se prijavljuju svojim Google računom. Ako usluga nije dostupna, prijava ne radi.
- **Usluga za slanje e-pošte** – omogućuje slanje potvrda rezervacija korisnicima.
- **OpenStreetMap** – daje kartu na kojoj se prikazuju korisnik i točke interesa.
- **GPS lokacija uređaja** – preko preglednika na mobitelu određuje gdje se korisnik nalazi. Bez nje digitalni vodič ne može prikazati mjesta u blizini.
- **Platforma za smještaj aplikacije (Microsoft Azure)** – omogućuje pokretanje web aplikacije i pristup korisnicima putem interneta te korištenje usluge za pohranu podataka.

## 1.4 Postojeći pristupi i opravdanost projekta

Primjer mogućeg načina organizacije izleta: manji organizator izleta prima upite i rezervacije e-poštom ili telefonom, a popunjenost termina i raspored vodiča vodi u zasebnoj tablici. U tom slučaju turist mora kontaktirati organizatora da bi saznao ima li slobodnih mjesta, a organizator ručno usklađuje rezervacije s kapacitetom i dostupnim vodičima. Predložena aplikacija objedinjuje ponudu, rezervacije, kapacitete i vodiče na jednom mjestu te ih dopunjuje digitalnim vodičem za vrijeme izleta. Aplikacija ne zamjenjuje vodiča na terenu, nego korisniku daje dodatne informacije o mjestima koja posjećuje.
