  //raspored stranice
  import "./App.css";
  import PonudaIzleta from "./pages/PonudaIzleta"
  import Zaglavlje from "./components/Zaglavlje.jsx";
  import { Routes, Route, Link} from "react-router"
  import StranicaIzleta from "./pages/StranicaIzleta.jsx";
  import Prijava from "./pages/Prijava"
import MojeRezervacije from "./pages/MojeRezervacije.jsx";

  export default function App() {

      return (
          <>
            <Zaglavlje />

            <main className="kontejner">
                <Routes>
                    <Route
                        path="/"
                        element={<PonudaIzleta/>}
                    />
                    <Route
                        path="/izleti/:id"
                        element={<StranicaIzleta/>}
                    />

                    <Route path="/login" element={<Prijava />} />
                    <Route path="/moje-rezervacije" element={<MojeRezervacije />} />
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

