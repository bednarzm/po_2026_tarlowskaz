package wycieczka;

import wycieczka.osoba.Osoba;
import wycieczka.osoba.Uczen;
import wycieczka.osoba.kadra.CzlonekKadry;
import wycieczka.wizytor.WizytorPasazera;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Kierowca {

    private final int id;
    private final Autobus autobus;
    private final Map<Zachowanie, List<Uczen>> przewinienia;

    public Kierowca(int id, Autobus autobus) {
        this.id = id;
        this.autobus = autobus;
        this.przewinienia = new EnumMap<>(Zachowanie.class);
    }

    public int getId() {
        return id;
    }

    public void usadzCzlonkaKadry(CzlonekKadry czlonekKadry) {
        int numerMiejsca = autobus.znajdzPierwszeWolneMiejsce();
        autobus.usadzPasazera(numerMiejsca, czlonekKadry);
    }

    public void usadzUczniow(Collection<Uczen> uczniowie) {
        List<Uczen> uczniowiePosortowani = new ArrayList<>(uczniowie);

        uczniowiePosortowani.sort(
                Comparator.comparingInt(Uczen::getCzynnikImprezowy).reversed()
        );

        List<Uczen> najbardziejImprezowi = new ArrayList<>();
        List<Uczen> pozostali = new ArrayList<>();

        for (int i = 0; i < uczniowiePosortowani.size(); i++) {
            if (i < 5) {
                najbardziejImprezowi.add(uczniowiePosortowani.get(i));
            } else {
                pozostali.add(uczniowiePosortowani.get(i));
            }
        }

        int numerMiejsca = 41;

        for (Uczen uczen : najbardziejImprezowi) {
            autobus.usadzPasazera(numerMiejsca, uczen);
            numerMiejsca++;
        }

        for (Uczen uczen : pozostali) {
            int pierwszeWolneMiejsce = autobus.znajdzPierwszeWolneMiejsce();
            autobus.usadzPasazera(pierwszeWolneMiejsce, uczen);
        }
    }

    public void kontrolujUczniow() {
        WizytorPasazera kontroler = new WizytorPasazera() {

            @Override
            public void odwiedz(Uczen uczen) {
                Zachowanie zachowanie = uczen.zachowanie();

                if (zachowanie != Zachowanie.POPRAWNE) {
                    przewinienia
                            .computeIfAbsent(zachowanie, klucz -> new ArrayList<>())
                            .add(uczen);
                }
            }

            @Override
            public void odwiedz(CzlonekKadry czlonekKadry) {
                // Kadra nie jest kontrolowana pod wzgledem zachowania uczniowskiego.
            }
        };

        for (Osoba pasazer : autobus.pobierzPasazerow()) {
            pasazer.akceptuj(kontroler);
        }
    }

    public void wypiszPodpadziochow() {
        if (przewinienia.isEmpty()) {
            System.out.println("Brak przewinien. Uczniowie zachowywali sie poprawnie.");
            return;
        }

        for (Map.Entry<Zachowanie, List<Uczen>> entry : przewinienia.entrySet()) {
            System.out.println("\nPrzewinienie: " + entry.getKey());

            for (Uczen uczen : entry.getValue()) {
                System.out.println(" - " + uczen);
            }
        }
    }

    @Override
    public String toString() {
        return "Kierowca {id=" + id + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Kierowca kierowca = (Kierowca) o;

        return id == kierowca.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}