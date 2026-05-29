package zdarzenia.zdarzenie;

import zdarzenia.enumy.Klasyfikacja;
import zdarzenia.enumy.RodzajStatku;
import zdarzenia.wizytor.WizytowalneZdarzenie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Zdarzenie implements WizytowalneZdarzenie {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    private final String id;
    private final LocalDate data;
    private final String miejsce;
    private final String typ;
    private final RodzajStatku rodzajStatku;
    private final Klasyfikacja klasyfikacja;

    public Zdarzenie(String id, LocalDate data, String miejsce, String typ,
                     RodzajStatku rodzajStatku, Klasyfikacja klasyfikacja) {
        this.id = id;
        this.data = data;
        this.miejsce = miejsce;
        this.typ = typ;
        this.rodzajStatku = rodzajStatku;
        this.klasyfikacja = klasyfikacja;
    }

    public String getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public String getMiejsce() {
        return miejsce;
    }

    public String getTyp() {
        return typ;
    }

    public RodzajStatku getRodzajStatku() {
        return rodzajStatku;
    }

    public Klasyfikacja getKlasyfikacja() {
        return klasyfikacja;
    }

    protected String wspolnyOpis() {
        return "id='" + id + '\'' +
                ", data=" + data.format(FORMATTER) +
                ", miejsce='" + miejsce + '\'' +
                ", typ='" + typ + '\'' +
                ", rodzajStatku=" + rodzajStatku +
                ", klasyfikacja=" + klasyfikacja;
    }

    @Override
    public String toString() {
        return "Zdarzenie{" + wspolnyOpis() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Zdarzenie zdarzenie = (Zdarzenie) o;

        return Objects.equals(id, zdarzenie.id)
                && Objects.equals(data, zdarzenie.data)
                && Objects.equals(miejsce, zdarzenie.miejsce)
                && Objects.equals(typ, zdarzenie.typ)
                && rodzajStatku == zdarzenie.rodzajStatku
                && klasyfikacja == zdarzenie.klasyfikacja;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), id, data, miejsce, typ, rodzajStatku, klasyfikacja);
    }
}