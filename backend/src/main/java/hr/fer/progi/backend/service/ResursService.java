package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.request.ResursCreateRequest;
import hr.fer.progi.backend.dto.request.ResursUpdateRequest;
import hr.fer.progi.backend.dto.response.ResursDetailsResponse;

import java.util.List;

public interface ResursService {

    List<ResursDetailsResponse> dohvatiSveResurse();

    ResursDetailsResponse dohvatiResurs(Long id);

    ResursDetailsResponse kreirajResurs(ResursCreateRequest request);

    ResursDetailsResponse azurirajResurs(Long id, ResursUpdateRequest request);

    void obrisiResurs(Long id);
}
