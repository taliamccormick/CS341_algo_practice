package cs341.test.com;

import java.util.Arrays;
import cs341.algo.com.Algebra.Number;

public class AlgebraTest {
	static boolean outPutEnabled;

	private enum AlgorithmType { NUMBER }
	private enum TestActionType { ADD, SUBTRACT, SCALARMULTIPLY, MULTIPLY, FASTMULTIPLY, SHIFTRIGHT, UPDATE, SPLITLEFT, SPLITRIGHT, EQUALS }

	private static abstract class TestContents {
		abstract AlgorithmType getAlgorithmType();
	}

	private static class NumberTestContents extends TestContents {
		int[] input1, input2;
		int[] expectedReturn;
		TestActionType actionType;
		NumberTestContents(int[] inp1, int[] inp2, int[] er, TestActionType t) { input1 = inp1; input2 = inp2; expectedReturn = er; actionType = t; }
		@Override
		public String toString() { return "(" + Arrays.toString(input1) + " " + actionType + " " + Arrays.toString(input2) + ", " + Arrays.toString(expectedReturn) + ")"; }
		@Override
		AlgorithmType getAlgorithmType() { return AlgorithmType.NUMBER; }
	}

	static boolean runTest(TestContents tc) {
		int[] result;

		switch (tc.getAlgorithmType()) {
		case NUMBER: 
			NumberTestContents t = (NumberTestContents)tc;
			Number inp1 = new Number(t.input1);
			Number inp2 = new Number(t.input2);
			switch (t.actionType) {
			case ADD:
				result = inp1.add(inp2).getContents();
				break;
			case SUBTRACT:
				result = inp1.subtract(inp2).getContents();
				break;
			case SCALARMULTIPLY:
				result = inp1.scalarMultiply(inp2.getContents()[0]).getContents();
				break;
			case MULTIPLY:
				result = inp1.multiply(inp2).getContents();
				break;
			case FASTMULTIPLY:
				result = inp1.fastMultiply(inp2).getContents();
				break;
			case SHIFTRIGHT:
				result = inp1.shiftRight(inp2.getContents()[0]).getContents();
				break;
			case UPDATE:
				result = inp1.update().getContents();
				break;
			case SPLITLEFT:
				result = inp1.splitLeft(inp2.getContents()[0]).getContents();
				break;
			case SPLITRIGHT:
				result = inp1.splitRight(inp2.getContents()[0]).getContents();
				break;
			case EQUALS:
			default:
				result = null;
				break;
			}
			if (outPutEnabled) {
				System.out.println("Algorithm " + tc.getAlgorithmType() + " with test case: " + tc + " and result " + Arrays.toString(result));
			}
			return Arrays.equals(result, t.expectedReturn);
		default:
			result = null;
			break;
		}
		return false;
	}

	static void runtests() {
		// toggle for output when analysing failed test cases
		outPutEnabled = false;
		NumberTestContents[] tests = {
				new NumberTestContents(
						new int[] {1, 2, 3},
						new int[] {4, 5, 6},
						new int[] {5, 7, 9},
						TestActionType.ADD
						),
				new NumberTestContents(
						new int[] {1, 2, 3, 8},
						new int[] {4, 5, 6},
						new int[] {5, 7, 9, 8},
						TestActionType.ADD
						),
				new NumberTestContents(
						new int[] {1, 2, 3},
						new int[] {-1, -2, -3},
						new int[] {0},
						TestActionType.ADD
						),
				new NumberTestContents(
						new int[] {1, 2, 3},
						new int[] {4, 5, 6},
						new int[] {-3, -3, -3},
						TestActionType.SUBTRACT
						),
				new NumberTestContents(
						new int[] {1, 2, 3, 7},
						new int[] {4, 5, 6},
						new int[] {-3, -3, -3, 7},
						TestActionType.SUBTRACT
						),
				new NumberTestContents(
						new int[] {1, 2, 3},
						new int[] {4, 5, 6, 7},
						new int[] {-3, -3, -3, -7},
						TestActionType.SUBTRACT
						),
				new NumberTestContents(
						new int[] {1, 2, 3},
						new int[] {4},
						new int[] {4, 8, 12},
						TestActionType.SCALARMULTIPLY
						),
				new NumberTestContents(
						new int[] {1, 2, 3, 7},
						new int[] {4},
						new int[] {4, 8, 12, 28},
						TestActionType.SCALARMULTIPLY
						),
				new NumberTestContents(
						new int[] {1, 2, 3},
						new int[] {-1},
						new int[] {-1, -2, -3},
						TestActionType.SCALARMULTIPLY
						),
				new NumberTestContents(
						new int[] {1, 2, 3},
						new int[] {4, 5, 6},
						new int[] {4, 13, 28, 27, 18},
						TestActionType.MULTIPLY
						),
				new NumberTestContents(
						new int[] {1, 2, 3, 7},
						new int[] {0, 0, 0},
						new int[] {0},
						TestActionType.MULTIPLY
						),
				new NumberTestContents(
						new int[] {1, 2, 3},
						new int[] {-1, -2, -3},
						new int[] {-1, -4, -10, -12, -9},
						TestActionType.MULTIPLY
						),
				new NumberTestContents(
						new int[] {1, 2, 3},
						new int[] {-1, -2, -3, -4},
						new int[] {-1, -4, -10, -16, -17, -12},
						TestActionType.MULTIPLY
						),
				new NumberTestContents(
						new int[] {1, 2, 3},
						new int[] {4, 5, 6},
						new int[] {4, 13, 28, 27, 18},
						TestActionType.FASTMULTIPLY
						),
				new NumberTestContents(
						new int[] {1, 2, 3, 7},
						new int[] {0, 0, 0},
						new int[] {0},
						TestActionType.FASTMULTIPLY
						),
				new NumberTestContents(
						new int[] {1, 2, 3},
						new int[] {-1, -2, -3},
						new int[] {-1, -4, -10, -12, -9},
						TestActionType.FASTMULTIPLY
						),
				new NumberTestContents(
						new int[] {1, 2, 3},
						new int[] {-1, -2, -3, -4},
						new int[] {-1, -4, -10, -16, -17, -12},
						TestActionType.FASTMULTIPLY
						)
		};
		
		for (TestContents t : tests) {
			if (!runTest(t)) {
				System.out.println("Failure in Algebra test for test case " + t);
			}

		}
	}

	public static void main(String[] args) {
		runtests();
		System.out.println("ran");
	}
}
