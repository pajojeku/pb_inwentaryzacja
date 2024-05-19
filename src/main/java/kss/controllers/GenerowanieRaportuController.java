package kss.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kss.app.*;
import kss.model.Sala;
import kss.model.Uczelnia;
import kss.model.Wyposazenie;
import kss.model.Wyposazenie.Stan;
import kss.utils.OperacjeNaPlikach;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GenerowanieRaportuController {

    @FXML
    private Pane stronaGlowna;

    @FXML
    private ComboBox<Sala> listaSalBox;

    @FXML
    private Button przyciskWczytywania;

    @FXML
    private CheckBox dokladnyRaportBox;

    private ObservableList<Sala> listaSal = FXCollections.observableArrayList();

    private Uczelnia ewidencja;


    @FXML
    public void initialize() {
        // Dodanie opisu (dymka) do checkboxa
        Tooltip tooltipDokladnyRaport = new Tooltip("Uwzględniaj różnice stanów wyposażenia między salami");
        tooltipDokladnyRaport.setShowDelay(Duration.seconds(0.3));
        Tooltip.install(dokladnyRaportBox, tooltipDokladnyRaport);

        // Wypelnienie comboboxa listy sal, salami uczelni
        listaSal.setAll(App.uczelnia.getSale());
        listaSalBox.setItems(listaSal);
        przyciskWczytywania.setDisable(true);

        // Dodanie nasłuchiwacza do comboboxa z salami, który odblokowuje przycisk generowania raportu
        // ,gdy uzytkownik wybral sale
        listaSalBox.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {przyciskWczytywania.setDisable(false);});
    }

    // Otwarcie okna wczytania pliku i wczytanie z niego ewidencji
    @FXML
    void wczytajPlik(ActionEvent event) {
        ewidencja = OperacjeNaPlikach.wczytajUczelnie(event);
        porownajUczelnie(ewidencja, event);
    }

    // Metoda porównująca aktualny stan uczelni z ewidencją
    private void porownajUczelnie(Uczelnia uczelnia, ActionEvent event) {
        Sala salaAktualna = listaSalBox.getSelectionModel().getSelectedItem();

        String raport = utworzRaport(uczelnia, salaAktualna);
       
        // Otwarcie okna zapisu pliku z raportem sali z domyslna nazwa
        String nazwaPliku = "Sala" + salaAktualna.getNumerSali() + "raport.txt";
        OperacjeNaPlikach.zapiszRaport(event, raport, nazwaPliku);
    }

    // Metoda tworząca raport różnic między obiektami uczelni
    private String utworzRaport(Uczelnia uczelnia, Sala salaAktualna) {
        String raport = "";
        List<Wyposazenie> listaAktualna = new ArrayList<>(salaAktualna.getWyposazenie());
        if (uczelnia.getSale().contains(salaAktualna)) {
            Sala salaEwidencji = uczelnia.getSala(uczelnia.getSale().indexOf(salaAktualna));
            List<Wyposazenie> listaEwidencji = new ArrayList<>(salaEwidencji.getWyposazenie());

            Collections.sort(listaAktualna, Comparator.comparing(Wyposazenie::getNazwa).thenComparing(Wyposazenie::getStan));
            Collections.sort(listaEwidencji, Comparator.comparing(Wyposazenie::getNazwa).thenComparing(Wyposazenie::getStan));

            List<Wyposazenie> prawidloweWyposazenie = new ArrayList<>();


            // Wykluczenie prawidłowego wyposazenia
            for(Wyposazenie wyposazenieAktualne : listaAktualna) {
                if(listaEwidencji.contains(wyposazenieAktualne)) {
                    listaEwidencji.remove(listaEwidencji.indexOf(wyposazenieAktualne));
                    prawidloweWyposazenie.add(wyposazenieAktualne);
                }
            }

            for(Wyposazenie odhaczone : prawidloweWyposazenie) {
                if(listaAktualna.contains(odhaczone)) {
                    listaAktualna.remove(listaAktualna.indexOf(odhaczone));
                }
            }

            List<Wyposazenie> tmp = new ArrayList<>(listaAktualna);
            Wyposazenie innyStan = null;

            // Zapis wyposażenia różniącego się stanem i nadajacego sie do wymiany
            glownaPetla:
            for(Wyposazenie wyposazenieAktualne : listaAktualna) {
                if(innyStan!=null) { listaEwidencji.remove(innyStan); }
                for(Wyposazenie wewidencji : listaEwidencji) {
                    if(wyposazenieAktualne.getNazwa().equals(wewidencji.getNazwa())) {
                        if(wyposazenieAktualne.getStan().equals(Stan.DO_WYMIANY)) {
                            raport += "Wyposazenie " + wyposazenieAktualne.getNazwa() + " nadaje się do wymiany!" + "\n";
                        }
                        // Dodanie informacji o roznicach stanu miedzy wyposazeniem jesli uzytkownik wybral dokladny raport
                        else if(dokladnyRaportBox.isSelected()) {
                            raport += "Wyposazenie " + wyposazenieAktualne.getNazwa() + " ma stan " + wyposazenieAktualne.getStan() + " zamiast " + wewidencji.getStan() + "\n";
                        }
                        innyStan = wewidencji;
                        tmp.remove(wyposazenieAktualne);
                        continue glownaPetla;
                    }
                }
            }

            listaEwidencji.remove(innyStan);
            listaAktualna = tmp;

            // Zapisanie wyposażenia, które nie powinno znalezc sie w sali
            for(Wyposazenie niepotrzebneWyposazenie : listaAktualna) {
                System.out.println(niepotrzebneWyposazenie);
                raport += "Wyposazenie " + niepotrzebneWyposazenie.getNazwa() + " nie powinno znaleźć się w sali! \n";
            }

            //Zapisanie wyposażenia, którego brakowało w sali
            for(Wyposazenie brakujaceWyposazenie : listaEwidencji) {
                raport += "W sali brakuje wyposazenia " + brakujaceWyposazenie.getNazwa() + "\n";
            }
        } else {
            // Informacja w przypadku braku wybranej sali w ewidencji
            raport = "Nie znaleziono sali " + salaAktualna.getNumerSali() + " w ewidencji!";
        }

        // Informacja w przypadku braku różnic 
        if(raport.equals("")) {
            raport = "Wszystko zgodne z ewidencją!";
        }

        return raport;
    }
}
