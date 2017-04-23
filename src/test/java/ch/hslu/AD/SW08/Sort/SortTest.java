package ch.hslu.AD.SW08.Sort;


import org.junit.Before;
import org.junit.Test;

import ch.hslu.AD.SW08.Sort.Sort;

import static org.junit.Assert.assertFalse;

/**
 * Unit test for simple App.
 */
public class SortTest {
	
	private Integer[] sortedNumbers;
	private Integer[] reversedSortedNumbers;
	private Integer[] unsortedNumbers;
	private Integer[] singleNumber;
	
	@Before
	public void setup() {
		sortedNumbers = new Integer[] {1, 2, 3, 4, 5};
		reversedSortedNumbers = new Integer[] {5, 4, 3, 2, 1};
		unsortedNumbers = new Integer[] {5, 2, 3, 1, 4};
		singleNumber = new Integer[] {0};
	}

    /**
     * Rigourous Test :-)
     */
	@Test
    public void testInsertionSort() {
    	// with already sorted array
    	Sort.insertionSort(sortedNumbers);
    	assertIsSorted(sortedNumbers, Order.ASCENDING);
    	
    	// with numbers in reversed sort order
    	Sort.insertionSort(reversedSortedNumbers);
    	assertIsSorted(reversedSortedNumbers, Order.ASCENDING);
    	
    	// with unsorted array
    	Sort.insertionSort(unsortedNumbers);
    	assertIsSorted(unsortedNumbers, Order.ASCENDING);
    	
    	// test sort algorithm with single element array
    	Sort.insertionSort(singleNumber);
    	assertIsSorted(singleNumber, Order.ASCENDING);
    }
	
	@Test
    public void testSelectionSort() {
    	// with already sorted array
    	Sort.selectionSort(sortedNumbers);
    	assertIsSorted(sortedNumbers, Order.ASCENDING);
    	
    	// with numbers in reversed sort order
    	Sort.selectionSort(reversedSortedNumbers);
    	assertIsSorted(reversedSortedNumbers, Order.ASCENDING);
    	
    	// with unsorted array
    	Sort.selectionSort(unsortedNumbers);
    	assertIsSorted(unsortedNumbers, Order.ASCENDING);
    	
    	// test sort algorithm with single element array
    	Sort.selectionSort(singleNumber);
    	assertIsSorted(singleNumber, Order.ASCENDING);
    }
	
	@Test
    public void testBubbleSort() {
    	// with already sorted array
    	Sort.bubbleSort(sortedNumbers);
    	assertIsSorted(sortedNumbers, Order.ASCENDING);
    	
    	// with numbers in reversed sort order
    	Sort.bubbleSort(reversedSortedNumbers);
    	assertIsSorted(reversedSortedNumbers, Order.ASCENDING);
    	
    	// with unsorted array
    	Sort.bubbleSort(unsortedNumbers);
    	assertIsSorted(unsortedNumbers, Order.ASCENDING);
    	
    	// test sort algorithm with single element array
    	Sort.bubbleSort(singleNumber);
    	assertIsSorted(singleNumber, Order.ASCENDING);
    }
	
	public enum Order {
		ASCENDING, DESCENDING
	}
	
	@SuppressWarnings("unchecked")
	public <T> boolean assertIsSorted(Comparable<T> a[], Order order) {
		for(int i = 0; i < a.length - 1; i++) {
			Comparable<T> current = a[i];
			Comparable<T> next = a[i + 1];
			
			int result = current.compareTo((T) next);
			switch(order) {
				case ASCENDING: 
					assertFalse(result > 0);
					break;
				case DESCENDING:
					assertFalse(result < 0);
					break;
			}
		}
		
		return true;
	}

}
