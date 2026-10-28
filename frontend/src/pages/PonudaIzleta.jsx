import {useState} from "react";
import useIzleti from "../hooks/useIzleti.js";
import KarticaIzleta from "../components/KarticaIzleta";
//import DetaljiIzleta from "../components/DetaljiIzleta.jsx"

const pocetniFilteri={
    naziv:"",
    lokacija:"",
    aktivnost:"",
    datum:"",
    trajanje:"",
    cijena:"",
    osobe:""
};


export default function PonudaIzleta(){ 

    const paket=useIzleti(); //podaci s backenda
    const izleti=paket.izleti;
    const ucitavanje=paket.ucitavanje;
    const greska=paket.greska;
    
    const [odabraniIzlet, setOdabraniIzlet]=useState(null); //korisnik još nije ništa kliknuo -> null

    //trenutno su rez isto sto i izleti
    //dodati da se mogu filtrirati kasnije pa da rez!=izleti
    const rezultati=izleti; 

    if (ucitavanje){
        return <p className="poruka">
            Učitavanje izleta...
            </p>;
    }

    if (greska){
        return <p className="poruka greska">
            {greska}
            </p>;
    }

    return (
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
            
            {rezultati.length === 0 ? (<p className="poruka">Nema izleta za prikaz.</p>) :
            (<div className="popis-izleta">
                {rezultati.map((izlet) => (
                    <div className="rezultat-izleta" key={izlet.id}>
                        <KarticaIzleta izlet={izlet} />
                        <button
                            type="button"
                            className="gumb-detalji"
                            aria-label={`Prikaži detalje: ${izlet.naziv}`}
                            onClick={() => setOdabraniIzlet(izlet)}
                        >Pogledaj detalje</button>
                    </div>
                ))}
            </div>)
            }

        </section>
    )
}