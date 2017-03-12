package ch.hslu.AD.SW01.MemoryAllocator;

import java.util.List;

import ch.hslu.AD.SW02.SinglyLinkedList.SinglyLinkedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class MemoryReuseBestMatch implements Memory {
	private int size;
	
	//private List<Allocation> blocks = new ArrayList<>();
	private List<Allocation> blocks = new SinglyLinkedList<>();
	
	public MemoryReuseBestMatch(int size) {
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
		// Sort all registered blocks by it's address
		sortBlocksByAddress();
		
		// Sort all spaces between the registered blocks by it's size
		int bestStartAddress = 0;
		int bestError = Integer.MAX_VALUE;
		
		Iterator<Allocation> it = blocks.iterator();
		Allocation previousBlock = it.hasNext() ? it.next() : null;
		while(it.hasNext() && bestError != 0) {
			Allocation currentBlock = it.next();

			int requiredStartAddress = previousBlock.getAddress() + previousBlock.getSize();
			int error = currentBlock.getAddress() - (requiredStartAddress + size);
			// check if required block would match into gap and the error is less than our best error
			if(requiredStartAddress + size <= currentBlock.getAddress() &&
					error < bestError) {
				bestStartAddress = requiredStartAddress;
				bestError = error;
			}
			
			previousBlock = currentBlock;
		}
		
		// if no matching gap was found but there are registered blocks, return the next adjacent address.
		if(bestError == Integer.MAX_VALUE && previousBlock != null) {
			bestStartAddress = previousBlock.getAddress() + previousBlock.getSize();
		}
		return bestStartAddress;
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
		return String.format("MemoryReuseBestMatch[%d of %d used]", getUsedSize(), size);
	}
}
