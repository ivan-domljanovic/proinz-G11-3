<!--
PREDLOŽAK — README završnog projekta na kolegiju Programsko inženjerstvo FER-a.
Kopirajte ovu datoteku u README.md u repozitoriju svojeg projekta. Zamijenite
svaku uputu u uglatim zagradama činjenicama o svojem projektu, uklonite sve
komentare s uputama i ogledne <details> blokove te prije predaje izbrišite ovu
napomenu. Nemojte kopirati nastavni primjer kao rezultat svojeg projekta.
-->

# [Waypoint]

Waypoint je aplikacija namijenjena za pretraživanje, rezervaciju i organizaciju turističkih aktivnosti i izleta.

**Projekt na kolegiju:** Ovu je aplikaciju razvio studentski tim u sklopu kolegija [Programsko inženjerstvo](https://www.fer.unizg.hr/predmet/proinz) na Fakultetu elektrotehnike i računarstva Sveučilišta u Zagrebu (FER).

## Opis aplikacije

Aplikacija je namijenjena organizatorima turističkih aktivnosti i izleta te njihovim korisnicima. Omogućuje jednostavnu organizaciju upravljanjem izleta, vodiča i ostalih resursa. Korisnicima pojednostavljuje pregled dostupnih aktivnosti i rezervaciju termina.

**Ključne funkcionalnosti**

- Pregled dostupnih aktivnosti
- ...

Za potpuni opseg, zahtjeve, oblikovanje i dokaze ispitivanja pogledajte [docs](docs/Home.md).

<a id="deployment"></a>

- Pregled i pretraživanje izleta prema terminu, lokaciji, vrsti aktivnosti, trajanju, cijeni i broju sudionika
- Rezervacija termina za više osoba s dodatnim uslugama (prijevoz, najam opreme) i potvrda e-poštom
- Automatska kontrola kapaciteta: termin se ne može prebukirati
- Upravljanje izletima, terminima, vodičima i resursima za administratore i organizatore
- Statistika poslovanja i izvoz u CSV za voditelja agencije
- Digitalni vodič na mobitelu koji prema GPS lokaciji prikazuje informacije o mjestima u blizini

## Postavljanje (engl. deployment)

<!-- UPUTA: Ovaj odjeljak opisuje već postavljenu aplikaciju koju se  može otvoriti i isprobati bez lokalnog pokretanja izvornog koda.
Za Iteraciju 1 i Završni pregled navedite konkretne podatke. Provjerite vodi li URL na javno dostupnu pokrenutu aplikaciju i radi li navedeni tijek uporabe. Nikada ne objavljujte lozinke ni pristupne tokene. -->

**Javno dostupna aplikacija:** `...`

<!--
**Isprobajte:** [Jedna ili dvije kratke radnje koje pokazuju funkcionalan korisnički tijek.]

**Demonstracija:** [Objasnite kako dobiti potreban ograničeni pristup ako je prijava obavezna; u suprotnom napišite „Korisnički račun nije potreban.” Ovdje ne objavljujte lozinke ni pristupne tokene.]

**Ograničenja:** [Kratko navedite ograničenja postavljene inačice ili postavite poveznicu na jedinstveno mjerodavno mjesto u dokumentaciji s poznatim problemima.]

[Vodič za postavljanje i pokretanje](docs/6-Postavljanje-instalacija-i-konfiguracija.md) opisuje smještaj aplikacije (engl. hosting) koji tim stvarno koristi. Tim može koristiti Render ili drugu platformu koju ste dogovorili s asistentom/demonstratorom.
-->

<a id="quick-start"></a>

## Brzi početak / instalacija

<!-- Obavezno od Demo 1 nadalje. Zamijenite mjesta za unos naredbama koje je drugi član tima uspješno provjerio nakon čistog kloniranja repozitorija. Detaljne upute za administraciju držite u projektnoj dokumentaciji u direktoriju docs/. -->

**Preduvjeti:**

- Java JDK 25, ....
- Node.js, Vite v[...], React v19
- PostgreSQL

```bash
git clone https://github.com/ivan-domljanovic/proinz-G11-3
cd proinz-G11-3/backend
./mvnw spring-boot:run

# (novi terminal)
cd frontend
npm install
npm run
```

**Adresa:** `http://localhost:5173`

**Konfiguracija:** `.env.example`

<!--
[Navedite potrebne varijable okruženja (engl. environment variables) ili uputite na sigurnu datoteku `.env.example`; objasnite gdje se dobivaju vrijednosti. Nikada ne stavljajte stvarne pristupne podatke u ovu datoteku, primjer konfiguracije, snimke zaslona, zapise ni zapise promjena (engl. commits).]

[Dodajte kratak korak provjere, primjerice koja se stranica treba otvoriti ili koja naredba pokreće osnovnu provjeru.]
-->

Za detaljno postavljanje pogledajte [projektnu dokumentaciju](docs/Home.md).

## Tehnologije

<!--Navedite nekoliko tehnologija i vanjskih usluga koje bitno određuju kako vaša aplikacija radi. Za svaku navedite njezinu konkretnu ulogu i jedan razlog ili ograničenje važno za projekt. Alat za razvoj ili ispitivanje uključite samo ako je njegova uloga važna za razumijevanje načina na koji je projekt izrađen ili provjeren. Nemojte prepisivati popis ovisnosti (engl. dependencies), statistiku programskih jezika ni popis razvojnih okruženja i komunikacijskih alata. Tablicu provjerite prema stvarnom repozitoriju i pokrenutoj aplikaciji; potrebne verzije i konfiguraciju dokumentirajte u vodiču za instalaciju.-->

| Tehnologija ili alat | Uloga u ovom projektu | Razlog uporabe ili važno ograničenje |
| --- | --- | --- |
| React | Prikazuje korisničko sučelje za turiste, organizatore i administratore u pregledniku. | Jedan responzivan klijent radi na računalu, tabletu i mobitelu, pa nije potrebna zasebna mobilna aplikacija. |
| Vite | Pokreće frontend tijekom razvoja i prosljeđuje zahtjeve prema `/api` na backend. | Brzo osvježavanje pri razvoju; u produkciji frontend i backend moraju biti dostupni na javnim adresama. |
| Spring Boot | Obrađuje zahtjeve klijenta, provodi poslovna pravila (npr. provjeru slobodnih mjesta) i upravlja podacima. | Jedno središnje mjesto za pravila rezervacije i autorizaciju; kvar poslužitelja zaustavlja sve funkcije. |
| PostgreSQL | Trajno pohranjuje korisnike, izlete, termine, rezervacije i resurse. | Podaci su usko povezani (izlet – termin – rezervacija). |
| Google OAuth 2.0 / OpenID Connect | Prijava korisnika Google računom. | Aplikacija ne pohranjuje lozinke; korisnik bez Google računa ne može se prijaviti. |

<!--
<details>
<summary><strong>Primjer: objašnjenje ključnih tehnoloških odluka</strong></summary>

**Primjer — CrisisMap:**

| Tehnologija ili alat | Uloga u ovom projektu | Razlog uporabe ili važno ograničenje |
| --- | --- | --- |
| React | Prikazuje obrazac za prijavu i kartu kriznih događaja u pregledniku. | Karta i poslane prijave mogu se ažurirati bez ponovnog učitavanja cijele stranice. |
| PostgreSQL | Pohranjuje prijave, lokacije i njihove odnose. | Zapisi o prijavama moraju ostati povezani s autorima i lokacijama; promjene sheme zahtijevaju migracije baze podataka (engl. database migrations). |
| Usluga kartografskih pločica (engl. map tile service) | Omogućuje prikaz karte u pregledniku. | Karta ovisi o vanjskoj usluzi; aplikacija mora imati definirano ponašanje i kada kartografske pločice nisu dostupne. |


**Analiza primjera:** Svaki redak opisuje ulogu ili posljedicu konkretne tehnologije u ovoj aplikaciji. Verzije paketa pripadaju datotekama s ovisnostima projekta; verzije izvršnog okruženja i konfiguracija važna za postavljanje pripadaju vodiču za instalaciju. Važne kompromise u oblikovanju (engl. design trade-offs) objasnite na stranici arhitekture u direktoriju `docs/` umjesto da ovdje ponavljate cijeli zapis odluke. Navodite samo usluge koje vaš tim stvarno integrira.

</details>
-->

<a id="team-members"></a>

## Članovi tima

| Član     | Glavna uloga ili doprinos                 |
| -------- | ----------------------------------------- |
| Ivan     | voditelj                                  |
| Anita    | dokumentacija |
| Deni     | frontend                                  |
| Lucija   | frontend                                  |
| Mate     | frontend                                  |
| Matej    | backend                                   |
| Tomislav | backend                                   |

Uloge se tijekom projekta mogu mijenjati. Repozitorij i dogovoreni alat za praćenje zadataka (engl. task tracker) služe kao dodatni dokazi timskog rada.

## Doprinos projektu

<!-- [Ako je primjenjivo, ukratko opišite kako se evidentiraju zadaci i nedostaci te kako se pregledavaju promjene koda i gdje se nalazi aktualna evidencija zadataka. Ako tim održava `CONTRIBUTING.md`, ovdje postavite poveznicu umjesto ponavljanja pravila. Nemojte stvarati tu datoteku samo zato da biste popunili ovaj odjeljak.] -->

<a id="license"></a>

**Status licence:** <!--[Navedite licencu i postavite poveznicu na projektnu datoteku `LICENSE` ili izričito navedite da nije dodijeljena licenca koja dopušta javnu ponovnu uporabu. Namjeravani opseg provjerite s timom; nemojte prepisivati licencu predloška ove dokumentacije kao licencu svoje aplikacije.]-->

Dokumentacijski predložak kolegija sadrži otvorene obrazovne sadržaje (engl. Open Educational Resources, OER) i licenciran je licencom Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International. Ta licenca **ne određuje automatski licencu studentske aplikacije**; tim treba jasno navesti licencu svojeg projekta u prethodnom retku i datoteci `LICENSE`, ako je primjenjivo.

Kod trećih strana, skupovi podataka, slike, ikone i drugi sadržaji zadržavaju vlastite licence i uvjete uporabe. [Po potrebi postavite poveznicu na zahvale i izvore.]

<a id="ai-usage"></a>

## Uporaba umjetne inteligencije [![AI Usage: Disclosed](https://img.shields.io/badge/AI%20Usage-Disclosed-blue.svg)](https://www.fer.unizg.hr/_download/repository/Policy%20on%20the%20appropriate%20use%20of%20artificial%20intelligence%20at%20the%20faculty%20of%20electrical%20engineering%20and%20computing%5B1%5D.pdf)

Ovaj projekt slijedi FER-ova [pravila o primjerenoj uporabi umjetne inteligencije](https://www.fer.unizg.hr/_download/repository/Policy%20on%20the%20appropriate%20use%20of%20artificial%20intelligence%20at%20the%20faculty%20of%20electrical%20engineering%20and%20computing%5B1%5D.pdf).

**Izjava:**

<!-- [Navedite jesu li korišteni alati umjetne inteligencije. Ako jesu, navedite alate i njihove glavne namjene te objasnite kako je tim pregledao nastali kod ili tekst i kako može objasniti vlastiti doprinos. Ako nisu korišteni, to jasno navedite. U alate umjetne inteligencije nemojte unositi privatne podatke, pristupne podatke ni tuđe zaštićene materijale.]

[Ako je za ovaj projekt potreban detaljniji zapis uporabe umjetne inteligencije, postavite poveznicu na njegovo jedinstveno mjerodavno mjesto u dokumentaciji.] -->

<a id="code-of-conduct-and-support"></a>

## Kodeks ponašanja i podrška [![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](CODE_OF_CONDUCT.md)

Od članova tima očekuje se pridržavanje **Kodeksa ponašanja studenata** Fakulteta elektrotehnike i računarstva Sveučilišta u Zagrebu, pravila timskog rada na kolegiju i [Etičkog kodeksa IEEE-a](https://www.ieee.org/about/corporate/governance/p7-8.html). [Postavite poveznicu na `CODE_OF_CONDUCT.md` ako se ta datoteka koristi u vašem repozitoriju.]

---

Struktura dokumentacije prilagođena je predlošku kolegija Programsko inženjerstvo FER-a autora [Vlade Sruka](https://www.fer.unizg.hr/vlado.sruk), licenciranom pod [CC BY-NC-SA 4.0](https://creativecommons.org/licenses/by-nc-sa/4.0/). Aplikaciju i njezin sadržaj izradio je gore navedeni studentski tim.
