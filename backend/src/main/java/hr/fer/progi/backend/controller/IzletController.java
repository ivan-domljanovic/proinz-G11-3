package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.dto.ApiResponse;
import hr.fer.progi.backend.dto.IzletDetailResponse;
import hr.fer.progi.backend.service.IzletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/izleti")
public class IzletController {

    private final IzletService izletService;

    public IzletController(IzletService izletService) {
        this.izletService = izletService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<IzletDetailResponse>> detaljiOdredenogIzleta(@PathVariable("id") Long id) {
        var data = izletService.dohvatiIzlet(id);
        return ResponseEntity.ok(ApiResponse.ok(data));
    }
}
