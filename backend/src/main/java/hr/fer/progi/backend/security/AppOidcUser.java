package hr.fer.progi.backend.security;

import hr.fer.progi.backend.enums.Uloga;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.HashSet;
import java.util.Set;

@Getter
public class AppOidcUser extends DefaultOidcUser {

    private final Long userId;

    public AppOidcUser(OidcUser googleUser, Long userId, Set<Uloga> uloge) {
        var authorities = popuniUloge(uloge);
        super(authorities, googleUser.getIdToken(), googleUser.getUserInfo());
        this.userId = userId;
    }

    private static Set<GrantedAuthority> popuniUloge(Set<Uloga> uloge) {
        Set<GrantedAuthority> ovlasti = new HashSet<>();

        for (var u : uloge)
            ovlasti.add(new SimpleGrantedAuthority("ROLE_" + u.name()));

        return ovlasti;
    }
}
