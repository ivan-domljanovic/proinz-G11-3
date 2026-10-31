package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.dto.request.IzletCreateRequest;
import hr.fer.progi.backend.dto.request.IzletSearchRequest;
import hr.fer.progi.backend.dto.request.IzletUpdateRequest;
import hr.fer.progi.backend.dto.response.IzletDetailResponse;
import hr.fer.progi.backend.dto.response.RecenzijaIzletaResponse;
import hr.fer.progi.backend.dto.response.TerminResponse;
import hr.fer.progi.backend.mapper.IzletMapper;
import hr.fer.progi.backend.mapper.RecenzijaIzletaMapper;
import hr.fer.progi.backend.mapper.TerminMapper;
import hr.fer.progi.backend.service.IzletService;
import hr.fer.progi.backend.service.RecenzijaIzletaService;
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
    private final IzletMapper izletMapper;
    private final TerminMapper terminMapper;
    private final RecenzijaIzletaService recenzijaIzletaService;
    private final RecenzijaIzletaMapper recenzijaIzletaMapper;

    @GetMapping("")
    public ResponseEntity<List<IzletDetailResponse>> sviIzleti(
            @Valid @ModelAttribute IzletSearchRequest request) {

        return ResponseEntity.ok(
                izletService.pretraziIzlete(request));
    }

    // todo: dodat filtriranje u url-u
    @GetMapping("/{id}")
    public ResponseEntity<IzletDetailResponse> detaljiOdredenogIzleta(@PathVariable("id") Long id) {
        var data = izletService.dohvatiIzlet(id);
        return ResponseEntity.ok(izletMapper.toResponse(data));
    }

    @PostMapping("")
    public ResponseEntity<IzletDetailResponse> kreirajIzlet(
            @Valid @RequestBody IzletCreateRequest request) {
        var data = izletService.kreirajIzlet(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(izletMapper.toResponse(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisiIzlet(@PathVariable Long id) {
        izletService.obrisiIzlet(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<IzletDetailResponse> azurirajIzlet(@PathVariable Long id,
                                                             @Valid @RequestBody
                                                             IzletUpdateRequest request) {
        var data = izletService.azurirajIzlet(id, request);
        return ResponseEntity.ok(izletMapper.toResponse(data));
    }

    @GetMapping("/{id}/termini")
    public ResponseEntity<List<TerminResponse>> dohvatiTermineIzleta(@PathVariable Long id) {
        var data = izletService.dohvatiTermineIzleta(id).stream().map(
                terminMapper::toResponse).toList();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}/recenzije")
    public ResponseEntity<List<RecenzijaIzletaResponse>> dohvatiRecenzije(@PathVariable Long id) {
        var data = recenzijaIzletaService.dohvatiSveRecenzijeIzleta(id).stream().map(
                recenzijaIzletaMapper::toResponse).toList();
        return ResponseEntity.ok(data);
    }
}
