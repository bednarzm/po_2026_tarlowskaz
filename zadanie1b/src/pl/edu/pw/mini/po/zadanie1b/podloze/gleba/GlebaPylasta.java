package pl.edu.pw.mini.po.zadanie1b.podloze.gleba;

import pl.edu.pw.mini.po.zadanie1b.narzedzia.Losowanie;

public class GlebaPylasta extends Gleba {
    private final boolean uzytekRolny;

    public GlebaPylasta() {
        super(
                Losowanie.losujInt(0, 36),
                36,
                Losowanie.losujDouble(1.2, 1.6)
        );
        this.uzytekRolny = Losowanie.losujBoolean();
    }

    public boolean isUzytekRolny() {
        return uzytekRolny;
    }

    @Override
    public double getUpCurrent() {
        return 0.62 * getTemperatura() / 7.0;
    }

    @Override
    public String opisPodstawowy() {
        return super.opisPodstawowy() + ", uzytekRolny=" + (uzytekRolny ? "tak" : "nie");
    }
}