import { useEffect, useRef } from "react";
import "./KarticaPrijave.css";

export default function KarticaPrijave({ otvoreno, onZatvori }) {
   const ref = useRef(null);

   useEffect(() => {
      const dialog = ref.current;
      if (otvoreno && !dialog.open) dialog.showModal();
      if (!otvoreno && dialog.open) dialog.close();
   }, [otvoreno]);

   return (
      <dialog
         ref={ref}
         className="prijava-dialog"
         onClose={onZatvori}
         onClick={(e) => {
            if (e.target === ref.current) onZatvori(); // klik na pozadinu
         }}
      >
         <div className="prijava-kartica">
            <h2>Prijava</h2>
            <p>Prijavite se za rezervaciju izleta.</p>
            <a
               className="prijava-gumb"
               href="http://localhost:8080/oauth2/authorization/google"
            >
               Prijavi se putem Google
            </a>
            <button type="button" onClick={onZatvori}>
               Odustani
            </button>
         </div>
      </dialog>
   );
}