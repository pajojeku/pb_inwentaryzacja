package kss.helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import kss.app.App;

public abstract class WyswietlanieAlertow {

    // Metoda wyswietlajaca prosty Alert
    public static void wyswietl(AlertType typAlertu, String tytul, String tresc) {
            Alert alert = new Alert(typAlertu);
            alert.setContentText(tresc);

            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(App.icon);
            alert.setTitle(tytul);
            alert.setHeaderText(null);

            alert.showAndWait();
    }
    
}
