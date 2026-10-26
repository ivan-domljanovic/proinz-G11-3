# 2. Analiza zahtjeva

**Cilj:** Iz odobrenog opsega projekta izvesti prepoznatljive, prioritetizirane i provjerljive zahtjeve. Zahtjevi se mogu mijenjati; pri završnoj predaji moraju odražavati dogovorenu aplikaciju. Izvori, odluke i poveznice u primjerima kolegija nastavni su scenariji, a ne projektni zapisi.

<details>
<summary><strong>Održavanje zahtjeva ažurnima</strong></summary>

Primjeri koriste temu projekta CrisisMap. Neki namjerno sadrže korisne nedostatke za raspravu. Izvori, odluke i poveznice na provjere u tim primjerima **nastavni su scenariji**, a ne zapisi rada stvarnog studentskog tima. Zamijenite ih zahtjevima i dokazima svojeg odobrenog projekta. Značajne promjene zabilježite u odjeljku 2.5.

</details>

## 2.1 Funkcijski zahtjevi

**Cilj:** Navesti što aplikacija mora omogućiti korisnicima, odakle pojedina mogućnost proizlazi i koji se opažljivi rezultat smatra prihvaćanjem.

| ID    | Opis                                                                                                                                                                                                                                                           | Prioritet | Izvor             | Kriteriji prihvaćanja                                                                                                                         |
| ----- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------- | ----------------- | --------------------------------------------------------------------------------------------------------------------------------------------- |
| F-001 | Korisnik se prijavljuje putem Googlea (OpenID Connect / OAuth 2.0).                                                                                                                                                                                            | Visok     | Projektni zadatak | Nakon uspješne prijave korisnik vidi svoje ime; odustajanje od prijave ostavlja radnje za prijavljene korisnike nedostupnima.                 |
| F-002 | Korisnik pregledava izlete (naziv, opis, kategorija, trajanje, lokacija) i njihove termine (datum i vrijeme, mjesto polaska, cijena, slobodna mjesta).                                                                                                         | Visok     | Projektni zadatak | Za svaki izlet prikazani su svi navedeni podaci i svi njegovi termini s cijenom i brojem slobodnih mjesta.                                    |
| F-003 | Korisnik pretražuje izlete prema terminu, lokaciji, vrsti aktivnosti, trajanju, cijeni i broju sudionika.                                                                                                                                                      | Visok     | Projektni zadatak | Rezultati odgovaraju zadanim kriterijima; ako ih nema, prikazuje se poruka.                                                                   |
| F-004 | Korisnik rezervira termin izleta za jednu ili više osoba i bira dodatne usluge (prijevoz, najam opreme).                                                                                                                                                       | Visok     | Projektni zadatak | Rezervacija za odabrani termin, broj osoba i usluge se sprema i vidljiva je korisniku.                                                        |
| F-005 | Sustav onemogućava rezervaciju koja premašuje broj slobodnih mjesta termina.                                                                                                                                                                                   | Visok     | Projektni zadatak | Rezervacija za više osoba nego što ima slobodnih mjesta odbija se uz poruku; za popunjen termin nije moguća.                                  |
| F-006 | Korisnik prati status rezervacije (zahtjev, potvrđena, otkazana, realizirana) i pregledava njezine podatke.                                                                                                                                                    | Srednji   | Projektni zadatak | Korisnik vidi svoje rezervacije sa statusom; za potvrđenu vidi izlet, termin, broj osoba, usluge i cijenu.                                    |
| F-007 | Sustav šalje potvrdu rezervacije e-poštom.                                                                                                                                                                                                                     | Srednji   | Projektni zadatak | Nakon potvrde rezervacije na e-adresu korisnika stiže poruka s podacima rezervacije.                                                          |
| F-008 | Korisnik tijekom izleta na mobitelu vidi svoj položaj i točke interesa na karti te informacije o točki kad joj se približi.                                                                                                                                    | Srednji   | Projektni zadatak | Karta prikazuje položaj korisnika i točke interesa; u blizini točke prikazuju se naziv, opis, značaj i zanimljivosti.                         |
| F-009 | Administrator dodaje, mijenja i briše izlete (naziv, opis, kategorija, trajanje, lokacija, mjesto polaska, cijena, maksimalan broj sudionika, potreban broj vodiča). Mjesto polaska, cijena, kapacitet i broj vodiča zadaju se za svaki termin izleta (F-010). | Visok     | Projektni zadatak | Administrator može unijeti novi izlet sa svim podacima te izmijeniti ili obrisati postojeći; promjena je vidljiva korisnicima.                |
| F-010 | Administrator za izlet definira termine (datum i vrijeme, mjesto polaska, cijena, kapacitet, potreban broj vodiča) i dodatne usluge.                                                                                                                           | Visok     | Projektni zadatak | Za izlet se može zadati jedan ili više termina sa svim navedenim podacima te popis dodatnih usluga koje korisnik vidi pri rezervaciji.        |
| F-011 | Administrator upravlja rezervacijama.                                                                                                                                                                                                                          | Srednji   | Projektni zadatak | Administrator vidi sve rezervacije i može promijeniti njihov status; korisnik vidi novi status.                                               |
| F-012 | Organizator objavljuje, mijenja i briše vlastite ponude izleta.                                                                                                                                                                                                | Srednji   | Projektni zadatak | Organizator vidi samo svoje izlete i može ih objaviti, izmijeniti i obrisati; tuđe izlete ne može mijenjati.                                  |
| F-013 | Organizator dodjeljuje vodiče terminima izleta.                                                                                                                                                                                                                | Srednji   | Projektni zadatak | Organizator za termin odabire vodiče do potrebnog broja; dodijeljeni vodiči vidljivi su uz termin.                                            |
| F-014 | Organizator vodi evidenciju resursa (prijevoz, oprema) i njihove zauzetosti po terminima.                                                                                                                                                                      | Srednji   | Projektni zadatak | Za svaki resurs vidljiva je ukupna količina i koliko je zauzeto u pojedinom terminu; terminu se ne može dodijeliti više od slobodne količine. |
| F-015 | Administrator i organizator definiraju točke interesa izleta (naziv, kratak opis, značaj, zanimljivosti, lokacija).                                                                                                                                            | Srednji   | Projektni zadatak | Za izlet se može unijeti točka interesa s koordinatama i opisom; prikazuje se korisniku na karti tijekom izleta.                              |
| F-016 | Voditelj turističke agencije pregledava statistiku: broj rezervacija, popunjenost izleta, ostvareni prihod te najtraženije aktivnosti i dodatne usluge.                                                                                                        | Srednji   | Projektni zadatak | Prikazani su svi navedeni podaci za izlete agencije.                                                                                          |
| F-017 | Voditelj turističke agencije izvozi odabrane podatke i izvještaje u standardnom formatu (npr. CSV).                                                                                                                                                            | Srednji   | Projektni zadatak | Odabrani podaci mogu se preuzeti kao datoteka koja se otvara u uobičajenim alatima za obradu podataka.                                        |
|       |                                                                                                                                                                                                                                                                |           |                   |                                                                                                                                               |

