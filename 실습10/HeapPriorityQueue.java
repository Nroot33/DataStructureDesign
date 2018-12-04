public class HeapPriorityQueue {
	public Entry[] queue = new Entry[31];
	private int size;
	
	public class Entry {
		public char str;
		public int count;
		public char binary = '@';
		public Entry left, right, root;
		public Entry () {
			count = 0;
			left = null; right = null;
		}
		public Entry (char str, int count) {
			this.str = str;
			this.count = count;
		}
		public void addQueue(Entry e) {
			add(e);
		}

		public Entry (Entry left, Entry right) {
			this.str = '@';		 
			this.left = left;
			this.right = right;
			left.binary = '0';
			right.binary = '1';
			left.root = this;
			right.root = this;
			count = left.count + right.count;
		}
	}
	
	public void add(Entry e) {
		Entry temp = e;
		int i = size++;
		while (i > 0) {
			int j = i;
			i = (i - 1) / 2;
			if (queue[i] == null)	
				queue[i] = new Entry();
			if (queue[i].count <= temp.count) {
				if (queue[j] == null)
					queue[j] = new Entry();
				queue[j] = temp;
				return;
			}
			queue[j] = queue[i];
		}
		queue[i] = temp;
	}

	public Object best() {
		if (size == 0)
			throw new java.util.NoSuchElementException();
		return queue[0];
	}

	public Object remove() {
		Object best = best();
		queue[0] = queue[--size];
		queue[size] = null;
		heapify(0, size);
		return best;
	}

	public int size() {
		return size;
	}

	public String toString() {
		if (size == 0)
			return "{}";
		StringBuffer buf = new StringBuffer("{" + queue[0].str);
		for (int i = 1; i < size; i++)
			buf.append("," + queue[i].str);
		return buf + "}";
	}

	public void heapify(int i, int n) {
		Entry ai = queue[i];
		while (i < n / 2) {
			int j = 2 * i + 1;
			if (j + 1 < n && queue[j + 1].count < queue[j].count)
				++j;
			if (queue[j].count >= ai.count) 
				break;
			queue[i] = queue[j];
			i = j;
			queue[i] = ai;
		}
	}
}
