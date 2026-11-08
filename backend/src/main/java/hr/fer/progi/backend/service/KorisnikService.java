package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.request.UlogaUpdateRequest;
import hr.fer.progi.backend.model.Korisnik;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface KorisnikService {
    Korisnik getKorisnik(Long id);

    List<Korisnik> dohvatiSveKorisnike();

    Set<Korisnik> dohvatiSveKorisnikePoId(Collection<Long> ids);

    Korisnik azurirajUlogeKorisniku(Long id, UlogaUpdateRequest request);
}
