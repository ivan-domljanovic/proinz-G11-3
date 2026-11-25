package hr.fer.progi.backend.security;

import hr.fer.progi.backend.enums.Uloga;
import hr.fer.progi.backend.repository.KorisnikRepository;
import hr.fer.progi.backend.service.impl.GoogleOidcUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.web.cors.*;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${app.frontend-url}")
    private String frontendUrl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   GoogleOidcUserService googleOidcUserService,
                                                   KorisnikRepository korisnikRepository) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new AktivneUlogeFilter(korisnikRepository),
                        AuthorizationFilter.class)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/oauth2/authorization/**",
                                        "/login/oauth2/code/**").permitAll()

                                .requestMatchers(HttpMethod.GET, "/api/izleti",
                                        "/api/izleti/**", "/api/vodici/*/recenzije").permitAll()

                                .requestMatchers("/api/me", "/api/logout").authenticated()

                                .requestMatchers("/api/recenzije-izleta/*",
                                        "/api/recenzije-vodica/*").authenticated()

                                .requestMatchers("/api/rezervacije", "/api/rezervacije/**")
                                .authenticated()

                                .requestMatchers("/api/termini", "/api/termini/**",
                                        "/api/izleti", "/api/izleti/**",
                                        "/api/resursi", "/api/resursi/**",
                                        "/api/atrakcije", "/api/atrakcije/**",
                                        "/api/dodatne-usluge", "/api/dodatne-usluge/**").hasAnyRole(
                                        Uloga.ADMINISTRATOR.name(),
                                        Uloga.ORGANIZATOR.name())

                                .requestMatchers("/api/admin", "/api/admin/**").hasRole(
                                        Uloga.ADMINISTRATOR.name())

                                .anyRequest().denyAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization/google") // iskljucuje springov login html
                        .userInfoEndpoint(info -> info
                                .oidcUserService(googleOidcUserService)
                        )
                        .defaultSuccessUrl(frontendUrl, true)
                        .failureUrl(frontendUrl + "/login")
                )
                .logout(logout -> logout
                        .logoutUrl("/api/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessHandler(
                                (request, response, authentication) -> response.setStatus(
                                        HttpStatus.NO_CONTENT.value()))
                );
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(
        @Value("${app.cors.allowed-origin}") String origin) {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(origin));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}