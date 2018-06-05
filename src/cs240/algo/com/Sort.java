package cs240.algo.com;

import java.util.Arrays;

public class Sort {
	/* 
	 * Summary of algorithms:
	 * 		mergeSort1 -	Given an int array A, merge sort recursively in nlogn
	 * 		mergeSort2 -		Given an int array A, merge sort iteratively (non-
	 * 						recursively) in nlogn
	 */
	
	/* Iterative in-place mergesort */
	public static int[] mergeSort2(int[] input) {
		int[] result = input.clone();
		if (result.length < 2) return result;
		
		/* 
		 * By iterating over the length of sub-arrays (from 1 to total length), 
		 * and then iterating through the sub-arrays at each stage, two nested
		 * loops can be used to replicate the effect of a recursive call without
		 * explicitly recursing
		 */
		for (int subArrayLength = 1; subArrayLength < result.length; subArrayLength *= 2) {
			
			for (int startIndex = 0; startIndex < result.length + subArrayLength; startIndex += 2 * subArrayLength) {
				if (startIndex + subArrayLength < result.length) {
					int rightArrayLength = Math.min(subArrayLength, result.length - (startIndex + subArrayLength)); 
					int[] left = new int[subArrayLength];
					int[] right = new int[rightArrayLength];
					System.arraycopy(result, startIndex, left, 0, subArrayLength);
					System.arraycopy(result, startIndex + subArrayLength, right, 0, rightArrayLength);
					int[] mergeResult = merge1(left, right);
					System.arraycopy(mergeResult, 0, result, startIndex, subArrayLength + rightArrayLength);
				}
			}
		}
		return result;
	}
	

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
