//raspored stranice
import "./App.css";
import PonudaIzleta from "./pages/PonudaIzleta"
import Zaglavlje from "./components/Zaglavlje.jsx";
import { Routes, Route, Link} from "react-router"
import StranicaIzleta from "./pages/StranicaIzleta.jsx";
export default function App() {

    return (
        <>
          <Zaglavlje />

          <main className="kontejner">
              <Routes>
                  <Route
                      path="/"
                      element={
                      <>
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
                      </>
                  }
                  />
                  <Route
                      path="/izleti/:id"
                      element={<StranicaIzleta/>}
                  />


              <Route
                  path="*"
                  element={
                  <section className="poruka">
                      <h2>Stranica nije pronadena.</h2>
                      <Link to="/">Natrag na ponudu</Link>
                  </section>
                  }
              />
              </Routes>
          </main>

          <footer className="podnozje">
            <div className="kontejner">
              Projekt G11-3 · Upravljanje izletima i turističkim aktivnostima
            </div>
          </footer>
        </>
      );
}

