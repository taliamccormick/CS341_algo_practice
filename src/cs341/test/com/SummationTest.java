package cs341.test.com;

import java.util.Arrays;

import cs240.algo.com.Search;
import cs341.algo.com.Summations;

public class SummationTest {
	static boolean outPutEnabled;
	private static class TestContents {
		int searchValue;
		int[] array;
		boolean expectedReturn;
		TestContents(int sv, int[] a, boolean er) { searchValue = sv; array = a; expectedReturn = er; }
		@Override
		public String toString() { return "(" + searchValue + ", " + Arrays.toString(array) + ", " + expectedReturn + ")"; }
	}

	static boolean test1Sum(TestContents t) {
		boolean result = Summations.TwoSum1(t.searchValue, t.array);
		if (outPutEnabled) {
			System.out.println("TwoSum1 with test case: " + t + " and result " + result);
		}
		return result == t.expectedReturn;

	}
	
	static boolean test2Sum(TestContents t) {
		boolean result = Summations.TwoSum1(t.searchValue, t.array);
		if (outPutEnabled) {
			System.out.println("TwoSum2 with test case: " + t + " and result " + result);
		}
		return result == t.expectedReturn;

	}

	static void runtest() {
		// toggle for output when analysing failed test cases
		outPutEnabled = false;
		TestContents[] tests = {
				new TestContents(0, new int[] {1, 2, 3}, false),
				new TestContents(1, new int[] {1, 2, 3, 4}, false),
				new TestContents(2, new int[] {1, 2, 3}, true),
				new TestContents(3, new int[] {1, 2, 3, 4}, true),

				new TestContents(1, new int[] {-4, -3, -2, -1, 0, 1, 2, 3, 4}, true),
				new TestContents(0, new int[] {-4, -3, -2, -1, 0, 1, 2, 3, 4}, true),
				new TestContents(0, new int[] {-4, -3, -2, 0, 1, 2, 3, 4}, true),
				
				new TestContents(2, new int[] {}, false)
		};

		for (TestContents t : tests) {
			if (!test1Sum(t)) {
				System.out.println("Failure in TwoSum1 for test case " + t);
			}
			if (!test2Sum(t)) {
				System.out.println("Failure in TwoSum2 for test case " + t);
			}
		}


	}

	public static void main(String[] args) {
		runtest();
	}
}
