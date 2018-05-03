package cs240.algo.com;

import java.util.Arrays;

public class Sort {

	/* Top-down mergesort */
	public static int[] mergeSort1(int[] input) {
		if (input.length < 2) return input;
		int[] right = Arrays.copyOfRange(input, 0, (int) Math.floor(input.length / 2));
		int[] left = Arrays.copyOfRange(input, (int) Math.ceil(input.length / 2), input.length);
		right = mergeSort1(right);
		left = mergeSort1(left);
		return merge1(left, right);
	}
	
	private static int[] merge1(int[] A, int[] B) {
		if (A.length == 0) return B;
		if (B.length == 0) return A;
		
		int lengthA = A.length;
		int lengthB = B.length;
		int indexA = 0;
		int indexB = 0;
		int resultIndex = 0;
		int[] result = new int[lengthA + lengthB];
		
		/* Overlapping portions of pre-sorted arrays must be merged*/
		while ((indexA < lengthA) && (indexB < lengthB)) {
			if (A[indexA] < B[indexB]) {
				result[resultIndex] = A[indexA];
				indexA++;
			} else {
				result[resultIndex] = B[indexB];
				indexB++;
			}
			resultIndex++;
		}
		
		/* Remaining portion of pre-sorted array must still be copied */
		while (indexA < lengthA) {
			result[resultIndex] = A[indexA];
			indexA++;
			resultIndex++;
		}
		while (indexB < lengthB) {
			result[resultIndex] = B[indexB];
			indexB++;
			resultIndex++;
		}
		
		return result;
	}
	
	/* Top-down mergesort's 'Merge' function */
}
