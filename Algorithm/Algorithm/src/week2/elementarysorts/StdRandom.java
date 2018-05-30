package week2.elementarysorts;
/**
 * Knuth shuffle
 */
public class StdRandom {
	
	public static void shuffle(Object[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int r = StdRandom.uniform(i + 1);
			exch(a, i, r);
		}
	}
	
	private static int uniform(int i) {
		return (int) (Math.random()*i);
	}
	
	private static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	} 

}
