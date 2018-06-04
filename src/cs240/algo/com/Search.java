package cs240.algo.com;

public class Search {
	/* Assumes sorted array in non-decreasing order */
	public static boolean binarySearch(int n, int[] A) {
		int min = 0;
		int max = A.length;
		int middle = (min + max) / 2;
		
		while (max > min) {
			if (A[middle] == n) {
				return true;
			} else if (A[middle] < n) {
				min = middle;
			} else /* A[middle] > n */ {
				max = middle;
			}
			middle = (min + max) / 2;
		}
		return false;
	}
}
