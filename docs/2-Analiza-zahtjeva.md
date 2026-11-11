# 2. Analiza zahtjeva

## 2.1 Funkcijski zahtjevi


| ID    | Opis                                                                                                                                                                                                                                                           | Prioritet | Izvor             | Kriteriji prihvaćanja                                                                                                                         |
| ----- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------- | ----------------- | --------------------------------------------------------------------------------------------------------------------------------------------- |
| F-001 | Korisnik se prijavljuje putem Googlea (OpenID Connect / OAuth 2.0).                                                                                                                                                                                            | Visok     | Upute kolegija / odluka tima | Nakon uspješne prijave korisnik vidi svoje ime i može pristupiti funkcijama svoje uloge. Ako odustane od prijave, funkcije namijenjene prijavljenim korisnicima ostaju nedostupne.|                                                    
| F-002 | Korisnik pregledava izlete (naziv, opis, kategorija, trajanje, lokacija) i njihove termine (datum i vrijeme, mjesto polaska, cijena, slobodna mjesta).                                                                                                         | Visok     | Projektni zadatak | Za svaki izlet prikazani su svi navedeni podaci i svi njegovi termini s cijenom i brojem slobodnih mjesta.                                    |
| F-003 | Korisnik pretražuje izlete prema terminu, lokaciji, vrsti aktivnosti, trajanju, cijeni i broju sudionika.                                                                                                                                                      | Visok     | Projektni zadatak | Prikazani izleti odgovaraju zadanim kriterijima pretraživanja. Ako nema odgovarajućih rezultata, prikazuje se odgovarajuća poruka.                                                                 |
| F-004 | Prijavljeni korisnik rezervira termin izleta za jednu ili više osoba i bira dodatne usluge (prijevoz, najam opreme). | Visok | Projektni zadatak | Rezervacija se sprema s odabranim terminom, brojem osoba i dodatnim uslugama. Ako ima dovoljno slobodnih mjesta, rezervacija dobiva status POTVRDENO, a u suprotnom status ZAHTJEV. | 
| F-005 | Sustav automatski provjerava raspoloživi kapacitet termina i sprječava njegovo prekoračenje. | Visok | Projektni zadatak | Rezervacije se potvrđuju samo ako ima dovoljno slobodnih mjesta. U suprotnom dobivaju status ZAHTJEV i ne zauzimaju mjesta u terminu. Kapacitet se ne smije prekoračiti ni pri istovremenim rezervacijama. |
| F-006 | Korisnik prati status svojih rezervacija (zahtjev za rezervaciju, potvrđena rezervacija, otkazana rezervacija i realizirani izlet) te pregledava njihove podatke. | Srednji | Projektni zadatak | Korisnik vidi svoje rezervacije i njihove statuse; za potvrđenu rezervaciju vidi izlet, termin, broj osoba, dodatne usluge i cijenu. |
| F-007 | Sustav šalje potvrdu rezervacije e-poštom. | Srednji | Projektni zadatak | Nakon potvrđivanja rezervacije korisnik na svoju adresu e-pošte prima poruku s podacima o rezervaciji, uključujući rezervacije naknadno potvrđene s liste čekanja. |
| F-008 | Korisnik tijekom izleta na mobitelu vidi svoj položaj i točke interesa na karti te dobiva informacije o obližnjim točkama interesa.                                                                                                                                   | Visok  | Projektni zadatak |Karta prikazuje položaj korisnika i unaprijed definirane točke interesa. Kada se korisnik približi točki interesa, prikazuju se njezin naziv, opis, značaj i zanimljivosti.                          |
| F-009 | Administrator dodaje, mijenja i briše izlete te upravlja njihovim osnovnim podacima (naziv, opis, kategorija, trajanje, lokacija). | Visok | Projektni zadatak | Administrator može dodati novi izlet, izmijeniti njegove podatke i obrisati izlet. Dodani i izmijenjeni podaci vidljivi su korisnicima, a obrisani izlet više nije dostupan u ponudi. |
| F-010 | Administrator za pojedini izlet definira termine (datum i vrijeme, mjesto polaska, cijena, kapacitet, potreban broj vodiča) i dodatne usluge. | Visok | Projektni zadatak / odluka tima | Za isti izlet može se definirati jedan ili više termina sa svim navedenim podacima te popis dodatnih usluga koje korisnik vidi pri rezervaciji. |
| F-011 | Administrator upravlja rezervacijama.                                                                                                                                                                                                                          | Srednji   | Projektni zadatak | Administrator vidi sve rezervacije i može promijeniti njihov status; korisnik vidi novi status.                                               |
| F-012 | Organizator objavljuje, mijenja i briše vlastite ponude izleta.                                                                                                                                                                                                | Srednji   | Projektni zadatak | Organizator vidi samo svoje izlete i može ih objaviti, izmijeniti i obrisati; tuđe izlete ne može mijenjati.                                  |
| F-013 | Organizator dodjeljuje turističke vodiče pojedinim terminima izleta.                                                                                                                                                                                                                | Srednji   | Projektni zadatak | Organizator može dodijeliti vodiče odabranom terminu do potrebnog broja vodiča. Dodijeljeni vodiči vidljivi su uz termin, a njihova se raspoloživost može provjeriti.                                           |
| F-014 | Organizator vodi evidenciju resursa (prijevoz, oprema) i njihove zauzetosti po terminima.                                                                                                                                                                      | Srednji   | Projektni zadatak | Za svaki resurs vidljiva je ukupna količina i koliko je zauzeto u pojedinom terminu; terminu se ne može dodijeliti više od slobodne količine. |
| F-015 | Administrator i organizator definiraju točke interesa izleta (naziv, kratak opis, značaj, zanimljivosti, lokacija).                                                                                                                                            | Srednji   | Projektni zadatak | Za izlet se može unijeti točka interesa s koordinatama i opisom; prikazuje se korisniku na karti tijekom izleta.                              |
| F-016 | Organizator pregledava statistiku svojih izleta: broj rezervacija, popunjenost izleta, ostvareni prihod te najtraženije aktivnosti i dodatne usluge. | Srednji | Projektni zadatak | Organizator može pregledati navedene statističke podatke za izlete kojima upravlja. |
| F-017 | Organizator izvozi odabrane podatke i izvještaje u CSV formatu. | Srednji | Projektni zadatak | Organizator može preuzeti CSV datoteku s odabranim podacima koja se može otvoriti u alatima za obradu podataka. |
| F-018 | Administrator korisnicima dodjeljuje korisničke uloge (organizator, administrator), a sustav svakoj ulozi omogućuje samo njezine radnje. | Visok | Projektni zadatak | Administrator može korisniku dodijeliti ulogu organizatora ili administratora. Korisnik može izvršavati samo radnje dopuštene njegovim ulogama, dok se neovlašteni zahtjevi odbijaju. |
| F-019 |Prijavljeni korisnik ocjenjuje i recenzira izlete.  | Srednji | Dogovor s asistentom | Korisnik može unijeti ocjenu i tekstualnu recenziju izleta. Spremljena ocjena i recenzija prikazuju se uz odgovarajući izlet. |
| F-020 |Prijavljeni korisnik ocjenjuje i recenzira turističke vodiče.  | Srednji | Dogovor s asistentom | Korisnik može unijeti ocjenu i tekstualnu recenziju turističkog vodiča. Spremljena ocjena i recenzija prikazuju se uz odgovarajućeg vodiča. |
| F-021 | Sustav automatski stavlja rezervacije na listu čekanja kada nema dovoljno slobodnih mjesta te obrađuje zahtjeve nakon oslobađanja kapaciteta. | Srednji | Dogovor s asistentom / odluka tima | Ako nema dovoljno slobodnih mjesta, rezervacija dobiva status ZAHTJEV i ne zauzima kapacitet termina. Nakon otkazivanja ili smanjenja broja sudionika sustav automatski potvrđuje zahtjeve za koje postoji dovoljno slobodnih mjesta. |
| F-022 |Prijavljeni korisnik naknadno mijenja broj sudionika ili dodatne usluge postojeće rezervacije. | Srednji | Dogovor s asistentom  | Nakon izmjene ažuriraju se podaci rezervacije. Ako ima dovoljno slobodnih mjesta, rezervacija ima status POTVRDENO, a u suprotnom status ZAHTJEV. |
| F-023 | Prijavljeni korisnik otkazuje vlastitu rezervaciju izleta. | Srednji | Odluka tima | Korisnik može otkazati vlastitu rezervaciju, nakon čega ona dobiva status OTKAZANO. Ako je rezervacija bila potvrđena, oslobađaju se zauzeta mjesta u terminu. |

