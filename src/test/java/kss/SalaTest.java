package kss;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import kss.model.Sala;

public class SalaTest {
    private Sala sala;

    @Before
    public void setUp() {
        sala = new Sala("101");
    }

    @Test
    public void testGetNumerSali() {
        assertEquals("101", sala.getNumerSali());
    }

    @Test
    public void testEquals() {
        Sala innaSala = new Sala("101");
        assertTrue(sala.equals(innaSala));
    }

    @Test
    public void testToString() {
        assertEquals("101", sala.toString());
    }
}
