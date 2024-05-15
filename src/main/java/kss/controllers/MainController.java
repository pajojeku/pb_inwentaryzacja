package kss.controllers;

import java.io.IOException;
import kss.app.App;
import kss.helpers.OperacjeNaPlikach;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainController {

    @FXML
    private Pane stronaGlowna;

    @FXML
    private BorderPane borderPane;

    // Metody uruchamiane poprzez wybranie pozycji z panelu nawigacji

    @FXML
    void scenaDodajSale(ActionEvent event) {
        zmienWidok("DodawanieSaliScene.fxml");
    }

    @FXML
    void scenaGenerujRaport(ActionEvent event) {
        sprobujZmienicWidok("GenerowanieRaportuScene.fxml");
    }

    @FXML
    void scenaPrzegladajSale(ActionEvent event) {
        sprobujZmienicWidok("PrzegladajSaleScene.fxml");
    }

    @FXML
    void scenaUsunSale(ActionEvent event) {
        sprobujZmienicWidok("UsuwanieSaliScene.fxml");
    }

    // Otwarcie okna zapisu stanu uczelni do pliku
    @FXML
    void zapiszStan(ActionEvent event) {
        OperacjeNaPlikach.zapiszUczelnie(event, App.uczelnia);
    }
    
    // Otwarcie okna wczytywania pliku i wczytanie z niego zapisanego stanu uczelni
    @FXML
    void wczytajPlik(ActionEvent event) {
        App.uczelnia = OperacjeNaPlikach.wczytajUczelnie(event, "SAV files (*.sav)", "*.sav");
        sprobujZmienicWidok("PrzegladajSaleScene.fxml");
    }

    // Metoda zmieniajaca widok tylko jesli uczelnia ma jakies sale (wczytano ja z pliku, lub dodano)
    private void sprobujZmienicWidok(String string) {
        if(App.uczelnia != null && !App.uczelnia.czyPusta()) {
            zmienWidok(string);
        } else {
            borderPane.setCenter(stronaGlowna);
        }
    }

    // Metoda zmieniajaca widok (podmieniajca sekcje glowna borderPane)
    private void zmienWidok(String scena) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/scenes/"+scena));
            borderPane.setCenter(root);  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
