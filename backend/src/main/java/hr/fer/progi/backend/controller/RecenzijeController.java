package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.dto.request.RIzletaUpdateRequest;
import hr.fer.progi.backend.dto.request.RVodicaUpdateRequest;
import hr.fer.progi.backend.dto.response.RecenzijaIzletaResponse;
import hr.fer.progi.backend.dto.response.RecenzijaVodicaResponse;
import hr.fer.progi.backend.mapper.RecenzijaIzletaMapper;
import hr.fer.progi.backend.mapper.RecenzijaVodicaMapper;
import hr.fer.progi.backend.security.AppOidcUser;
import hr.fer.progi.backend.service.RecenzijaIzletaService;
import hr.fer.progi.backend.service.RecenzijaVodicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RecenzijeController {

    private final RecenzijaVodicaService recenzijaVodicaService;
    private final RecenzijaVodicaMapper recenzijaVodicaMapper;
    private final RecenzijaIzletaMapper recenzijaIzletaMapper;
    private final RecenzijaIzletaService recenzijaIzletaService;

    @PutMapping("/recenzije-vodica/{id}")
    public ResponseEntity<RecenzijaVodicaResponse> azuriraj(@PathVariable Long id,
                                                            @Valid @RequestBody
                                                            RVodicaUpdateRequest request,
                                                            @AuthenticationPrincipal
                                                            AppOidcUser user) {
        var data = recenzijaVodicaService.azurirajRecenzijuVodica(id, user.getUserId(), request);
        return ResponseEntity.ok(recenzijaVodicaMapper.toResponse(data));
    }

    @DeleteMapping("/recenzije-vodica/{id}")
    public ResponseEntity<Void> obrisi(@PathVariable Long id,
                                       @AuthenticationPrincipal
                                       AppOidcUser user) {
        recenzijaVodicaService.obrisiRecenzijuVodica(id, user.getUserId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/recenzije-izleta/{id}")
    public ResponseEntity<RecenzijaIzletaResponse> azuriraj(@PathVariable Long id,
                                                            @Valid @RequestBody
                                                            RIzletaUpdateRequest request,
                                                            @AuthenticationPrincipal
                                                            AppOidcUser user) {

        var data = recenzijaIzletaService.azurirajRecenzijuIzleta(id, user.getUserId(), request);
        return ResponseEntity.ok(recenzijaIzletaMapper.toResponse(data));
    }

    @DeleteMapping("/recenzije-izleta/{id}")
    public ResponseEntity<Void> obrisiRecenzijuIzleta(@PathVariable Long id,
                                                      @AuthenticationPrincipal
                                                      AppOidcUser user) {
        recenzijaIzletaService.obrisiRecenzijuIzleta(id, user.getUserId());
        return ResponseEntity.noContent().build();
    }
}
