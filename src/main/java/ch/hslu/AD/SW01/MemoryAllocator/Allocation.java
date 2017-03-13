package ch.hslu.AD.SW01.MemoryAllocator;

import java.util.Objects;

public final class Allocation implements Comparable<Allocation> {
	final private int startAddress;
	final private int size;
	
	public Allocation(int startAddress, int size) {
		this.startAddress = startAddress;
		this.size = size;
	}
	
	final public int getAddress() {
		return startAddress;
	}
	
	final public int getSize() {
		return size;
	}
	
	@Override
    final public int hashCode() {
    	return Objects.hash(this.startAddress, this.size);
    }
    
    @Override
    final public boolean equals(Object obj) {
    	if (this == obj) {
    		return true;
    	}
    	
    	if (!(obj instanceof Allocation)) {
    		return false;
    	}
    	
    	Allocation other = (Allocation) obj;
    	return startAddress == other.getAddress() && size == other.getSize();
    }
	
	public String toString() {
		return String.format("Block[Address:%d; Size:%d]", startAddress, size);
	}

	@Override
	public int compareTo(Allocation arg0) {
		if(this.getAddress() < arg0.getAddress()) {
			return -1;
		} else if(this.getAddress() > arg0.getAddress()) {
			return 1;
		}
		return 0;
	}
}