<details>
<summary><strong>Izvori zahtjeva i kriteriji prihvaćanja</strong></summary>

Svaki zahtjev treba imati stabilan ID. **Izvor označava odakle zahtjev potječe**, primjerice iz odobrenog projektnog zadatka, zahtjeva dionika ili povratne informacije koja je promijenila zahtjev. Opišite opažljivo ponašanje umjesto naziva zaslona ili programskog okvira. U cijeloj dokumentaciji koristite istu ljestvicu prioriteta. Složenu korisničku interakciju objasnite u odgovarajućem obrascu uporabe umjesto kopiranja cijelog tijeka u ovu tablicu.

**Primjer — funkcijski zahtjevi za aplikaciju CrisisMap:**

Kategorije izvora pokazuju različite načine na koje zahtjev može nastati.

| ID    | Opis                                                                             | Prioritet | Izvor                          | Kriteriji prihvaćanja                                                                                                                                                                                |
| ----- | -------------------------------------------------------------------------------- | --------- | ------------------------------ | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| F-001 | Sustav omogućuje korisniku registraciju računa pomoću adrese e-pošte.            | Visok     | Zahtjev dionika                | Korisnik se može registrirati adresom e-pošte, primiti poruku za potvrdu i aktivirati račun slijedeći poveznicu.                                                                                     |
| F-002 | Sustav omogućuje oporavak lozinke putem e-pošte.                                 | Srednji   | Zahtjev dionika                | Korisnik može zatražiti ponovno postavljanje lozinke, primiti poveznicu i uspješno postaviti novu lozinku.                                                                                           |
| F-003 | Sustav prikazuje popis dostupnih resursa povezanih s kriznim događajima.         | Visok     | Postojeći sustav               | Korisnik može pregledati ažurirani popis resursa relevantnih za vrstu kriznog događaja, lokaciju i raspoloživost.                                                                                    |
| F-004 | Sustav omogućuje korisnicima podnošenje prijave kriznog događaja s lokacijom.    | Visok     | Dokument sa zahtjevima         | Korisnik može podnijeti prijavu s vrstom kriznog događaja, razinom ozbiljnosti i lokacijom te dobiti potvrdu o zaprimanju. Ako odbije pristup geolokaciji preglednika, može lokaciju odabrati ručno. |
| F-005 | Sustav šalje korisnicima obavijesti o relevantnim promjenama u njihovoj blizini. | Visok     | Povratna informacija korisnika | Korisnici dobivaju obavijest unutar jedne minute od relevantne promjene za njihovu lokaciju.                                                                                                         |
| F-006 | Sustav prikazuje dostupne resurse prema lokaciji.                                | Visok     | Analiza dokumentacije          | Prikazuje se ažurirani popis resursa prilagođen vrsti kriznog događaja, lokaciji i raspoloživosti.                                                                                                   |
| F-007 | Sustav prikazuje prijave kriznih događaja s lokacijom kao oznake na karti.       | Visok     | Odobreni zadatak               | Prijava s valjanom lokacijom prikazuje se kao oznaka na karti; odabirom oznake korisnik može otvoriti pripadajuću prijavu.                                                                           |
| F-008 | Sustav omogućuje moderatoru odobravanje ili odbijanje podnesenih prijava.        | Visok     | Zahtjev dionika                | Moderator može odobriti ili odbiti prijavu koja čeka pregled; korisnik bez moderatorskih ovlasti ne može izvršiti te radnje.                                                                         |

