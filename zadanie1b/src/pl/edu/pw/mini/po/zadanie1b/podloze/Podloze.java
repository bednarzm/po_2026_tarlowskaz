package pl.edu.pw.mini.po.zadanie1b.podloze;

public abstract class Podloze {
    private int temperatura;
    private final int maksymalnaTemperatura;

    protected Podloze(int temperatura, int maksymalnaTemperatura) {
        this.maksymalnaTemperatura = maksymalnaTemperatura;
        this.temperatura = ograniczTemperature(temperatura);
    }

    public abstract double getUpCurrent();

    public void modifyTemperature(int deltaT) {
        this.temperatura = ograniczTemperature(this.temperatura + deltaT);
    }

    private int ograniczTemperature(int nowaTemperatura) {
        if (nowaTemperatura < 0) {
            return 0;
        }
        if (nowaTemperatura > maksymalnaTemperatura) {
            return maksymalnaTemperatura;
        }
        return nowaTemperatura;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public int getMaksymalnaTemperatura() {
        return maksymalnaTemperatura;
    }

    public String opisPodstawowy() {
        return "temperatura=" + temperatura + "/" + maksymalnaTemperatura;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + opisPodstawowy() + "}";
    }
}