package cs240.algo.com;

public abstract class PriorityQueue {
	public static class KVP {
		int key;
		int value;
		
		public KVP(int k, int v) { key = k; value = v; }

		@Override
		public String toString() {
			return "(" + key + ", " + value + ")";
		}

		@Override
		public PriorityQueue.KVP clone() {
			try {
				return (KVP) super.clone();
			} catch (CloneNotSupportedException c) {
				return null;
			}
		}
		
		@Override
		public boolean equals(Object that) {
			return (this.key == ((KVP)that).key) && (this.value == ((KVP)that).value);
		}
	}

	public abstract void insert(KVP kvp);
	public abstract void deleteMax();
	
	/* Used for testing only */
	public abstract boolean contains(KVP kvp);
	public abstract int getSize();
	public abstract void clear();
	
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException c) {
			return null;
		}
	}
}