## 2.2 Nefunkcijski zahtjevi

| ID         | Opis                                                                                            | Prioritet | Izvor                        | Način provjere                                                                            |
| ---------- | ----------------------------------------------------------------------------------------------- | --------- | ---------------------------- | ----------------------------------------------------------------------------------------- |
| NF-01 | Sustav podržava istodobni rad više korisnika.       | Visok     | Projektni zadatak | Istovremeno pristupiti aplikaciji s više korisničkih računa te provjeriti mogu li korisnici pregledavati izlete i izrađivati rezervacije bez međusobnog ometanja. |
| NF-02 | Sučelje je responzivno (računalo, tablet, mobitel). | Visok     | Projektni zadatak | Pregled sučelja na sva tri tipa uređaja.                                                  |
| NF-03 | Aplikacija je javno dostupna na internetu.          | Visok     | Projektni zadatak | Provjeriti može li se aplikaciji pristupiti putem javne web-adrese izvan lokalne mreže.                      |

## 2.3 Dionici i akteri


| ID uloge ili aktera                                   | Odnos prema sustavu                                                                                                                                         | Relevantni zahtjevi          |
| ----------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------- |
| A-01 Prijavljeni korisnik  | Rezervira izlete, pregledava i mijenja svoje rezervacije, koristi digitalni vodič, ocjenjuje izlete i vodiče te može otkazati rezervaciju.                                         |F-001 do F-008, F-019 do F-023            |
| A-02 Organizator                                      | Objavljuje i uređuje vlastite izlete, dodjeljuje vodiče, vodi evidenciju resursa, definira točke interesa te pregledava statistiku i izvozi izvještaje.     | F-012 do F-017               |
| A-03 Administrator                                    | Upravlja svim izletima, terminima, cijenama, kapacitetima, dodatnim uslugama, rezervacijama i točkama interesa te dodjeljuje uloge korisnicima.             | F-009 do F-011, F-015, F-018 |
| A-04 Pružatelj identiteta (Google)                    | Vanjski sustav koji provodi prijavu korisnika.                                                                                                              | F-001                        |
| A-05 Usluga e-pošte                 | Vanjski sustav koji omogućuje slanje potvrda rezervacija.                                                                                                     | F-007         |
| A-06 Neprijavljeni posjetitelj               | Pregledava i pretražuje ponudu izleta bez prijave u sustav.                                                  | F-002, F-003         |
| A-07 Kartografska usluga              | Vanjska usluga koja pruža kartografske podatke za prikaz korisnikove lokacije i točaka interesa. | F-008        |
| D-01 Turistički vodič (dionik)         | Dodjeljuje se terminima izleta te je povezan s evidencijom raspoloživosti i korisničkim recenzijama, ali ne koristi izravno aplikaciju. | F-013, F-020      |

