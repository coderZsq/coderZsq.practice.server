package reconfiguration.case5;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RangeLimiterTest {
    @Test
    public void testMove_betweenRange() {
        RangeLimiter rangeLimiter = new RangeLimiter();
        assertTrue(rangeLimiter.move(1));
        assertTrue(rangeLimiter.move(3));
        assertTrue(rangeLimiter.move(-5));
    }
    @Test
    public void testMove_exceedRange() {
        RangeLimiter rangeLimiter = new RangeLimiter();
        assertFalse(rangeLimiter.move(6));
    }
}