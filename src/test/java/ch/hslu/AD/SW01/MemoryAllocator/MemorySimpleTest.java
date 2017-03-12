package ch.hslu.AD.SW01.MemoryAllocator;

import ch.hslu.AD.SW01.MemoryAllocator.Allocation;
import ch.hslu.AD.SW01.MemoryAllocator.Memory;
import ch.hslu.AD.SW01.MemoryAllocator.MemorySimple;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class MemorySimpleTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MemorySimpleTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( MemorySimpleTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testAllocation()
    {
    	Memory memory = new MemorySimple(1024);
    	Allocation block = memory.malloc(16);
    	assertEquals(0, block.getAddress());
    	assertEquals(16, block.getSize());
    	assertEquals(16, memory.getUsedSize());
    }
    
    public void testAllocationNoMemory()
    {
    	Memory memory = new MemorySimple(1024);
    	Allocation block = memory.malloc(2048);
    	assertNull(block);
    }
    
    public void testFree()
    {
    	Memory memory = new MemorySimple(1024);
    	Allocation block = memory.malloc(16);
    	memory.free(block);
    }
}
