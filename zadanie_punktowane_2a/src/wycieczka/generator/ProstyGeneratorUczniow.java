package wycieczka.generator;

import wycieczka.osoba.Uczen;

import java.util.Random;

public class ProstyGeneratorUczniow implements GeneratorUczniow {

    protected final Random random;

    public ProstyGeneratorUczniow() {
        this.random = new Random();
    }

    @Override
    public Uczen stworzUcznia() {
        String imie = wylosujImie();
        String nazwisko = wylosujNazwisko();
        int dodatkoweId = 1;
        int czynnikImprezowy = wylosujCzynnikImprezowy();

        return new Uczen(dodatkoweId, imie, nazwisko, czynnikImprezowy);
    }

    protected String wylosujImie() {
        int indeks = random.nextInt(DaneOsobowe.IMIONA.size());
        return DaneOsobowe.IMIONA.get(indeks);
    }

    protected String wylosujNazwisko() {
        int indeks = random.nextInt(DaneOsobowe.NAZWISKA.size());
        return DaneOsobowe.NAZWISKA.get(indeks);
    }

    protected int wylosujCzynnikImprezowy() {
        return random.nextInt(21);
    }
}