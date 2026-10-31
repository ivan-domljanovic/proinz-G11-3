package hr.fer.progi.backend.controller;


import hr.fer.progi.backend.dto.request.RIzletaCreateRequest;
import hr.fer.progi.backend.dto.request.RVodicaCreateRequest;
import hr.fer.progi.backend.dto.request.RezervacijaRequest;
import hr.fer.progi.backend.dto.response.RecenzijaIzletaResponse;
import hr.fer.progi.backend.dto.response.RecenzijaVodicaResponse;
import hr.fer.progi.backend.dto.response.RezervacijaResponse;
import hr.fer.progi.backend.mapper.RecenzijaIzletaMapper;
import hr.fer.progi.backend.mapper.RecenzijaVodicaMapper;
import hr.fer.progi.backend.mapper.RezervacijaMapper;
import hr.fer.progi.backend.security.AppOidcUser;
import hr.fer.progi.backend.service.RecenzijaIzletaService;
import hr.fer.progi.backend.service.RecenzijaVodicaService;
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
    private final RecenzijaVodicaService recenzijaVodicaService;
    private final RecenzijaVodicaMapper recenzijaVodicaMapper;
    private final RecenzijaIzletaService recenzijaIzletaService;
    private final RecenzijaIzletaMapper recenzijaIzletaMapper;

    @GetMapping("")
    public ResponseEntity<List<RezervacijaResponse>> dohvatiSveKorisnikoveRezervacije(
            @AuthenticationPrincipal OAuth2User user
    ) {
        var rezervacije = rezervacijaService.dohvatiSveKorisnikoveRezervacije(
                user.getAttribute("sub"));
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
        return ResponseEntity.status(HttpStatus.CREATED).body(
                rezervacijaMapper.toResponse(rezervacija));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> otkaziRezervaciju(
            @PathVariable Long id,
            @AuthenticationPrincipal OAuth2User user
    ) {
        rezervacijaService.otkazi(id, user.getAttribute("sub"));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/recenzije-vodica")
    public ResponseEntity<RecenzijaVodicaResponse> recenzirajVodica(@PathVariable Long id,
                                                                    @Valid @RequestBody
                                                                    RVodicaCreateRequest request,
                                                                    @AuthenticationPrincipal
                                                                    AppOidcUser user) {
        var autorId = user.getUserId();
        var data = recenzijaVodicaService.dodajRecenzijuVodica(id, autorId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                recenzijaVodicaMapper.toResponse(data));
    }

    @PostMapping("/{id}/recenzije-izleta")
    public ResponseEntity<RecenzijaIzletaResponse> recenzirajIzlet(@PathVariable Long id,
                                                                   @Valid @RequestBody
                                                                   RIzletaCreateRequest request,
                                                                   @AuthenticationPrincipal
                                                                   AppOidcUser user) {
        var autorId = user.getUserId();
        var data = recenzijaIzletaService.dodajRecenzijuIzleta(id, autorId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                recenzijaIzletaMapper.toResponse(data));
    }
}