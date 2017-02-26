package ch.hslu.AD.SW1.MemoryAllocator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class MemoryReuseBestMatchTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MemoryReuseBestMatchTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( MemoryReuseBestMatchTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testAllocation()
    {
    	Memory memory = new MemoryReuseBestMatch(1024);
    	Allocation block = memory.malloc(16);
    	assertEquals(0, block.getAddress());
    	assertEquals(16, block.getSize());
    	assertEquals(16, memory.getUsedSize());
    }
    
    public void testAllocationNoMemory()
    {
    	Memory memory = new MemoryReuseBestMatch(1024);
    	Allocation block = memory.malloc(2048);
    	assertNull(block);
    }
    
    public void testFree()
    {
    	Memory memory = new MemoryReuseBestMatch(1024);
    	Allocation block = memory.malloc(16);
    	memory.free(block);
    }
    
    public void testReuseFreedStartBlock() 
    {
    	Memory memory = new MemoryReuseBestMatch(1024);
    	Allocation block = memory.malloc(8);
    	memory.free(block);
    	Allocation block1 = memory.malloc(16);
    	assertEquals(0, block1.getAddress());
    	assertEquals(16, block1.getSize());
    }
    
    public void testReuseFreedBestMatchBlock() 
    {
    	Memory memory = new MemoryReuseBestMatch(1024);
    	Allocation block1 = memory.malloc(8);
    	Allocation block2 = memory.malloc(64);
    	Allocation block3 = memory.malloc(24); // Will be freed
    	Allocation block4 = memory.malloc(128);
    	Allocation block5 = memory.malloc(16); // Will be freed
    	Allocation block6 = memory.malloc(8);
    	
    	memory.free(block3);
    	memory.free(block5);
    	
    	Allocation reusedBlock = memory.malloc(16);
    	assertEquals(8 + 64 + 24 + 128, reusedBlock.getAddress());
    	assertEquals(16, reusedBlock.getSize());
    }
    
    public void testReuseFreedBestMatchBlock2() 
    {
    	Memory memory = new MemoryReuseBestMatch(1024);
    	Allocation block1 = memory.malloc(8);
    	Allocation block2 = memory.malloc(64); // Will be freed
    	Allocation block3 = memory.malloc(24);
    	Allocation block4 = memory.malloc(128);
    	Allocation block5 = memory.malloc(16); // Will be freed
    	Allocation block6 = memory.malloc(8);
    	
    	memory.free(block2);
    	memory.free(block5);
    	
    	Allocation reusedBlock1 = memory.malloc(8);
    	assertEquals(8 + 64 + 24 + 128, reusedBlock1.getAddress());
    	assertEquals(8, reusedBlock1.getSize());
    	
    	Allocation reusedBlock2 = memory.malloc(24);
    	assertEquals(8, reusedBlock2.getAddress());
    	assertEquals(24, reusedBlock2.getSize());
    	
    	Allocation reusedBlock3 = memory.malloc(16);
    	assertEquals(8 + 24, reusedBlock3.getAddress());
    	assertEquals(16, reusedBlock3.getSize());
    	
    	Allocation reusedBlock4 = memory.malloc(8);
    	assertEquals(8 + 64 + 24 + 128 + 8, reusedBlock4.getAddress());
    	assertEquals(8, reusedBlock4.getSize());
    	
    	Allocation notReusedBlock1 = memory.malloc(64);
    	assertEquals(8 + 64 + 24 + 128 + 16 + 8, notReusedBlock1.getAddress());
    	assertEquals(64, notReusedBlock1.getSize());
    	
    	Allocation reusedBlock5 = memory.malloc(16);
    	assertEquals(8 + 24 + 16, reusedBlock5.getAddress());
    	assertEquals(16, reusedBlock5.getSize());
    	
    	Allocation notReusedBlock2 = memory.malloc(16);
    	assertEquals(8 + 64 + 24 + 128 + 16 + 8 + 64, notReusedBlock2.getAddress());
    	assertEquals(16, notReusedBlock2.getSize());
    	
    	Allocation reusedBlock6 = memory.malloc(8);
    	assertEquals(8 + 24 + 16 + 16, reusedBlock6.getAddress());
    	assertEquals(8, reusedBlock6.getSize());
    	
    	Allocation notReusedBlock3 = memory.malloc(8);
    	assertEquals(8 + 64 + 24 + 128 + 16 + 8 + 64 + 16, notReusedBlock3.getAddress());
    	assertEquals(8, notReusedBlock3.getSize());
    }
}
