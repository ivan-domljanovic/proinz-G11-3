
import useIzleti from "../hooks/useIzleti.js";
import KarticaIzleta from "../components/KarticaIzleta";
import {Link} from "react-router";
import { useState } from "react";

const pocetniFilteri={
    lokacija:"",
    kategorija: "",
    datumOd: "",
    datumDo: "",
    trajanjeOd: "",
    trajanjeDo: "",
    cijenaOd: "",
    cijenaDo: "",
    brojOsoba: ""
};

//dodati sve kategorije koje ce jos postojati kasnije!
const kategorije = [
    { vrijednost: "PLANINARENJE", tekst: "Planinarenje" },
    { vrijednost: "PRIRODA", tekst: "Priroda" },
];


export default function PonudaIzleta(){ 
    const [filteri, setFilteri]=useState(pocetniFilteri);
    const [privremeniFilteri, setPrivremeniFilteri]=useState(pocetniFilteri);

    const danas=new Date().toLocaleDateString("sv-SE");

    const paket=useIzleti(filteri); //podaci s backenda
    const izleti=paket.izleti;
    const ucitavanje=paket.ucitavanje;
    const greska=paket.greska;

    const [pretraga, setPretragu]=useState("");

    const [prozorZaFiltre, setProzorZaFiltre]=useState(false); //kada pritisnem ostali filtri da se otvori i namjeste ostali filtri
    const brojAktivnihFiltera=Object.values(filteri).filter((v)=>v!="").length; 

    //mijenja jedan filter u radnoj kopiji (funkc da ne moram 9 puta pisat)
    function promijeni(kljuc, vrijednost) {
        setPrivremeniFilteri({ ...privremeniFilteri, [kljuc]: vrijednost });
    }


    //sada su rez!=izleti    
    const rezultati=izleti.filter((i)=>( 
        i.naziv.toLowerCase().includes(pretraga.toLowerCase())
    )); 

    return (
        <>
        <section className="uvod">
            <div className="uvod-tekst">
                <p className="nadnaslov">NOVO ISKUSTVO VAS ČEKA</p>
                <h1 id="glavni-naslov">
                    Mali odmak.<br />   
                    Veliki doživljaji.
                </h1>
                <p className="uvod-opis">
                    Istražite gradove, provedite dan u prirodi ili
                    pronađite svoju sljedeću avanturu.
                </p>
            </div>

            <form
                className="filteri-izleta"
                onSubmit={(event) => event.preventDefault()}>
                <label className="traka-pretrage">
                    <span className="ikona-pretrage" aria-hidden="true">⌕</span>
                    <input
                        type="search"
                        name="naziv"
                        placeholder="Pretraži izlete"
                        aria-label="Pretraži izlete po nazivu"
                        value={pretraga}
                        onChange={(p) => setPretragu(p.target.value)}
                    />
                </label>

                <button
                    type="button"
                    className="gumb-filteri"
                    onClick={()=>{
                        setPrivremeniFilteri(filteri);
                        setProzorZaFiltre(true)}}
                >
                    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor"
                        strokeWidth="2" strokeLinecap="round" aria-hidden="true">
                        <path d="M4 6h10M18 6h2M4 12h4M12 12h8M4 18h12M20 18h0" />
                        <circle cx="16" cy="6" r="2" />
                        <circle cx="10" cy="12" r="2" />
                        <circle cx="18" cy="18" r="2" />
                    </svg>
                    Filteri{brojAktivnihFiltera > 0 && ` · ${brojAktivnihFiltera}`}
                </button>
            </form>
        </section>

        <section className="ponuda" id="izleti">
            <div className="naslov-ponude">
                <div>
                    <p className="nadnaslov">ISTRAŽITE PONUDU</p>
                    <h2 id="naslov-ponude">Naši izleti</h2>
                </div>
                <span className="brojac" role="status">
                Pronađeno: {rezultati.length} / {izleti.length}
              </span>
            </div>

        {ucitavanje ? (<p className="poruka">Učitavanje izleta...</p>) : 
        greska ? (<p className="poruka greska">{greska}</p>) : 
        rezultati.length === 0 ? (
                <div className="poruka" role="status">
                    <h3>
                        {izleti.length === 0
                            ? "Trenutačno nema izleta u ponudi."
                            : "Nema izleta koji odgovara odabranim kriterijima."}
                    </h3>
                    {izleti.length > 0 && (
                        <p>Promijeni kriterije ili očisti filtere.</p>
                    )}
                </div>) : 
                (
                <div className="popis-izleta">
                    {rezultati.map((izlet) => (
                        <div className="rezultat-izleta" key={izlet.id}>
                            <KarticaIzleta izlet={izlet} />
                            <Link
                                to={`/izleti/${izlet.id}`}
                                className="gumb-detalji"
                                aria-label={`Prikaži detalje: ${izlet.naziv}`}
                            >
                                Pogledaj detalje
                            </Link>
                        </div>
                    ))}
                </div>
        )}   

        </section>

        {prozorZaFiltre && (
            <div className="pozadina-prozora" onClick={()=>setProzorZaFiltre(false)}>
                <div
                    className="prozor-filtera"
                    role="dialog"
                    aria-modal="true"
                    aria-labelledby="naslov-filtera"
                    onClick={(e) => e.stopPropagation()}
                >
                    <header className="prozor-zaglavlje">
                        <h2 id="naslov-filtera">Filteri</h2>
                        <button
                            type="button"
                            className="gumb-zatvori"
                            aria-label="Zatvori filtere"
                            onClick={()=>setProzorZaFiltre(false)}
                        > ✕ </button>
                    </header>

                    <div className="prozor-sadrzaj">
                        <section className="odjeljak-lokacija">
                            <h3>Lokacija</h3>
                            <input 
                                type="text"
                                placeholder="Pretraži lokaciju"
                                value={privremeniFilteri.lokacija}
                                onChange={(p) => promijeni("lokacija", p.target.value)}
                            />
                        </section>

                        <section className="odjeljak-kategorija">
                            <h3>Vrsta aktivnosti</h3>
                            <div className="cipovi">
                                {kategorije.map((k) => (
                                    <button
                                        key={k.vrijednost}
                                        type="button"
                                        className={privremeniFilteri.kategorija===k.vrijednost ? "cip odabran" : "cip"}
                                        onClick={() => promijeni("kategorija", privremeniFilteri.kategorija===k.vrijednost?"":k.vrijednost)}
                                    >
                                        {k.tekst}
                                    </button>
                                ))}
                            </div>
                        </section>

                        <section className="odjeljak-datum">
                            <h3>Datum</h3>
                            <div className="dva-polja">
                                    <label>
                                        Od
                                        <input
                                            type="date"
                                            value={privremeniFilteri.datumOd}
                                            min={danas}
                                            onChange={(p) => promijeni("datumOd", p.target.value)}
                                        />
                                    </label>
                                    <label>
                                        Do
                                        <input
                                            type="date"
                                            min={privremeniFilteri.datumOd || danas}
                                            value={privremeniFilteri.datumDo}
                                            onChange={(p)=> promijeni("datumDo", p.target.value)}
                                        />
                                    </label>
                                </div>                            
                        </section>

                        
                    </div>


                    <footer className="prozor-dno">
                        <button
                            type="button"
                            className="gumb-ocisti"
                            onClick={()=>setPrivremeniFilteri(pocetniFilteri)}
                        >
                            Očisti
                        </button>
                        <button
                            type="button"
                            className="gumb-primijeni"
                            onClick={()=>{
                                setFilteri(privremeniFilteri);
                                setProzorZaFiltre(false)}}
                        >
                            Prikaži rezultate
                        </button>
                    </footer>
                </div>
            </div>
        )}
        </>
    )
}