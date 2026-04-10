package pl.edu.pw.mini.po.zadanie1b.podloze.piasek;

import pl.edu.pw.mini.po.zadanie1b.narzedzia.Losowanie;
import pl.edu.pw.mini.po.zadanie1b.podloze.Podloze;

public class Piasek extends Podloze {
    private final Sypkosc sypkosc;

    public Piasek() {
        super(Losowanie.losujInt(0, 48), 48);
        Sypkosc[] wartosci = Sypkosc.values();
        this.sypkosc = wartosci[Losowanie.losujIndeks(wartosci.length)];
    }

    public Sypkosc getSypkosc() {
        return sypkosc;
    }

    @Override
    public double getUpCurrent() {
        return 0.22 * getTemperatura() * Losowanie.losujDoubleZeroDoJedenBezJedynki();
    }

    @Override
    public String opisPodstawowy() {
        return super.opisPodstawowy() + ", sypkosc=" + sypkosc;
    }
}