package zdarzenia;

import zdarzenia.enumy.RodzajStatku;
import zdarzenia.zdarzenie.Zdarzenie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProcesorZdarzen {

    private final Collection<Zdarzenie> wszystkieZdarzenia;

    public ProcesorZdarzen(Collection<Zdarzenie> wszystkieZdarzenia) {
        this.wszystkieZdarzenia = wszystkieZdarzenia;
    }

    public List<Zdarzenie> zwrocZdarzeniaNajliczniejszegoRodzajuBezDuplikatow() {
        Set<Zdarzenie> bezDuplikatow = new LinkedHashSet<>(wszystkieZdarzenia);

        Map<RodzajStatku, Integer> licznik = new EnumMap<>(RodzajStatku.class);

        for (Zdarzenie zdarzenie : bezDuplikatow) {
            RodzajStatku rodzajStatku = zdarzenie.getRodzajStatku();
            licznik.put(rodzajStatku, licznik.getOrDefault(rodzajStatku, 0) + 1);
        }

        int najwiekszaLiczba = 0;

        for (int liczba : licznik.values()) {
            if (liczba > najwiekszaLiczba) {
                najwiekszaLiczba = liczba;
            }
        }

        List<RodzajStatku> najliczniejszeRodzaje = new ArrayList<>();

        for (Map.Entry<RodzajStatku, Integer> wpis : licznik.entrySet()) {
            if (wpis.getValue() == najwiekszaLiczba) {
                najliczniejszeRodzaje.add(wpis.getKey());
            }
        }

        List<Zdarzenie> wynik = new ArrayList<>();

        for (Zdarzenie zdarzenie : bezDuplikatow) {
            if (najliczniejszeRodzaje.contains(zdarzenie.getRodzajStatku())) {
                wynik.add(zdarzenie);
            }
        }

        wynik.sort(Comparator.comparing(Zdarzenie::getId));

        return wynik;
    }

    public Map<RodzajStatku, Integer> policzZdarzeniaWedlugRodzajuBezDuplikatow() {
        Set<Zdarzenie> bezDuplikatow = new LinkedHashSet<>(wszystkieZdarzenia);
        Map<RodzajStatku, Integer> licznik = new EnumMap<>(RodzajStatku.class);

        for (Zdarzenie zdarzenie : bezDuplikatow) {
            RodzajStatku rodzajStatku = zdarzenie.getRodzajStatku();
            licznik.put(rodzajStatku, licznik.getOrDefault(rodzajStatku, 0) + 1);
        }

        return licznik;
    }
}