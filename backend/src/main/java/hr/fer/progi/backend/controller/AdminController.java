package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.dto.request.UlogaUpdateRequest;
import hr.fer.progi.backend.dto.response.KorisnikResponse;
import hr.fer.progi.backend.mapper.KorisnikMapper;
import hr.fer.progi.backend.service.KorisnikService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final KorisnikMapper korisnikMapper;
    private final KorisnikService korisnikService;

    @GetMapping("/korisnici")
    public ResponseEntity<List<KorisnikResponse>> dohvatiSve() {
        var data = korisnikService.dohvatiSveKorisnike();
        return ResponseEntity.ok(data.stream().map(korisnikMapper::toResponse).toList());
    }

    @GetMapping("/korisnici/{id}")
    public ResponseEntity<KorisnikResponse> dohvatiKorisnika(@PathVariable Long id) {
        var data = korisnikService.getKorisnik(id);
        return ResponseEntity.ok(korisnikMapper.toResponse(data));
    }

    @PostMapping("/korisnici/{id}/uloge")
    public ResponseEntity<KorisnikResponse> dohvatiKorisnika(@PathVariable Long id,
                                                             @Valid @RequestBody
                                                             UlogaUpdateRequest request) {
        var data = korisnikService.azurirajUlogeKorisniku(id, request);
        return ResponseEntity.ok(korisnikMapper.toResponse(data));
    }

}
