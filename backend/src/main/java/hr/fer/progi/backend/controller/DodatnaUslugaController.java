package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.dto.request.DUslugeCreateRequest;
import hr.fer.progi.backend.dto.request.DUslugeUpdateRequest;
import hr.fer.progi.backend.dto.response.DodatnaUslugaResponse;
import hr.fer.progi.backend.mapper.DodatnaUslugaMapper;
import hr.fer.progi.backend.service.DodatnaUslugaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dodatneUsluge")
public class DodatnaUslugaController {

    private final DodatnaUslugaService dodatnaUslugaService;
    private final DodatnaUslugaMapper dodatnaUslugaMapper;

    @GetMapping("")
    public ResponseEntity<List<DodatnaUslugaResponse>> dohvatiSve() {
        var data = dodatnaUslugaService.dohvatiSveDodatneUsluge().stream().map(
                dodatnaUslugaMapper::toResponse).toList();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DodatnaUslugaResponse> dohvatiUslugu(@PathVariable Long id) {
        var data = dodatnaUslugaService.dohvatiDodatnuUslugu(id);
        return ResponseEntity.ok(dodatnaUslugaMapper.toResponse(data));
    }

    @PostMapping("")
    public ResponseEntity<DodatnaUslugaResponse> kreirajNovu(
            @Valid @RequestBody DUslugeCreateRequest request) {
        var data = dodatnaUslugaService.kreirajDodatnuUslugu(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dodatnaUslugaMapper.toResponse(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DodatnaUslugaResponse> azuriraj(@PathVariable Long id,
                                                             @Valid @RequestBody
                                                             DUslugeUpdateRequest request) {
        var data = dodatnaUslugaService.azurirajDodatnuUslugu(id, request);
        return ResponseEntity.ok(dodatnaUslugaMapper.toResponse(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisi(@PathVariable Long id) {
        dodatnaUslugaService.obrisiDodatnuUslugu(id);
        return ResponseEntity.noContent().build();
    }

}
