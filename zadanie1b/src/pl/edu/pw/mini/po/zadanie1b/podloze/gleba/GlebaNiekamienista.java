package pl.edu.pw.mini.po.zadanie1b.podloze.gleba;

import pl.edu.pw.mini.po.zadanie1b.narzedzia.Losowanie;

public class GlebaNiekamienista extends Gleba {
    public GlebaNiekamienista() {
        super(
                Losowanie.losujInt(0, 36),
                36,
                Losowanie.losujDouble(1.0, 1.4)
        );
    }

    @Override
    public double getUpCurrent() {
        return 0.62 * getTemperatura() / 7.0;
    }
}