## 2.4 Ograničenja i pretpostavke

**Cilj:** Zabilježiti projektno specifične granice i pretpostavke koje utječu na zahtjeve ili oblikovanje, zajedno s njihovim izvorom i praktičnom posljedicom. Ovo je njihovo mjerodavno mjesto u dokumentaciji.

| ID    | Tip          | Opis ograničenja ili pretpostavke                                                                                                   | Izvor             | Inženjerska posljedica / Provjera                                                                                                                                               |
| ----- | ------------ | ----------------------------------------------------------------------------------------------------------------------------------- | ----------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| C-01  | Ograničenje  | Prijava mora ići putem vanjskog pružatelja identiteta (OpenID Connect / OAuth 2.0).                                                 | Projektni zadatak | Sustav mora koristiti vanjskog pružatelja identiteta i provjeravati ovlasti prijavljenih korisnika.                                                                           |
| AS-01 | Ograničenje | Neprijavljen korisnik smije pregledavati i pretraživati izlete; prijava se traži tek za rezervaciju.                                | Procjena tima     | Provjeriti da neprijavljeni korisnik može pregledavati izlete, ali ne može napraviti rezervaciju. |
| AS-02 | Ograničenje | Korisnik smije sam otkazati vlastitu rezervaciju.                                                                                   | Procjena tima     | Nakon otkazivanja potvrđene rezervacije oslobađaju se zauzeta mjesta, a sustav obrađuje listu čekanja.                         |
| AS-03 | Ograničenje | Za rad digitalnog vodiča koristi se GPS lokacija dobivena putem preglednika mobilnog uređaja.                 | Procjena tima     | Provjeriti može li aplikacija dohvatiti lokaciju uz dopuštenje korisnika i prikazati obližnje točke interesa.                                                                                                                   |
| AS-04 | Ograničenje | Turistički vodič nema vlastiti korisnički račun, nego se vodi kao podatak koji organizator dodjeljuje terminima izleta.| Procjena tima     | Nije potrebno razvijati zasebne funkcionalnosti za prijavu i rad turističkih vodiča.|
| AS-05 | Ograničenje | Korisnici se prijavljuju Google računom, a dodatne korisničke uloge dodjeljuje administrator.| Procjena tima     | Pri prvoj prijavi korisnik dobiva osnovnu ulogu, a administrator mu može dodijeliti dodatne ovlasti. Provjeriti dobiva li korisnik pri prvoj prijavi osnovnu ulogu te može li mu administrator dodijeliti dodatne ovlasti.|


