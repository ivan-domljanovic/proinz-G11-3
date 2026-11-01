
import useIzleti from "../hooks/useIzleti.js";
import KarticaIzleta from "../components/KarticaIzleta";
import {Link} from "react-router";

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
                        <Link
                            to={`/izleti/${izlet.id}`}
                            className="gumb-detalji"
                            aria-label={`Prikaži detalje: ${izlet.naziv}`}
                        >Pogledaj detalje</Link>
                    </div>
                ))}
            </div>)
            }

        </section>
    )
}