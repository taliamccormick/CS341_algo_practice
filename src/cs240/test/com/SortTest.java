package cs240.test.com;

import java.util.Arrays;

import cs240.algo.com.Sort;

public class SortTest {
	static boolean outPutEnabled;
	private static class TestContents {
		int[] input;
		int[] expectedResult;
		TestContents(int[] i, int[] r) { input = i; expectedResult = r; }
		@Override
		public String toString() { return "(" + Arrays.toString(input) + ", " + Arrays.toString(expectedResult) + ")"; }

	}

	static boolean testMergeSort1(TestContents t) {
		int[] result = Sort.mergeSort1(t.input);
		if (outPutEnabled) {
			System.out.println("Input: " + Arrays.toString(t.input) + "   Expected: " + Arrays.toString(t.expectedResult) + "   Actual: " + Arrays.toString(result));
		}
		return java.util.Arrays.equals(result, t.expectedResult);
	}
	
	static boolean testMergeSort2(TestContents t) {
		int[] result = Sort.mergeSort2(t.input);
		if (outPutEnabled) {
			System.out.println("Input: " + Arrays.toString(t.input) + "   Expected: " + Arrays.toString(t.expectedResult) + "   Actual: " + Arrays.toString(result));
		}
		return java.util.Arrays.equals(result, t.expectedResult);
	}

	static void runtest() {
		// toggle for output when analysing failed test cases
		outPutEnabled = false;
		TestContents[] tests = {
				new TestContents(new int[] {5, 4, 3, 2, 1},new int[] {1, 2, 3, 4, 5}),
				new TestContents(new int[] {1, 2, 3},new int[] {1, 2, 3}),
				new TestContents(new int[] {3, 2, 1},new int[] {1, 2, 3}),
				new TestContents(new int[] {4, 2, 1, 3},new int[] {1, 2, 3, 4}),
				new TestContents(new int[] {5, 3, 1, 2, 4},new int[] {1, 2, 3, 4, 5}),
		};

		for (TestContents t : tests) {
			if (!testMergeSort1(t)) {
				System.out.println("Failure in MergeSort1 for test case " + t);
			}
			if (!testMergeSort2(t)) {
				System.out.println("Failure in MergeSort1 for test case " + t);
			}
		}
	}

	public static void main(String[] args) {
		runtest();
	}
}
