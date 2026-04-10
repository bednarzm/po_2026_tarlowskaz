package pl.edu.pw.mini.po.zadanie1b.podloze.gleba;

import pl.edu.pw.mini.po.zadanie1b.narzedzia.Losowanie;

public class GlebaKamienista extends Gleba {
    private static final double GESTOSC_MINIMALNA = 1.5;

    public GlebaKamienista() {
        super(
                Losowanie.losujInt(0, 36),
                36,
                Losowanie.losujDouble(1.5, 3.5)
        );
    }

    public double getGestoscMinimalna() {
        return GESTOSC_MINIMALNA;
    }

    @Override
    public double getUpCurrent() {
        return 0.62 * getTemperatura() / 7.0;
    }

    @Override
    public String opisPodstawowy() {
        return super.opisPodstawowy() + ", gestoscMinimalna=" + String.format("%.2f", GESTOSC_MINIMALNA);
    }
}
