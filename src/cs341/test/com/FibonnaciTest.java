package cs341.test.com;

import cs341.algo.com.Fibonacci;

public class FibonnaciTest {
	static boolean outPutEnabled;
	private static class TestContents {
		int input;
		int expectedResult;
		TestContents(int i, int r) { input = i; expectedResult = r; }
		@Override
		public String toString() { return "(" + input + ", " + expectedResult + ")"; }

	}

	static boolean testFib1(TestContents t) {
		int result = Fibonacci.Fib1(t.input);
		if (outPutEnabled) {
			System.out.println("Input: " + t.input + "   Expected: " + t.expectedResult + "   Actual: " + result);

		}
		return result == t.expectedResult;
	}

	static boolean testFib2(TestContents t) {
		int result = Fibonacci.Fib2(t.input);
		if (outPutEnabled) {
			System.out.println("Input: " + t.input + "   Expected: " + t.expectedResult + "   Actual: " + result);

		}
		return result == t.expectedResult;
	}

	static boolean testFib3(TestContents t) {
		int result = Fibonacci.Fib3(t.input);
		if (outPutEnabled) {
			System.out.println("Input: " + t.input + "   Expected: " + t.expectedResult + "   Actual: " + result);

		}
		return result == t.expectedResult;
	}

	static boolean testFib4(TestContents t) {
		int result = Fibonacci.Fib4(t.input);
		if (outPutEnabled) {
			System.out.println("Input: " + t.input + "   Expected: " + t.expectedResult + "   Actual: " + result);

		}
		return result == t.expectedResult;
	}

	static void runtest() {
		// toggle for output when identifying failed test cases
		outPutEnabled = false;
		TestContents[] tests = {
				new TestContents(0,0),
				new TestContents(1,1),
				new TestContents(2,1),
				new TestContents(3,2),
				new TestContents(4,3),
				new TestContents(5,5),
				new TestContents(6,8),
				new TestContents(7,13),
				new TestContents(8,21),
				new TestContents(9,34),
				new TestContents(10,55)
		};

		for (TestContents t : tests) {
			if (!testFib1(t)) {
				System.out.println("Failure in Fib1 for test case " + t);
			}
			if (!testFib2(t)) {
				System.out.println("Failure in Fib2 for test case " + t);
			}
			if (!testFib3(t)) {
				System.out.println("Failure in Fib3 for test case " + t);
			}
			if (!testFib4(t)) {
				System.out.println("Failure in Fib4 for test case " + t);
			}
		}
	}



	public static void main(String[] args) {
		runtest();
	}
}
