package wycieczka;

import wycieczka.osoba.Osoba;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Autobus {

    public static final int POJEMNOSC = 45;

    private final Map<Integer, Osoba> miejsca;

    public Autobus() {
        this.miejsca = new HashMap<>();
    }

    public void usadzPasazera(int numerMiejsca, Osoba osoba) {
        sprawdzNumerMiejsca(numerMiejsca);

        if (miejsca.containsKey(numerMiejsca)) {
            throw new IllegalStateException("Miejsce " + numerMiejsca + " jest juz zajete.");
        }

        miejsca.put(numerMiejsca, osoba);
    }

    public Collection<Osoba> wysadzPasazerow() {
        List<Osoba> wysadzeniPasazerowie = pobierzPasazerow();
        miejsca.clear();
        return wysadzeniPasazerowie;
    }

    public List<Osoba> pobierzPasazerow() {
        List<Osoba> pasazerowie = new ArrayList<>();

        for (int i = 1; i <= POJEMNOSC; i++) {
            if (miejsca.containsKey(i)) {
                pasazerowie.add(miejsca.get(i));
            }
        }

        return pasazerowie;
    }

    public boolean czyMiejsceWolne(int numerMiejsca) {
        sprawdzNumerMiejsca(numerMiejsca);
        return !miejsca.containsKey(numerMiejsca);
    }

    public int znajdzPierwszeWolneMiejsce() {
        for (int i = 1; i <= POJEMNOSC; i++) {
            if (czyMiejsceWolne(i)) {
                return i;
            }
        }

        throw new IllegalStateException("Brak wolnych miejsc w autobusie.");
    }

    public void wypiszPasazerow() {
        for (int i = 1; i <= POJEMNOSC; i++) {
            if (miejsca.containsKey(i)) {
                System.out.println("Miejsce " + i + ": " + miejsca.get(i));
            } else {
                System.out.println("Miejsce " + i + ": wolne");
            }
        }
    }

    private void sprawdzNumerMiejsca(int numerMiejsca) {
        if (numerMiejsca < 1 || numerMiejsca > POJEMNOSC) {
            throw new IllegalArgumentException("Numer miejsca musi byc z zakresu 1-" + POJEMNOSC + ".");
        }
    }
}