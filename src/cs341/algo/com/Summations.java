package cs341.algo.com;

import cs240.algo.com.Search;
import cs240.algo.com.Sort;

public class Summations {
	/* Summary of algorithms:
	 * 		2SUM - 	Given an int array A and int m, return true if there exist
	 * 				i and j such that A[i] + A[j] = m
	 * 		3SUM -	Given an int array A and int m, return true if there exist
	 * 				i, j, and k such that A[i] + A[j] + A[k] = m
	 */

	public static boolean TwoSum1(int m, int[] A) {
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				if (A[i] + A[j] == m) {
					return true;
				}
			}
		}
		return false;
	}


	public static boolean TwoSum2(int m, int[] A) {
		int[] sortedA = Sort.mergeSort1(A);
		for (int i = 0; i < A.length; i++) {
			if (true == Search.binarySearch(m - A[i], sortedA)) {
				return true;
			}
		}
		return false;
	}

	public static boolean ThreeSum1(int m, int[] A) {
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				for (int k = 0; k < A.length; k++) {
					if (A[i] + A[j] + A[k] == m) {
						return true;
					}
				}
			}
		}
		return false;
	}


	public static boolean ThreeSum2(int m, int[] A) {
		int[] AA = new int[A.length * A.length];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				AA[(i * A.length) + j] = A[i] + A[j];
			}
		}
		
		int[] sortedAA = Sort.mergeSort1(AA);
		
		for (int i = 0; i < A.length; i++) {
			if (true == Search.binarySearch(m - A[i], sortedAA)) {
				return true;
			}
		}
		return false;
	}

}
