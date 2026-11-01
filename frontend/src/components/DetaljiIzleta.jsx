import {useEffect, useRef} from "react";
import TerminiIzleta from "./TerminiIzleta";
import {cijenaFormat} from "../utils/formatiranje";

export default function DetaljiIzleta({izlet, onNatrag}){
    const atrakcije = izlet.atrakcije ?? [];
    const dodatneUsluge = izlet.dodatneUsluge ?? [];
    const naslovRef = useRef(null);

    useEffect(()=>{
        naslovRef.current?.focus();
    },[izlet.id]);

    return(
        <section className="detalji-izleta">
            <button
                type="button"
                className="gumb-natrag"
                onClick={onNatrag}> ← Natrag na ponudu
            </button>
            <header className="detalji-zaglavlje">
                <span className="kategorija">
                    {izlet.kategorijaAktivnosti}
                </span>
                <h2
                    id="naslov-detalja"
                    ref={naslovRef}
                    tabIndex={-1}>
                    {izlet.naziv}
                </h2>
                <p className="detalji-lokacija">
                    Lokacija: {izlet.lokacija || "Nije navedena"}
                </p>
            </header>

            <div className="detalji-raspored">
                <div className="detalji-sadrzaj">
                    <section className="detalji-blok">
                        <h3>O izletu</h3>
                        <p className="detalji-opis">
                            {izlet.opis || "Opis jos nije dostupan."}
                        </p>
                    </section>

                    <section className="detalji-blok">
                        <h3>Što ćemo posjetiti</h3>
                        {atrakcije.length === 0 ? (
                            <p>Atrakcije još nisu navedene</p>
                        ) : (
                            <ul className="detalji-popis">
                                {atrakcije.map((atrakcija) =>(
                                    <li key = {atrakcija.id}>
                                        <h4>{atrakcija.naziv}</h4>
                                        <p>
                                            {atrakcija.opis || "Opis nije naveden."}
                                        </p>
                                    </li>
                                ))}
                            </ul>
                        )
                        }
                    </section>

                    <section className="detalji-blok">
                        <h3>Dodatne usluge</h3>

                        {dodatneUsluge.length === 0 ? (
                            <p>Za ovaj izlet nema dodatnih usluga.</p>
                        ) : (
                            <ul className="detalji-popis">
                                {dodatneUsluge.map((usluga) => (
                                    <li key={usluga.id}>
                                        <div className="usluga-zaglavlje">
                                            <h4>{usluga.naziv}</h4>
                                            <span className="usluga-cijena">
                                                {usluga.cijena == null
                                                    ? "Cijena na upit"
                                                    : cijenaFormat.format(
                                                        usluga.cijena
                                                    )}
                                            </span>
                                        </div>

                                        {usluga.opis && (
                                            <p>{usluga.opis}</p>
                                        )}
                                    </li>
                                ))}
                            </ul>
                        )}
                    </section>
                </div>

                <aside className="detalji-termini">
                    <TerminiIzleta
                        key = {izlet.id}
                        terminiIzleta = {izlet.termini ?? []}
                    />
                </aside>
            </div>
        </section>
    );
}