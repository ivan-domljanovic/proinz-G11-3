import "./App.css";

const izleti = [
  {
    id: 1,
    naziv: "Obilazak Zagreba",
    lokacija: "Zagreb",
    cijena: 25,
    slobodnaMjesta: 12,
  }
];

function KarticaIzleta({ izlet }) {
  return (
    <article className="kartica">
      <p>{izlet.lokacija}</p>
      <h2>{izlet.naziv}</h2>
      <p>Slobodnih mjesta: {izlet.slobodnaMjesta}</p>
      <strong>{izlet.cijena} € / osoba</strong>
    </article>
  );
}

export default function App() {
  return (
    <main className="sadrzaj">
      <section className="popis" aria-label="Dostupni izleti">
        {izleti.map((izlet) => (
          <KarticaIzleta key={izlet.id} izlet={izlet} />
        ))}
      </section>
    </main>
  );
}