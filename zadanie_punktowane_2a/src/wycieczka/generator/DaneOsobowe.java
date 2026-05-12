package wycieczka.generator;

import java.util.Collections;
import java.util.List;

public final class DaneOsobowe {

    public static final List<String> IMIONA = Collections.unmodifiableList(List.of(
            "Jacek",
            "Asia",
            "Piotrek",
            "Jola",
            "Adam",
            "Zuzia",
            "Nikodem",
            "Alicja",
            "Zygmunt",
            "Genowefa"
    ));

    public static final List<String> NAZWISKA = Collections.unmodifiableList(List.of(
            "Kowalek",
            "Kluseczka",
            "Nowak",
            "Zbijak",
            "Zabijak",
            "Hallumi",
            "Gyros",
            "Kozak",
            "Wojda",
            "Brok"
    ));

    private DaneOsobowe() {
    }
}