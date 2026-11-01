//dropdown, sortiranje i prikazi jos
import { useState } from "react";
import TerminIzleta from "./TerminIzleta.jsx";


export default function TerminiIzleta({ terminiIzleta = [] }) {
  const [prikaziSveTermine, setPrikaziSveTermine] = useState(false);
  const [prikazTermina, setPrikazTermina] = useState("nadolazeci")
  const sada = Date.now();

  const termini = terminiIzleta.filter((termin) =>{
    if(!termin.datumVrijeme) return false;
    const vrijeme = new Date(termin.datumVrijeme).getTime();

    if(!Number.isFinite(vrijeme)) return false;

    return prikazTermina === "nadolazeci"
      ? vrijeme >= sada : vrijeme < sada;
  })
      .sort((a,b) => {
        const vrijemeA = new Date(a.datumVrijeme) .getTime();
        const vrijemeB = new Date(b.datumVrijeme).getTime();

    return prikazTermina === "nadolazeci"
      ? vrijemeA - vrijemeB : vrijemeB - vrijemeA;
      });

  const vidljiviTermini = prikaziSveTermine ? termini : termini.slice(0, 1);


  return (
    <section className="termini" aria-label="Termini izleta">
      <h3>Termini izleta</h3>
      <label className="odabir-termina">
        <span>Prikaži termine</span>

        <select
            value={prikazTermina}
            onChange={(event) => {
              setPrikazTermina(event.target.value);
              setPrikaziSveTermine(false);
            }}
        >
          <option value="nadolazeci">Nadolazeći termini</option>
          <option value="prosli">Prošli termini</option>
        </select>
      </label>
      {termini.length === 0 ? (
          <p className="bez-termina">
            {prikazTermina === "nadolazeci"
                ? "Nema nadolazećih termina za ovaj izlet."
                : "Nema prošlih termina za ovaj izlet."}
          </p>
      ) : (
          <ul>
            {vidljiviTermini.map((termin) => (
              <TerminIzleta key={termin.id} termin={termin} />
            ))}
          </ul>
      )}
      {termini.length > 1 && (
        <button
          type="button"
          className="gumb-vise-termina"
          aria-expanded={prikaziSveTermine}
          onClick={() => setPrikaziSveTermine((prethodno) => !prethodno)}
        >
          {prikaziSveTermine
            ? "Prikaži manje"
            : `Prikaži još termina (${termini.length - 1})`}
        </button>
      )}
    </section>
  );
}
