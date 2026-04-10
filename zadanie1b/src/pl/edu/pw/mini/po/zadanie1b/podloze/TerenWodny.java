package pl.edu.pw.mini.po.zadanie1b.podloze;

import pl.edu.pw.mini.po.zadanie1b.narzedzia.Losowanie;

public class TerenWodny extends Podloze {
    private final boolean dotacje;

    public TerenWodny() {
        super(Losowanie.losujInt(0, 24), 24);
        this.dotacje = Losowanie.losujBoolean();
    }

    public boolean isDotacje() {
        return dotacje;
    }

    @Override
    public double getUpCurrent() {
        return getTemperatura() / 10.0 - Losowanie.losujInt(1, 3);
    }

    @Override
    public String opisPodstawowy() {
        return super.opisPodstawowy() + ", dotacje=" + (dotacje ? "tak" : "nie");
    }
}