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

    const {izleti,ucitavanje,greska} = useIzleti();

    const rezultati = izleti;

    if(ucitavanje){
        return <p className="poruka">Učitavanje izleta...</p>;
    }

    if(greska){
        return <p className="poruka greska">{greska}</p>;
    }

    return(
        <section className="ponuda">
            <div className="naslov-ponude">
                <div>
                    <p className="nadnaslov">ISTRAŽITE PONUDU</p>
                    <h2 id="naslov-ponude">Naši izleti</h2>
                </div>
                <span className="brojac" role="status">
                Pronađeno: {rezultati.length} / {izleti.length}
              </span>
            </div>


                <div className="popis-izleta">
                    {rezultati.map((izlet) => (
                        <div className="rezultat-izleta" key={izlet.id}>
                            <KarticaIzleta izlet={izlet} />

                            <button
                                type="button"
                                className="gumb-detalji"
                                aria-label={`Prikaži detalje: ${izlet.naziv}`}
                                onClick={() => setOdabraniIzlet(izlet)}
                            >
                                Pogledaj detalje
                            </button>
                        </div>
                    ))}
                </div>

        </section>
    )
}