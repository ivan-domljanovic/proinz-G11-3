//raspored stranice
import "./App.css";
import PonudaIzleta from "./pages/PonudaIzleta"
import Zaglavlje from "./components/Zaglavlje.jsx";

export default function App() {

    return (
        <>
          <Zaglavlje />

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

