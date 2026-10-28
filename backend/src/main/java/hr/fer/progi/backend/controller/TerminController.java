package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.dto.request.TerminCreateRequest;
import hr.fer.progi.backend.dto.request.TerminSetVodiciRequest;
import hr.fer.progi.backend.dto.request.TerminUpdateRequest;
import hr.fer.progi.backend.dto.response.TerminResponse;
import hr.fer.progi.backend.service.TerminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/termini")
public class TerminController {

    private final TerminService terminService;

    @GetMapping("")
    public ResponseEntity<List<TerminResponse>> dohvatiSveTermine() {
        var data = terminService.dohvatiSveTermine();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TerminResponse> dohvatiTermin(@PathVariable Long id) {
        var data = terminService.dohvatiTermin(id);
        return ResponseEntity.ok(data);
    }

    @PostMapping("")
    public ResponseEntity<TerminResponse> kreirajTermin(@Valid @RequestBody TerminCreateRequest request) {
        var data = terminService.kreirajTermin(request);
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TerminResponse> azurirajTermin(@PathVariable Long id, @Valid @RequestBody
    TerminUpdateRequest request) {
        var data = terminService.azurirajTermin(id, request);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> obirisiTermin(@PathVariable Long id) {
        terminService.obrisiTermin(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{terminId}/vodici")
    public ResponseEntity<TerminResponse> postaviVodice(@PathVariable Long terminId, @Valid @RequestBody
    TerminSetVodiciRequest request) {
        var data = terminService.postaviVodice(terminId, request);
        return ResponseEntity.ok(data);
    }
}
