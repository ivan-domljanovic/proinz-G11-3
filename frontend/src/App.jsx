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

        <div>
          <dt>Mjesto polaska</dt>
          <dd>{izlet.mjestoPolaska}</dd>
        </div>
      </dl>

      <div className="termini">
        <h3>Termini izleta</h3>

        {izlet.termini.length === 0 ? (
          <p className="bez-termina">Trenutačno nema termina.</p>
        ) : (
          <ul>
            {izlet.termini.map((termin) => {
              const zavrsen =
                new Date(termin.datumVrijeme).getTime() < Date.now();

              const oznaka = zavrsen
                ? "Termin je prošao"
                : termin.slobodnaMjesta > 0
                  ? `${termin.slobodnaMjesta} slobodnih mjesta`
                  : "Popunjeno";

              const stil = zavrsen
                ? "prosao"
                : termin.slobodnaMjesta > 0
                  ? "slobodno"
                  : "popunjeno";

              return (
                <li key={termin.id}>
                  <time dateTime={termin.datumVrijeme}>
                    {datumFormat.format(
                      new Date(termin.datumVrijeme)
                    )}
                  </time>

                  <span className={`status ${stil}`}>
                    {oznaka}
                  </span>
                </li>
              );
            })}
          </ul>
        )}
      </div>

      <footer className="kartica-dno">
        <span>Cijena po osobi</span>
        <strong>{cijenaFormat.format(izlet.cijena)}</strong>
      </footer>
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