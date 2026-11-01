// Dohvat svih izleta za početnu stranicu
export async function dohvatiIzlete(signal) {
  const odgovor = await fetch("/api/izleti", { signal });

  if (!odgovor.ok) {
    throw new Error(`Dohvat nije uspio (HTTP ${odgovor.status}).`);
  }

  const tip = odgovor.headers.get("content-type") || "";

  if (!tip.includes("application/json")) {
    throw new Error("Backend nije vratio JSON.");
  }

  const podaci = await odgovor.json();

  if (!Array.isArray(podaci)) {
    throw new Error("Backend nije vratio popis izleta.");
  }

  return podaci;
}

// Dohvat jednog izleta za stranicu detalja
export async function dohvatiIzlet(id, signal) {
  const odgovor = await fetch(
      `/api/izleti/${encodeURIComponent(id)}`,
      { signal }
  );

  if (odgovor.status === 404) {
    throw new Error("Traženi izlet ne postoji.");
  }

  if (!odgovor.ok) {
    throw new Error(`Dohvat nije uspio (HTTP ${odgovor.status}).`);
  }

  const tip = odgovor.headers.get("content-type") || "";

  if (!tip.includes("application/json")) {
    throw new Error("Backend nije vratio JSON.");
  }

  const izlet = await odgovor.json();

  if (!izlet || typeof izlet !== "object" || Array.isArray(izlet)) {
    throw new Error("Backend nije vratio podatke jednog izleta.");
  }

  return izlet;
}