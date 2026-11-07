package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.dto.response.KorisnikResponse;
import hr.fer.progi.backend.mapper.KorisnikMapper;
import hr.fer.progi.backend.security.AppOidcUser;
import hr.fer.progi.backend.service.KorisnikService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final KorisnikService korisnikService;
    private final KorisnikMapper korisnikMapper;

    @GetMapping("/me")
    public KorisnikResponse me(@AuthenticationPrincipal AppOidcUser user) {
        return korisnikMapper.toResponse(korisnikService.dohvatiKorisnika(user.getUserId()));
    }
}
