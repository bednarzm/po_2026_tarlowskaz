package wycieczka;

public enum Zachowanie {

    POPRAWNE("poprawne"),
    NIEPOPRAWNE("niepoprawne"),
    WYKRECIL_NUMER("wykrecil_numer"),
    WYWOLAL_SKANDAL("wywolal_skandal");

    private final String opis;

    Zachowanie(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return opis;
    }
}