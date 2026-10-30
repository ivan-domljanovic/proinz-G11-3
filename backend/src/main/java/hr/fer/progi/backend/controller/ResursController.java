package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.dto.request.ResursCreateRequest;
import hr.fer.progi.backend.dto.request.ResursUpdateRequest;
import hr.fer.progi.backend.dto.response.ResursDetailsResponse;
import hr.fer.progi.backend.mapper.ResursMapper;
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
    private final ResursMapper resursMapper;

    @GetMapping("")
    public ResponseEntity<List<ResursDetailsResponse>> sviResursi() {
        var data = resursService.dohvatiSveResurse().stream().map(
                resursMapper::toResponse).toList();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResursDetailsResponse> dohvatiResurs(@PathVariable Long id) {
        var data = resursService.dohvatiResurs(id);
        return ResponseEntity.ok(resursMapper.toResponse(data));
    }

    @PostMapping("")
    public ResponseEntity<ResursDetailsResponse> kreirajResurs(
            @Valid @RequestBody ResursCreateRequest request) {
        var data = resursService.kreirajResurs(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(resursMapper.toResponse(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResursDetailsResponse> azurirajResurs(@PathVariable Long id,
                                                                @Valid @RequestBody
                                                                ResursUpdateRequest request) {
        var data = resursService.azurirajResurs(id, request);
        return ResponseEntity.ok(resursMapper.toResponse(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisiResurs(@PathVariable Long id) {
        resursService.obrisiResurs(id);
        return ResponseEntity.noContent().build();
    }
}
