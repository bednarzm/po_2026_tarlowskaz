package zadanie_punktowane_4.gra;

import zadanie_punktowane_4.bomba.Bomba;
import zadanie_punktowane_4.bombowiec.Bombowiec;
import zadanie_punktowane_4.narzedzia.StaleGry;

public class SilnikGry {

    private final StanGry stanGry;
    private long czasPoprzedniejKlatki;
    private long ostatniCzas;

    public SilnikGry(StanGry stanGry) {
        this.stanGry = stanGry;
    }

    public void dodajBombowiec(double klikX, double klikY) {
        double x = klikX - StaleGry.SZEROKOSC_BOMBOWCA / 2;
        double y = klikY - StaleGry.WYSOKOSC_BOMBOWCA / 2;

        x = ogranicz(x, 0, StaleGry.SZEROKOSC_PLANSZY - StaleGry.SZEROKOSC_BOMBOWCA);
        y = ogranicz(y, 0, StaleGry.WYSOKOSC_PLANSZY - StaleGry.WYSOKOSC_BOMBOWCA);

        stanGry.dodajBombowiec(new Bombowiec(
                x,
                y,
                StaleGry.SZEROKOSC_BOMBOWCA,
                StaleGry.WYSOKOSC_BOMBOWCA,
                StaleGry.LICZBA_BOMB_W_BOMBOWCU
        ));
    }

    public void aktualizuj(long teraz) {
        ostatniCzas = teraz;
        double czasOdOstatniejKlatki = obliczCzasOdOstatniejKlatki(teraz);

        zrzucBomby(teraz);

        for (Bombowiec bombowiec : stanGry.getBombowce()) {
            bombowiec.aktualizujPolozenie(czasOdOstatniejKlatki);
        }

        for (Bomba bomba : stanGry.getBomby()) {
            bomba.aktualizujPolozenie(czasOdOstatniejKlatki);
        }

        stanGry.usunBombowcePozaPlansza(StaleGry.SZEROKOSC_PLANSZY);
        stanGry.usunBombyPozaPlansza(StaleGry.WYSOKOSC_PLANSZY);
    }

    public int policzPusteBombowce() {
        int liczba = 0;

        for (Bombowiec bombowiec : stanGry.getBombowce()) {
            if (bombowiec.czyPusty()) {
                liczba++;
            }
        }

        return liczba;
    }

    public void uzupelnijPusteBombowce() {
        for (Bombowiec bombowiec : stanGry.getBombowce()) {
            if (bombowiec.czyPusty()) {
                bombowiec.uzupelnijBomby(ostatniCzas);
            }
        }
    }

    private double obliczCzasOdOstatniejKlatki(long teraz) {
        if (czasPoprzedniejKlatki == 0) {
            czasPoprzedniejKlatki = teraz;
            return 0;
        }

        double czas = (teraz - czasPoprzedniejKlatki) / 1_000_000_000.0;
        czasPoprzedniejKlatki = teraz;
        return czas;
    }

    private void zrzucBomby(long teraz) {
        for (Bombowiec bombowiec : stanGry.getBombowce()) {
            bombowiec.przygotujZrzuty(teraz);

            if (!bombowiec.mozeZrzucicBombe(teraz) || !bombowiec.zrzucBombe()) {
                continue;
            }

            Bomba bomba = new Bomba(
                    bombowiec.getSrodekX() - StaleGry.PROMIEN_BOMBY,
                    bombowiec.getY() + bombowiec.getWysokosc(),
                    StaleGry.PREDKOSC_BOMBY
            );

            stanGry.dodajBombe(bomba);
            bombowiec.zaplanujNastepnyZrzut(teraz);
        }
    }

    private double ogranicz(double wartosc, double min, double max) {
        return Math.max(min, Math.min(wartosc, max));
    }
}
