package hr.fer.progi.backend.security;

import hr.fer.progi.backend.enums.Uloga;
import hr.fer.progi.backend.service.impl.GoogleOidcUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChainDev(HttpSecurity http,
                                                      GoogleOidcUserService googleOidcUserService) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
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

                                .anyRequest().denyAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization/google") // iskljucuje springov login html
                        .userInfoEndpoint(info -> info
                                .oidcUserService(googleOidcUserService)
                        )
                        .defaultSuccessUrl("http://localhost:5173", true)
                        .failureUrl("http://localhost:5173/login")
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
}