**Kriteriji prihvaćanja i podrijetlo zahtjeva:** Primjer prikazuje registraciju, oporavak lozinke, resurse, prijavu kriznog događaja i obavijesti te nekoliko vrsta izvora i opažljivih kriterija. Istodobno otvara korisna pitanja: opisuju li F-003 i F-006 istu funkcionalnost resursa? Je li obećanje od jedne minute u F-005 dogovoreno i moguće ispitati? Treba li aplikaciji koja koristi isključivo OAuth uopće F-001 i F-002? Retke koristite kao materijal za analizu; u svojem projektu navedite vlastite zahtjeve. U stvarnom projektu stupac Izvor bilježi odakle su doista potekli njegovi zahtjevi.

**Primjer prilagodbe kada odobreni zadatak koristi OAuth i kartu:** Umjesto kopiranja registracije e-poštom i oporavka lozinke formulirajte relevantni zahtjev za prijavu, primjerice: „F-001: Korisnici se prijavljuju putem odobrenog OAuth pružatelja identiteta; uspješna prijava omogućuje radnje rezervirane za prijavljene korisnike, a odustajanje od prijave ostavlja te radnje nedostupnima.” Zaseban zahtjev za kartu može glasiti: „F-007 : Prijave s lokacijom prikazuju se kao oznake na karti; odabirom oznake otvara se pripadajuća prijava.” Izvor treba biti stvarni odobreni zadatak ili dogovoreni zahtjev dionika. Ova je prilagodba zamjena za neodgovarajuće retke, a ne dodatna tablica koju svaki tim mora ispuniti.

