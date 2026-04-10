package pl.edu.pw.mini.po.zadanie1b.teren;

import pl.edu.pw.mini.po.zadanie1b.narzedzia.Losowanie;
import pl.edu.pw.mini.po.zadanie1b.podloze.Podloze;
import pl.edu.pw.mini.po.zadanie1b.podloze.gleba.GlebaKamienista;
import pl.edu.pw.mini.po.zadanie1b.podloze.gleba.GlebaNiekamienista;
import pl.edu.pw.mini.po.zadanie1b.podloze.gleba.GlebaPylasta;
import pl.edu.pw.mini.po.zadanie1b.podloze.PodmoklaLaka;
import pl.edu.pw.mini.po.zadanie1b.podloze.piasek.Piasek;
import pl.edu.pw.mini.po.zadanie1b.podloze.TerenWodny;

public class Teren {
    private static final int MIN_WYMIAR = 10;
    private static final int MAX_WYMIAR = 100;

    private final int dlugosc;
    private final int szerokosc;
    private final Podloze[][] plansza;

    public Teren(int dlugosc, int szerokosc) {
        this.dlugosc = ograniczWymiar(dlugosc);
        this.szerokosc = ograniczWymiar(szerokosc);
        this.plansza = new Podloze[this.szerokosc][this.dlugosc];
        wypelnijTeren();
    }

    private int ograniczWymiar(int wymiar) {
        if (wymiar < MIN_WYMIAR) {
            return MIN_WYMIAR;
        }
        if (wymiar > MAX_WYMIAR) {
            return MAX_WYMIAR;
        }
        return wymiar;
    }

    private void wypelnijTeren() {
        for (int wiersz = 0; wiersz < szerokosc; wiersz++) {
            for (int kolumna = 0; kolumna < dlugosc; kolumna++) {
                plansza[wiersz][kolumna] = utworzLosowePodloze();
            }
        }
    }

    private Podloze utworzLosowePodloze() {
        int wybor = Losowanie.losujInt(0, 5);
        return switch (wybor) {
            case 0 -> new Piasek();
            case 1 -> new GlebaNiekamienista();
            case 2 -> new GlebaKamienista();
            case 3 -> new PodmoklaLaka();
            case 4 -> new TerenWodny();
            default -> new GlebaPylasta();
        };
    }

    public void modifyAtmosphere() {
        for (int wiersz = 0; wiersz < szerokosc; wiersz++) {
            for (int kolumna = 0; kolumna < dlugosc; kolumna++) {
                int deltaT = Losowanie.losujInt(-2, 4);
                plansza[wiersz][kolumna].modifyTemperature(deltaT);
            }
        }
    }

    public Podloze getPodloze(int wiersz, int kolumna) {
        if (!czyWGranicach(wiersz, kolumna)) {
            return null;
        }
        return plansza[wiersz][kolumna];
    }

    public boolean czyWGranicach(int wiersz, int kolumna) {
        return wiersz >= 0 && wiersz < szerokosc && kolumna >= 0 && kolumna < dlugosc;
    }

    public int getDlugosc() {
        return dlugosc;
    }

    public int getSzerokosc() {
        return szerokosc;
    }

    public int losowyWierszStartowy() {
        return Losowanie.losujInt(0, szerokosc - 1);
    }

    public String opisTerenu() {
        return "Teren{dlugosc=" + dlugosc + ", szerokosc=" + szerokosc + "}";
    }
}