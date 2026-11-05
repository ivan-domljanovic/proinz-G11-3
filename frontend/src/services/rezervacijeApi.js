export class PotrebnaPrijava extends Error {
    constructor() {
        super("Za rezervaciju termina trebate se prijaviti.");
    }
}

function provjeriPrijavu(odgovor) {
    if ([401, 403].includes(odgovor.status) || odgovor.type === "opaqueredirect" || odgovor.redirected) {
        throw new PotrebnaPrijava();
    }
}

async function provjeriSesiju(signal) {
    // Provjera sesije prije slanja rezervacije, uključujući dev profil.
    const sesija = await fetch("/me", { credentials: "include", redirect: "manual", signal });
    provjeriPrijavu(sesija);
    if (!sesija.ok) {
        const greska = new Error("Nije moguće provjeriti prijavu. Ako niste prijavljeni, otvorite prijavu pa pokušajte ponovno.");
        greska.provjeraPrijave = true;
        throw greska;
    }
    const korisnik = await sesija.json();
    if (!korisnik?.sub) throw new PotrebnaPrijava();
}

export async function dohvatiRezervacije(signal) {
    await provjeriSesiju(signal);
    const odgovor = await fetch("/api/rezervacije", { credentials: "include", redirect: "manual", signal });
    provjeriPrijavu(odgovor);
    if (!odgovor.ok) throw new Error("Dohvat rezervacija nije uspio. Pokušajte ponovno.");
    const podaci = await odgovor.json();
    if (!Array.isArray(podaci)) throw new Error("Poslužitelj nije vratio popis rezervacija.");
    return podaci;
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
        throw new Error(rezultat?.message || "Rezervacija nije uspjela. Pokušajte ponovno.");
    }
    if (!rezultat?.id || !rezultat.status) {
        throw new Error("Poslužitelj nije vratio potvrdu. Provjerite svoje rezervacije prije ponovnog slanja.");
    }
    return rezultat;
}