Za složenu interakciju kratak scenarij _Given / When / Then_ može pojasniti kriterij. Primjerice: _Given_ prijava bez lokacije, _when_ građanin je podnese, _then_ sustav objašnjava da lokacija nedostaje i ne pohranjuje prijavu. Nemojte jednostavan kriterij ponavljati i u prozi i u Gherkinu niti pisati Gherkin za svaki zahtjev.

</details>

## 2.2 Nefunkcijski zahtjevi

**Cilj:** Odrediti važna svojstva kvalitete i uvjete rada odobrene aplikacije na način koji tim može razumno provjeriti.

| ID    | Opis                                                                                                                   | Prioritet | Izvor             | Način provjere                                                                            |
| ----- | ---------------------------------------------------------------------------------------------------------------------- | --------- | ----------------- | ----------------------------------------------------------------------------------------- |
| NF-01 | Sustav razlikuje uloge (korisnik, organizator, administrator, voditelj agencije) i svakoj dopušta samo njezine radnje. | Visok     | Projektni zadatak | Testni scenarij: korisnik ne može izvršiti administratorske radnje.                       |
| NF-02 | Sustav podržava istodobni rad više korisnika.                                                                          | Visok     | Projektni zadatak | Testni scenarij: dvije istodobne rezervacije zadnjeg slobodnog mjesta, uspije samo jedna. |
| NF-03 | Sučelje je responzivno (računalo, tablet, mobitel).                                                                    | Visok     | Projektni zadatak | Pregled sučelja na sva tri tipa uređaja.                                                  |
| NF-04 | Aplikacija je javno dostupna na internetu.                                                                             | Visok     | Projektni zadatak | Pregled: aplikacija se otvara na javnoj adresi izvan lokalne mreže.                       |
|       |                                                                                                                        |           |                   |                                                                                           |

<details>
<summary><strong>Nefunkcijski zahtjevi za analizu</strong></summary>

Relevantna svojstva mogu se odnositi na uporabljivost, pristupačnost, sigurnost, performanse ili pouzdanost. Odaberite svojstva specifična za projekt i opišite kako ih je moguće provjeriti. Zahtjev navodi što se očekuje; stvarna opažanja i dokazi pripadaju rezultatima ispitivanja, a ne drugom stupcu s rezultatima u ovoj tablici.

**Primjer — nefunkcijski zahtjevi za aplikaciju CrisisMap:**

| ID         | Opis                                                                                            | Prioritet | Izvor                        |
| ---------- | ----------------------------------------------------------------------------------------------- | --------- | ---------------------------- | ----------------------------------------------------------------------------------------- |
| NF-1.1     | Sustav mora podržati 1.000 istodobnih korisnika uz vrijeme odziva kraće od 1 s.                 | Visok     | SLA                          | Opteretiti sustav zadanim brojem istodobnih korisnika i izmjeriti vrijeme odziva.         |
| NF-1.2     | Svi osjetljivi podaci moraju biti šifrirani algoritmom AES-256.                                 | Visok     | Sigurnosna politika          | Pregledati način pohrane i konfiguraciju šifriranja za podatke na koje se zahtjev odnosi. |
| NF-1.3     | Korisničko sučelje mora podržavati engleski i španjolski jezik.                                 | Srednji   | Povratna informacija dionika | Pregledati reprezentativne korisničke tijekove u oba jezika.                              |
| NF-1.4     | Dostupnost sustava mora iznositi najmanje 99,5 % mjesečno.                                      | Visok     | SLA                          | Izračunati mjesečnu dostupnost iz zapisa sustava za nadzor, ako takvi podaci postoje.     |
| NF-3.1.7   | Sustav treba imati odgovarajuću dokumentaciju.                                                  | Visok     | Pravila dokumentiranja       | Pregledati zahtijevane dokumentaciju prema obavezama.                                     |
| NF-3.1.7.1 | Kod sustava treba biti dokumentiran prema „Code Conventions for the Java Programming Language”. | Visok     | Pravila dokumentiranja       | Pregledati kod.                                                                           |
| NF-3.1.7.2 | Sustav treba biti opisan projektnim dokumentom/SRS-om.                                          | Visok     | Pravila dokumentiranja       | Pregledati strukturu dokumenta                                                            |
| NF-3.1.7.3 | Sustav treba imati „Operativni priručnik” koji opisuje pravilnu uporabu.                        | Visok     | Pravila dokumentiranja       | Pregledati postoji li priručnik i mogu li se prema njemu izvršiti opisane radnje.         |
| NF-3.1.7.4 | Sustav treba imati „Plan implementacije” za pravilno postavljanje.                              | Visok     | Smjernice za postavljanje    | Pregledati odgovara li opisani postupak stvarnom postupku postavljanja.                   |

