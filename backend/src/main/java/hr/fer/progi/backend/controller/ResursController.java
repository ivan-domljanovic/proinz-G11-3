package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.dto.request.ResursCreateRequest;
import hr.fer.progi.backend.dto.request.ResursUpdateRequest;
import hr.fer.progi.backend.dto.response.ResursDetailsResponse;
import hr.fer.progi.backend.service.ResursService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resursi")
@RequiredArgsConstructor
public class ResursController {

    private final ResursService resursService;

    @GetMapping("")
    public ResponseEntity<List<ResursDetailsResponse>> sviResursi() {
        return ResponseEntity.ok(resursService.dohvatiSveResurse());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResursDetailsResponse> dohvatiResurs(@PathVariable Long id) {
        return ResponseEntity.ok(resursService.dohvatiResurs(id));
    }

    @PostMapping("")
    public ResponseEntity<ResursDetailsResponse> kreirajResurs(
            @Valid @RequestBody ResursCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(resursService.kreirajResurs(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResursDetailsResponse> azurirajResurs(@PathVariable Long id, @Valid @RequestBody
        ResursUpdateRequest request) {
        var data = resursService.azurirajResurs(id, request);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> obrisiResurs(@PathVariable Long id) {
        resursService.obrisiResurs(id);
        return ResponseEntity.noContent().build();
    }
}
