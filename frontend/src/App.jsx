import { useEffect, useState } from "react";
import "./App.css";

const cijenaFormat = new Intl.NumberFormat("hr-HR", {
  style: "currency",
  currency: "EUR",
});

const datumFormat = new Intl.DateTimeFormat("hr-HR", {
  dateStyle: "medium",
  timeStyle: "short",
});

function prikaziTrajanje(minute) {
  if (minute == null) return "Nije navedeno";

  const sati = Math.floor(minute / 60);
  const ostatak = Math.round(minute % 60);

  return [
    sati > 0 ? `${sati} h` : "",
    ostatak > 0 ? `${ostatak} min` : "",
  ]
    .filter(Boolean)
    .join(" ") || "0 min";
}

function KarticaIzleta({ izlet }) {
  const [prikaziSveTermine, setPrikaziSveTermine] = useState(false);
  const [prikazTermina, setPrikazTermina] = useState("nadolazeci")
  const sada = Date.now();

  const termini = (izlet.termini ?? []).filter((termin) =>{
    if(!termin.datumVrijeme) return false;
    const vrijeme = new Date(termin.datumVrijeme).getTime();

    if(!Number.isFinite(vrijeme)) return false;

    return prikazTermina === "nadolazeci"
      ? vrijeme >= sada : vrijeme < sada;
  })
      .sort((a,b) => {
        const vrijemeA = new Date(a.datumVrijeme) .getTime();
        const vrijemeB = new Date(b.datumVrijeme).getTime();

    return prikazTermina === "nadolazeci"
      ? vrijemeA - vrijemeB : vrijemeB - vrijemeA;
      });

  const vidljiviTermini = prikaziSveTermine ? termini : termini.slice(0, 1);

  return (
      <article className="kartica">
        <div className="kartica-vrh">
        <span className="kategorija">
          {izlet.kategorijaAktivnosti}
        </span>

          <span className="trajanje">
          {prikaziTrajanje(izlet.trajanjeMinuta)}
        </span>
        </div>

        <h2>{izlet.naziv}</h2>
        <p className="opis">{izlet.opis}</p>

        <dl className="podaci">
          <div>
            <dt>Lokacija</dt>
            <dd>{izlet.lokacija}</dd>
          </div>
        </dl>

        <section className="termini" aria-label="Termini izleta">
          <h3>Termini izleta</h3>
          <label className="odabir-termina">
            <span>Prikaži termine</span>

            <select
                value={prikazTermina}
                onChange={(event) => {
                  setPrikazTermina(event.target.value);
                  setPrikaziSveTermine(false);
                }}
            >
              <option value="nadolazeci">Nadolazeći termini</option>
              <option value="prosli">Prošli termini</option>
            </select>
          </label>
          {termini.length === 0 ? (
              <p className="bez-termina">
                {prikazTermina === "nadolazeci"
                    ? "Nema nadolazećih termina za ovaj izlet."
                    : "Nema prošlih termina za ovaj izlet."}
              </p>
          ) : (
              <ul>
                {vidljiviTermini.map((termin) => {
                  const datum = termin.datumVrijeme
                      ? new Date(termin.datumVrijeme)
                      : null;

                  const ispravanDatum =
                      datum !== null && !Number.isNaN(datum.getTime());

                  const prosao =
                      ispravanDatum && datum.getTime() < Date.now();

                  const slobodnaMjesta = termin.brojSlobodnihMjesta;
                  const kapacitet = termin.kapacitet;
                  let oznaka = "Dostupnost nije navedena";
                  let stil = "prosao";

                  if (prosao) {
                    oznaka = "Termin je prošao";
                  } else if (slobodnaMjesta != null) {
                    if(slobodnaMjesta <= 0){
                      oznaka = "Popunjeno";
                      stil = "popunjeno"
                    }else {
                      oznaka = `${slobodnaMjesta} slobodnih mjesta`;
                      stil = kapacitet > 0 && slobodnaMjesta / kapacitet < 0.3 ? "malo-mjesta":"slobodno"
                    }

                  }

                  return (
                      <li key={termin.id} className="termin-stavka">
                        {ispravanDatum ? (
                            <time dateTime={termin.datumVrijeme}>
                              {datumFormat.format(datum)}
                            </time>
                        ) : (
                            <span>Datum nije naveden</span>
                        )}

                        <span className={`status ${stil}`}>
                    {oznaka}
                  </span>

                        <dl className="podaci podaci-termina">
                          <div>
                            <dt>Mjesto polaska</dt>
                            <dd>
                              {termin.mjestoPolaska || "Nije navedeno"}
                            </dd>
                          </div>

                          <div>
                            <dt>Cijena po osobi</dt>
                            <dd className="cijena-termina">
                              {termin.cijena == null
                                  ? "Nije navedena"
                                  : cijenaFormat.format(termin.cijena)}
                            </dd>
                          </div>
                        </dl>
                      </li>
                  );
                })}
              </ul>
          )}
          {termini.length > 1 && (
            <button
              type="button"
              className="gumb-vise-termina"
              aria-expanded={prikaziSveTermine}
              onClick={() => setPrikaziSveTermine((prethodno) => !prethodno)}
            >
              {prikaziSveTermine
                ? "Prikaži manje"
                : `Prikaži još termina (${termini.length - 1})`}
            </button>
          )}
        </section>
      </article>
  );
}

