package zadanie_punktowane_4.sterowanie;

import javafx.scene.control.Button;
import zadanie_punktowane_4.gra.SilnikGry;
import zadanie_punktowane_4.gra.StanGry;
import zadanie_punktowane_4.narzedzia.StaleGry;
import zadanie_punktowane_4.rysowanie.RysownikGry;

public class KontrolerGry {

    private final StanGry stanGry;
    private final SilnikGry silnikGry;
    private final RysownikGry rysownikGry;
    private final Button przyciskUzupelniania;

    public KontrolerGry(StanGry stanGry, SilnikGry silnikGry, RysownikGry rysownikGry, Button przyciskUzupelniania) {
        this.stanGry = stanGry;
        this.silnikGry = silnikGry;
        this.rysownikGry = rysownikGry;
        this.przyciskUzupelniania = przyciskUzupelniania;
    }

    public void dodajBombowiec(double x, double y) {
        silnikGry.dodajBombowiec(x, y);
        odswiez();
    }

    public void wykonajKlatkeGry(long teraz) {
        silnikGry.aktualizuj(teraz);
        odswiez();
    }

    public void uzupelnijPusteBombowce() {
        silnikGry.uzupelnijPusteBombowce();
        odswiez();
    }

    private void odswiez() {
        rysownikGry.odswiez(stanGry);
        przyciskUzupelniania.setText(
                StaleGry.przygotujTekstPrzycisku(silnikGry.policzPusteBombowce())
        );
    }
}
