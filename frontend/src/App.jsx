//raspored stranice
import KarticaIzleta from "./components/KarticaIzleta.jsx";
import useIzleti from "./hooks/useIzleti.js";
import "./App.css";

export default function App() {
  const { izleti, ucitavanje, greska } = useIzleti();

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

