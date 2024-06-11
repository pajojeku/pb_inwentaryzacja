package kss;

import org.junit.Before;
import org.junit.Test;

import kss.model.wyposazenie.Wyposazenie;
import kss.model.wyposazenie.Wyposazenie.Stan;
import kss.model.wyposazenie.mebel.Krzeslo;
import kss.model.wyposazenie.sprzet.Laptop;

import static org.junit.Assert.*;

public class SprzetTest {
    private Wyposazenie wyposazenie;

    @Before
    public void setUp() {
        wyposazenie = new Laptop(Stan.DOBRY);
    }

    @Test
    public void testGetNazwa() {
        assertEquals("LAPTOP", wyposazenie.getNazwa());
    }

    @Test
    public void testGetTyp() {
        assertEquals(Wyposazenie.Typ.SPRZET, wyposazenie.getTyp());
    }

    @Test
    public void testGetStan() {
        assertEquals(Wyposazenie.Stan.DOBRY, wyposazenie.getStan());
    }

    @Test
    public void testEquals() {
        Wyposazenie innyWyposazenie = new Laptop(Stan.DOBRY);
        assertTrue(wyposazenie.equals(innyWyposazenie));
    }
}
