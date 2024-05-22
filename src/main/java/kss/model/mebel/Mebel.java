package kss.model.mebel;

import kss.model.Wyposazenie;

public class Mebel extends Wyposazenie{

    public Mebel(String nazwa, Stan stan) {
        super(nazwa, Typ.MEBEL, stan);
    }
}