**Provjerljivost i opseg:** Primjer uvodi performanse, sigurnost, lokalizaciju, dostupnost i dokumentaciju, uključujući hijerarhijski ID zahtjeva. Navedeni izvori primjeri su podrijetla zahtjeva, a ne dokaz da za studentski projekt stvarno postoji SLA ili sigurnosna politika. Vrijednosti poput 1.000 korisnika, 99,5 % mjesečne dostupnosti i AES-256 moraju imati odobren izvor i izvediv način provjere prije nego što postanu zahtjevi tima. Skup zahtjeva o dokumentaciji ilustrira razlaganje zahtjeva, ali zahtjevi za izradu _ove dokumentacije kolegija_ u pravilu pripadaju uputama zadatka, a ne zahtjevima kvalitete web-aplikacije.

**Primjer prilagodbe:** Ako je pristupačnost zahtjevana za projekt, projektno specifičan zahtjev može glasiti: „NF-01: Sve obavezne radnje u obrascu za podnošenje prijave moguće je izvršiti samo tipkovnicom, uz vidljiv fokus na aktivnoj kontroli.” Provjera: navigirati kroz obrazac bez pokazivačkog uređaja, odabrati ili unijeti lokaciju, poslati prijavu i provjeriti može li se pročitati rezultat. To je provjerljivo svojstvo kvalitete, a ne dodatna funkcionalnost koju svaki projekt mora implementirati.

</details>

## 2.3 Dionici i akteri

**Cilj:** Utvrditi uloge koje utječu na zahtjeve i razlikovati dionike od aktera koji komuniciraju s aplikacijom.

| ID uloge ili aktera                  | Odnos prema sustavu                                                                                             | Relevantni zahtjevi   |
| ------------------------------------ | --------------------------------------------------------------------------------------------------------------- | --------------------- |
| A-01 Korisnik (uključuje neprijavljenog posjetitelja) | Neprijavljen pregledava i pretražuje izlete; prijavljen ih rezervira i koristi aplikaciju kao vodič tijekom izleta. | F-001 do F-008 |
| A-02 Organizator                     | Objavljuje i uređuje vlastite izlete, dodjeljuje vodiče, vodi evidenciju resursa i definira točke interesa.     | F-012 do F-015        |
| A-03 Administrator                   | Upravlja svim izletima, terminima, cijenama, kapacitetima, dodatnim uslugama, rezervacijama i točkama interesa. | F-009 do F-011, F-015 |
| A-04 Voditelj turističke agencije    | Pregledava statistiku poslovanja i izvozi izvještaje.                                                           | F-016, F-017          |
| A-05 Pružatelj identiteta (Google)   | Vanjski sustav koji provodi prijavu korisnika.                                                                  | F-001                 |
| A-06 Vanjske usluge (karta, e-pošta) | Vanjski sustavi za prikaz karte i slanje potvrda.                                                               | F-007, F-008          |
|                                      |                                                                                                                 |                       |

<details>
<summary><strong>Akteri i povezani zahtjevi</strong></summary>

**Primjer — akteri aplikacije CrisisMap:**

