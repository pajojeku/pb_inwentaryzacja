
package kss.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import kss.model.wyposazenie.Wyposazenie;

public class Sala implements Serializable {

    private List<Wyposazenie> wyposazenie = new ArrayList<>();
    private String numerSali;

    // Konstruktor sali
    public Sala(String numerSali) {
        this.numerSali = numerSali;
    }

    // Metoda zwracajaca numerSali
    public String getNumerSali() {
        return numerSali;
    }

    // Metoda dodajaca nowe wyposazenie do sali
    public void dodajSkladnik(Wyposazenie w) {
        this.wyposazenie.add(w);
    }

    // Metoda zwracajaca cale wyposazenie sali
    public List<Wyposazenie> getWyposazenie() {
        return wyposazenie;
    }


    // Nadpisanie metody equals()
    // Sale sa rowne gdy maja takie same numery
    @Override
    public boolean equals(Object obj) {
        if(obj!=null && obj instanceof Sala) {
            return getNumerSali().equals(((Sala) obj).getNumerSali());
        } else {
            return false;
        }
    }

    // Nadpisanie metody toString
    // Sala zwraca swoj numerSali, gdy zrzucona do stringa
    @Override
    public String toString() {
        return getNumerSali();
    }
}