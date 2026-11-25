import { Link, useSearchParams} from "react-router"
const API_URL = import.meta.env.VITE_API_URL ?? "";

export default function Prijava(){
    const [parametri] = useSearchParams();
    const greska = parametri.get("error");

    return(
        <section className="stranica-prijave">
            <div className="kartica-prijave">
                <p className="nadnaslov">VAŠA SLJEDEĆA AVANTURA</p>
                <h1> Dobro došli u Waypoint.</h1>
                <p className="prijava-opis">
                    Prijavite se Google računom kako biste mogli rezervirati izlete i pregledavati svoje rezervacije.
                </p>

                {greska && (
                    <p className="prijava-greska" role="alert">
                        Prijava nije uspjela. Pokušajte ponovo.
                    </p>
                )}
                <a href={`http://${API_URL}/oauth2/authorization/google`} className="gumb-google">
                    Nastavi s Googlom
                    <span aria-hidden="true">→</span>
                </a>

                <Link className="prijava-povratak" to="/">← Nastavi pregledavati izlete</Link>
            </div>
        </section>
    );
}