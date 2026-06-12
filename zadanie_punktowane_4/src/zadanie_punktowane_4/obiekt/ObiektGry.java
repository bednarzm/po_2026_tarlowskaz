package zadanie_punktowane_4.obiekt;

public abstract class ObiektGry {

    private double x;
    private double y;
    private double szerokosc;
    private double wysokosc;

    protected ObiektGry(double x, double y, double szerokosc, double wysokosc) {
        this.x = x;
        this.y = y;
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSzerokosc() {
        return szerokosc;
    }

    public double getWysokosc() {
        return wysokosc;
    }

    public double getSrodekX() {
        return x + szerokosc / 2;
    }

    public double getSrodekY() {
        return y + wysokosc / 2;
    }

    public void przesun(double dx, double dy) {
        x += dx;
        y += dy;
    }

    protected void ustawPozycje(double x, double y) {
        this.x = x;
        this.y = y;
    }

    protected void ustawRozmiar(double szerokosc, double wysokosc) {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
    }
}
