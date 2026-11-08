export class PotrebnaPrijava extends Error {
  constructor() {
    super("Za rezervaciju termina trebate se prijaviti.");
    this.provjeraPrijave = true;
  }
}

function provjeriPrijavu(odgovor) {
  if (
    [401, 403].includes(odgovor.status) ||
    odgovor.type === "opaqueredirect" ||
    odgovor.redirected
  ) {
    throw new PotrebnaPrijava();
  }
}

async function provjeriSesiju(signal) {
  // Provjera sesije prije slanja rezervacije, uključujući dev profil.
  const sesija = await fetch("/api/me", {
    credentials: "include",
    redirect: "manual",
    signal,
  });
  provjeriPrijavu(sesija);
  const korisnik = await sesija.json().catch(() => null);
  // Postojeći backend u dev profilu za praznu sesiju vraća JSON grešku 500.
  // Vite za ugašeni backend vraća grešku bez tog JSON odgovora.
  if (
    sesija.status === 500 &&
    korisnik?.path === "/api/me" &&
    korisnik?.status === 500
  ) {
    throw new PotrebnaPrijava();
  }
  if (!sesija.ok) {
    throw new Error(
      "Poslužitelj trenutačno nije dostupan ili provjera prijave nije uspjela. Pokušajte ponovno.",
    );
  }
  if (!korisnik)
    throw new Error(
      "Provjera prijave nije vratila ispravan odgovor. Pokušajte ponovno.",
    );
  if (!korisnik?.sub) throw new PotrebnaPrijava();
}

export async function dohvatiRezervacije(signal) {
  await provjeriSesiju(signal);
  const odgovor = await fetch("/api/rezervacije", {
    credentials: "include",
    redirect: "manual",
    signal,
  });
  provjeriPrijavu(odgovor);
  if (!odgovor.ok)
    throw new Error("Dohvat rezervacija nije uspio. Pokušajte ponovno.");
  const podaci = await odgovor.json();
  if (!Array.isArray(podaci))
    throw new Error("Poslužitelj nije vratio popis rezervacija.");
  return podaci;
}

export async function otkaziRezervaciju(id) {
  await provjeriSesiju();
  const odgovor = await fetch(`/api/rezervacije/${encodeURIComponent(id)}`, {
    method: "DELETE",
    credentials: "include",
    redirect: "manual",
  });
  provjeriPrijavu(odgovor);
  if (!odgovor.ok) {
    const podaci = await odgovor.json().catch(() => null);
    throw new Error(
      podaci?.message || "Otkazivanje nije uspjelo. Pokušajte ponovno.",
    );
  }
}

export async function rezervirajTermin(podaci) {
  await provjeriSesiju();

  const odgovor = await fetch("/api/rezervacije", {
    method: "POST",
    credentials: "include",
    redirect: "manual",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(podaci),
  });
  provjeriPrijavu(odgovor);
  const rezultat = await odgovor.json().catch(() => null);
  if (!odgovor.ok) {
    throw new Error(
      rezultat?.message || "Rezervacija nije uspjela. Pokušajte ponovno.",
    );
  }
  if (!rezultat?.id || !rezultat.status) {
    throw new Error(
      "Poslužitelj nije vratio potvrdu. Provjerite svoje rezervacije prije ponovnog slanja.",
    );
  }
  return rezultat;
}
