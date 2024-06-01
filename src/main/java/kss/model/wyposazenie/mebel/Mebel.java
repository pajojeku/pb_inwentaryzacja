package kss.model.wyposazenie.mebel;

import kss.model.wyposazenie.Wyposazenie;

public abstract class Mebel extends Wyposazenie{

    public Mebel(String nazwa, Stan stan) {
        super(nazwa, Typ.MEBEL, stan);
    }
}
