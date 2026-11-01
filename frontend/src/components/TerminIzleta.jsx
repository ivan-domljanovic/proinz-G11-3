//datum, dostupnost, polazak i cijena
import { cijenaFormat, datumFormat, prikaziTrajanje } from "../utils/formatiranje.js";

export default function TerminIzleta({ termin }) {
  const datum = termin.datumVrijeme
      ? new Date(termin.datumVrijeme)
      : null;

  const ispravanDatum =
      datum !== null && !Number.isNaN(datum.getTime());

  const prosao =
      ispravanDatum && datum.getTime() < Date.now();

  const slobodnaMjesta = termin.brojSlobodnihMjesta;
  const kapacitet = termin.kapacitet;
  let oznaka = "Dostupnost nije navedena";
  let stil = "prosao";

  if (prosao) {
    oznaka = "Termin je prošao";
  } else if (slobodnaMjesta != null) {
    if(slobodnaMjesta <= 0){
      oznaka = "Popunjeno";
      stil = "popunjeno"
    }else {
      oznaka = `${slobodnaMjesta} slobodnih mjesta`;
      stil = kapacitet > 0 && slobodnaMjesta / kapacitet < 0.3 ? "malo-mjesta":"slobodno"
    }

  }

  return (
      <li key={termin.id} className="termin-stavka">
        {ispravanDatum ? (
            <time dateTime={termin.datumVrijeme}>
              {datumFormat.format(datum)}
            </time>
        ) : (
            <span>Datum nije naveden</span>
        )}

        <span className={`status ${stil}`}>
    {oznaka}
  </span>

        <dl className="podaci podaci-termina">
          <div>
            <dt>Mjesto polaska</dt>
            <dd>
              {termin.mjestoPolaska || "Nije navedeno"}
            </dd>
          </div>

          <div>
            <dt>Cijena po osobi</dt>
            <dd className="cijena-termina">
              {termin.cijena == null
                  ? "Nije navedena"
                  : cijenaFormat.format(termin.cijena)}
            </dd>
          </div>

          <div>
            <dt>Trajanje</dt>
            <dd>{prikaziTrajanje((termin.trajanjeMinuta))}</dd>
          </div>
        </dl>
      </li>
  );
}
