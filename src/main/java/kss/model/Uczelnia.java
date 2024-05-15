
package kss.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Uczelnia implements Serializable {
    private List<Sala> sale = new ArrayList<>();

    // Metoda zwracajaca sale uczelni po indexie
    public Sala getSala(int i) {
        return this.sale.get(i);
    }

    // Metoda dodajaca sale do uczelni
    // Jesli dodano pomyslnie zwraca - kod 0
    // Jesli sala juz istniala w uczelni - kod 1
    // Jesli sali brakuje nazwy - kod 2
    public int dodajSale(Sala s) {
        if(s.getNumerSali().equals("")) {
            return 2;
        }
        for(int i=0; i<sale.size(); i++) {
            if(sale.get(i).getNumerSali().equals(s.getNumerSali())) {
                return 1;
            }
        }
        this.sale.add(s);
        return 0;
    }

    // Metoda usuwajaca sale z uczelni
    public int usunSale(Sala s) {
        if(s!=null) {
            this.sale.remove(s);
            return 0;
        }
        return 1;
    }

    // Metoda sprawdzajaca czy uczelnia nie ma sal
    public boolean czyPusta() {
        return this.sale.isEmpty();
    }

    // Metoda zwracaja liste Sal
    public List<Sala> getSale() {
        return this.sale;
    }    
}