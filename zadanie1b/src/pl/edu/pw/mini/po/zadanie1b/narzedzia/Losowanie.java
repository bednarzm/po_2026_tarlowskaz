package pl.edu.pw.mini.po.zadanie1b.narzedzia;

import java.util.Random;

public final class Losowanie {
    private static final Random RANDOM = new Random();

    private Losowanie() {
    }

    public static int losujInt(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }

    public static double losujDouble(double min, double max) {
        return min + (max - min) * RANDOM.nextDouble();
    }

    public static boolean losujBoolean() {
        return RANDOM.nextBoolean();
    }

    public static double losujDoubleZeroDoJedenBezJedynki() {
        return RANDOM.nextDouble();
    }

    public static int losujIndeks(int rozmiar) {
        return RANDOM.nextInt(rozmiar);
    }

    public static boolean losujZPrawdopodobienstwem(double prawdopodobienstwo) {
        return RANDOM.nextDouble() < prawdopodobienstwo;
    }
}