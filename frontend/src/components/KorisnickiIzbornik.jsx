
import { useEffect, useRef } from "react";
import "./KorisnickiIzbornik.css";

export default function KorisnickiIzbornik({
   korisnik,
   onOdjava
}) {
   const izbornikRef = useRef(null);

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

   return (
      <details
         ref={izbornikRef}
         className="korisnicki-izbornik"
      >
         <summary className="korisnicko-ime">
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
