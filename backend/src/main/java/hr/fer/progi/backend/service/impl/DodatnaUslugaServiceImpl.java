package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.request.DUslugeCreateRequest;
import hr.fer.progi.backend.dto.request.DUslugeUpdateRequest;
import hr.fer.progi.backend.exception.ResourceNotFoundException;
import hr.fer.progi.backend.model.DodatnaUsluga;
import hr.fer.progi.backend.repository.DodatnaUslugaRepository;
import hr.fer.progi.backend.service.DodatnaUslugaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DodatnaUslugaServiceImpl implements DodatnaUslugaService {
    private final DodatnaUslugaRepository dodatnaUslugaRepository;

    @Override
    public List<DodatnaUsluga> dohvatiSveDodatneUsluge() {
        return dodatnaUslugaRepository.findAll();
    }

    @Override
    public DodatnaUsluga dohvatiDodatnuUslugu(Long id) {
        return dodatnaUslugaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Dodatna usluga ne postoji"));
    }

    @Override
    @Transactional
    public DodatnaUsluga kreirajDodatnuUslugu(DUslugeCreateRequest request) {
        var usluga = new DodatnaUsluga(request.naziv(), request.opis(), request.cijena());
        return dodatnaUslugaRepository.save(usluga);
    }

    @Override
    @Transactional
    public DodatnaUsluga azurirajDodatnuUslugu(Long id, DUslugeUpdateRequest request) {
        var usluga = dodatnaUslugaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Dodatna usluga s tim id ne postoji"));

        usluga.setCijena(request.cijena());
        usluga.setNaziv(request.naziv());
        usluga.setOpis(request.opis());

        return dodatnaUslugaRepository.save(usluga);
    }

    @Override
    @Transactional
    public void obrisiDodatnuUslugu(Long id) {
        if (!dodatnaUslugaRepository.existsById(id))
            throw new ResourceNotFoundException("Dodatna usluga s tim id ne postoji");

        dodatnaUslugaRepository.deleteById(id);
    }
}
