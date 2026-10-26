//raspored stranice
import KarticaIzleta from "./components/KarticaIzleta.jsx";
import useIzleti from "./hooks/useIzleti.js";
import "./App.css";
import PonudaIzleta from "./pages/PonudaIzleta"

export default function App() {
  const { izleti, ucitavanje, greska } = useIzleti();

return (
    <>
      <header className="zaglavlje">
        <div className="kontejner navigacija">
          <a className="logo" href="/">
            Waypoint<span>.</span>
          </a>
          <span className="opis-aplikacije">
            Izleti i turističke aktivnosti
          </span>
        </div>
      </header>

      <main className="kontejner">
        <section className="uvod" >
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

        <PonudaIzleta/>
      </main>

      <footer className="podnozje">
        <div className="kontejner">
          Projekt G11-3 · Upravljanje izletima i turističkim aktivnostima
        </div>
      </footer>
    </>
  );
}

