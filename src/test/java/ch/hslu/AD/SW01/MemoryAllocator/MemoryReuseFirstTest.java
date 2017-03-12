package ch.hslu.AD.SW01.MemoryAllocator;

import ch.hslu.AD.SW01.MemoryAllocator.Allocation;
import ch.hslu.AD.SW01.MemoryAllocator.Memory;
import ch.hslu.AD.SW01.MemoryAllocator.MemoryReuseFirst;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class MemoryReuseFirstTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MemoryReuseFirstTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( MemoryReuseFirstTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testAllocation()
    {
    	Memory memory = new MemoryReuseFirst(1024);
    	Allocation block = memory.malloc(16);
    	assertEquals(0, block.getAddress());
    	assertEquals(16, block.getSize());
    	assertEquals(16, memory.getUsedSize());
    }
    
    public void testAllocationNoMemory()
    {
    	Memory memory = new MemoryReuseFirst(1024);
    	Allocation block = memory.malloc(2048);
    	assertNull(block);
    }
    
    public void testFree()
    {
    	Memory memory = new MemoryReuseFirst(1024);
    	Allocation block = memory.malloc(16);
    	memory.free(block);
    }
    
    public void testReuseFreedStartBlock() 
    {
    	Memory memory = new MemoryReuseFirst(1024);
    	Allocation block = memory.malloc(8);
    	memory.free(block);
    	Allocation block1 = memory.malloc(16);
    	assertEquals(0, block1.getAddress());
    	assertEquals(16, block1.getSize());
    }
    
    public void testReuseFreedMiddleBlock() 
    {
    	Memory memory = new MemoryReuseFirst(1024);
    	memory.malloc(8);
    	Allocation block2 = memory.malloc(16);
    	memory.malloc(24);
    	memory.free(block2);
    	Allocation block1Reused = memory.malloc(8);
    	Allocation block2Reused = memory.malloc(8);
    	assertEquals(8, block1Reused.getAddress());
    	assertEquals(8, block1Reused.getSize());
    	assertEquals(16, block2Reused.getAddress());
    	assertEquals(8, block2Reused.getSize());
    }
    
    public void testNotReuseFreedSmallBlock() 
    {
    	Memory memory = new MemoryReuseFirst(1024);
    	memory.malloc(8);
    	Allocation block2 = memory.malloc(16);
    	memory.malloc(24);
    	memory.free(block2);
    	Allocation block1Reused = memory.malloc(24);
    	assertEquals(48, block1Reused.getAddress());
    	assertEquals(24, block1Reused.getSize());
    }
}
