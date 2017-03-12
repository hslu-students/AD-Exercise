package ch.hslu.AD.SW01.MemoryAllocator;

import ch.hslu.AD.SW01.MemoryAllocator.Allocation;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * Unit test for simple App.
 */
public class AllocationTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AllocationTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AllocationTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testEquals()
    {
    	EqualsVerifier.forClass(Allocation.class).verify();
    }
}
