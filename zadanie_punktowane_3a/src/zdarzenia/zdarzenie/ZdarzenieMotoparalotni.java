package zdarzenia.zdarzenie;

import zdarzenia.enumy.Klasyfikacja;
import zdarzenia.enumy.RodzajStatku;
import zdarzenia.wizytor.WizytorZdarzenia;

import java.time.LocalDate;
import java.util.Objects;

public class ZdarzenieMotoparalotni extends Zdarzenie {

    private final double losowyCzynnik;

    public ZdarzenieMotoparalotni(String id, LocalDate data, String miejsce, String typ,
                                  Klasyfikacja klasyfikacja, double losowyCzynnik) {
        super(id, data, miejsce, typ, RodzajStatku.MOTOPARALOTNIA, klasyfikacja);
        this.losowyCzynnik = losowyCzynnik;
    }

    public double getLosowyCzynnik() {
        return losowyCzynnik;
    }

    @Override
    public <T> T akceptuj(WizytorZdarzenia<T> wizytor) {
        return wizytor.odwiedzZdarzenieMotoparalotni(this);
    }

    @Override
    public String toString() {
        return "ZdarzenieMotoparalotni{" +
                wspolnyOpis() +
                ", losowyCzynnik=" + losowyCzynnik +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }

        ZdarzenieMotoparalotni that = (ZdarzenieMotoparalotni) o;

        return Double.compare(that.losowyCzynnik, losowyCzynnik) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), losowyCzynnik);
    }
}