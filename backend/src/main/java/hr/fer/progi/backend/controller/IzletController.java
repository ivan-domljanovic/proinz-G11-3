package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.dto.IzletDetailResponse;
import hr.fer.progi.backend.service.IzletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    public ResponseEntity<IzletDetailResponse> detaljiOdredenogIzleta(@PathVariable("id") Long id) {
        var data = izletService.dohvatiIzlet(id);
        return ResponseEntity.ok(data);
    }
}
