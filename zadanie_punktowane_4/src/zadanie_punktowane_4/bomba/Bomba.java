package zadanie_punktowane_4.bomba;

import zadanie_punktowane_4.narzedzia.StaleGry;
import zadanie_punktowane_4.obiekt.ObiektGry;

public class Bomba extends ObiektGry {

    public enum Stan {
        SPADA,
        WYBUCHLA
    }

    private final double predkoscY;
    private Stan stan;

    public Bomba(double x, double y, double predkoscY) {
        super(x, y, StaleGry.PROMIEN_BOMBY * 2, StaleGry.PROMIEN_BOMBY * 2);
        this.predkoscY = predkoscY;
        this.stan = Stan.SPADA;
    }

    public void aktualizujPolozenie(double czasOdOstatniejKlatki) {
        if (stan != Stan.SPADA) {
            return;
        }

        przesun(0, predkoscY * czasOdOstatniejKlatki);

        if (getSrodekY() + StaleGry.PROMIEN_BOMBY >= StaleGry.POZIOM_ZIEMI) {
            wybuch();
        }
    }

    private void wybuch() {
        stan = Stan.WYBUCHLA;

        double srednicaWybuchu = StaleGry.PROMIEN_WYBUCHU * 2;
        ustawRozmiar(srednicaWybuchu, srednicaWybuchu);
        ustawPozycje(
                getSrodekX() - StaleGry.PROMIEN_WYBUCHU,
                StaleGry.POZIOM_ZIEMI - StaleGry.PROMIEN_WYBUCHU
        );
    }

    public Stan getStan() {
        return stan;
    }

    public double getPromienRysowania() {
        return stan == Stan.SPADA ? StaleGry.PROMIEN_BOMBY : StaleGry.PROMIEN_WYBUCHU;
    }

    public boolean czyPozaPlansza(double wysokoscPlanszy) {
        return stan == Stan.SPADA && getY() > wysokoscPlanszy;
    }
}
