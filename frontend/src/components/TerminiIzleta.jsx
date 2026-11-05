//dropdown, sortiranje i prikazi jos
import { useState } from "react";
import TerminIzleta from "./TerminIzleta.jsx";


export default function TerminiIzleta({ terminiIzleta = [] }) {
  const [prikaziSveTermine, setPrikaziSveTermine]=useState(false);
  const sada=Date.now(); //

  const termini=terminiIzleta.filter((termin) =>{
    if(!termin.datumVrijeme) return false;
    const vrijeme=new Date(termin.datumVrijeme).getTime();
    return Number.isFinite(vrijeme) && vrijeme>=sada;
  }).sort((a, b) => new Date(a.datumVrijeme)-new Date(b.datumVrijeme));

  const vidljiviTermini=prikaziSveTermine ? termini : termini.slice(0, 1);


  return (
    <section className="termini" aria-label="Termini izleta">
      <h3>Termini izleta</h3>

      {termini.length===0 ? (<p className="bez-termina">Nema nadolazećih termina za ovaj izlet.</p>) : 
      (
        <ul>
          {vidljiviTermini.map((termin) => (
            <TerminIzleta key={termin.id} termin={termin} />
          ))}
        </ul>
      )}

      {termini.length>1 && (
        <button
          type="button"
          className="gumb-vise-termina"
          aria-expanded={prikaziSveTermine}
          onClick={()=>setPrikaziSveTermine((prethodno)=>!prethodno)}
        >
          {prikaziSveTermine
            ? "Prikaži manje"
            : `Prikaži još termina (${termini.length-1})`}
        </button>
      )}
    </section>
  );
}
