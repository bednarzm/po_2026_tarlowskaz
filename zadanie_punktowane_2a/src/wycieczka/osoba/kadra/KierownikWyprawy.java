package wycieczka.osoba.kadra;

public class KierownikWyprawy extends Nauczyciel {

    public KierownikWyprawy(int dodatkoweId, String imie, String nazwisko, int staz) {
        super(dodatkoweId, imie, nazwisko, staz);
    }

    @Override
    public String toString() {
        return "KierownikWyprawy {dodatkoweId=" + getDodatkoweId()
                + ", imie='" + getImie() + '\''
                + ", nazwisko='" + getNazwisko() + '\''
                + ", staz=" + getStaz()
                + ", funkcja='kierownik wyprawy'"
                + '}';
    }
}