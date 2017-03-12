package ch.hslu.AD.SW01.MemoryAllocator;

public interface Memory {
	public Allocation malloc(int size);
	public void free(Allocation allocation);
	public int getUsedSize();
}
