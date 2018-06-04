package cs240.test.com;

import java.util.Arrays;

import cs240.algo.com.Search;

public class SearchTest {
	static boolean outPutEnabled;
	private static class TestContents {
		int searchValue;
		int[] array;
		boolean expectedReturn;
		TestContents(int sv, int[] a, boolean er) { searchValue = sv; array = a; expectedReturn = er; }
		@Override
		public String toString() { return "(" + searchValue + ", " + Arrays.toString(array) + ", " + expectedReturn + ")"; }
	}

	static boolean testBinarySearch(TestContents t) {
		boolean result = Search.binarySearch(t.searchValue, t.array);
		if (outPutEnabled) {
			System.out.println("Test case: " + t + " and result " + result);
		}
		return result == t.expectedReturn;

	}

	static void runtest() {
		// toggle for output when analysing failed test cases
		outPutEnabled = false;
		TestContents[] tests = {
				new TestContents(1, new int[] {1, 2, 3}, true),
				new TestContents(1, new int[] {1, 2, 3, 4}, true),
				new TestContents(3, new int[] {1, 2, 3}, true),
				new TestContents(4, new int[] {1, 2, 3, 4}, true),
				new TestContents(2, new int[] {1, 2, 3}, true),
				new TestContents(2, new int[] {1, 2, 3, 4}, true),

				new TestContents(0, new int[] {1, 2, 3}, false),
				new TestContents(0, new int[] {1, 2, 3, 4}, false),

				new TestContents(3, new int[] {-3, -2, -1, 0, 1, 2, 3}, true),
				new TestContents(3, new int[] {-2, -1, 0, 1, 2, 3}, true),
				new TestContents(-2, new int[] {-2, -1, 1, 2, 3, 4}, true),

				new TestContents(2, new int[] {}, false)
		};

		for (TestContents t : tests) {
			if (!testBinarySearch(t)) {
				System.out.println("Failure in binary search for test case " + t);
			}
		}


	}

	public static void main(String[] args) {
		runtest();
	}
}
