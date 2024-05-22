package kss;

import org.junit.Before;
import org.junit.Test;

import kss.model.Wyposazenie;
import kss.model.Wyposazenie.Stan;
import kss.model.mebel.Krzeslo;

import static org.junit.Assert.*;

public class WyposazenieTest {
    private Wyposazenie wyposazenie;

    @Before
    public void setUp() {
        wyposazenie = new Krzeslo(Stan.DOBRY);
    }

    @Test
    public void testGetNazwa() {
        assertEquals("KRZESŁO", wyposazenie.getNazwa());
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
        Wyposazenie innyWyposazenie = new Krzeslo(Stan.DOBRY);
        assertTrue(wyposazenie.equals(innyWyposazenie));
    }
}
