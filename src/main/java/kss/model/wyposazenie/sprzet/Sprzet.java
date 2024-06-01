package kss.model.wyposazenie.sprzet;

import kss.model.wyposazenie.Wyposazenie;

public abstract class Sprzet extends Wyposazenie{

    public Sprzet(String nazwa, Stan stan) {
        super(nazwa, Typ.SPRZET, stan);
    }
}
