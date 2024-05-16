package kss.model;

import java.io.Serializable;

public class Wyposazenie implements Serializable{

    private String nazwa;
    private Typ typ;
    private Stan stan;

    // Konstruktor wyposazenia
    public Wyposazenie(String nazwa, Typ typ, Stan stan) {
        this.nazwa = nazwa;
        this.typ = typ;
        this.stan = stan;
    }

    // Metoda zwracajaca nazwe wyposazenia
    public String getNazwa() {
        return nazwa;
    }

    // Metoda zwracaja typ wypoaszenia
    public Typ getTyp() {
        return typ;
    }

    // Metoda zwracaja stan wypoaszenia
    public Stan getStan() {
        return stan;
    }

    // Nadpisanie metody equals
    // Skladniki sa sobie rowne jesli maja taka sama nazwe, typ i stan
    @Override
    public boolean equals(Object obj) {
        if(obj!=null && obj instanceof Wyposazenie) {
            boolean zgodnaNazwa = getNazwa().equals(((Wyposazenie) obj).getNazwa());
            boolean zgodnyTyp = getTyp() == ((Wyposazenie) obj).getTyp();
            boolean zgodnyStan = getStan() == ((Wyposazenie) obj).getStan();
            return zgodnaNazwa && zgodnyStan && zgodnyTyp;
        } else {
            return false;
        }
    }

    // Mozliwe stany wyposazenia
    public static enum Stan {
        NOWY,
        DOBRY,
        DO_WYMIANY
    }
    
    // Mozliwe typy wyposazenia
    public static enum Typ {
        MEBEL,
        SPRZET
    }
}
