package zadanie_punktowane_4.rysowanie;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import zadanie_punktowane_4.bomba.Bomba;
import zadanie_punktowane_4.bomba.Bomba.Stan;
import zadanie_punktowane_4.bombowiec.Bombowiec;
import zadanie_punktowane_4.gra.StanGry;
import zadanie_punktowane_4.narzedzia.StaleGry;

public class RysownikGry {

    private static final Color KOLOR_OBWODKI_PELNY = Color.rgb(65, 105, 225);

    private final Pane plansza;

    public RysownikGry(Pane plansza) {
        this.plansza = plansza;
    }

    public void odswiez(StanGry stanGry) {
        plansza.getChildren().clear();

        narysujZiemie();

        for (Bomba bomba : stanGry.getBomby()) {
            narysujBombe(bomba);
        }

        for (Bombowiec bombowiec : stanGry.getBombowce()) {
            narysujBombowiec(bombowiec);
        }
    }

    private void narysujZiemie() {
        Line liniaZiemi = new Line(
                0,
                StaleGry.POZIOM_ZIEMI,
                StaleGry.SZEROKOSC_PLANSZY,
                StaleGry.POZIOM_ZIEMI
        );
        liniaZiemi.setStroke(Color.LIMEGREEN);
        liniaZiemi.setStrokeWidth(2);
        plansza.getChildren().add(liniaZiemi);
    }

    private void narysujBombowiec(Bombowiec bombowiec) {
        Rectangle prostokat = new Rectangle(
                bombowiec.getX(),
                bombowiec.getY(),
                bombowiec.getSzerokosc(),
                bombowiec.getWysokosc()
        );
        prostokat.setFill(Color.WHITE);
        prostokat.setStroke(obliczKolorObwodki(bombowiec));
        prostokat.setStrokeWidth(2);

        Text napis = new Text("Bombs: " + bombowiec.getLiczbaBomb());
        napis.setFill(Color.BLUE);
        napis.setFont(Font.font(11));

        Bounds granice = napis.getLayoutBounds();
        napis.setX(bombowiec.getSrodekX() - (granice.getMinX() + granice.getWidth()) / 2);
        napis.setY(bombowiec.getSrodekY() - (granice.getMinY() + granice.getHeight()) / 2);

        plansza.getChildren().add(prostokat);
        plansza.getChildren().add(napis);
    }

    private void narysujBombe(Bomba bomba) {
        Circle kolko = new Circle(
                bomba.getSrodekX(),
                bomba.getSrodekY(),
                bomba.getPromienRysowania()
        );

        kolko.setFill(Color.TRANSPARENT);

        if (bomba.getStan() == Stan.SPADA) {
            kolko.setStroke(Color.BLACK);
        } else {
            kolko.setStroke(Color.RED);
        }

        kolko.setStrokeWidth(2);
        plansza.getChildren().add(kolko);
    }

    private Color obliczKolorObwodki(Bombowiec bombowiec) {
        double proporcjaZuzytych = bombowiec.getProporcjaZuzytychBomb();
        double wspolczynnik = 1 - proporcjaZuzytych;

        return Color.color(
                KOLOR_OBWODKI_PELNY.getRed() * wspolczynnik,
                KOLOR_OBWODKI_PELNY.getGreen() * wspolczynnik,
                KOLOR_OBWODKI_PELNY.getBlue() * wspolczynnik
        );
    }
}
