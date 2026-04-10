package pl.edu.pw.mini.po.zadanie1b;

import pl.edu.pw.mini.po.zadanie1b.szybowiec.Bocian;
import pl.edu.pw.mini.po.zadanie1b.szybowiec.Szybowiec;
import pl.edu.pw.mini.po.zadanie1b.teren.Teren;

public class Main {
    public static void main(String[] args) {
        System.out.println("---------------DEMONSTRACJA ZADANIA 1B------------------");

        Teren teren = new Teren(12, 10);

        System.out.println("\n[1] UTWORZONO TEREN");
        System.out.println(teren.opisTerenu());

        System.out.println("\n[2] DEMONSTRACJA - 5 PRZELOTOW TYM SAMYM TYPEM SZYBOWCA");
        System.out.println("----------------------------------------------------------");

        for (int numerPrzelotu = 1; numerPrzelotu <= 5; numerPrzelotu++) {
            System.out.println("\n********** PRZELOT NR " + numerPrzelotu + " **********");

            Szybowiec szybowiec = new Bocian(teren, 20);

            System.out.println("[START] " + szybowiec);

            for (int cykl = 1; cykl <= 20; cykl++) {
                System.out.println("\n>>> CYKL " + cykl);
                teren.modifyAtmosphere();
                szybowiec.glide();
                System.out.println("[STAN] " + szybowiec);

                if (szybowiec.czyPozaTerenem()) {
                    System.out.println("[INFO] Szybowiec opuscil obszar symulacji.");
                    break;
                }
            }
        }
        System.out.println("-----------------KONIEC DEMONSTRACJI----------------------");
    }
}