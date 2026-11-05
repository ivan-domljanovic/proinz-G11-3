//datum, dostupnost, polazak i cijena
import { cijenaFormat, datumFormat, prikaziTrajanje } from "../utils/formatiranje.js";

function tekstMjesta (broj){
  //koliko slobodnih mjesta 
  //ostatak 1 slobodno, 2-4 slobodna, 5+ slobodnih
  const zadnja=broj%10;
  const zadnjedvije=broj%100;

  if (zadnja===1 && zadnjedvije!==11) 
    return `${broj} slobodno mjesto`;
  else if (zadnja>=2 && zadnja <=4 && (zadnjedvije<12 || zadnjedvije>14))
    return `${broj} slobodna mjesta`;
  else {
    return `${broj} slobodnih mjesta`;
  }
}


export default function TerminIzleta({ termin }) {
  //ako termin ima datum pretvoriti ga iz teksta u pravi Date 
  const datum=termin.datumVrijeme ? new Date(termin.datumVrijeme) : null;
  const ispravanDatum=datum!==null && !Number.isNaN(datum.getTime()); //dat mora postojat i ne bit besmislen
  const prosao=ispravanDatum && datum.getTime() < Date.now();

  const slobodnaMjesta=termin.brojSlobodnihMjesta;
  const kapacitet=termin.kapacitet;
  let oznaka="Dostupnost nije navedena";
  let stil="nepoznato";

  if (prosao) {
    oznaka="Termin je prošao";
    stil='prosao';
  } else if (slobodnaMjesta != null) {
    if(slobodnaMjesta<=0){
      oznaka="Popunjeno";
      stil="popunjeno"
    }else {
      oznaka=tekstMjesta(slobodnaMjesta);
      stil=kapacitet>0 && slobodnaMjesta/kapacitet<0.3 ? "malo-mjesta":"slobodno"
    }

  }

  return (
      <li className="termin-stavka">
        {ispravanDatum ? (
            <time dateTime={termin.datumVrijeme}>
              {datumFormat.format(datum)}
            </time>
        ) : (
            <span>Datum nije naveden</span>
        )}

        <span className={`status ${stil}`}>{oznaka}</span>

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
              {termin.cijena==null
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
