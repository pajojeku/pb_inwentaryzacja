package kss.model.wyposazenie;

import java.io.Serializable;

public abstract class Wyposazenie implements Serializable{

    protected String nazwa;
    private Typ typ;
    private Stan stan;

    // Konstruktor wyposazenia
    public Wyposazenie(String nazwa, Typ typ, Stan stan) {
        this.nazwa = nazwa;
        this.typ = typ;
        this.stan = stan;
    }

    public Wyposazenie() {
        this.nazwa = null;
        this.typ = null;
        this.stan = null;
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

    public void setStan(Stan stan) {
        this.stan = stan;
    }

    public void setTyp(Typ typ) {
        this.typ = typ;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
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

    @Override
    public String toString() {
        return nazwa;
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
