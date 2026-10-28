package hr.fer.progi.backend.controller;


import hr.fer.progi.backend.dto.request.RezervacijaRequest;
import hr.fer.progi.backend.dto.response.RezervacijaResponse;
import hr.fer.progi.backend.mapper.RezervacijaMapper;
import hr.fer.progi.backend.service.RezervacijaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rezervacije")
@RequiredArgsConstructor
public class RezervacijaController {

    private final RezervacijaService rezervacijaService;
    private final RezervacijaMapper rezervacijaMapper;

    @GetMapping("")
    public ResponseEntity<List<RezervacijaResponse>> dohvatiSveKorisnikoveRezervacije(
            @AuthenticationPrincipal OAuth2User user
    ) {
        var rezervacije = rezervacijaService.dohvatiSveKorisnikoveRezervacije(user.getAttribute("sub"));
        return ResponseEntity.ok(rezervacije.stream().map(rezervacijaMapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RezervacijaResponse> dohvatiRezervaciju(
            @PathVariable Long id,
            @AuthenticationPrincipal OAuth2User user
    ) {
        var rezervacija = rezervacijaService.dohvatiRezervaciju(id, user.getAttribute("sub"));
        return ResponseEntity.ok(rezervacijaMapper.toResponse(rezervacija));
    }

    @PostMapping("")
    public ResponseEntity<RezervacijaResponse> rezerviraj(
            @Valid @RequestBody RezervacijaRequest request,
            @AuthenticationPrincipal OAuth2User user
    ) {
        var rezervacija = rezervacijaService.rezerviraj(request, user.getAttribute("sub"));
        return ResponseEntity.status(HttpStatus.CREATED).body(rezervacijaMapper.toResponse(rezervacija));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> otkaziRezervaciju(
            @PathVariable Long id,
            @AuthenticationPrincipal OAuth2User user
    ) {
        rezervacijaService.otkazi(id, user.getAttribute("sub"));
        return ResponseEntity.noContent().build();
    }
}