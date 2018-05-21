package cs240.algo.com;

public class UnsortedArrayPriorityQueue extends PriorityQueue {
	// Array may have extra space to minimize costly recopying
	int numKvps;
	private KVP[] kvps;

	public UnsortedArrayPriorityQueue() {
		numKvps = 0;
		kvps = null;
	}

	public UnsortedArrayPriorityQueue(KVP[] contents) {
		numKvps = contents.length;
		kvps = contents;
	}

	@Override
	/* (Amortized) constant time */
	public void insert(KVP kvp) {
		if (kvps == null) {
			kvps = new KVP[] { kvp };
			numKvps = 1;
		} else {
			if (kvps.length <= numKvps) {
				KVP[] newKvps = new KVP[numKvps * 2];
				System.arraycopy(kvps, 0, newKvps, 0, numKvps);
				kvps = newKvps;
			}
			kvps[numKvps] = kvp;
			numKvps++;
		}
	}

	@Override
	/* Linear time */
	public void deleteMax() {
		if (numKvps > 0) {
			int maxIndex = 0;
			for (int i = 1; i < numKvps; i++) {
				if (kvps[maxIndex].key < kvps[i].key) {
					maxIndex = i;
				}
			}
			numKvps--;
			kvps[maxIndex] = kvps[numKvps];
		}
	}

	/* Used for testing */
	@Override
	public String toString() {
		String contents = new String();
		if (kvps != null) {
			for (int i = 0; i < numKvps; i++) {
				contents = contents + " " + kvps[i];
			}
		}
		return "Unsorted PQ containing {" + contents + " }";
	}
	
	@Override
	public boolean contains(KVP kvp) {
		for (int i = 0; i < numKvps; i++) {
			if (kvps[i].equals(kvp)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getSize() {
		return numKvps;
	}
	
	public void clear() {
		numKvps = 0;
	}
}
