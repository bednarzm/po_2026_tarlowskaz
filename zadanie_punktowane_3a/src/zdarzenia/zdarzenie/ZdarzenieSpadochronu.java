package zdarzenia.zdarzenie;

import zdarzenia.enumy.Klasyfikacja;
import zdarzenia.enumy.RodzajStatku;
import zdarzenia.wizytor.WizytorZdarzenia;

import java.time.LocalDate;

public class ZdarzenieSpadochronu extends Zdarzenie {

    public ZdarzenieSpadochronu(String id, LocalDate data, String miejsce, String typ,
                                Klasyfikacja klasyfikacja) {
        super(id, data, miejsce, typ, RodzajStatku.SPADOCHRON, klasyfikacja);
    }

    @Override
    public <T> T akceptuj(WizytorZdarzenia<T> wizytor) {
        return wizytor.odwiedzZdarzenieSpadochronu(this);
    }

    @Override
    public String toString() {
        return "ZdarzenieSpadochronu{" + wspolnyOpis() + '}';
    }
}