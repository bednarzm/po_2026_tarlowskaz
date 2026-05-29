package zdarzenia.parser;

import zdarzenia.enumy.Klasyfikacja;
import zdarzenia.enumy.RodzajStatku;
import zdarzenia.zdarzenie.Zdarzenie;
import zdarzenia.zdarzenie.ZdarzenieMotoparalotni;
import zdarzenia.zdarzenie.ZdarzenieSpadochronu;
import zdarzenia.zdarzenie.ZdarzenieSzybowca;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ParserZdarzen {

    private static final String SEPARATOR_POL = "###";
    private static final String SEPARATOR_PREFIXU = ":";
    private static final DateTimeFormatter FORMATTER_DATY = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public ArrayList<Zdarzenie> wczytajZdarzenia(String sciezkaWzgledna) throws IOException {
        ArrayList<Zdarzenie> zdarzenia = new ArrayList<>();

        for (String linia : Files.readAllLines(Path.of(sciezkaWzgledna), StandardCharsets.UTF_8)) {
            if (!linia.isBlank()) {
                zdarzenia.add(parsujLinie(linia));
            }
        }

        return zdarzenia;
    }

    public Collection<Zdarzenie> wczytajZduplikowaneZdarzenia(String sciezkaWzgledna) throws IOException {
        ArrayList<Zdarzenie> wszystkieZdarzenia = wczytajZdarzenia(sciezkaWzgledna);

        Set<Zdarzenie> napotkane = new HashSet<>();
        Collection<Zdarzenie> zduplikowane = new ArrayList<>();

        for (Zdarzenie zdarzenie : wszystkieZdarzenia) {
            if (!napotkane.add(zdarzenie)) {
                zduplikowane.add(zdarzenie);
            }
        }

        return zduplikowane;
    }

    private Zdarzenie parsujLinie(String linia) {
        Map<String, String> pola = new HashMap<>();

        String[] fragmenty = linia.split(SEPARATOR_POL);

        for (String fragment : fragmenty) {
            if (!fragment.isBlank()) {
                dodajPoleDoMapy(fragment, pola);
            }
        }

        return utworzZdarzenie(pola);
    }

    private void dodajPoleDoMapy(String fragment, Map<String, String> pola) {
        int indeksSeparatora = fragment.indexOf(SEPARATOR_PREFIXU);

        if (indeksSeparatora < 0) {
            throw new NiepoprawnyPrefixException("Brak separatora ':' we fragmencie: " + fragment);
        }

        String prefix = fragment.substring(0, indeksSeparatora).trim();
        String wartosc = fragment.substring(indeksSeparatora + 1).trim();

        if (prefix.isEmpty()) {
            throw new NiepoprawnyPrefixException("Wystąpił pusty prefix we fragmencie: " + fragment);
        }

        pola.put(prefix.toLowerCase(), wartosc);
    }

    private Zdarzenie utworzZdarzenie(Map<String, String> pola) {
        RodzajStatku rodzajStatku = RodzajStatku.valueOf(pobierz(pola, "rodzajstatku"));
        Klasyfikacja klasyfikacja = Klasyfikacja.valueOf(pobierz(pola, "klasyfikacja"));

        String id = pobierz(pola, "id");
        LocalDate data = LocalDate.parse(pobierz(pola, "data"), FORMATTER_DATY);
        String miejsce = pobierz(pola, "miejsce");
        String typ = pobierz(pola, "typ");

        switch (rodzajStatku) {
            case SZYBOWIEC:
                return new ZdarzenieSzybowca(
                        id,
                        data,
                        miejsce,
                        typ,
                        klasyfikacja,
                        pobierz(pola, "linkdosprawy"),
                        pobierz(pola, "znakirozpoznawcze")
                );

            case SPADOCHRON:
                return new ZdarzenieSpadochronu(
                        id,
                        data,
                        miejsce,
                        typ,
                        klasyfikacja
                );

            case MOTOPARALOTNIA:
                return new ZdarzenieMotoparalotni(
                        id,
                        data,
                        miejsce,
                        typ,
                        klasyfikacja,
                        Double.parseDouble(pobierz(pola, "losowyczynnik"))
                );

            default:
                throw new IllegalArgumentException("Nieobsługiwany rodzaj statku: " + rodzajStatku);
        }
    }

    private String pobierz(Map<String, String> pola, String klucz) {
        if (!pola.containsKey(klucz)) {
            throw new IllegalArgumentException("Brakuje wymaganego pola: " + klucz);
        }

        return pola.get(klucz);
    }
}