//podacio jednog izleta
//import { prikaziTrajanje } from "../utils/formatiranje.js";
import TerminiIzleta from "./TerminiIzleta.jsx";

export default function KarticaIzleta({ izlet }) {
  return (
      <article className="kartica">
        <div className="kartica-vrh">
        <span className="kategorija">
          {izlet.kategorijaAktivnosti}
        </span>



        </div>

        <h2>{izlet.naziv}</h2>
        <p className="opis">{izlet.opis}</p>

        <dl className="podaci">
          <div>
            <dt>Lokacija</dt>
            <dd>{izlet.lokacija}</dd>
          </div>
        </dl>

        {<TerminiIzleta terminiIzleta={izlet.termini ?? []} />}
      </article>
  );
}
