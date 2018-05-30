package week3.mergesort.practicalimprovements;


import week2.elementarysorts.Insertion;

public class Merge {
	private static Comparable[] aux;
	private static int CUTOFF = 7;
	
	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		for(int i = 0; i < a.length; i++) {
			aux[i] = a[i];
		}
		sort(a, aux, 0, a.length-1);
	}
	
	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		// Improvement 1: Use insertion for small subarrays.
		if (hi <= lo + CUTOFF -1) {
			Insertion.sort(a, lo, hi);
			return;
		}
		int mid = lo + (hi - lo) / 2;
		
		// Improvement 3: Eliminate the copy to the auxiliary array.
		// Recursive argument switchery
		// To sort an array, put the result in the other one.
		sort(aux, a, lo, mid);
		sort(aux, a, mid+1, hi);
		
		// Improvement 2: Stop if already sorted.
		if(!less(a[mid+1],a[mid]))
			return;
		
		// To merge an array, put the result back in the first one.
		merge(a, aux, lo, mid, hi);
	}
	
	
	// abstract in-place merge
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		assert isSorted(a, lo, mid); // precondition: a[lo..mid] sorted
		assert isSorted(a, mid+1, hi); // precondition: a[mid+1..hi] sorted
		
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) 
				aux[k] = a[j++];
			else if (j > hi)
				aux[k] = a[i++];
			if (less(a[j], a[i]))
				aux[k] = a[j++];
			else 
				aux[k] = a[i++];
		}
	}
	
	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++) {
			if (less(a[i],a[i-1]))
				return false;
		}
		return true;
	}
	
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
}
