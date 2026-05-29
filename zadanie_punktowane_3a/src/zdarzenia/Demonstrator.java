package zdarzenia;

import zdarzenia.enumy.RodzajStatku;
import zdarzenia.parser.NiepoprawnyPrefixException;
import zdarzenia.parser.ParserZdarzen;
import zdarzenia.ProcesorZdarzen;
import zdarzenia.SkanerKatalogu;
import zdarzenia.wizytor.WizytorZdarzenia;
import zdarzenia.zdarzenie.Zdarzenie;
import zdarzenia.zdarzenie.ZdarzenieMotoparalotni;
import zdarzenia.zdarzenie.ZdarzenieSpadochronu;
import zdarzenia.zdarzenie.ZdarzenieSzybowca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Demonstrator {

    private static final String SCIEZKA_DO_PLIKU =
            "my_favorite_occurences/zdarzenia.txt";

    private static final String SCIEZKA_DO_PLIKU_BLEDNEGO =
            "my_favorite_occurences/zdarzenia_bad.txt";

    private static final String SCIEZKA_DO_KATALOGU =
            "my_favorite_occurences";

    public static void main(String[] args) {
        ParserZdarzen parser = new ParserZdarzen();

        try {
            System.out.println();
            System.out.println("1. WCZYTANIE WSZYSTKICH ZDARZEŃ DO LISTY");

            ArrayList<Zdarzenie> wszystkieZdarzenia = parser.wczytajZdarzenia(SCIEZKA_DO_PLIKU);

            System.out.println("Liczba wszystkich wczytanych zdarzeń: " + wszystkieZdarzenia.size());
            System.out.println("Pierwsze 5 zdarzeń:");

            wszystkieZdarzenia.stream()
                    .limit(5)
                    .forEach(System.out::println);

            System.out.println();
            System.out.println("2. ZDARZENIA ZDUPLIKOWANE W KOLEJNOŚCI WYSTĘPOWANIA");

            Collection<Zdarzenie> zduplikowaneZdarzenia =
                    parser.wczytajZduplikowaneZdarzenia(SCIEZKA_DO_PLIKU);

            System.out.println("Liczba odnalezionych powtórzeń: " + zduplikowaneZdarzenia.size());

            if (zduplikowaneZdarzenia.isEmpty()) {
                System.out.println("Brak zduplikowanych zdarzeń.");
            } else {
                zduplikowaneZdarzenia.forEach(System.out::println);
            }

            System.out.println();
            System.out.println("3. WYKORZYSTANIE WIZYTORA DO OPISU RODZAJU ZDARZENIA");

            System.out.println("Pierwsze 5 zdarzeń opisane przez Visitor:");

            WizytorZdarzenia<String> wizytorOpisujacy = utworzWizytorOpisujacy();

            wszystkieZdarzenia.stream()
                    .limit(5)
                    .forEach(zdarzenie -> System.out.println(zdarzenie.akceptuj(wizytorOpisujacy)));

            System.out.println();
            System.out.println("4. PROCESOWANIE - NAJLICZNIEJSZY RODZAJ STATKU PO USUNIĘCIU DUPLIKATÓW");

            ProcesorZdarzen procesor = new ProcesorZdarzen(wszystkieZdarzenia);

            Map<RodzajStatku, Integer> licznik = procesor.policzZdarzeniaWedlugRodzajuBezDuplikatow();

            System.out.println("Liczba zdarzeń według rodzaju statku, po usunięciu duplikatów:");

            for (Map.Entry<RodzajStatku, Integer> wpis : licznik.entrySet()) {
                System.out.println(" - " + wpis.getKey() + ": " + wpis.getValue());
            }

            List<Zdarzenie> zdarzeniaNajliczniejszegoRodzaju =
                    procesor.zwrocZdarzeniaNajliczniejszegoRodzajuBezDuplikatow();

            System.out.println();
            System.out.println("Zdarzenia najliczniejszego rodzaju, posortowane po id rosnąco:");

            zdarzeniaNajliczniejszegoRodzaju.forEach(System.out::println);

            System.out.println();
            System.out.println("5. OBSŁUGA PLIKU Z BŁĘDNYM PUSTYM PREFIXEM");

            try {
                parser.wczytajZdarzenia(SCIEZKA_DO_PLIKU_BLEDNEGO);
                System.out.println("Plik błędny został wczytany, ale nie powinno tak być.");
            } catch (NiepoprawnyPrefixException e) {
                System.out.println("Poprawnie przerwano parsowanie pliku błędnego.");
                System.out.println("Powód: " + e.getMessage());
            }

            System.out.println();
            System.out.println("6. SKANOWANIE KATALOGU");

            SkanerKatalogu skanerKatalogu = new SkanerKatalogu();
            skanerKatalogu.wypiszZawartoscKatalogu(SCIEZKA_DO_KATALOGU);

        } catch (IOException e) {
            System.out.println("Wystąpił problem z odczytem pliku.");
            System.out.println("Szczegóły: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Wystąpił nieoczekiwany problem podczas działania programu.");
            System.out.println("Szczegóły: " + e.getMessage());
        }
    }

    private static WizytorZdarzenia<String> utworzWizytorOpisujacy() {
        return new WizytorZdarzenia<>() {

            @Override
            public String odwiedzZdarzenieSzybowca(ZdarzenieSzybowca zdarzenie) {
                return "SZYBOWIEC -> id: " + zdarzenie.getId()
                        + ", znaki rozpoznawcze: " + zdarzenie.getZnakiRozpoznawcze()
                        + ", link do sprawy: " + zdarzenie.getLinkDoSprawy();
            }

            @Override
            public String odwiedzZdarzenieSpadochronu(ZdarzenieSpadochronu zdarzenie) {
                return "SPADOCHRON -> id: " + zdarzenie.getId()
                        + ", typ: " + zdarzenie.getTyp()
                        + ", miejsce: " + zdarzenie.getMiejsce();
            }

            @Override
            public String odwiedzZdarzenieMotoparalotni(ZdarzenieMotoparalotni zdarzenie) {
                return "MOTOPARALOTNIA -> id: " + zdarzenie.getId()
                        + ", losowy czynnik: " + zdarzenie.getLosowyCzynnik()
                        + ", miejsce: " + zdarzenie.getMiejsce();
            }
        };
    }
}