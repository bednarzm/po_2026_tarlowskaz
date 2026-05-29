package zdarzenia.zdarzenie;

import zdarzenia.enumy.Klasyfikacja;
import zdarzenia.enumy.RodzajStatku;
import zdarzenia.wizytor.WizytorZdarzenia;

import java.time.LocalDate;
import java.util.Objects;

public class ZdarzenieSzybowca extends Zdarzenie {

    private final String linkDoSprawy;
    private final String znakiRozpoznawcze;

    public ZdarzenieSzybowca(String id, LocalDate data, String miejsce, String typ,
                             Klasyfikacja klasyfikacja, String linkDoSprawy,
                             String znakiRozpoznawcze) {
        super(id, data, miejsce, typ, RodzajStatku.SZYBOWIEC, klasyfikacja);
        this.linkDoSprawy = linkDoSprawy;
        this.znakiRozpoznawcze = znakiRozpoznawcze;
    }

    public String getLinkDoSprawy() {
        return linkDoSprawy;
    }

    public String getZnakiRozpoznawcze() {
        return znakiRozpoznawcze;
    }

    @Override
    public <T> T akceptuj(WizytorZdarzenia<T> wizytor) {
        return wizytor.odwiedzZdarzenieSzybowca(this);
    }

    @Override
    public String toString() {
        return "ZdarzenieSzybowca{" +
                wspolnyOpis() +
                ", linkDoSprawy='" + linkDoSprawy + '\'' +
                ", znakiRozpoznawcze='" + znakiRozpoznawcze + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }

        ZdarzenieSzybowca that = (ZdarzenieSzybowca) o;

        return Objects.equals(linkDoSprawy, that.linkDoSprawy)
                && Objects.equals(znakiRozpoznawcze, that.znakiRozpoznawcze);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), linkDoSprawy, znakiRozpoznawcze);
    }
}