| ID aktera | Uloga                 | Obuhvaćeni funkcijski zahtjevi                                      |
| --------- | --------------------- | ------------------------------------------------------------------- |
| A-01      | Koordinator pomoći    | F-003: Pregled resursa; F-004: Podnošenje prijave kriznog događaja. |
| A-02      | Građanin              | F-001: Izrada računa; F-005: Primanje obavijesti.                   |
| A-03      | Administrator sustava | NF-1.2: Provođenje sigurnosnih pravila; upravljanje korisnicima.    |
| A-04      | Posjetitelj           | F-007: Pregled prijava na karti.                                    |
| A-05      | Moderator             | F-008: Odobravanje ili odbijanje prijava.                           |

**Odgovornosti aktera:** Uloga aktera treba biti povezana s interakcijom, a ne samo s nazivom. Provjerite podnosi li koordinator pomoći u odobrenom zadatku doista prijave kriznih događaja. Poveznica administratora s NF-1.2 navodi sigurnosni aspekt, ali sama po sebi ne opisuje obrazac uporabe; navedite stvarnu radnju administratora ili izostavite ulogu ako je nema. Nastavnici zainteresirani za rezultat mogu biti dionici, a da nisu akteri aplikacije. Tablica je polazište za razmišljanje, a ne potvrđen opis ovlasti u CrisisMapu.

</details>

---

## 2.4 Ograničenja i pretpostavke

**Cilj:** Zabilježiti projektno specifične granice i pretpostavke koje utječu na zahtjeve ili oblikovanje, zajedno s njihovim izvorom i praktičnom posljedicom. Ovo je njihovo mjerodavno mjesto u dokumentaciji.

| ID    | Tip          | Opis ograničenja ili pretpostavke                                                                                                   | Izvor             | Inženjerska posljedica / Provjera                                                                                                                                               |
| ----- | ------------ | ----------------------------------------------------------------------------------------------------------------------------------- | ----------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| C-01  | Ograničenje  | Prijava mora ići putem vanjskog pružatelja identiteta (OpenID Connect / OAuth 2.0).                                                 | Projektni zadatak | Autentifikaciju oblikovati oko odabranog pružatelja; provjeriti prijavljene i neprijavljene zahtjeve.                                                                           |
| AS-01 | Pretpostavka | Neprijavljen korisnik smije pregledavati i pretraživati izlete; prijava se traži tek za rezervaciju.                                | Procjena tima     | Pregled i pretraga izleta dostupni su bez prijave, a rezervacija i sve ostale radnje traže prijavu. Provjera: neprijavljen korisnik vidi popis izleta, ali ne može rezervirati. |
| AS-02 | Pretpostavka | Korisnik smije sam otkazati vlastitu rezervaciju.                                                                                   | Procjena tima     | Pri otkazivanju se mjesta vraćaju u termin (UC-004). Provjera: nakon otkazivanja broj slobodnih mjesta termina poveća se za broj osoba iz rezervacije.                          |
| AS-03 | Pretpostavka | Rezervacija iz statusa „zahtjev” u „potvrđena” prelazi automatski, bez odobrenja organizatora ili administratora.                   | Procjena tima     | Potvrditi, inače dodati zahtjev za potvrđivanje rezervacije.                                                                                                                    |
| AS-04 | Pretpostavka | Dovoljna je trenutačna GPS lokacija korisnika u pregledniku, bez praćenja lokacije na poslužitelju.                                 | Procjena tima     | Lokacija se ne šalje na poslužitelj niti sprema u bazu, što pojednostavljuje zaštitu osobnih podataka (GDPR). Provjera: u bazi ne postoje podaci o lokaciji korisnika.          |
| AS-05 | Pretpostavka | Vodič je podatak uz izlet (dodjeljuje ga organizator), a ne zasebna korisnička uloga s vlastitim funkcijama.                        | Procjena tima     | Potvrditi, ako treba, dodati ulogu i zahtjeve za vodiča.                                                                                                                        |
| AS-06 | Pretpostavka | Prijava je putem Googlea, a ulogu organizatora, administratora ili voditelja agencije korisniku dodjeljuje administrator u sustavu. | Procjena tima     | Pri prvoj prijavi korisnik automatski dobiva ulogu korisnika; ostale uloge dodaje administrator. Jedan korisnik može imati više uloga.                                          |

