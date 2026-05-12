package wycieczka.wizytor;

import wycieczka.osoba.Uczen;
import wycieczka.osoba.kadra.CzlonekKadry;

public interface WizytorPasazera {

    void odwiedz(Uczen uczen);

    void odwiedz(CzlonekKadry czlonekKadry);
}