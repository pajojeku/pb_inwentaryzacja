package kss.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import kss.helpers.WyswietlanieAlertow;
import kss.model.Uczelnia;
import xss.it.nfx.NfxWindow;

public class App extends Application {

    // Utworzenie głównej instancji Uczelni
    public static Uczelnia uczelnia = new Uczelnia();
    
    // Załadowanie ikony aplikacji
    public static Image icon = new Image("/icon.png");

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Sprawdzenie, czy system operacyjny to Windows 10 lub nowszy
        if(System.getProperty("os.name").contains("Windows 1")) {
            // Załadowanie głównego widoku aplikacji
            Parent root = FXMLLoader.load(getClass().getResource("/scenes/MainScene.fxml"));

            // Jeśli system operacyjny to Windows 11, to ustaw ciemny motyw
            if(System.getProperty("os.name").equals("Windows 11")) {
                primaryStage = new NfxWindow();
                ((NfxWindow) primaryStage).setTitleBarColor(Color.BLACK);
                ((NfxWindow) primaryStage).setCaptionColor(Color.WHITESMOKE);
            }

            // Utworzenie sceny i dodanie do niej załadowanego widoku
            Scene scene = new Scene(root);
    
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Inwentaryzacja Uczelni");
            primaryStage.getIcons().add(icon);
            
            // Wyświetlenie okna
            primaryStage.show();

        } else {
            // Jeśli system operacyjny nie jest Windows 10 lub nowszy, wyświetlenie błędu i zakończenie działania aplikacji
            WyswietlanieAlertow.wyswietl(AlertType.ERROR, "Błąd",
            "Do działania programu wymagany jest system operacyjny Windows 10 lub nowszy!");
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        // Uruchomienie aplikacji JavaFX
        launch(args);
    }
}