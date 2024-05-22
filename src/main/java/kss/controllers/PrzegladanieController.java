package kss.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import kss.app.App;
import kss.model.*;
import kss.model.sprzet.Drukarka;
import kss.model.sprzet.InnyMebel;
import kss.model.sprzet.Komputer;
import kss.model.Wyposazenie.Stan;
import kss.model.Wyposazenie.Typ;
import kss.model.mebel.Biurko;
import kss.model.mebel.InnySprzet;
import kss.model.mebel.Krzeslo;
import kss.ui.TableCellNumerowany;
import kss.ui.TableCellZTooltipem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

public class PrzegladanieController {

    @FXML
    private ComboBox<Sala> listaSalBox;

    @FXML
    private ComboBox<Sala> nowaSalaBox;

    @FXML
    private StackPane stackPane;


    @FXML
    private TextField wyszukiwarka;

    @FXML
    private ComboBox<Wyposazenie> wyposazenieBox;

    @FXML
    private ComboBox<Wyposazenie.Stan> stanBox;

    @FXML
    private ComboBox<Wyposazenie.Typ> typBox;

    private ObservableList<Sala> listaSal = FXCollections.observableArrayList();
    private ObservableList<Wyposazenie.Stan> stanBoxList = FXCollections.observableArrayList();
    private ObservableList<Wyposazenie.Typ> typBoxList = FXCollections.observableArrayList();
    private ObservableList<Wyposazenie> listaWyposazenia = FXCollections.observableArrayList();
    private ObservableList<Wyposazenie> wyposazenieBoxList = FXCollections.observableArrayList();
    private TableView<Wyposazenie> tabelaWyposazenia;

