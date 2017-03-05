package ch.hslu.AD.SW1.MemoryAllocator;

import java.util.List;

import ch.hslu.AD.SW2.SinglyLinkedList.SinglyLinkedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class MemoryReuseFirst implements Memory {
	private int size;
	
	//private List<Allocation> blocks = new ArrayList<>();
	private List<Allocation> blocks = new SinglyLinkedList<>();
	
	public MemoryReuseFirst(int size) {
		this.size = size;
	}
	
	public Allocation malloc(int size) {
		// TODO(TF): this should actually check 
		//           if the memory address range is not exceeded
		//           instead of checked the "used" space.
		if(this.size - getUsedSize() < size) {
			return null;
		}
		
		Allocation block = new Allocation(getFreeAddress(size), size);
		blocks.add(block);
		return block;
	}
	
	public void free(Allocation block) {
		blocks.remove(block);
	}
	
	private int getFreeAddress(int size) {
		sortBlocksByAddress();
		
		Iterator<Allocation> it = blocks.iterator();
		Allocation previousBlock = null;
		
		if(!it.hasNext()) {
			return 0;
		}
		
		previousBlock = it.next();
		
		while(true) {  // because I'm bad ass!
			int nextAddress = previousBlock.getAddress() + previousBlock.getSize();
			
			// if there is no other block available
			// put the next block adjacent
			if(!it.hasNext()) {
				return nextAddress;
			}
			Allocation currentBlock = it.next();
			
			// if there is space between this block and the
			// next one we should use it. ;)
			if(nextAddress + size <= currentBlock.getAddress()) {
				return nextAddress;
			}
			
			previousBlock = currentBlock;
		}
	}
	
	private void sortBlocksByAddress() {
		Collections.sort(blocks, (b1, b2) -> Integer.compare(b1.getAddress(), b2.getAddress()));
	}
	
	public int getUsedSize() {
		int usedSize = 0;
		for(Allocation block : blocks) {
			usedSize += block.getSize();
		}
		return usedSize;
	}
	
	public String toString() {
		return String.format("MemoryReuseFirst[%d of %d used]", getUsedSize(), size);
	}
}
