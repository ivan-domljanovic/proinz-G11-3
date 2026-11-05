import { useEffect, useState } from "react";
import { Link } from "react-router";
import { dohvatiRezervacije, PotrebnaPrijava } from "../services/rezervacijeApi.js";
import { cijenaFormat, datumFormat } from "../utils/formatiranje.js";

const statusi = {
    POTVRDENO: "Potvrđeno",
    ZAHTJEV: "Na listi čekanja",
    OTKAZANO: "Otkazano",
    REALIZIRANO: "Realizirano",
};

export default function MojeRezervacije() {
    const [rezervacije, setRezervacije] = useState([]);
    const [ucitavanje, setUcitavanje] = useState(true);
    const [greska, setGreska] = useState("");
    const [prijava, setPrijava] = useState(false);
    const [pokusaj, setPokusaj] = useState(0);

    useEffect(() => {
        const controller = new AbortController();
        setUcitavanje(true);
        setGreska("");
        setPrijava(false);
        dohvatiRezervacije(controller.signal).then((podaci) => {
            if (!controller.signal.aborted) setRezervacije(podaci);
        }).catch((error) => {
            if (!controller.signal.aborted) {
                setGreska(error.message);
                setPrijava(error instanceof PotrebnaPrijava || error.provjeraPrijave === true);
            }
        }).finally(() => {
            if (!controller.signal.aborted) setUcitavanje(false);
        });
        return () => controller.abort();
    }, [pokusaj]);

    return <section className="moje-rezervacije">
        <header className="naslov-ponude">
            <div>
                <p className="nadnaslov">VAŠI DOŽIVLJAJI</p>
                <h1>Moje rezervacije</h1>
            </div>
            <button type="button" className="gumb-natrag" disabled={ucitavanje} onClick={() => setPokusaj((p) => p + 1)}>Osvježi status</button>
        </header>
        {ucitavanje ? <p className="poruka" role="status">Učitavanje rezervacija...</p>
            : greska ? <div className="poruka greska" role="alert">
                <p>{greska}</p>
                {prijava && <Link to="/login">Idi na prijavu →</Link>}
            </div>
            : rezervacije.length === 0 ? <div className="poruka">
                <p>Još nemate rezervacija. Pronađite svoj sljedeći izlet.</p>
                <Link to="/">Pregledaj izlete →</Link>
            </div>
            : <ul className="popis-rezervacija">
                {[...rezervacije].sort((a, b) => b.id - a.id).map((r) => {
                    const datum = new Date(r.termin?.datumVrijeme);
                    return <li className="kartica" key={r.id}>
                        <div className="kartica-vrh">
                            <h2>Rezervacija #{r.id}</h2>
                            <span className={`status ${r.status === "POTVRDENO" ? "slobodno" : r.status === "ZAHTJEV" ? "malo-mjesta" : "prosao"}`}>{statusi[r.status] ?? r.status}</span>
                        </div>
                        <dl className="podaci">
                            <div><dt>Termin</dt><dd>{Number.isNaN(datum.getTime()) ? "Nije naveden" : <time dateTime={r.termin.datumVrijeme}>{datumFormat.format(datum)}</time>}</dd></div>
                            <div><dt>Mjesto polaska</dt><dd>{r.termin?.mjestoPolaska || "Nije navedeno"}</dd></div>
                            <div><dt>Broj ljudi</dt><dd>{r.brojMjesta}</dd></div>
                            <div><dt>Cijena izleta po osobi</dt><dd>{r.termin?.cijena == null ? "Na upit" : cijenaFormat.format(r.termin.cijena)}</dd></div>
                            <div><dt>Dodatne usluge</dt><dd>{r.dodatneUsluge?.length ? r.dodatneUsluge.map((u) => u.naziv).join(", ") : "Bez dodatnih usluga"}</dd></div>
                        </dl>
                        {r.status === "ZAHTJEV" && <p className="opis">Kad se oslobode mjesta, rezervacija se može automatski potvrditi. Osvježite status za provjeru.</p>}
                    </li>;
                })}
            </ul>}
    </section>;
}
