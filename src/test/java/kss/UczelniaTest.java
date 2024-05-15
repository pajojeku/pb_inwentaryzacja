package kss;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import kss.model.Sala;
import kss.model.Uczelnia;

public class UczelniaTest {
    @Test
    public void testDodajSale() {
        Uczelnia uczelnia = new Uczelnia();
        Sala sala = new Sala("101");
        
        int result = uczelnia.dodajSale(sala);
        assertEquals(0, result);
        assertEquals(1, uczelnia.getSale().size());
        assertEquals(sala, uczelnia.getSala(0));
    }

    @Test
    public void testUsunSale() {
        Uczelnia uczelnia = new Uczelnia();
        Sala sala = new Sala("101");
        
        uczelnia.dodajSale(sala);
        int result = uczelnia.usunSale(sala);
        assertEquals(0, result);
        assertTrue(uczelnia.czyPusta());
    }

    @Test
    public void testCzyPusta() {
        Uczelnia uczelnia = new Uczelnia();
        assertTrue(uczelnia.czyPusta());
        
        Sala sala = new Sala("101");
        uczelnia.dodajSale(sala);
        assertFalse(uczelnia.czyPusta());
    }

    @Test
    public void testGetSale() {
        Uczelnia uczelnia = new Uczelnia();
        Sala sala = new Sala("101");
        uczelnia.dodajSale(sala);
        
        List<Sala> sale = uczelnia.getSale();
        assertEquals(1, sale.size());
        assertEquals(sala, sale.get(0));
    }
}
