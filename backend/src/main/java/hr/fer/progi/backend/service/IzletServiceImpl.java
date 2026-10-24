package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.IzletCreateRequest;
import hr.fer.progi.backend.dto.IzletDetailResponse;
import hr.fer.progi.backend.dto.TerminResponse;
import hr.fer.progi.backend.exception.ResourceNotFoundException;
import hr.fer.progi.backend.mapper.IzletMapper;
import hr.fer.progi.backend.model.Izlet;
import hr.fer.progi.backend.repository.AtrakcijaRepository;
import hr.fer.progi.backend.repository.DodatnaUslugaRepository;
import hr.fer.progi.backend.repository.IzletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IzletServiceImpl implements IzletService {

    private final IzletRepository izletRepository;
    private final IzletMapper izletMapper;
    private final AtrakcijaRepository atrakcijaRepository;
    private final DodatnaUslugaRepository dodatnaUslugaRepository;

    public IzletDetailResponse dohvatiIzlet(Long id) {
        var izlet = izletRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Izlet s tim id ne postoji"));
        return izletMapper.toResponse(izlet);
    }

    public List<IzletDetailResponse> dohvatiSveIzlete() {
        return izletRepository.findAll().stream().map(izletMapper::toResponse).toList();
    }

    @Transactional
    public IzletDetailResponse kreirajIzlet(IzletCreateRequest request) {
//        todo: dodat validaciju
        var atrakcije = atrakcijaRepository.findAllById(List.of(request.atrakcije()));
        var dodatneUsluge = dodatnaUslugaRepository.findAllById(List.of(request.dodatneUsluge()));

        var izlet = new Izlet(request.naziv(), request.opis(), request.kategorijaAktivnosti(),
                request.trajanjeMinuta(), request.lokacija(), request.mjestoPolaska(),
                request.cijena(), request.kapacitet(), request.potrebanBrojVodica(), atrakcije,
                dodatneUsluge);

        var izletDb = izletRepository.save(izlet);

        return izletMapper.toResponse(izletDb);
    }

    @Transactional
    public void obrisiIzlet(Long id) {
//        todo: kaskada brisanja?? provjere?? obrisat dodjeljene termine itd...
        if (!izletRepository.existsById(id))
            throw new ResourceNotFoundException("Izlet s tim id ne postoji");
        izletRepository.deleteById(id);
    }

    @Override
    public IzletDetailResponse azurirajIzlet(Long id) {
        return null;
    }

    @Override
    public List<TerminResponse> dohvatiTermineIzleta(Long id) {
        return List.of();
    }

    @Override
    public TerminResponse dodajTerminIzletu(Long id) {
        return null;
    }
}
