import {useState} from "react";
import useIzleti from "../hooks/useIzleti.js";
import KarticaIzleta from "../components/KarticaIzleta";
import DetaljiIzleta from "../components/DetaljiIzleta";

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

    if(odabraniIzlet){
        return(
            <DetaljiIzleta
                izlet = {odabraniIzlet}
                onNatrag={()=> setOdabraniIzlet(null)}
                />
        )
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

            <form
                className="filteri-izleta"
                onSubmit={(event) => event.preventDefault()}>

                <label>
                    Naziv izleta
                    <input
                    type="search"
                    name="naziv"
                    placeholder="Pretraži izlete"

                    />
                </label>
            </form>

            {rezultati.length === 0 ? (
                <div className="poruka" role="status">
                    <h3>
                        {izleti.length===0 ? "Trenutačno nema izleta u ponudi."
                            :"Nema izleta koji odgovara odabranim kriterijima."}
                    </h3>
                    {izleti.length > 0 && (
                        <p>Promjeni kriterije ili očisti filtere.</p>
                    )}
                </div>
                ) :
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