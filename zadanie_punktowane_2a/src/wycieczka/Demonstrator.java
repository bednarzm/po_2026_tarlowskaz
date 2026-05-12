package wycieczka;

import wycieczka.generator.GeneratorUczniow;
import wycieczka.generator.UnikalnyGeneratorUczniow;
import wycieczka.osoba.Uczen;

import java.util.ArrayList;
import java.util.List;

public class Demonstrator {

    public static void main(String[] args) {

        System.out.println("=======SYMULACJA WYCIECZKI=======");

        System.out.println("\n--- 1. Tworzenie autobusu i kierowcy ---");

        Autobus autobus = new Autobus();
        Kierowca kierowca = new Kierowca(1, autobus);

        System.out.println("Utworzono autobus o pojemnosci: " + Autobus.POJEMNOSC);
        System.out.println("Utworzono kierowce: " + kierowca);

        System.out.println("\n--- 2. Tworzenie generatora uczniow ---");

        GeneratorUczniow generator = new UnikalnyGeneratorUczniow();

        System.out.println("Generator gotowy do pracy.");

        System.out.println("\n--- 3. Generowanie 40 unikalnych uczniow ---");

        List<Uczen> uczniowie = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            Uczen uczen = generator.stworzUcznia();
            uczniowie.add(uczen);
            System.out.println((i + 1) + ". " + uczen);
        }

        System.out.println("\nLiczba wygenerowanych uczniow: " + uczniowie.size());

        System.out.println("\n--- 4. Usadzanie uczniow w autobusie ---");

        kierowca.usadzUczniow(uczniowie);

        System.out.println("Uczniowie zostali usadzeni.");
        System.out.println("Pieciu uczniow z najwiekszym czynnikiem imprezowym siedzi na miejscach 41-45.");

        System.out.println("\n--- 5. Aktualny stan autobusu ---");

        autobus.wypiszPasazerow();

        System.out.println("\n--- 6. Kontrola uczniow przez kierowce ---");

        kierowca.kontrolujUczniow();

        System.out.println("Kontrola zakonczona.");

        System.out.println("\n--- 7. Podsumowanie przewinien ---");

        kierowca.wypiszPodpadziochow();

        System.out.println("=======KONIEC SYMULACJI=======");
    }
}