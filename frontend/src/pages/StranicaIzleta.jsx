import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router";
import { dohvatiIzlet } from "../services/izletiApi.js";
import DetaljiIzleta from "../components/DetaljiIzleta.jsx";

export default function StranicaIzleta() {
    const { id } = useParams();

    // Novi ID pokreće novi prikaz i novo učitavanje.
    return <DohvatIzleta key={id} id={id} />;
}

function DohvatIzleta({ id }) {
    const navigate = useNavigate();
    const [izlet, setIzlet] = useState(null);
    const [ucitavanje, setUcitavanje] = useState(true);
    const [greska, setGreska] = useState("");
    const [pokusaj, setPokusaj] = useState(0);

    useEffect(() => {
        const controller = new AbortController();

        async function ucitaj() {
            setUcitavanje(true);
            setGreska("");

            try {
                const podaci = await dohvatiIzlet(id, controller.signal);

                if (!controller.signal.aborted) {
                    setIzlet(podaci);
                }
            } catch (error) {
                if (!controller.signal.aborted) {
                    setGreska(error.message);
                }
            } finally {
                if (!controller.signal.aborted) {
                    setUcitavanje(false);
                }
            }
        }

        ucitaj();

        return () => controller.abort();
    }, [id, pokusaj]);

    if (ucitavanje) {
        return (
            <p className="poruka" role="status">
                Učitavanje detalja izleta...
            </p>
        );
    }

    if (greska) {
        return (
            <section className="poruka greska" role="alert">
                <h2>Detalji nisu dostupni</h2>
                <p>{greska}</p>

                <button
                    type="button"
                    onClick={() => setPokusaj((prethodno) => prethodno + 1)}
                >
                    Pokušaj ponovno
                </button>

                <p>
                    <Link to="/">Natrag na ponudu</Link>
                </p>
            </section>
        );
    }

    return (
        <DetaljiIzleta
            izlet={izlet}
            onNatrag={() => navigate("/")}
        />
    );
}