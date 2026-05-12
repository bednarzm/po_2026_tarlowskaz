package wycieczka.osoba;

import wycieczka.wizytor.WizytowalnyPasazer;

import java.util.Objects;

public abstract class Osoba implements WizytowalnyPasazer {

    private final int dodatkoweId;
    private final String imie;
    private final String nazwisko;

    public Osoba(int dodatkoweId, String imie, String nazwisko) {
        this.dodatkoweId = dodatkoweId;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public int getDodatkoweId() {
        return dodatkoweId;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Osoba osoba = (Osoba) o;

        return dodatkoweId == osoba.dodatkoweId
                && Objects.equals(imie, osoba.imie)
                && Objects.equals(nazwisko, osoba.nazwisko);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dodatkoweId, imie, nazwisko);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + " {dodatkoweId=" + dodatkoweId
                + ", imie='" + imie + '\''
                + ", nazwisko='" + nazwisko + '\''
                + '}';
    }
}