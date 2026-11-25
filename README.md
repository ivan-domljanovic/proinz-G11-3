# Waypoint

Waypoint je aplikacija namijenjena pretraživanju, rezervaciji i organizaciji turističkih aktivnosti i izleta.

**Projekt na kolegiju:** Ovu je aplikaciju razvio studentski tim u sklopu kolegija [Programsko inženjerstvo](https://www.fer.unizg.hr/predmet/proinz) na Fakultetu elektrotehnike i računarstva Sveučilišta u Zagrebu (FER).

## Opis aplikacije

Aplikacija je namijenjena organizatorima turističkih aktivnosti i izleta te njihovim korisnicima. Organizatorima i administratorima olakšava upravljanje izletima, terminima, vodičima i ostalim resursima, a korisnicima pojednostavljuje pregled dostupnih aktivnosti i rezervaciju termina.

**Ključne funkcionalnosti**

- Pregled i pretraživanje izleta prema terminu, lokaciji, vrsti aktivnosti, trajanju, cijeni i broju sudionika
- Rezervacija termina za jednu ili više osoba s dodatnim uslugama (prijevoz, najam opreme)
- Automatska kontrola kapaciteta: termin se ne može prebukirati, a rezervacije bez slobodnih mjesta stavljaju se na listu čekanja
- Izmjena i otkazivanje vlastitih rezervacija te ocjenjivanje izleta i vodiča
- Upravljanje izletima, terminima, vodičima, resursima i točkama interesa za organizatore i administratore
- Prijava Google računom i dodjela korisničkih uloga
- U izradi: potvrde e-poštom, statistika i izvoz u CSV za organizatore te digitalni vodič na mobitelu koji prema lokaciji prikazuje informacije o točkama interesa u blizini

Za potpuni opseg, zahtjeve, oblikovanje i dokaze ispitivanja pogledajte [docs](docs/Home.md).

<a id="deployment"></a>

## Postavljanje (engl. deployment)

**Javno dostupna aplikacija:** Aplikacija još nije javno postavljena. Postavljanje je planirano na platformi Microsoft Azure.

[Vodič za postavljanje i pokretanje](docs/6-Postavljanje-instalacija-i-konfiguracija.md) opisuje smještaj aplikacije (engl. hosting) koji tim koristi.

<a id="quick-start"></a>

## Brzi početak / instalacija

**Preduvjeti:**

- Java JDK 25 (Maven nije potrebno zasebno instalirati, koristi se priloženi `mvnw`)
- Node.js s npm-om
- PostgreSQL koji sluša na portu `5433`, s bazom `turisticka-agencija` i shemom `turisticka-agencija`
- Google OAuth klijent (Client ID i Client Secret) s preusmjeravanjem na `http://localhost:8080/login/oauth2/code/google`

**Konfiguracija:** Poslužitelj čita varijable okruženja navedene u `backend/.env.example`: `DATABASE_USERNAME`, `DATABASE_PASSWORD`, `GOOGLE_CLIENT_ID` i `GOOGLE_CLIENT_SECRET`. Pristupni podaci baze su podaci vašeg lokalnog PostgreSQL računa, a Google podaci dobivaju se u Google Cloud Consoleu. Stvarne vrijednosti nikad ne spremajte u repozitorij. Varijable vrijede samo u terminalu u kojem su postavljene, pa ih pri svakom novom pokretanju poslužitelja treba postaviti ponovno.

Na Linuxu i macOS-u:

```bash
git clone https://github.com/ivan-domljanovic/proinz-G11-3
cd proinz-G11-3/backend

export DATABASE_USERNAME=...
export DATABASE_PASSWORD=...
export GOOGLE_CLIENT_ID=...
export GOOGLE_CLIENT_SECRET=...
./mvnw spring-boot:run

# (novi terminal)
cd proinz-G11-3/frontend
npm install
npm run dev
```

Na Windowsu (PowerShell):

```powershell
git clone https://github.com/ivan-domljanovic/proinz-G11-3
cd proinz-G11-3\backend

$env:DATABASE_USERNAME="..."
$env:DATABASE_PASSWORD="..."
$env:GOOGLE_CLIENT_ID="..."
$env:GOOGLE_CLIENT_SECRET="..."
.\mvnw.cmd spring-boot:run

# (novi terminal)
cd proinz-G11-3\frontend
npm install
npm run dev
```

**Adresa:** `http://localhost:5173` (frontend zahtjeve prema `/api` prosljeđuje poslužitelju na `http://localhost:8080`)

**Provjera:** Na početnoj stranici prikazuje se popis izleta. Pri svakom pokretanju poslužitelja baza se iznova stvara i puni testnim podacima.

Za detaljno postavljanje pogledajte [projektnu dokumentaciju](docs/Home.md).

## Tehnologije

| Tehnologija ili alat | Uloga u ovom projektu | Razlog uporabe ili važno ograničenje |
| --- | --- | --- |
| React | Prikazuje korisničko sučelje za korisnike, organizatore i administratore u pregledniku. | Jedan responzivan klijent radi na računalu, tabletu i mobitelu, pa nije potrebna zasebna mobilna aplikacija. |
| Vite | Pokreće frontend tijekom razvoja i prosljeđuje zahtjeve prema `/api` na backend. | Brzo osvježavanje pri razvoju; u produkciji frontend i backend moraju biti dostupni na javnim adresama. |
| Spring Boot | Obrađuje zahtjeve klijenta, provodi poslovna pravila (npr. provjeru slobodnih mjesta i listu čekanja) i upravlja podacima. | Jedno središnje mjesto za pravila rezervacije i autorizaciju; kvar poslužitelja zaustavlja sve funkcije. |
| PostgreSQL | Trajno pohranjuje korisnike, izlete, termine, rezervacije, resurse i recenzije. | Podaci su usko povezani (izlet – termin – rezervacija), a transakcije sa zaključavanjem termina sprječavaju prekoračenje kapaciteta. |
| Google OAuth 2.0 / OpenID Connect | Prijava korisnika Google računom. | Aplikacija ne pohranjuje lozinke; korisnik bez Google računa ne može se prijaviti. |
| PlantUML | UML dijagrami u dokumentaciji pišu se kao tekst u `docs/puml/`. | GitHub Action pri svakoj promjeni automatski generira SVG slike dijagrama. |

<a id="team-members"></a>

## Članovi tima

| Član     | Glavna uloga ili doprinos                 |
| -------- | ----------------------------------------- |
| Ivan     | voditelj                                  |
| Anita    | dokumentacija; backend od drugog ciklusa  |
| Deni     | frontend                                  |
| Lucija   | frontend                                  |
| Mate     | frontend                                  |
| Matej    | backend                                   |
| Tomislav | backend                                   |

Uloge se tijekom projekta mogu mijenjati. Repozitorij i dogovoreni alat za praćenje zadataka (engl. task tracker) služe kao dodatni dokazi timskog rada.

## Doprinos projektu

Zadaci se raspodjeljuju na tjednom sastanku tima (Discord, ponedjeljkom) i na konzultacijama s asistentom (četvrtkom nakon laboratorijskih vježbi). Kod i dokumentacija nalaze se u ovom repozitoriju; dokumentacija se piše u Markdownu u direktoriju `docs/`, a promjene se prate kroz povijest commitova.

<a id="license"></a>

**Status licence:** Aplikaciji zasad nije dodijeljena licenca koja dopušta javnu ponovnu uporabu. Datoteka [`LICENSE`](LICENSE) u repozitoriju odnosi se na predložak dokumentacije kolegija, a ne na aplikaciju.

Dokumentacijski predložak kolegija sadrži otvorene obrazovne sadržaje (engl. Open Educational Resources, OER) i licenciran je licencom Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International. Ta licenca **ne određuje automatski licencu studentske aplikacije**.

Kod trećih strana, skupovi podataka, slike, ikone i drugi sadržaji zadržavaju vlastite licence i uvjete uporabe.

<a id="ai-usage"></a>

## Uporaba umjetne inteligencije [![AI Usage: Disclosed](https://img.shields.io/badge/AI%20Usage-Disclosed-blue.svg)](https://www.fer.unizg.hr/_download/repository/Policy%20on%20the%20appropriate%20use%20of%20artificial%20intelligence%20at%20the%20faculty%20of%20electrical%20engineering%20and%20computing%5B1%5D.pdf)

Ovaj projekt slijedi FER-ova [pravila o primjerenoj uporabi umjetne inteligencije](https://www.fer.unizg.hr/_download/repository/Policy%20on%20the%20appropriate%20use%20of%20artificial%20intelligence%20at%20the%20faculty%20of%20electrical%20engineering%20and%20computing%5B1%5D.pdf).

**Izjava:** Tim je koristio alat umjetne inteligencije Claude kao pomoć pri izradi dokumentacije: provjeri usklađenosti zahtjeva, obrazaca uporabe i dijagrama s nastavnim materijalima, preoblikovanju tekstova te izradi PlantUML izvora dijagrama. Sav dobiveni sadržaj članovi tima pregledali su, provjerili prema kodu i predavanjima te prilagodili prije unosa u repozitorij, a za sadržaj dokumentacije odgovara tim. U alate umjetne inteligencije nisu unošeni pristupni podaci ni osobni podaci.

<a id="code-of-conduct-and-support"></a>

## Kodeks ponašanja i podrška [![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](CODE_OF_CONDUCT.md)

Od članova tima očekuje se pridržavanje **Kodeksa ponašanja studenata** Fakulteta elektrotehnike i računarstva Sveučilišta u Zagrebu, pravila timskog rada na kolegiju i [Etičkog kodeksa IEEE-a](https://www.ieee.org/about/corporate/governance/p7-8.html). Pravila ponašanja u repozitoriju navedena su u datoteci [`CODE_OF_CONDUCT.md`](CODE_OF_CONDUCT.md).

---

Struktura dokumentacije prilagođena je predlošku kolegija Programsko inženjerstvo FER-a autora [Vlade Sruka](https://www.fer.unizg.hr/vlado.sruk), licenciranom pod [CC BY-NC-SA 4.0](https://creativecommons.org/licenses/by-nc-sa/4.0/). Aplikaciju i njezin sadržaj izradio je gore navedeni studentski tim.
