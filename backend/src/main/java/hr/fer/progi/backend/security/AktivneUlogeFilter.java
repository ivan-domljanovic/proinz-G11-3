package hr.fer.progi.backend.security;

import hr.fer.progi.backend.repository.KorisnikRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class AktivneUlogeFilter extends OncePerRequestFilter {

    private final KorisnikRepository korisnikRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof OAuth2AuthenticationToken token && token.getPrincipal() instanceof AppOidcUser user) {
            var korisnik = korisnikRepository.findById(user.getUserId());

            if (korisnik.isEmpty()) {
                SecurityContextHolder.clearContext();
                var session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }

                response.sendError(HttpStatus.UNAUTHORIZED.value());
                return;
            }

            var noviUser = new AppOidcUser(user, korisnik.get().getId(),
                    korisnik.get().getUloge());

            var noviAuth = new OAuth2AuthenticationToken(
                    noviUser,
                    noviUser.getAuthorities(),
                    token.getAuthorizedClientRegistrationId()
            );
            noviAuth.setDetails(token.getDetails());

            var context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(noviAuth);
            SecurityContextHolder.setContext(context);
        }

        filterChain.doFilter(request, response);
    }
}
