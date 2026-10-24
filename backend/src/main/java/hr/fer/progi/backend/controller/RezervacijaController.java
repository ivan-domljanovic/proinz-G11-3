package hr.fer.progi.backend.controller;


import hr.fer.progi.backend.dto.RezervacijaRequest;
import hr.fer.progi.backend.dto.RezervacijaResponse;
import hr.fer.progi.backend.mapper.RezervacijaMapper;
import hr.fer.progi.backend.service.RezervacijaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
            @RequestBody RezervacijaRequest request
    ) {
        var rezervacija = rezervacijaService.rezerviraj(request);
        return ResponseEntity.ok(rezervacijaMapper.toResponse(rezervacija));
    }
}
