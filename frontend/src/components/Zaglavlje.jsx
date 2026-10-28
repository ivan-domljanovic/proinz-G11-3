//zaglavlje s logom i navigacijom koje će biti na svakoj stranici app

export default function Zaglavlje(){
    return (
        <header className="zaglavlje">
            <div className="kontejner navigacija">
                <a className="logo" href="/">
                    Waypoint<span>.</span>
                </a>
                <nav className="glavni-izbornik" aria-label="Glavni izbornik">
                    <a href="#izleti">Izleti</a>
                </nav>

                <a className="gumb-prijava" href="/login">Prijava</a>
            </div>

        
        </header>

    );

}