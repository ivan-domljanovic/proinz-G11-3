package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.dto.request.IzletCreateRequest;
import hr.fer.progi.backend.dto.request.IzletUpdateRequest;
import hr.fer.progi.backend.dto.response.IzletDetailResponse;
import hr.fer.progi.backend.dto.response.TerminResponse;
import hr.fer.progi.backend.service.IzletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/izleti")
@RequiredArgsConstructor
public class IzletController {

    private final IzletService izletService;

    @GetMapping("")
    public ResponseEntity<List<IzletDetailResponse>> sviIzleti() {
        var data = izletService.dohvatiSveIzlete();
        return ResponseEntity.ok(data);
    }

    // todo: dodat filtriranje u url-u
    @GetMapping("/{id}")
    public ResponseEntity<IzletDetailResponse> detaljiOdredenogIzleta(@PathVariable("id") Long id) {
        var data = izletService.dohvatiIzlet(id);
        return ResponseEntity.ok(data);
    }

    @PostMapping("")
    public ResponseEntity<IzletDetailResponse> kreirajIzlet(
            @Valid @RequestBody IzletCreateRequest request) {
        var data = izletService.kreirajIzlet(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> obrisiIzlet(@PathVariable Long id) {
        izletService.obrisiIzlet(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<IzletDetailResponse> azurirajIzlet(@PathVariable Long id, @Valid @RequestBody
    IzletUpdateRequest request) {
        var data = izletService.azurirajIzlet(id, request);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}/termini")
    public ResponseEntity<List<TerminResponse>> dohvatiTermineIzleta(@PathVariable Long id) {
        return ResponseEntity.ok(izletService.dohvatiTermineIzleta(id));
    }
}
