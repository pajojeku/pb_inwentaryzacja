package kss.controllers;

import kss.app.App;
import kss.helpers.WyswietlanieAlertow;
import kss.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class DodajController {

    // Pole tekstowe do wprowadzania numeru sali
    @FXML
    private TextField numerSaliDoDodania;

    // Metoda wywołana przez przycisk, dodająca nową sale
    @FXML
    void dodajSale(ActionEvent event) {
        // Pobranie numeru sali z pola tekstowego, ustawienie wielkich liter i usuniecie zbednych spacji
        String numerSali = numerSaliDoDodania.getText().toUpperCase().trim();

        // Próba dodania sali do uczelni i zapisanie wyniku operacji
        int wynik = App.uczelnia.dodajSale(new Sala(numerSali));

        // Wyświetlenie odpowiedniego komunikatu w zależności od wyniku operacji
        WyswietlanieAlertow.wyswietl(wynik==0 ? AlertType.INFORMATION : AlertType.ERROR, 
        "Informacja", wynik==0 ? "Dodano salę!" : 
        wynik==1 ? "Sala o tym numerze już istnieje!" : "Numer sali nie może być pusty!");

        numerSaliDoDodania.clear();
    }
}