## 2.5 Značajne promjene zahtjeva

**Cilj:** Sačuvati razlog promjena koje mijenjaju dogovoreni opseg, prioritet ili kriterije prihvaćanja, uz istodobno održavanje prethodnih zahtjeva ažurnima.

| Datum         | ID zahtjeva         | Promjena i razlog                                                                                                                                                                                              | Dogovoreno s / dokaz                                         |
| ------------- | ------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------ |
| 25. 10. 2026. | F-002, F-009, F-010 | Mjesto polaska, cijena, kapacitet i potreban broj vodiča zadaju se za svaki termin izleta, a ne jednom za cijeli izlet, jer isti izlet može imati termine s različitom cijenom, kapacitetom i mjestom polaska. | Backend tim; model `Termin` u repozitoriju                   |
| 25. 10. 2026. | F-013, F-014        | Vodiči se dodjeljuju pojedinom terminu izleta. Resursi (prijevoz, oprema) vode se kao količina koja se dodjeljuje terminu (npr. 2 od 3 kombija), a ne samo kao slobodan ili zauzet.                            | Backend tim; modeli `Termin` i `TerminResurs` u repozitoriju |
| 6. 11. 2026. | NF-01, F-018 | Razlikovanje uloga i ovlasti opisuje što sustav radi, a ne svojstvo kvalitete, pa je premješteno iz nefunkcijskih zahtjeva u funkcijski zahtjev F-018. Ostali nefunkcijski zahtjevi renumerirani su u NF-01 do NF-03. | Asistent (konzultacije) |
| 6. 11. 2026. | F-012 do F-017 | Uloge organizatora i voditelja turističke agencije spojene su u jednu ulogu, organizator, jer se obje odnose na istu osobu koja vodi ponudu izleta; statistika i izvoz sada pripadaju organizatoru. | Asistent (konzultacije) |
| 11. 11. 2026. | F-019, F-020, F-021 | Ocjene i recenzije izleta i vodiča te lista čekanja prebačene su iz odgođenih u uključene funkcionalnosti, prema prijedlogu asistenta. | Asistent (konzultacije) |
| 11. 11. 2026. | F-004, F-005, F-021  | Definirano je automatsko upravljanje listom čekanja. Rezervacije bez dovoljno slobodnih mjesta dobivaju status ZAHTJEV, a nakon oslobađanja kapaciteta sustav automatski potvrđuje odgovarajuće zahtjeve. | Dogovor s backend timom |
| 11. 11. 2026. | F-022, F-023 | Dodane mogućnosti naknadne izmjene broja sudionika i dodatnih usluga te otkazivanja vlastite rezervacije. Izmjene zahtijevaju ponovno provjeravanje raspoloživog kapaciteta. | Dogovor s timom / povratna informacija asistenta |


