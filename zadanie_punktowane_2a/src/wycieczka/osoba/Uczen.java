package wycieczka.osoba;

import wycieczka.wizytor.WizytorPasazera;
import wycieczka.Zachowanie;

import java.util.Random;

public class Uczen extends Osoba {

    private static final Random RANDOM = new Random();

    private final int czynnikImprezowy;

    public Uczen(int dodatkoweId, String imie, String nazwisko, int czynnikImprezowy) {
        super(dodatkoweId, imie, nazwisko);
        sprawdzCzynnikImprezowy(czynnikImprezowy);
        this.czynnikImprezowy = czynnikImprezowy;
    }

    public int getCzynnikImprezowy() {
        return czynnikImprezowy;
    }

    public Zachowanie zachowanie() {
        double los = RANDOM.nextDouble();

        if (los < 0.70) {
            return Zachowanie.POPRAWNE;
        } else if (los < 0.80) {
            return Zachowanie.NIEPOPRAWNE;
        } else if (los < 0.95) {
            return Zachowanie.WYKRECIL_NUMER;
        } else {
            return Zachowanie.WYWOLAL_SKANDAL;
        }
    }

    @Override
    public void akceptuj(WizytorPasazera wizytorPasazera) {
        wizytorPasazera.odwiedz(this);
    }

    @Override
    public String toString() {
        return "Uczen {dodatkoweId=" + getDodatkoweId()
                + ", imie='" + getImie() + '\''
                + ", nazwisko='" + getNazwisko() + '\''
                + ", czynnikImprezowy=" + czynnikImprezowy
                + '}';
    }

    private void sprawdzCzynnikImprezowy(int czynnikImprezowy) {
        if (czynnikImprezowy < 0 || czynnikImprezowy > 20) {
            throw new IllegalArgumentException("Czynnik imprezowy musi byc z zakresu 0-20.");
        }
    }
}