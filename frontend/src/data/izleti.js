// Privremeni podaci za razvoj sučelja.
// Oblik API odgovora kasnije dogovaramo s backend ekipom.
export const izleti = [
  {
    id: 1,
    naziv: "Šetnja povijesnim Zagrebom",
    opis: "Upoznajte Gornji grad, njegove ulice i znamenitosti uz vodiča.",
    kategorijaAktivnosti: "Obilazak grada",
    trajanjeMinuta: 120,
    lokacija: "Zagreb",
    mjestoPolaska: "Trg bana Jelačića",
    cijena: 25,
    termini: [
      { id: 101, datumVrijeme: "2026-11-07T10:00:00", slobodnaMjesta: 12 },
      { id: 102, datumVrijeme: "2026-11-08T10:00:00", slobodnaMjesta: 0 },
    ],
  },
  {
    id: 2,
    naziv: "Planinarski izlet na Sljeme",
    opis: "Provedite dan u prirodi uz vođeni uspon i odmor na vrhu.",
    kategorijaAktivnosti: "Planinarenje",
    trajanjeMinuta: 300,
    lokacija: "Medvednica",
    mjestoPolaska: "Tunel Sljeme",
    cijena: 35,
    termini: [
      { id: 201, datumVrijeme: "2026-11-07T08:00:00", slobodnaMjesta: 6 },
    ],
  },
  {
    id: 3,
    naziv: "Biciklom kroz Samobor",
    opis: "Istražite okolicu Samobora na vođenoj biciklističkoj turi.",
    kategorijaAktivnosti: "Biciklistička tura",
    trajanjeMinuta: 180,
    lokacija: "Samobor",
    mjestoPolaska: "Trg kralja Tomislava",
    cijena: 40,
    termini: [
      { id: 301, datumVrijeme: "2026-11-08T09:00:00", slobodnaMjesta: 4 },
      { id: 302, datumVrijeme: "2026-11-14T09:00:00", slobodnaMjesta: 8 },
    ],
  },
];