## 2.6 Sljedivost zahtjeva

**Cilj:** Osigurati dvosmjernu sljedivost — povezati zahtjeve s opisanim ponašanjem, dijelovima sustava koji ih ostvaruju te ispitivanjem ili drugom provjerom.

| ID zahtjeva | Obrazac uporabe / modul                    | Arhitekturna realizacija                                                 | Ispitivanje ili provjera                            |
| ----------- | ------------------------------------------ | ------------------------------------------------------------------------ | --------------------------------------------------- |
| F-001       | UC-001                                     | Modul Autentikacija (`SecurityConfig`, `AuthController`)                 | Na čekanju: ispitni slučajevi još nisu definirani   |
| F-002       | UC-002                                     | Web-klijent; Modul Izleti (`IzletController`)                            | Na čekanju                                          |
| F-003       | UC-002                                     | Web-klijent; Modul Izleti                                                | Na čekanju                                          |
| F-004       | UC-003                                     | Modul Rezervacije (`RezervacijaController`, `RezervacijaService`)        | Na čekanju                                          |
| F-005       | UC-003 (6a)                                | Modul Rezervacije: provjera slobodnih mjesta na poslužitelju             | Na čekanju                                          |
| F-006       | UC-004                                     | Modul Rezervacije                                                        | Na čekanju                                          |
| F-007       | UC-012 (uključen u UC-003)                 | Modul Obavijesti; vanjska usluga e-pošte                                 | Na čekanju                                          |                                                                
| F-008       | UC-005                                     | Web-klijent (karta, lokacija preglednika); Modul Izleti (točke interesa) | Na čekanju                                          |
| F-009       | UC-006                                     | Modul Izleti (`IzletController`, `IzletService`)                         | Na čekanju                                          |
| F-010       | UC-007                                     | Modul Izleti (`TerminService`, `DodatneUslugeService`)                   | Na čekanju                                          |
| F-011       | UC-008                                     | Modul Rezervacije                                                        | Na čekanju                                          |
| F-012       | UC-006 (3c)                                | Modul Izleti; Modul Autentikacija (provjera vlasništva izleta)           | Na čekanju                                          |
| F-013       | UC-009                                     | Modul Resursi                                                            | Na čekanju                                          |
| F-014       | UC-009                                     | Modul Resursi (`ResursController`, `ResursService`)                      | Na čekanju                                          |
| F-015       | UC-010                                     | Modul Izleti (točke interesa)                                            | Na čekanju                                          |
| F-016       | UC-011                                     | Modul Statistika                                                         | Na čekanju                                          |
| F-017       | UC-013 (proširuje UC-011)                  | Modul Statistika                                                         | Na čekanju                                          |
| NF-01       | Svi obrasci uporabe s prijavljenim akterom | Modul Autentikacija (provjera uloge za svaki zaštićeni zahtjev)          | Na čekanju                                          |
| NF-02       | UC-003 (6a)                                | Modul Rezervacije: provjera kapaciteta unutar transakcije                | Na čekanju                                          |
| NF-03       | — (svojstvo cijelog sučelja)               | Web-klijent                                                              | Na čekanju: pregled na računalu, tabletu i mobitelu |
| NF-04       | —                                          | Platforma za postavljanje (poglavlje 6)                                  | Na čekanju: provjera javne adrese                   |

