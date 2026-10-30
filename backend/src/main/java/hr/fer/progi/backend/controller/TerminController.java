package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.dto.request.*;
import hr.fer.progi.backend.dto.response.TerminResponse;
import hr.fer.progi.backend.dto.response.TerminResursResponse;
import hr.fer.progi.backend.mapper.TerminMapper;
import hr.fer.progi.backend.mapper.TerminResursMapper;
import hr.fer.progi.backend.service.TerminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/termini")
public class TerminController {

    private final TerminService terminService;
    private final TerminMapper terminMapper;
    private final TerminResursMapper terminResursMapper;

    @GetMapping("")
    public ResponseEntity<List<TerminResponse>> dohvatiSveTermine() {
        var data = terminService.dohvatiSveTermine().stream().map(
                terminMapper::toResponse).toList();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TerminResponse> dohvatiTermin(@PathVariable Long id) {
        var data = terminService.dohvatiTermin(id);
        return ResponseEntity.ok(terminMapper.toResponse(data));
    }

    @PostMapping("")
    public ResponseEntity<TerminResponse> kreirajTermin(
            @Valid @RequestBody TerminCreateRequest request) {
        var data = terminService.kreirajTermin(request);
        return ResponseEntity.ok(terminMapper.toResponse(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TerminResponse> azurirajTermin(@PathVariable Long id, @Valid @RequestBody
    TerminUpdateRequest request) {
        var data = terminService.azurirajTermin(id, request);
        return ResponseEntity.ok(terminMapper.toResponse(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obirisiTermin(@PathVariable Long id) {
        terminService.obrisiTermin(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{terminId}/vodici")
    public ResponseEntity<TerminResponse> postaviVodice(@PathVariable Long terminId,
                                                        @Valid @RequestBody
                                                        TerminSetVodiciRequest request) {
        var data = terminService.postaviVodice(terminId, request);
        return ResponseEntity.ok(terminMapper.toResponse(data));
    }

    @GetMapping("/{id}/resursi")
    public ResponseEntity<List<TerminResursResponse>> sviResursiTermina(@PathVariable Long id) {
        var data = terminService.dohvatiSveResurseTermina(id).stream().map(
                terminResursMapper::toResponse).toList();
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{terminId}/resursi/{resursId}")
    public ResponseEntity<Void> makniResursTerminu(@PathVariable Long terminId,
                                                   @PathVariable Long resursId) {
        terminService.makniResursTerminu(terminId, resursId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/resursi")
    public ResponseEntity<TerminResursResponse> dodijeliResursTerminu(@PathVariable Long id,
                                                                      @Valid @RequestBody
                                                                      TerminResursSetRequest request) {
        var data = terminService.dodijeliResursTerminu(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(terminResursMapper.toResponse(data));
    }

    @PutMapping("/{terminId}/resursi/{resursId}")
    public ResponseEntity<TerminResursResponse> azurirajKolicinuResursaTerminu(
            @PathVariable Long terminId, @PathVariable Long resursId,
            @Valid @RequestBody TerminResursUpdateRequest request) {
        var data =
                terminService.azurirajKolicinuResursaTerminu(terminId, resursId, request);
        return ResponseEntity.ok(terminResursMapper.toResponse(data));
    }
}
