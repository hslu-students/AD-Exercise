package ch.hslu.AD.SW01.MemoryAllocator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	final Memory memory = new MemorySimple(1024); 
        System.out.println(memory); 
        final Allocation block1 = memory.malloc(16); 
        System.out.println(block1); 
        System.out.println(memory); 
        final Allocation block2 = memory.malloc(8); 
        System.out.println(block2); 
        System.out.println(memory); 
        memory.free(block1);
    }
}
