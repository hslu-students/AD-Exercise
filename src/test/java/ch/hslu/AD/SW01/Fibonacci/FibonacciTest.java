package ch.hslu.AD.SW01.Fibonacci;

import java.util.Collection;
import java.util.HashMap;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ch.hslu.AD.SW01.Fibonacci.App;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
@RunWith(Parameterized.class)
public class FibonacciTest
{
	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                 { 0, 0 }, { 1, 1 }, { 2, 1 }, { 3, 2 }, { 4, 3 }, { 5, 5 }, { 6, 8 }  
           });
    }

    private int input;
    private int expected;

    public FibonacciTest(int input, int expected) {
        this.input= input;
        this.expected= expected;
    }

    @Test
    public void testFiboRec1() {
        assertEquals(expected, App.fiboRec1(input));
    }

    @Test
    public void testFiboRec2() {
    	App.memoizedFib = new HashMap<Integer, Integer>();
        assertEquals(expected, App.fiboRec2(input));
    }

    @Test
    public void testFiboIter() {
        assertEquals(expected, App.fiboIter(input));
    }
}
