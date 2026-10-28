
import "./KorisnickiIzbornik.css";

export default function KorisnickiIzbornik({
   korisnik,
   onOdjava
}) {
   return (
      <details className="korisnicki-izbornik">

         <summary className="korisnicko-ime">
            <span>{korisnik.name}</span>
            <span className="strelica"></span>
         </summary>

         <div className="korisnicka-kartica">

            <div className="korisnicki-podaci">
               <strong>{korisnik.name}</strong>
               <p>{korisnik.email}</p>
            </div>

            <div className="korisnicke-aktivnosti">
               <span>Zakazane aktivnosti</span>
               <strong>—</strong>
            </div>

            <button
               type="button"
               className="gumb-odjava"
               onClick={onOdjava}
            >
               Odjava
            </button>

         </div>

      </details>
   );
}
