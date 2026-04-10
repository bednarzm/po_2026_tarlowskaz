package pl.edu.pw.mini.po.zadanie1b.podloze.gleba;

import pl.edu.pw.mini.po.zadanie1b.podloze.Podloze;

public abstract class Gleba extends Podloze {
    private final double gestosc;

    protected Gleba(int temperatura, int maksymalnaTemperatura, double gestosc) {
        super(temperatura, maksymalnaTemperatura);
        this.gestosc = gestosc;
    }

    public double getGestosc() {
        return gestosc;
    }

    @Override
    public String opisPodstawowy() {
        return super.opisPodstawowy() + ", gestosc=" + String.format("%.2f", gestosc);
    }
}