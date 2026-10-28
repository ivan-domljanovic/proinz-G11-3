package hr.fer.progi.backend.dto.request;

public record IzletCreateRequest(String naziv, String opis,
                                 String kategorijaAktivnosti,
                                 String lokacija,
                                 Long[] atrakcije,
                                 Long[] dodatneUsluge) {
    public IzletCreateRequest {
        atrakcije = atrakcije == null ? new Long[0] : atrakcije;
        dodatneUsluge = dodatneUsluge == null
                ? new Long[0]
                : dodatneUsluge;
    }
}