export default function App() {
  const [izleti, setIzleti] = useState([]);
  const [ucitavanje, setUcitavanje] = useState(true);
  const [greska, setGreska] = useState("");

  useEffect(() => {
    const controller = new AbortController();

    async function dohvatiIzlete() {
      try {
        const odgovor = await fetch("/api/izleti", {
          signal: controller.signal,
        });

        if (!odgovor.ok) {
          throw new Error(
            `Dohvat nije uspio (HTTP ${odgovor.status}).`
          );
        }

        const tip = odgovor.headers.get("content-type") || "";

        if (!tip.includes("application/json")) {
          throw new Error(
            "Backend nije vratio JSON. Provjeri API i dopuštenje za pregled izleta."
          );
        }

        const podaci = await odgovor.json();

        if (!Array.isArray(podaci)) {
          throw new Error("Backend je vratio neočekivan oblik podataka.");
        }

        setIzleti(podaci);
      } catch (error) {
        if (!controller.signal.aborted) {
          setGreska(error.message);
        }
      } finally {
        if (!controller.signal.aborted) {
          setUcitavanje(false);
        }
      }
    }

    dohvatiIzlete();

    return () => controller.abort();
  }, []);

  return (
    <>
      <header className="zaglavlje">
        <div className="kontejner navigacija">
          <a className="logo" href="/">
            PUTOVANJA<span>.</span>
          </a>

          <span className="opis-aplikacije">
            Izleti i turističke aktivnosti
          </span>
        </div>
      </header>

      <main className="kontejner">
        <section className="uvod" aria-labelledby="glavni-naslov">
          <p className="nadnaslov">NOVO ISKUSTVO VAS ČEKA</p>

          <h1 id="glavni-naslov">
            Mali odmak.<br />
            Veliki doživljaji.
          </h1>

          <p className="uvod-opis">
            Istražite gradove, provedite dan u prirodi ili
            pronađite svoju sljedeću avanturu.
          </p>
        </section>

        <section className="ponuda" aria-labelledby="naslov-ponude">
          <div className="naslov-ponude">
            <div>
              <p className="nadnaslov">ISTRAŽITE PONUDU</p>
              <h2 id="naslov-ponude">Naši izleti</h2>
            </div>

            {!ucitavanje && !greska && (
              <span className="brojac">
                Broj izleta: {izleti.length}
              </span>
            )}
          </div>

          {ucitavanje ? (
            <p className="poruka" role="status">
              Učitavanje izleta…
            </p>
          ) : greska ? (
            <div className="poruka greska" role="alert">
              <h3>Izleti se nisu mogli učitati</h3>
              <p>{greska}</p>
              <p>
                Provjeri radi li backend i postoji li adresa
                /api/izleti.
              </p>
              <button onClick={() => window.location.reload()}>
                Pokušaj ponovno
              </button>
            </div>
          ) : izleti.length === 0 ? (
            <p className="poruka">Trenutačno nema izleta u ponudi.</p>
          ) : (
            <div className="popis-izleta">
              {izleti.map((izlet) => (
                <KarticaIzleta key={izlet.id} izlet={izlet} />
              ))}
            </div>
          )}
        </section>
      </main>

      <footer className="podnozje">
        <div className="kontejner">
          Projekt G11-3 · Upravljanje izletima i turističkim aktivnostima
        </div>
      </footer>
    </>
  );
}




