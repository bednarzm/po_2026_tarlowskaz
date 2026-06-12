package zadanie_punktowane_4.narzedzia;

public final class StaleGry {

    public static final double SZEROKOSC_OKNA = 900;
    public static final double WYSOKOSC_OKNA = 620;

    public static final double SZEROKOSC_PLANSZY = 900;
    public static final double WYSOKOSC_PLANSZY = 540;

    public static final double SZEROKOSC_BOMBOWCA = 80;
    public static final double WYSOKOSC_BOMBOWCA = 35;

    public static final double PROMIEN_BOMBY = 3;
    public static final double PROMIEN_WYBUCHU = 24;

    public static final int LICZBA_BOMB_W_BOMBOWCU = 20;

    public static final double PREDKOSC_BOMBY = 90;
    public static final double PREDKOSC_BOMBOWCA = 30;

    public static final double POZIOM_ZIEMI = 480;

    public static final long MIN_ODSTEP_ZRZUTU_NANO = 300_000_000L;
    public static final long MAX_ODSTEP_ZRZUTU_NANO = 1_100_000_000L;

    public static final String TEKST_PRZYCISKU_UZUPELNIANIA = "Refill bombers!";

    public static final String STYL_PRZYCISKU = """
            -fx-background-color: crimson;
            -fx-text-fill: white;
            -fx-font-weight: bold;
            -fx-font-size: 14px;
            -fx-padding: 8px 16px 8px 16px;
            """;

    private StaleGry() {
    }

    public static String przygotujTekstPrzycisku(int liczbaPustychBombowcow) {
        return TEKST_PRZYCISKU_UZUPELNIANIA + " (Empty bombers: " + liczbaPustychBombowcow + ")";
    }
}