<details>
<summary><strong>Ograničenja nasuprot pretpostavkama</strong></summary>

Ograničenje je uvjet koji tim mora poštovati; pretpostavka je uvjet na koji se tim oslanja i koji treba ponovno provjeravati.

**Primjer — projektni kontekst CrisisMapa:**

| ID    | Ograničenje ili pretpostavka                                                                 | Izvor                   | Posljedica ili način provjere                                                                                             |
| ----- | -------------------------------------------------------------------------------------------- | ----------------------- | ------------------------------------------------------------------------------------------------------------------------- |
| C-01  | Odobreni zadatak zahtijeva OAuth prijavu u sustav za uređivanje vlastitih prijava korisnika. | Odobreni zadatak        | Zaštićeno uređivanje projektirati oko odabranog pružatelja identiteta; provjeriti autorizirane i neautorizirane zahtjeve. |
| AS-01 | Prijave koje se prikazuju na karti imaju uporabive koordinate.                               | Pretpostavka o podacima | Pregledati ogledne i uvezene prijave; planirati prikaz prijava bez valjanih koordinata.                                   |

**Ograničenje nasuprot pretpostavci:** C-01 navodi obvezujuću odluku i njezin arhitekturni učinak; AS-01 je moguće provjeriti i može se pokazati netočnim. Nemojte kopirati C-01 ako OAuth nije dio stvarnog odobrenog zadatka. Projektni odgovor objasnite u arhitekturi bez prepisivanja ove tablice.

</details>

---

## 2.5 Značajne promjene zahtjeva

**Cilj:** Sačuvati razlog promjena koje mijenjaju dogovoreni opseg, prioritet ili kriterije prihvaćanja, uz istodobno održavanje prethodnih zahtjeva ažurnima.

| Datum         | ID zahtjeva         | Promjena i razlog                                                                                                                                                                                              | Dogovoreno s / dokaz                                         |
| ------------- | ------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------ |
| 25. 10. 2026. | F-002, F-009, F-010 | Mjesto polaska, cijena, kapacitet i potreban broj vodiča zadaju se za svaki termin izleta, a ne jednom za cijeli izlet, jer isti izlet može imati termine s različitom cijenom, kapacitetom i mjestom polaska. | Backend tim; model `Termin` u repozitoriju                   |
| 25. 10. 2026. | F-013, F-014        | Vodiči se dodjeljuju pojedinom terminu izleta. Resursi (prijevoz, oprema) vode se kao količina koja se dodjeljuje terminu (npr. 2 od 3 kombija), a ne samo kao slobodan ili zauzet.                            | Backend tim; modeli `Termin` i `TerminResurs` u repozitoriju |

<details>
<summary><strong>Primjer značajne promjene</strong></summary>

**Primjer scenarija — odluka tijekom pregleda CrisisMapa:**

| Datum            | ID zahtjeva  | Promjena i razlog                                                                                                                                    | Dogovoreno s / dokaz                                      |
| ---------------- | ------------ | ---------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------- |
| [Datum pregleda] | F-004, NF-01 | Dodati ručni odabir lokacije kada korisnik odbije geolokaciju preglednika; bez toga građanin ne može podnijeti prijavu kriznog događaja s lokacijom. | [Poveznica na stvarnu bilješku pregleda ili zadatak tima] |

**Razlog promjene:** Zapis navodi zahvaćene stavke i objašnjava razlog iz perspektive korisnika, a zatim upućuje na dokaz odluke. Nakon stvarne odluke ažurirajte trenutačne zahtjeve; stilska izmjena teksta ne zahtijeva zapis u dnevniku promjena. Trenutačni red zadataka držite u alatu za praćenje zadataka.

</details>

---

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
