package ch.hslu.AD.SW08.Sort;

public class Sort {
	
	@SuppressWarnings("unchecked")
	public static <T> void insertionSort(Comparable<T> a[]) {
		for(int i = 1; i < a.length; i++) {
			for(int j = i; j > 0 && a[j].compareTo((T) a[j -1]) < 0; j--) {
				swap(a, j, j - 1);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> void selectionSort(Comparable<T> a[]) {
		for(int i = 0; i < a.length; i++) {
			// find smallest items
			int smallestIndex = i;
			for(int j = i + 1; j < a.length; j++) {
				if(a[j].compareTo((T) a[smallestIndex]) < 0) {
					smallestIndex = j;
				}
			}
			
			swap(a, i, smallestIndex);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> void bubbleSort(Comparable<T> a[]) {
		for(int i = 0; i < a.length; i++) {
			boolean swapped = false;
			for(int j = 1; j < a.length - i; j++) {
				if(a[j - 1].compareTo((T) a[j]) > 0) {
					swap(a, j - 1, j);
					swapped = true;
				}
			}
			if(!swapped) {
				return; // no elements were swapped during the last iteration, thus 'a' is sorted.
			}
		}
	}
	
	private static <T> void swap(T a[], int x, int y) {
		T temp = a[y];
		a[y] = a[x];
		a[x] = temp;
	}
}
