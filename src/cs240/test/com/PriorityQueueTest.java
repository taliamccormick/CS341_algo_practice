package cs240.test.com;


import java.util.Arrays;

import cs240.algo.com.PriorityQueue;
import cs240.algo.com.PriorityQueue.KVP;
import cs240.algo.com.UnsortedArrayPriorityQueue;

public class PriorityQueueTest {
	static boolean outPutEnabled;
	private static class TestContents {
		KVP additions[];
		// Requires that the removals list contains the KVPs with maximal priority
		KVP removals[];
		KVP finalContents[];

		TestContents(KVP[] a, KVP[] r, KVP[] fc) {
			additions = a;
			removals = r;
			finalContents = fc;
		}

		@Override
		public String toString() {
			return "("
					+ Arrays.toString(additions)
					+ ", "
					+ Arrays.toString(removals)
					+ ", "
					+ Arrays.toString(finalContents)
					+ ")"; }

	}

	static boolean testPriorityQueue(PriorityQueue pq, TestContents t) {
		int initialSize = pq.getSize();
		if (outPutEnabled == true) {
			System.out.println("Testing priority queue " + pq.getClass());
			System.out.println("Initial: " + pq.toString());
		}

		/* Must successfully add new KVPs without deleting duplicates or deleting existing KVPs */
		if (t.additions != null) {
			for (KVP kvp : t.additions) {
				pq.insert(kvp);
				if (outPutEnabled == true) {
					System.out.println("Adding KVP " + kvp + " gives " + pq.toString());
				}
			}
			if (pq.getSize() != initialSize + t.additions.length) {
				System.out.println("Initial size: " + initialSize + "   # additions: " + t.additions.length + "   New size: " + pq.getSize());
				return false;
			}
			for (KVP kvp : t.additions) {
				if (!pq.contains(kvp)) {
					System.out.println("Failure to add KVP: " + kvp);
					return false;
				}
			}
		}

		/* Must delete correct # of maximal value but no others */
		if (t.removals != null) {
			for (int i = 0; i < t.removals.length; i++ ) {
				pq.deleteMax();
				if (outPutEnabled == true) {
					System.out.println("Deleting max gives " + pq.toString());
				}

			}
			if (pq.getSize() != initialSize + t.additions.length - t.removals.length) {
				System.out.println("Initial size: " + initialSize + "   # removals: " + t.removals.length + "   New size: " + pq.getSize());
				return false;
			}
			
			/* TODO: Decide if test cases should allow for duplicate key entries */
			for (KVP kvp : t.removals) {
				if (pq.contains(kvp)) {
					System.out.println("Failure to remove KVP: " + kvp);
					return false;
				}
			}
			if (t.finalContents != null) {
				for (KVP kvp : t.finalContents) {
					if (!pq.contains(kvp)) {
						System.out.println("Should not have removed KVP: " + kvp);
						return false;
					}
				}
			}
		}
		if (outPutEnabled == true) {
			System.out.println();
		}
		return true;
	}

	static void runtest() {
		// toggle for output when analysing failed test cases
		outPutEnabled = false;
		TestContents[] tests = {
				new TestContents(null, null, null),
				new TestContents(
						new KVP[] { new KVP(1, -1), new KVP(2, -2), new KVP(3, -3) },
						null,
						new KVP[] { new KVP(1, -1), new KVP(2, -2), new KVP(3, -3) }),
				new TestContents(
						new KVP[] { new KVP(1, -1), new KVP(2, -2), new KVP(3, -3) },
						new KVP[] { new KVP(1, -1), new KVP(2, -2), new KVP(3, -3) },
						null),
				new TestContents(
						new KVP[] { new KVP(1, -1), new KVP(2, -2), new KVP(3, -3) },
						new KVP[] { new KVP(3, -3) },
						new KVP[] { new KVP(1, -1), new KVP(2, -2) }),
		};
		PriorityQueue[] priorityQueues = {
				new UnsortedArrayPriorityQueue()
		};

		for (PriorityQueue pq : priorityQueues) {
			for (TestContents t : tests) {
				pq.clear();
				if (!testPriorityQueue(pq, t)) {
					System.out.println("Failure in implementation " + pq.getClass().getName() + " of a priority queue for test case " + t);
				}
			}
		}
	}

	public static void main(String[] args) {
		runtest();
	}
}
