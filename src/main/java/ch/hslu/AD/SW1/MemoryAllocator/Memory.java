package ch.hslu.AD.SW1.MemoryAllocator;

public interface Memory {
	public Allocation malloc(int size);
	public void free(Allocation allocation);
	public int getUsedSize();
}
