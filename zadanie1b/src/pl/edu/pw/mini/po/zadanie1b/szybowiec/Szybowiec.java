package pl.edu.pw.mini.po.zadanie1b.szybowiec;

import pl.edu.pw.mini.po.zadanie1b.narzedzia.Losowanie;
import pl.edu.pw.mini.po.zadanie1b.podloze.Podloze;
import pl.edu.pw.mini.po.zadanie1b.teren.Teren;

public abstract class Szybowiec {
    private final Teren teren;
    private int wiersz;
    private int kolumna;
    private double wysokosc;
    private int liczbaWywolanGlide;
    private boolean wyladowal;
    private boolean pozaTerenem;

    protected Szybowiec(Teren teren, double wysokoscPoczatkowa) {
        this.teren = teren;
        this.wiersz = teren.losowyWierszStartowy();
        this.kolumna = 0;
        this.wysokosc = Math.max(0, wysokoscPoczatkowa);
        this.liczbaWywolanGlide = 0;
        this.wyladowal = false;
        this.pozaTerenem = false;
    }

    public void glide() {
        if (wyladowal || pozaTerenem) {
            return;
        }

        liczbaWywolanGlide++;

        if (Losowanie.losujZPrawdopodobienstwem(0.6)) {
            kolumna++;
        }

        if (liczbaWywolanGlide % 3 == 0) {
            int liczbaPol = Losowanie.losujInt(0, 3);
            int kierunek = Losowanie.losujInt(0, 2); // 0 - gora, 1 - dol, 2 - wcale

            if (kierunek == 0) {
                wiersz -= liczbaPol;
            } else if (kierunek == 1) {
                wiersz += liczbaPol;
            }
        }

        if (!teren.czyWGranicach(wiersz, kolumna)) {
            System.out.println("Wyjscie poza strefe");
            pozaTerenem = true;
            return;
        }

        Podloze aktualnePodloze = teren.getPodloze(wiersz, kolumna);
        double korekta = aktualnePodloze.getUpCurrent();
        wysokosc += korekta;

        if (wysokosc < 0) {
            wysokosc = 0;
        }

        if (wysokosc == 0) {
            System.out.println(getKomunikatLadowania());
            wyladowal = true;
        }
    }

    protected abstract String getKomunikatLadowania();

    public boolean czyPozaTerenem() {
        return pozaTerenem;
    }

    public int getWiersz() {
        return wiersz;
    }

    public int getKolumna() {
        return kolumna;
    }

    public double getWysokosc() {
        return wysokosc;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + "{wysokosc=" + String.format("%.2f", wysokosc) + "}";
    }
}