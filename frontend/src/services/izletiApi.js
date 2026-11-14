// Dohvat svih izleta za početnu stranicu

const API_URL = import.meta.env.VITE_API_URL ?? "";

export async function dohvatiIzlete(filteri, signal) {
  const param=new URLSearchParams();

  //ide po parovima [[kljuc, vrijednost], [brojOsoba, 2]] npr.
  for (const[kljuc, vrijednost] of Object.entries(filteri)) {
    if (vrijednost!==""){ //samo  ako ima vrijednost
      param.append(kljuc, vrijednost);
    } 
  }
  const odgovor=await fetch(`${API_URL}/api/izleti?${param}`, {signal});

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
      `${API_URL}//api/izleti/${encodeURIComponent(id)}`,
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