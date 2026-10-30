package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.enums.Uloga;
import hr.fer.progi.backend.model.Korisnik;
import hr.fer.progi.backend.repository.KorisnikRepository;
import hr.fer.progi.backend.security.AppOidcUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GoogleOidcUserService extends OidcUserService {

    private final KorisnikRepository korisnikRepository;

    @Override
    @Transactional
    public OidcUser loadUser(OidcUserRequest userRequest) {
        OidcUser googleUser = super.loadUser(userRequest);

        var korisnik = korisnikRepository
                .findBySub(googleUser.getSubject())
                .orElseGet(() -> {
                    var novi = new Korisnik();
                    novi.setSub(googleUser.getSubject());
                    novi.setIme(googleUser.getFullName());
                    novi.setEmail(googleUser.getEmail());
                    novi.setAvatarUrl(googleUser.getPicture());
                    novi.setUloge(new HashSet<>(Set.of(Uloga.KORISNIK)));

                    return korisnikRepository.save(novi);
                });

        return new AppOidcUser(googleUser, korisnik.getId());
    }
}