    @FXML
    public void initialize() {
        // Wyświetlenie tabeli wyposażenia
        stackPane.getChildren().add(skonstruujTabele());
        wypelnijComboboxy();

        // Dodanie nasluchiwacza, który odblokowuje wyszukiwarke i wyświetla przefiltrowane wyposażenie
        // ,gdy zostanie wybrana sala w comboboxie wyboru sali
        listaSalBox.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
            wyszukiwarka.setDisable(false);
            filtrujWyposazenie();
        });

        typBox.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
            wyposazenieBox.setDisable(false);
            Typ typ = typBox.getSelectionModel().getSelectedItem();
            if (typ.equals(Typ.SPRZET)) {
                wyposazenieBoxList.setAll(new Drukarka(null), new Komputer(null), new InnySprzet(null));
            } else if (typ.equals(Typ.MEBEL)) {
                wyposazenieBoxList.setAll(new Krzeslo(null), new Biurko(null), new InnyMebel(null));
            }
            wyposazenieBox.setItems(wyposazenieBoxList);

        });
        // Dodanie nasłuchiwacza, który na biezaco filtruje wyposazenie po nazwie wpisanej w wyszukiwarke
        wyszukiwarka.textProperty().addListener( (options, oldValue, newValue) -> {filtrujWyposazenie();});

        // Zablokowanie wyszukiwarki, zanim użytkownik wybierze sale
        wyszukiwarka.setDisable(true);

    }

    // Metoda wywołana przyciskiem dodania wyposazenia
    @FXML
    void dodajSkladnik(ActionEvent event) {
        Sala sala = listaSalBox.getSelectionModel().getSelectedItem();
        Typ typ = typBox.getSelectionModel().getSelectedItem();
        Stan stan = stanBox.getSelectionModel().getSelectedItem();

        // Pobranie nazwy z pola, ustawienie wielkich liter i usuniecie zbednych spacji
        Wyposazenie w = wyposazenieBox.getSelectionModel().getSelectedItem();
        w.setStan(stan);

        if(sala!=null && typ!=null && stan!=null) {
            sala.dodajSkladnik(w);
            filtrujWyposazenie();
        }
    }


    // Metoda wywołana przyciskiem usuniecia wyposazenia
    @FXML
    void usunSkladnik(ActionEvent event) {
        Wyposazenie wyposazenie = tabelaWyposazenia.getSelectionModel().getSelectedItem();

        if(wyposazenie!=null) {
            listaSalBox.getSelectionModel().getSelectedItem().getWyposazenie().remove(wyposazenie);
            filtrujWyposazenie();
        }
    }

    // Metoda wywołana przyciskiem przeniesienia wyposazenia
    @FXML
    void przeniesWyposazenie(ActionEvent event) {
        if(nowaSalaBox.getSelectionModel().getSelectedItem()!=null) {
            Wyposazenie wyposazenie = tabelaWyposazenia.getSelectionModel().getSelectedItem();
            nowaSalaBox.getSelectionModel().getSelectedItem().dodajSkladnik(wyposazenie);
            listaSalBox.getSelectionModel().getSelectedItem().getWyposazenie().remove(wyposazenie);
            filtrujWyposazenie();
        }
    }

    // Metoda wpisujaca w tabele wyposazenie sali przefiltrowane po nazwie
    private void filtrujWyposazenie() {
        if(!wyszukiwarka.getText().equals("")) {
            Sala wyposazenieSali = listaSalBox.getSelectionModel().getSelectedItem();

            List<Wyposazenie> tmp = wyposazenieSali.getWyposazenie().stream()
            .filter(o -> o.getNazwa().toLowerCase().contains(wyszukiwarka.getText().toLowerCase()))
            .collect(Collectors.toList());

            ObservableList<Wyposazenie> przefiltrowanaLista = FXCollections.observableArrayList(tmp);

            tabelaWyposazenia.setItems(przefiltrowanaLista);
        } else {
            aktualizujListeWyposazenia();
        }
    }

    // Metoda wpisujaca w tabele cale wyposazenie sali
    private void aktualizujListeWyposazenia() {       
        Sala wyposazenieSali = listaSalBox.getSelectionModel().getSelectedItem();
        listaWyposazenia = FXCollections.observableArrayList(wyposazenieSali.getWyposazenie());
        tabelaWyposazenia.getItems().clear();
        tabelaWyposazenia.setItems(listaWyposazenia);
    }

    // Metoda ustawiajaca zawartosc wszystkich comboboxow
    private void wypelnijComboboxy() {
        listaSal.addAll(App.uczelnia.getSale());
        listaSalBox.setItems(listaSal);

        typBoxList.setAll(Typ.values());
        typBox.setItems(typBoxList);

        stanBoxList.setAll(Stan.values());
        stanBox.setItems(stanBoxList);

        nowaSalaBox.setItems(listaSal);
        
        // Ustawienie domyślnego wyboru w comboboxie stanu na NOWY
        stanBox.getSelectionModel().select(Stan.NOWY);
    }

    // Metoda tworzaca tabele i jej kolumny
    private TableView<Wyposazenie> skonstruujTabele() {
        TableColumn<Wyposazenie, Void> nrColumn = new TableColumn<>("Nr");
        nrColumn.setCellFactory(cell -> new TableCellNumerowany<>());
        nrColumn.setSortable(false);
        nrColumn.setResizable(false);
        
        TableColumn<Wyposazenie, String> nazwaColumn = utworzKolumne("Nazwa", "nazwa");
        TableColumn<Wyposazenie, String> typColumn = utworzKolumne("Typ", "typ");
        TableColumn<Wyposazenie, String> stanColumn = utworzKolumne("Stan", "stan");

        tabelaWyposazenia = new TableView<>();
        tabelaWyposazenia.getColumns().addAll(Arrays.asList(nrColumn, nazwaColumn, typColumn, stanColumn));
        tabelaWyposazenia.setPrefWidth(stackPane.getWidth());
        tabelaWyposazenia.setPrefHeight(stackPane.getHeight());

        nrColumn.prefWidthProperty().bind(tabelaWyposazenia.widthProperty().multiply(0.08));
        nazwaColumn.prefWidthProperty().bind(tabelaWyposazenia.widthProperty().multiply(0.34));
        typColumn.prefWidthProperty().bind(tabelaWyposazenia.widthProperty().multiply(0.27));
        stanColumn.prefWidthProperty().bind(tabelaWyposazenia.widthProperty().multiply(0.27));

        tabelaWyposazenia.setPlaceholder(new Label("BRAK WYPOSAŻENIA W SALI"));

        return tabelaWyposazenia;
    }

    // Metoda tworzaca kolumny tabeli
    private TableColumn<Wyposazenie, String> utworzKolumne(String nazwa, String pole) {
        TableColumn<Wyposazenie, String> kolumna = new TableColumn<>(nazwa);
        kolumna.setCellValueFactory(new PropertyValueFactory<>(pole));
        kolumna.setCellFactory(cell -> new TableCellZTooltipem<>());
        kolumna.setResizable(false);

        return kolumna;
    }
}
