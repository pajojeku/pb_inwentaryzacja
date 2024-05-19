package kss.controllers;

import kss.app.*;
import kss.model.Sala;
import kss.utils.WyswietlanieAlertow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;

public class UsunController {

    
    // Combobox do wybrania sali do usuniecia
    @FXML
    private ComboBox<Sala> listaSalBox;

    // Zawartosc comboboxa listaSalBox
    private ObservableList<Sala> listaSal = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listaSal.setAll(App.uczelnia.getSale());
        listaSalBox.setItems(listaSal);
        listaSalBox.getSelectionModel().clearSelection();
    }

    @FXML
    void usunSale(ActionEvent event) {
        // Pobranie sali wybranej przez uzytkownika
        Sala sala = listaSalBox.getSelectionModel().getSelectedItem();

        // Próba usuniecia sali i zapisanie wyniku operacji
        int wynik = App.uczelnia.usunSale(sala);

        // Wyświetlenie odpowiedniego komunikatu w zależności od wyniku operacji
        WyswietlanieAlertow.wyswietl(wynik==0 ? AlertType.INFORMATION : AlertType.ERROR, 
        "Informacja", wynik==0 ? "Sala usunięta!" : "Najpierw wybierz salę!");
        
        // Ponowne ustawienie pol comboboxa listaSalBox po usunieciu sali
        initialize();
    }
}
