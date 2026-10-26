//zahtjev prema backendu
export async function dohvatiIzlete(signal) {
  const odgovor = await fetch("/api/izleti", { signal });

  if (!odgovor.ok) {
    throw new Error(`Dohvat nije uspio (HTTP ${odgovor.status}).`);
  }

  const tip = odgovor.headers.get("content-type") || "";
  if (!tip.includes("application/json")) {
    throw new Error(
      "Backend nije vratio JSON. Provjeri API i dopuštenje za pregled izleta."
    );
  }

  const podaci = await odgovor.json();
  if (!Array.isArray(podaci)) {
    throw new Error("Backend je vratio neočekivan oblik podataka.");
  }

  return podaci;
}
