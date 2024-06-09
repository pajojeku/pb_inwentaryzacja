package kss.utils;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import kss.app.App;

public abstract class WyswietlanieOkien {

    // Metoda wyswietlajaca prosty Alert
    public static final void wyswietlAlert(AlertType typAlertu, String tytul, String tresc) {
            Alert alert = new Alert(typAlertu);
            alert.setContentText(tresc);

            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(App.icon);
            alert.setTitle(tytul);
            alert.setHeaderText(null);

            alert.showAndWait();
    }

    // Metoda wyswietlajaca okno dialogowe
    public static final String wyswietlDialog(String tytul, String tekst) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setGraphic(null);
        ((Stage)dialog.getDialogPane().getScene().getWindow()).getIcons().add(App.icon);
        dialog.setTitle(tytul);
        dialog.setHeaderText("Proszę wybrać nazwę dla tego wyposażenia:");
        dialog.setContentText("Nazwa:");
        dialog.getDialogPane().getButtonTypes().remove(ButtonType.CANCEL);
        Optional<String> result = dialog.showAndWait();

        return result.isPresent() ? result.get().trim().toUpperCase() : "";
    }
    
}
