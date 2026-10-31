//zaglavlje s logom i navigacijom koje će biti na svakoj stranici app
import {Link} from  "react-router";

export default function Zaglavlje(){
    return (
        <header className="zaglavlje">
            <div className="kontejner navigacija">
                <Link className="logo" to="/">
                    Waypoint<span>.</span>
                </Link>
                <nav className="glavni-izbornik" aria-label="Glavni izbornik">
                    <Link to="/">Izleti</Link>
                </nav>

                <a className="gumb-prijava" href="/login">Prijava</a>
            </div>

        
        </header>

    );

}