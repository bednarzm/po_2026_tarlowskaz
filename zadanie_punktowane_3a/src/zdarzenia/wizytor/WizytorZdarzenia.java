package zdarzenia.wizytor;

import zdarzenia.zdarzenie.ZdarzenieMotoparalotni;
import zdarzenia.zdarzenie.ZdarzenieSpadochronu;
import zdarzenia.zdarzenie.ZdarzenieSzybowca;

public interface WizytorZdarzenia<T> {

    T odwiedzZdarzenieSzybowca(ZdarzenieSzybowca zdarzenie);

    T odwiedzZdarzenieSpadochronu(ZdarzenieSpadochronu zdarzenie);

    T odwiedzZdarzenieMotoparalotni(ZdarzenieMotoparalotni zdarzenie);
}