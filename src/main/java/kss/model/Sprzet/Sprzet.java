package kss.model.sprzet;

import kss.model.Wyposazenie;

public class Sprzet extends Wyposazenie{

    public Sprzet(String nazwa, Stan stan) {
        super(nazwa, Typ.SPRZET, stan);
    }
}
