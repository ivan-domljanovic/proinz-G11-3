package hr.fer.progi.backend.controller;


import hr.fer.progi.backend.dto.request.RezervacijaRequest;
import hr.fer.progi.backend.dto.response.RezervacijaResponse;
import hr.fer.progi.backend.mapper.RezervacijaMapper;
import hr.fer.progi.backend.service.RezervacijaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rezervacije")
@RequiredArgsConstructor
public class RezervacijaController {

    private final RezervacijaService rezervacijaService;
    private final RezervacijaMapper rezervacijaMapper;

    @PostMapping("")
    public ResponseEntity<RezervacijaResponse> rezerviraj(
            @RequestBody RezervacijaRequest request,
            @AuthenticationPrincipal OAuth2User user
    ) {
        var rezervacija = rezervacijaService.rezerviraj(request, user.getAttribute("sub"));
        return ResponseEntity.status(HttpStatus.CREATED).body(rezervacijaMapper.toResponse(rezervacija));
    }
}