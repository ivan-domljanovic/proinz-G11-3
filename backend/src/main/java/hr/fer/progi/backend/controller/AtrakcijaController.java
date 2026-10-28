package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.dto.request.AtrakcijaCreateRequest;
import hr.fer.progi.backend.dto.request.AtrakcijaUpdateRequest;
import hr.fer.progi.backend.dto.response.AtrakcijaResponse;
import hr.fer.progi.backend.service.AtrakcijaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/atrakcije")
public class AtrakcijaController {

    private final AtrakcijaService atrakcijaService;

    @GetMapping("")
    public ResponseEntity<List<AtrakcijaResponse>> dohvatiSveAtrakcije() {
        return ResponseEntity.ok(atrakcijaService.dohvatiSveAtrakcije());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtrakcijaResponse> dohvatiAtrakciju(@PathVariable Long id) {
        return ResponseEntity.ok(atrakcijaService.dohvatiAtrakciju(id));
    }

    @PostMapping("")
    public ResponseEntity<AtrakcijaResponse> kreirajAtrakciju(
            @Valid @RequestBody
            AtrakcijaCreateRequest request) {
        var data = atrakcijaService.kreirajAtrakciju(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtrakcijaResponse> azurirajAtrakciju(@PathVariable Long id,
                                                               @Valid @RequestBody
                                                               AtrakcijaUpdateRequest request) {
        var data = atrakcijaService.azurirajAtrakciju(id, request);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisiAtrakciju(@PathVariable Long id) {
        atrakcijaService.obrisiAtrakciju(id);
        return ResponseEntity.noContent().build();
    }
}
