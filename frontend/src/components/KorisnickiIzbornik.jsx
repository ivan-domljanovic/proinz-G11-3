
import { useEffect, useRef, useState } from "react";
import "./KorisnickiIzbornik.css";

export default function KorisnickiIzbornik({
   korisnik,
   onOdjava,
   brojRezervacija = null,
   onMojeRezervacije
}) {
   const izbornikRef = useRef(null);
   const [porukaRezervacije, setPorukaRezervacije] = useState(false);

   useEffect(() => {
      function klikIzvan(event) {
         if (
            izbornikRef.current &&
            !izbornikRef.current.contains(event.target)
         ) {
            izbornikRef.current.open = false;
         }
      }

      document.addEventListener("pointerdown", klikIzvan);

      return () => {
         document.removeEventListener("pointerdown", klikIzvan);
      };
   }, []);

   function otvoriRezervacije() {
      if (onMojeRezervacije) {
         onMojeRezervacije();
         izbornikRef.current.open = false;
      } else {
         setPorukaRezervacije(true);
      }
   }

   return (
      <details
         ref={izbornikRef}
         className="korisnicki-izbornik"
      >
         <summary className="korisnicko-ime">
            <span className="avatar">
               {korisnik.name?.charAt(0).toUpperCase()}
            </span>

            <span>{korisnik.name}</span>
            <span className="strelica" aria-hidden="true"></span>
         </summary>

         <div className="korisnicka-kartica">

            <div className="korisnicki-podaci">
               <strong>{korisnik.name}</strong>
               <p>{korisnik.email}</p>
            </div>

            <div className="korisnicke-aktivnosti">
               <span>Zakazane aktivnosti</span>
               <strong className="broj-aktivnosti">
                  {brojRezervacija ?? "—"}
               </strong>
            </div>

            <button
               type="button"
               className="moje-rezervacije"
               onClick={otvoriRezervacije}
            >
               <span className="rezervacije-ikona">▣</span>
               Moje rezervacije
               <span className="rezervacije-strelica">›</span>
            </button>

            {porukaRezervacije && !onMojeRezervacije && (
               <p className="rezervacije-poruka">
                  Pregled rezervacija još nije dostupan.
               </p>
            )}

            <div className="kartica-odjava">
               <button
                  type="button"
                  className="gumb-odjava"
                  onClick={onOdjava}
               >
                  Odjava
               </button>
            </div>

         </div>
      </details>
   );
}
