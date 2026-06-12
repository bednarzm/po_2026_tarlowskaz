package zadanie_punktowane_4.bombowiec;

import zadanie_punktowane_4.narzedzia.StaleGry;
import zadanie_punktowane_4.obiekt.ObiektGry;

import java.util.Random;

public class Bombowiec extends ObiektGry {

    private static final Random LOSOWANIE = new Random();

    private final int maksymalnaLiczbaBomb;
    private int liczbaBomb;
    private long czasNastepnegoZrzutu;

    public Bombowiec(double x, double y, double szerokosc, double wysokosc, int liczbaBomb) {
        super(x, y, szerokosc, wysokosc);
        this.maksymalnaLiczbaBomb = liczbaBomb;
        this.liczbaBomb = liczbaBomb;
        this.czasNastepnegoZrzutu = 0;
    }

    public void przygotujZrzuty(long teraz) {
        if (czasNastepnegoZrzutu == 0) {
            zaplanujNastepnyZrzut(teraz);
        }
    }

    public boolean mozeZrzucicBombe(long teraz) {
        return liczbaBomb > 0 && teraz >= czasNastepnegoZrzutu;
    }

    public boolean zrzucBombe() {
        if (liczbaBomb <= 0) {
            return false;
        }

        liczbaBomb--;
        return true;
    }

    public void zaplanujNastepnyZrzut(long teraz) {
        czasNastepnegoZrzutu = teraz + losujOdstepNano();
    }

    public void uzupelnijBomby(long teraz) {
        liczbaBomb = maksymalnaLiczbaBomb;
        zaplanujNastepnyZrzut(teraz);
    }

    public boolean czyPusty() {
        return liczbaBomb == 0;
    }

    public int getLiczbaBomb() {
        return liczbaBomb;
    }

    public int getMaksymalnaLiczbaBomb() {
        return maksymalnaLiczbaBomb;
    }

    public void aktualizujPolozenie(double czasOdOstatniejKlatki) {
        przesun(StaleGry.PREDKOSC_BOMBOWCA * czasOdOstatniejKlatki, 0);
    }

    public boolean czyPozaPlansza(double szerokoscPlanszy) {
        return getX() > szerokoscPlanszy;
    }

    public double getProporcjaZuzytychBomb() {
        return 1.0 - (double) liczbaBomb / maksymalnaLiczbaBomb;
    }

    private long losujOdstepNano() {
        long zakres = StaleGry.MAX_ODSTEP_ZRZUTU_NANO - StaleGry.MIN_ODSTEP_ZRZUTU_NANO;
        return StaleGry.MIN_ODSTEP_ZRZUTU_NANO + (long) (LOSOWANIE.nextDouble() * zakres);
    }
}
