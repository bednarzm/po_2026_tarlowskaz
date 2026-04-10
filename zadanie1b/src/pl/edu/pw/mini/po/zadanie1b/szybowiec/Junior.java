package pl.edu.pw.mini.po.zadanie1b.szybowiec;

import pl.edu.pw.mini.po.zadanie1b.teren.Teren;

public class Junior extends Szybowiec {
    public Junior(Teren teren, double wysokoscPoczatkowa) {
        super(teren, wysokoscPoczatkowa);
    }

    @Override
    protected String getKomunikatLadowania() {
        return "Junior dal w pole";
    }
}