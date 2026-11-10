//ucitavanje, greska i podaci
import { useEffect, useState } from "react";
import { dohvatiIzlete } from "../services/izletiApi.js";

export default function useIzleti(filteri) {
  const [izleti, setIzleti] = useState([]);
  const [ucitavanje, setUcitavanje] = useState(true);
  const [greska, setGreska] = useState("");

  useEffect(() => {
    const controller = new AbortController();

    async function ucitaj() {
      setUcitavanje(true);
      setGreska("");
      try {
        const podaci = await dohvatiIzlete(filteri, controller.signal);
        if (!controller.signal.aborted) setIzleti(podaci);
      } catch (error) {
        if (!controller.signal.aborted) setGreska(error.message);
      } finally {
        if (!controller.signal.aborted) setUcitavanje(false);
      }
    }

    ucitaj();
    return () => controller.abort();
  }, [filteri]);

  return { izleti, ucitavanje, greska };
}
