package ch.hslu.AD.SW1.MemoryAllocator;

import java.util.List;
import java.util.ArrayList;

public class MemorySimple implements Memory {
	private int size;
	
	private List<Allocation> blocks = new ArrayList<Allocation>();
	
	public MemorySimple(int size) {
		this.size = size;
	}
	
	public Allocation malloc(int size) {
		if(this.size - getUsedSize() < size) {
			return null;
		}
		
		Allocation block = new Allocation(getFreeAddress(), size);
		blocks.add(block);
		return block;
	}
	
	public void free(Allocation block) {
		blocks.remove(block);
	}
	
	private int getFreeAddress() {
		Allocation lastBlock = null;
		for(Allocation block : blocks) {
			if(lastBlock == null || block.getAddress() >= lastBlock.getAddress()) {
				lastBlock = block;
			}
		}
		if(lastBlock == null) {
			return 0;
		}
		return lastBlock.getAddress() + lastBlock.getSize();
	}
	
	public int getUsedSize() {
		int usedSize = 0;
		for(Allocation block : blocks) {
			usedSize += block.getSize();
		}
		return usedSize;
	}
	
	public String toString() {
		return String.format("MemorySimple[%d of %d used]", getUsedSize(), size);
	}
}
