import { useEffect, useRef, useState } from "react";
import { Link } from "react-router";

import {
    dohvatiAdminKorisnika,
    dohvatiPonuduZaUpravljanje,
    dohvatiIzletZaUpravljanje,
    spremiIzlet,
    obrisiIzlet
} from "../services/adminIzletiApi"

export default function AdminIzleti(){
    const [korisnik, setKorisnik] = useState(null);
    const [izleti,setIzleti] = useState([])
    const [ucitavanje, setUcitavanje] = useState(true);

    useEffect(()=>{
        const controller = new AbortController();
        async function ucitaj(){
            try{
                const osoba = await dohvatiAdminKorisnika(controller.signal);
                if(controller.signal.aborted) return;
                setKorisnik(osoba);

                if(!osoba.uluge?.includes("ADMINISTRATOR")){
                    return;
                }

                const ponuda = await dohvatiPonuduZaUpravljanje(controller.signal)

                if(!controller.signal.aborted){
                    setIzleti(ponuda);
                }
            } catch (error){
                if(!controller.signal.aborted){
                    setGreska(error.message);
                }
            } finally {
                if(!controller.signal.aborted){
                    setUcitavanje(false);
                }
            }
        }
    })
}