package hr.fer.progi.backend.mapper.helper;

import hr.fer.progi.backend.model.Termin;
import hr.fer.progi.backend.service.TerminService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TerminMapperHelper {
    private final TerminService terminService;

    @Named("brojSlobodnihMjesta")
    public Integer brojSlobodnihMjesta(Termin termin) {
        return terminService.dohvatiBrojSlobodnihMjesta(termin);
    }

}
