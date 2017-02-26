package ch.hslu.AD.SW1.Ackermann;

import java.util.Collection;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
@RunWith(Parameterized.class)
public class AckermannTest
{
	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                 { 0, 0, 1 }, { 0, 4, 5 }, 
                 { 1, 0, 2 }, {1, 2, 4 }, { 2, 0, 3 },
                 { 0, 1, 2 }, { 1, 1, 3 }, { 1, 2, 4 },
                 { 4, 0, 13 }, 
                 //{ 4, 1, 65533 }, { 5, 0, 65533 }  // StackOverflow with default stack size!
           });
    }

    private long m;
    private long n;
    private long expected;

    public AckermannTest(long m, long n, long expected) {
        this.m = m;
        this.n = n;
        this.expected= expected;
    }

    @Test
    public void testAck() {
        assertEquals(expected, App.ack(m, n));
    }
}
