async function zahtjev(url, options ={}){
    const odgovor = await fetch(url, {
        ...options,
        credentials: "include",
        redirect: "manual"
    })

    if(odgovor.status===401 || odgovor.type === "opaqueredirect"){
        throw new Error("Potrebna je prijava.");
    }

    if (odgovor.status === 403) {
        throw new Error("Nemate administratorske ovlasti.");
    }

    if (!odgovor.ok) {
        const podaci = await odgovor.json().catch(() => null);

        throw new Error(
            podaci?.message ||
            `Radnja nije uspjela (HTTP ${odgovor.status}).`
        );
    }

    if (odgovor.status === 204) return null;

    return odgovor.json();
}


export function dohvatiAdminKorisnika(signal) {
    return zahtjev("/api/me", { signal });
}

export function dohvatiPonuduZaUpravljanje(signal) {
    return zahtjev("/api/izleti", { signal });
}

export function dohvatiIzletZaUpravljanje(id) {
    return zahtjev(`/api/izleti/${encodeURIComponent(id)}`);
}

export function spremiIzlet(id,podaci){
    return zahtjev(
        id == null ? "/api/izleti" : `/api/izleti/${id}`,{
            method: "POST",
            headers: {
                "Content-Type":"application/json"
            },
            body: JSON.stringify(podaci)
        }
    )
}

export function obrisiIzlet(id){
    return zahtjev(`/api/izleti/${id}`,{
        method:"DELETE"
    })
}