import { useRef, useState } from "react";
import { Link } from "react-router";
import { PotrebnaPrijava, rezervirajTermin } from "../services/rezervacijeApi.js";
import { cijenaFormat, datumFormat } from "../utils/formatiranje.js";

function naplataPoOsobi(usluga) {
    const naziv = (usluga.naziv ?? "").normalize("NFD").replace(/[\u0300-\u036f]/g, "").toLowerCase();
    return naziv.includes("rucak") || naziv.includes("prijevoz");
}

export default function PregledRezervacije({ izlet, dialogRef }) {
    const [terminId, setTerminId] = useState("");
    const [brojLjudi, setBrojLjudi] = useState(1);
    const [odabraneUsluge, setOdabraneUsluge] = useState([]);
    const [slanje, setSlanje] = useState(false);
    const [greska, setGreska] = useState("");
    const [potrebnaPrijava, setPotrebnaPrijava] = useState(false);
    const [rezultat, setRezultat] = useState(null);
    const saljeSe = useRef(false);
    const usluge = izlet.dodatneUsluge ?? [];
    const odabrane = usluge.filter((u) => odabraneUsluge.includes(u.id));
    const cijenaUsluga = odabrane.reduce((zbroj, u) =>
        zbroj + Number(u.cijena ?? 0) * (naplataPoOsobi(u) ? Number(brojLjudi) : 1), 0);
    const cijenePoznate = odabrane.every((u) => u.cijena != null);
    const termini = (izlet.termini ?? [])
        .filter((t) => new Date(t.datumVrijeme).getTime() > Date.now())
        .sort((a, b) => new Date(a.datumVrijeme) - new Date(b.datumVrijeme));
    const termin = termini.find((t) => String(t.id) === terminId);
    const ispravanBroj = Number.isSafeInteger(Number(brojLjudi)) && Number(brojLjudi) > 0;

    async function potvrdiRezervaciju() {
        if (saljeSe.current || rezultat) return;
        if (!termin || !ispravanBroj) {
            setGreska("Odaberite termin i unesite pozitivan cijeli broj ljudi.");
            return;
        }
        saljeSe.current = true;
        setSlanje(true);
        setGreska("");
        setPotrebnaPrijava(false);
        try {
            setRezultat(await rezervirajTermin({
                terminId: termin.id,
                brojMjesta: Number(brojLjudi),
                dodatneUslugeId: odabrane.map((u) => u.id),
            }));
        } catch (error) {
            setPotrebnaPrijava(error instanceof PotrebnaPrijava || error.provjeraPrijave === true);
            setGreska(error.message || "Rezervacija nije uspjela. Pokušajte ponovno.");
        } finally {
            saljeSe.current = false;
            setSlanje(false);
        }
    }

    return <dialog
        ref={dialogRef}
        className="rezervacija-modal"
        aria-labelledby="rezervacija-naslov"
        onClick={(event) => {
            if (event.target !== event.currentTarget) return;
            const okvir = event.currentTarget.getBoundingClientRect();
            if (event.clientX < okvir.left || event.clientX > okvir.right || event.clientY < okvir.top || event.clientY > okvir.bottom) {
                event.currentTarget.close();
            }
        }}
    >
        <div className="rezervacija-modal-vrh">
            <p className="nadnaslov">VAŠ SLJEDEĆI DOŽIVLJAJ</p>
            <button type="button" className="rezervacija-zatvori" aria-label="Zatvori" onClick={() => dialogRef.current.close()}>×</button>
        </div>
        <h2 id="rezervacija-naslov">Rezerviraj termin</h2>
        <p className="rezervacija-izlet">{izlet.naziv}</p>
        {rezultat ? <div className="rezervacija-sazetak" role="status">
            <strong>{rezultat.status === "POTVRDENO" ? "Rezervacija je potvrđena!" : rezultat.status === "ZAHTJEV" ? "Rezervacija je na listi čekanja." : `Status rezervacije: ${rezultat.status}`}</strong>
            <p>Rezervacija #{rezultat.id} · Broj ljudi: {rezultat.brojMjesta ?? brojLjudi}</p>
            {rezultat.status === "ZAHTJEV" && <p>Kad se oslobode mjesta, rezervacija se može automatski potvrditi.</p>}
            <button type="button" className="gumb-detalji" onClick={() => dialogRef.current.close()}>Zatvori</button>
        </div> : termini.length === 0 ? <p>Nema nadolazećih termina za ovaj izlet.</p> : <>
            <fieldset className="rezervacija-unos" disabled={slanje}>
            <label className="rezervacija-polje">Odaberite termin
                <select autoFocus value={terminId} onChange={(e) => setTerminId(e.target.value)}>
                    <option value="">Odaberite datum i vrijeme</option>
                    {termini.map((t) => <option key={t.id} value={t.id}>
                        {datumFormat.format(new Date(t.datumVrijeme))}{t.brojSlobodnihMjesta != null ? ` · ${Math.max(0, t.brojSlobodnihMjesta)} slobodnih mjesta` : ""}
                    </option>)}
                </select>
            </label>
            <label className="rezervacija-polje">Broj ljudi
                <input type="number" min="1" step="1" required value={brojLjudi} onChange={(e) => setBrojLjudi(e.target.value)} />
            </label>
            {!ispravanBroj && <p role="alert">Unesite pozitivan cijeli broj ljudi.</p>}
            {usluge.length > 0 && <fieldset className="rezervacija-usluge">
                <legend>Dodatne usluge</legend>
                <p>Odaberite usluge koje želite dodati rezervaciji.</p>
                {usluge.map((usluga) => <label className="rezervacija-usluga" key={usluga.id}>
                    <input
                        type="checkbox"
                        checked={odabraneUsluge.includes(usluga.id)}
                        onChange={(event) => setOdabraneUsluge((prethodne) => event.target.checked
                            ? [...prethodne, usluga.id]
                            : prethodne.filter((id) => id !== usluga.id))}
                    />
                    <span className="rezervacija-usluga-opis">
                        <strong>{usluga.naziv}</strong>
                        {usluga.opis && <small>{usluga.opis}</small>}
                    </span>
                    <span className="rezervacija-usluga-cijena">{usluga.cijena == null ? "Na upit" : cijenaFormat.format(usluga.cijena)}<br /><small>{naplataPoOsobi(usluga) ? "po osobi" : "po rezervaciji"}</small></span>
                </label>)}
            </fieldset>}
            <div className="rezervacija-sazetak" aria-live="polite">
                {termin ? <>
                    <div><span>Cijena po osobi</span><strong>{termin.cijena == null ? "Na upit" : cijenaFormat.format(termin.cijena)}</strong></div>
                    {odabrane.length > 0 && <div><span>Dodatne usluge</span><strong>{!ispravanBroj ? "—" : cijenePoznate ? cijenaFormat.format(cijenaUsluga) : "Na upit"}</strong></div>}
                    <div className="rezervacija-ukupno"><span>Ukupno</span><strong>{termin.cijena != null && ispravanBroj && cijenePoznate ? cijenaFormat.format(termin.cijena * Number(brojLjudi) + cijenaUsluga) : "—"}</strong></div>
                    {ispravanBroj && termin.brojSlobodnihMjesta != null && Number(brojLjudi) > termin.brojSlobodnihMjesta && <p>Za odabrani broj ljudi rezervacija bi bila na listi čekanja.</p>}
                </> : <p>Odaberite termin za prikaz cijene.</p>}
            </div>
            </fieldset>
            <button type="button" className="gumb-detalji" disabled={slanje} onClick={potvrdiRezervaciju}>{slanje ? "Spremanje rezervacije..." : "Potvrdi rezervaciju"}</button>
            {greska && <div className="rezervacija-obavijest" role="alert">
                <p>{greska}</p>
                {potrebnaPrijava && <Link to="/login" onClick={() => dialogRef.current.close()}>Idi na prijavu →</Link>}
            </div>}
        </>}
    </dialog>;
}
