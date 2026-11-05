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

export async function rezervirajTermin(podaci) {
    // Provjera sesije prije slanja rezervacije, uključujući dev profil.
    const sesija = await fetch("/me", { credentials: "include", redirect: "manual" });
    provjeriPrijavu(sesija);
    if (!sesija.ok) {
        const greska = new Error("Nije moguće provjeriti prijavu. Ako niste prijavljeni, otvorite prijavu pa pokušajte ponovno.");
        greska.provjeraPrijave = true;
        throw greska;
    }
    const korisnik = await sesija.json();
    if (!korisnik?.sub) throw new PotrebnaPrijava();

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
