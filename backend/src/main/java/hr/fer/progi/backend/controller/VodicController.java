package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.dto.response.RecenzijaVodicaResponse;
import hr.fer.progi.backend.mapper.RecenzijaVodicaMapper;
import hr.fer.progi.backend.service.RecenzijaVodicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vodici")
public class VodicController {

    private final RecenzijaVodicaService recenzijaVodicaService;
    private final RecenzijaVodicaMapper recenzijaVodicaMapper;

    @GetMapping("/{id}/recenzije")
    public ResponseEntity<List<RecenzijaVodicaResponse>> dohvatiRecenzije(@PathVariable Long id) {
        var data = recenzijaVodicaService.dohvatiSveRecenzijeVodica(id).stream().map(
                recenzijaVodicaMapper::toResponse).toList();
        return ResponseEntity.ok(data);
    }
}
