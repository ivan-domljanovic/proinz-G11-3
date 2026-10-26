//format cijene, datum a i trajanja
export const cijenaFormat = new Intl.NumberFormat("hr-HR", {
  style: "currency",
  currency: "EUR",
});

export const datumFormat = new Intl.DateTimeFormat("hr-HR", {
  dateStyle: "medium",
  timeStyle: "short",
});

export function prikaziTrajanje(minute) {
  if (minute == null) return "Nije navedeno";

  const sati = Math.floor(minute / 60);
  const ostatak = Math.round(minute % 60);

  return [
    sati > 0 ? `${sati} h` : "",
    ostatak > 0 ? `${ostatak} min` : "",
  ]
    .filter(Boolean)
    .join(" ") || "0 min";
}
