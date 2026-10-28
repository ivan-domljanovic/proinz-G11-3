//raspored stranice
import KarticaIzleta from "./components/KarticaIzleta.jsx";
import useIzleti from "./hooks/useIzleti.js";
import "./App.css";
import PonudaIzleta from "./pages/PonudaIzleta"
import { useState, useEffect } from "react";
import KarticaPrijave from "./components/KarticaPrijave.jsx";

export default function App() {
  const { izleti, ucitavanje, greska } = useIzleti();
  const [prijavaOtvorena, setPrijavaOtvorena] = useState(false);

  //const [korisnik, setKorisnik] = useState(null);
  const [korisnik, setKorisnik] = useState({
    name: "Mate Knežević",
    email: "mate@gmail.com",
    picture: "https://i.pravatar.cc/150?img=12"
  });
  //const [ucitavanjeKorisnika, setUcitavanjeKorisnika] = useState(true);
  const [ucitavanjeKorisnika, setUcitavanjeKorisnika] = useState(false);

  /*useEffect(() => {
    async function dohvatiKorisnika() {
      try {
        const response = await fetch("/me", {
          credentials: "include"
        });

        if (!response.ok) {
          return;
        }

        const tip = response.headers.get("content-type");

        if (!tip?.includes("application/json")) {
          return;
        }

        const podaci = await response.json();
        setKorisnik(podaci);

      } catch (error) {
        console.error("Greška pri dohvaćanju korisnika:", error);
      } finally {
        setUcitavanjeKorisnika(false);
      }
    }

    dohvatiKorisnika();
  }, []);*/

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

          {ucitavanjeKorisnika ? (
            <span>Učitavanje...</span>
          ) : korisnik ? (
            <span className="ime-korisnika">
              {korisnik.name}
            </span>
          ) : (
            <button
              type="button"
              onClick={() => setPrijavaOtvorena(true)}
            >
              Prijava
            </button>
          )}

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

        <PonudaIzleta />

      </main>

      <footer className="podnozje">
        <div className="kontejner">
          Projekt G11-3 · Upravljanje izletima i turističkim aktivnostima
        </div>
      </footer>
      <KarticaPrijave
        otvoreno={prijavaOtvorena}
        onZatvori={() => setPrijavaOtvorena(false)}
      />
    </>

  );
}