<details>
<summary><strong>Sljedivost zahtjeva do obrazaca uporabe i ispitivanja</strong></summary>

**Primjer — poveznice zahtjeva CrisisMapa:**

| ID zahtjeva | Obrazac uporabe / ponašanje                | Arhitekturna realizacija                       | Ispitivanje / provjera                                                                                     |
| ----------- | ------------------------------------------ | ---------------------------------------------- | ---------------------------------------------------------------------------------------------------------- |
| F-004       | UC-002 Podnošenje prijave kriznog događaja | Web-klijent; API za prijave; usluga za prijave | ST-01 Valjano podnošenje prijave; ST-02 Nedostaje obavezna lokacija; ST-03 Dopuštenje geolokacije odbijeno |
| F-005       | Odabir primatelja obavijesti               | Proces za obavijesti                           | CT-06 Primatelji obavijesti; potpuna dostava obavijesti nije pokrivena ovom provjerom                      |

**Pokrivenost i dokazi:** CT-06 provjerava odabir primatelja za F-005, ali sam po sebi ne dokazuje da je obavijest stvarno dostavljena; ako projekt zahtijeva potpunu dostavu od početka do kraja, za taj dio treba postojati zasebna provjera. Svaki redak pokazuje gdje je zahtjev opisan kao ponašanje, gdje je ostvaren u sustavu i gdje se nalazi dokaz njegove provjere. Zahtjev ne mora imati zaseban obrazac uporabe ako ne opisuje korisničku interakciju; tada navedite relevantno ponašanje, arhitekturni element ili drugi prikladan način realizacije. U svojem projektu koristite stvarne nazive komponenata, modula i provjera iz dokumentacije i implementacije. Nedostajuću poveznicu ostavite praznom ili označite kao _na čekanju_ uz kratak razlog. Stvarni rezultati ispitivanja bilježe se uz ispitne slučajeve; ova tablica pokazuje gdje ih pronaći.

</details>

---

## 2.7 Pregled zahtjeva

**Cilj:** Zabilježiti važne povratne informacije o zahtjevima i odluke koje iz njih proizlaze na dvije formalne kontrolne točke projekta, u 7. i 14. tjednu.

| Kontrolna točka | Povratna informacija ili nalaz            | Odluka o zahtjevu ili opsegu |
| --------------- | ----------------------------------------- | ---------------------------- |
| Iteracija 1     | Na čekanju (kontrolna točka u 7. tjednu)  | —                            |
| Demo 2          | Na čekanju                                | —                            |
| Završni pregled | Na čekanju (kontrolna točka u 14. tjednu) | —                            |

<details>
<summary><strong>Odluke o zahtjevima nakon pregleda</strong></summary>

**Primjer scenarija — pregled zahtjeva CrisisMapa:**

| Kontrolna točka | Povratna informacija ili nalaz                                                                    | Odluka o zahtjevu ili opsegu                                                                            |
| --------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- |
| Iteracija 1     | Demonstracija pokazuje da odbijanje pristupa lokaciji preglednika onemogućuje podnošenje prijave. | Dogovoriti ručni odabir lokacije; ažurirati F-004 i primjer NF-01 te razlog zabilježiti u odjeljku 2.5. |

**Odluka povezana s povratnom informacijom:** Zapis povezuje opaženo ponašanje s konkretnom promjenom zahtjeva i izbjegava zapisnik cijelog sastanka. Zapišite samo stvarne povratne informacije i odluke za kontrolne točke. Konzultacije u nekom drugom tjednu ne stvaraju dodatnu obaveznu predaju.

</details>

---

> **Završna provjera:** Zahtjevi su aktualni, provjerljivi i povezani. Svaki važan zahtjev moguće je pratiti od njegova opisa i ponašanja preko mjesta realizacije u sustavu do odgovarajućeg ispitivanja ili druge provjere. Poveznice koriste stvarne identifikatore i nazive iz projektne dokumentacije.
