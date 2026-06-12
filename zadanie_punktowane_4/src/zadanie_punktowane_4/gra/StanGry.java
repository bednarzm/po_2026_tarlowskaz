package zadanie_punktowane_4.gra;

import zadanie_punktowane_4.bomba.Bomba;
import zadanie_punktowane_4.bombowiec.Bombowiec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StanGry {

    private final List<Bombowiec> bombowce = new ArrayList<>();
    private final List<Bomba> bomby = new ArrayList<>();

    public void dodajBombowiec(Bombowiec bombowiec) {
        bombowce.add(bombowiec);
    }

    public void dodajBombe(Bomba bomba) {
        bomby.add(bomba);
    }

    public List<Bombowiec> getBombowce() {
        return Collections.unmodifiableList(bombowce);
    }

    public List<Bomba> getBomby() {
        return Collections.unmodifiableList(bomby);
    }

    public void usunBombowcePozaPlansza(double szerokoscPlanszy) {
        bombowce.removeIf(bombowiec -> bombowiec.czyPozaPlansza(szerokoscPlanszy));
    }

    public void usunBombyPozaPlansza(double wysokoscPlanszy) {
        bomby.removeIf(bomba -> bomba.czyPozaPlansza(wysokoscPlanszy));
    }
}
