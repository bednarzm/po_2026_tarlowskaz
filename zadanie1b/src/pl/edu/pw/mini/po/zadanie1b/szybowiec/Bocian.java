package pl.edu.pw.mini.po.zadanie1b.szybowiec;

import pl.edu.pw.mini.po.zadanie1b.teren.Teren;

public class Bocian extends Szybowiec {
    public Bocian(Teren teren, double wysokoscPoczatkowa) {
        super(teren, wysokoscPoczatkowa);
    }

    @Override
    protected String getKomunikatLadowania() {
        return "Bocian wyladowal";
    }
}