package wycieczka.osoba.kadra;

import wycieczka.osoba.Osoba;
import wycieczka.wizytor.WizytorPasazera;

public abstract class CzlonekKadry extends Osoba {

    private final int staz;

    public CzlonekKadry(int dodatkoweId, String imie, String nazwisko, int staz) {
        super(dodatkoweId, imie, nazwisko);
        sprawdzStaz(staz);
        this.staz = staz;
    }

    public int getStaz() {
        return staz;
    }

    @Override
    public void akceptuj(WizytorPasazera wizytorPasazera) {
        wizytorPasazera.odwiedz(this);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + " {dodatkoweId=" + getDodatkoweId()
                + ", imie='" + getImie() + '\''
                + ", nazwisko='" + getNazwisko() + '\''
                + ", staz=" + staz
                + '}';
    }

    private void sprawdzStaz(int staz) {
        if (staz < 1 || staz > 20) {
            throw new IllegalArgumentException("Staz musi byc z zakresu 1-20.");
        }
    }
}