package zadanie_punktowane_4;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import zadanie_punktowane_4.gra.SilnikGry;
import zadanie_punktowane_4.gra.StanGry;
import zadanie_punktowane_4.narzedzia.StaleGry;
import zadanie_punktowane_4.rysowanie.RysownikGry;
import zadanie_punktowane_4.sterowanie.KontrolerGry;

public class BombowceAplikacja extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Pane plansza = new Pane();
        plansza.setPrefSize(StaleGry.SZEROKOSC_PLANSZY, StaleGry.WYSOKOSC_PLANSZY);
        plansza.setStyle("-fx-background-color: white; -fx-border-color: lightgray;");

        StanGry stanGry = new StanGry();
        SilnikGry silnikGry = new SilnikGry(stanGry);
        RysownikGry rysownikGry = new RysownikGry(plansza);

        Button przyciskUzupelniania = new Button();
        przyciskUzupelniania.setStyle(StaleGry.STYL_PRZYCISKU);

        KontrolerGry kontrolerGry = new KontrolerGry(stanGry, silnikGry, rysownikGry, przyciskUzupelniania);

        plansza.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                kontrolerGry.dodajBombowiec(event.getX(), event.getY());
            }
        });

        przyciskUzupelniania.setOnAction(event -> kontrolerGry.uzupelnijPusteBombowce());

        HBox dolnyPanel = new HBox(przyciskUzupelniania);
        dolnyPanel.setAlignment(Pos.CENTER);
        dolnyPanel.setPadding(new Insets(10));

        BorderPane korzen = new BorderPane();
        korzen.setCenter(plansza);
        korzen.setBottom(dolnyPanel);

        stage.setTitle("Bombowce");
        stage.setScene(new Scene(korzen, StaleGry.SZEROKOSC_OKNA, StaleGry.WYSOKOSC_OKNA));
        stage.show();

        przyciskUzupelniania.setText(StaleGry.przygotujTekstPrzycisku(silnikGry.policzPusteBombowce()));

        new AnimationTimer() {
            @Override
            public void handle(long teraz) {
                kontrolerGry.wykonajKlatkeGry(teraz);
            }
        }.start();
    }
}
