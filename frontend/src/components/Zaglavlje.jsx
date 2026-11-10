//zaglavlje s logom i navigacijom koje će biti na svakoj stranici app
import { Link, useNavigate    } from "react-router";
import { useEffect, useState } from "react";

export default function Zaglavlje() {
  const [korisnik, setKorisnik] = useState(null);
  const [ucitavanje, setUcitavanje] = useState(true);
  const [greskaSlike, setGreskaSlike] = useState(false);

  const navigate = useNavigate();
  const [odjavaUTijeku, setOdjavaUTijeku] = useState(false);
  const [greskaOdjave, setGreskaOdjave] = useState("");

  async function odjaviSe(){
    setOdjavaUTijeku(true);
    setGreskaOdjave("");

    try{
      const odgovor = await fetch("/api/logout", {
        method:"POST",
        credentials: "include",
        redirect: "error"
      });

      if(odgovor.status!==204){
        throw new Error("Odjava nije uspjela.");
      }
      setKorisnik(null);
      navigate("/",{replace:true});
    }catch{
      setGreskaOdjave("Odjava nije uspjela. Pokusaj ponovo.");
    } finally {
      setOdjavaUTijeku(false);
    }
  }
  useEffect(() => {
    const controller = new AbortController();

    async function ucitajKorisnika() {
      try {
        const odgovor = await fetch("/api/me", {
          credentials: "include",
          redirect: "manual",
          signal: controller.signal,
          cache: "no-store",
        });
        if (!odgovor.ok) return;
        const podaci = await odgovor.json();
        if (!controller.signal.aborted && podaci?.sub) setKorisnik(podaci);
      } catch {
        // Bez dostupne sesije ostaje poveznica za prijavu.
      } finally {
        if (!controller.signal.aborted) setUcitavanje(false);
      }
    }

    ucitajKorisnika();
    return () => controller.abort();
  }, []);

  return (
    <header className="zaglavlje">
      <div className="kontejner navigacija">
        <Link className="logo" to="/">
          Waypoint<span>.</span>
        </Link>
        <nav className="glavni-izbornik" aria-label="Glavni izbornik">
          <Link to="/">Izleti</Link>
          <Link to="/moje-rezervacije">Moje rezervacije</Link>
        </nav>

        {ucitavanje ? (
          <span className="korisnik-ucitavanje" role="status">
            Provjera prijave...
          </span>
        ) : korisnik ? (
          <div
            className="prijavljeni-korisnik"
            aria-label="Prijavljeni korisnik"
          >
            {korisnik.picture && !greskaSlike ? (
              <img
                className="korisnik-avatar"
                src={korisnik.picture}
                alt=""
                referrerPolicy="no-referrer"
                onError={() => setGreskaSlike(true)}
              />
            ) : (
              <span
                className="korisnik-avatar korisnik-inicijal"
                aria-hidden="true"
              >
                {(korisnik.ime || korisnik.email || "K")
                  .slice(0, 1)
                  .toUpperCase()}
              </span>
            )}
            <span className="korisnik-ime">
              {korisnik.ime || korisnik.email || "Korisnik"}
            </span>
            <button
                type="button"
                className="gumb-natrag"
                onClick={odjaviSe}
                disabled={odjavaUTijeku}
            >
              {odjavaUTijeku ? "Odjava..." : "Odjava"}
            </button>
          </div>
        ) : (
          <Link className="gumb-prijava" to="/login">
            Prijava
          </Link>
        )}
      </div>
      {greskaOdjave && (
          <p className="kontejner" role="alert">{greskaOdjave}</p>
      )}
    </header>
  );
}
