package kss;

import org.junit.Before;
import org.junit.Test;

import kss.model.Wyposazenie;

import static org.junit.Assert.*;

public class WyposazenieTest {
    private Wyposazenie wyposazenie;

    @Before
    public void setUp() {
        wyposazenie = new Wyposazenie("Krzesło", Wyposazenie.Typ.MEBEL, Wyposazenie.Stan.DOBRY);
    }

    @Test
    public void testGetNazwa() {
        assertEquals("Krzesło", wyposazenie.getNazwa());
    }

    @Test
    public void testGetTyp() {
        assertEquals(Wyposazenie.Typ.MEBEL, wyposazenie.getTyp());
    }

    @Test
    public void testGetStan() {
        assertEquals(Wyposazenie.Stan.DOBRY, wyposazenie.getStan());
    }

    @Test
    public void testEquals() {
        Wyposazenie innyWyposazenie = new Wyposazenie("Krzesło", Wyposazenie.Typ.MEBEL, Wyposazenie.Stan.DOBRY);
        assertTrue(wyposazenie.equals(innyWyposazenie));
    }
}
