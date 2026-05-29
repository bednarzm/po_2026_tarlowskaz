package zdarzenia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class SkanerKatalogu {

    public void wypiszZawartoscKatalogu(String sciezkaWzgledna) throws IOException {
        Path sciezka = Path.of(sciezkaWzgledna);

        if (!Files.isDirectory(sciezka)) {
            System.out.println("Podana ścieżka nie wskazuje na katalog: " + sciezkaWzgledna);
            return;
        }

        List<Path> elementy;

        try (Stream<Path> stream = Files.list(sciezka)) {
            elementy = stream
                    .sorted(Comparator.comparing(path -> path.getFileName().toString()))
                    .toList();
        }

        System.out.println("Pliki bez rozszerzeń:");

        for (Path element : elementy) {
            if (Files.isRegularFile(element)) {
                System.out.println(" - " + usunRozszerzenie(element.getFileName().toString()));
            }
        }

        System.out.println("Katalogi:");

        for (Path element : elementy) {
            if (Files.isDirectory(element)) {
                System.out.println(" - " + element.getFileName());
            }
        }

        System.out.println("Ścieżka bezwzględna analizowanego katalogu:");
        System.out.println(sciezka.toAbsolutePath());
    }

    private String usunRozszerzenie(String nazwaPliku) {
        int indeksKropki = nazwaPliku.lastIndexOf('.');

        if (indeksKropki <= 0) {
            return nazwaPliku;
        }

        return nazwaPliku.substring(0, indeksKropki);
    }
}