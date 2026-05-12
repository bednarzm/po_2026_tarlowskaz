package wycieczka.generator;

import wycieczka.osoba.Uczen;

import java.util.HashSet;
import java.util.Set;

public class UnikalnyGeneratorUczniow extends ProstyGeneratorUczniow {

    private final Set<Uczen> wygenerowaniUczniowie;

    public UnikalnyGeneratorUczniow() {
        this.wygenerowaniUczniowie = new HashSet<>();
    }

    @Override
    public Uczen stworzUcznia() {
        String imie = wylosujImie();
        String nazwisko = wylosujNazwisko();
        int dodatkoweId = 1;
        int czynnikImprezowy = wylosujCzynnikImprezowy();

        Uczen uczen = new Uczen(dodatkoweId, imie, nazwisko, czynnikImprezowy);

        while (wygenerowaniUczniowie.contains(uczen)) {
            dodatkoweId++;
            uczen = new Uczen(dodatkoweId, imie, nazwisko, czynnikImprezowy);
        }

        wygenerowaniUczniowie.add(uczen);

        return uczen;
    }
}