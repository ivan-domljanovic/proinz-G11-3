package hr.fer.progi.backend.security;

import lombok.Getter;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

@Getter
public class AppOidcUser extends DefaultOidcUser {

    private final Long userId;

    public AppOidcUser(OidcUser googleUser, Long userId) {
        super(googleUser.getAuthorities(), googleUser.getIdToken(), googleUser.getUserInfo());
        this.userId = userId;
    }
}
