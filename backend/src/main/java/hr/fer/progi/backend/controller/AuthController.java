package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.security.AppOidcUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    @GetMapping("/me")
    public Map<String, Object> me(@AuthenticationPrincipal AppOidcUser user) {
        return Map.of(
                "sub", user.getSubject(),
                "email", user.getEmail(),
                "ime", user.getGivenName(),
                "prezime", user.getFamilyName(),
                "picture", user.getPicture()
        );
    }
}
