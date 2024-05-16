package kss.helpers;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kss.model.Uczelnia;

public abstract class OperacjeNaPlikach {
    
    // Obsluga okna wczytywania uczelni z pliku
    public static Uczelnia wczytajUczelnie(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SAV files (*.sav)", "*.sav"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

        Uczelnia uczelnia = new Uczelnia();
    
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                FileInputStream fileIn = new FileInputStream(selectedFile.getPath());
                ObjectInputStream in = new ObjectInputStream(fileIn);
                uczelnia = (Uczelnia) in.readObject();
                in.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException c) {
                c.printStackTrace();
                WyswietlanieAlertow.wyswietl(AlertType.ERROR, "Błąd", "Nie udało się wczytać pliku!\nPlik "+selectedFile.getName()+" może być uszkodzony!");
            }
        }
        return uczelnia;
    }

    // Obsluga okna zapisu uczelni do pliku
    public static void zapiszUczelnie(ActionEvent event, Uczelnia uczelnia) {
        if(uczelnia.czyPusta()) {
            WyswietlanieAlertow.wyswietl(AlertType.ERROR, "Błąd zapisu", "Twoja uczelnia nie posiada sal!");
        } else {
            try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SAV files (*.sav)", "*.sav"));
                fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
                File file = fileChooser.showSaveDialog(stage);
                if (file != null) {
                    FileOutputStream fileOut = new FileOutputStream(file);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(uczelnia);
                    out.close();
                    fileOut.close();
                }
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }

    // Otwarcie pliku domyslnym programem
    public static void otworzPlik(File file) {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Obsluga okna zapisu raportu do pliku
    public static void zapiszRaport(ActionEvent event, String tekst, String nazwaPliku) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();        
        fileChooser.setInitialFileName(nazwaPliku);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(tekst);
            } catch (IOException e) {
                e.printStackTrace();
            }
            otworzPlik(file);
        }
    }
}
