public class DoubleHashingHashTable{
	private Entry[] entries;
	private int size, used;
	private float loadFactor;
	private final Entry NIL = new Entry(null, null);
	int collisionNum = 0;
	
	public DoubleHashingHashTable(int capacity, float loadFactor) {
		entries = new Entry[capacity];
		this.loadFactor = loadFactor;
	}
	
	public DoubleHashingHashTable(int capacity) {
		this(capacity, 0.75F);
	}
	
	public DoubleHashingHashTable() {
		this(101);
	}
	
	public Object get(Object key) {
		int h = hash(key);
		int d = hash2(key);
		for(int i=0; i<entries.length; i++) {
			int j = nextProbe(h,d,i);
			Entry entry = entries[j];
			if(entry == null) break;
			if(entry == NIL) continue;
			if(entry.key.equals(key)) return entry.value;
		}
		return null; 
	}
	
	public Object put(Object key, Object value) {
		if(used > loadFactor*entries.length) rehash();
		int h = hash(key);
		int d = hash2(key); 
		for(int i=0; i<entries.length; i++) {
			int j = nextProbe(h,d,i);
			Entry entry = entries[j];
			if(entry == null) {
				entries[j] = new Entry(key, value);
				++size;
				++used;
				return null; 
			}
			if(entry == NIL) continue;
			if(entry.key.equals(key)) {
				Object oldValue = entry.value;
				entries[j].value = value;
				entry.value = (int)value + (int)oldValue;
				return entry.value; 
			}
			collisionNum++;
		}
		return null; 
	}
	
	public void get_count() {
		System.out.println("DoubleProb : " + collisionNum);
	}
	
	public Object remove(Object key) {
		int h = hash(key);
		int d = hash2(key);
		for(int i=0; i<entries.length; i++) {
			int j = nextProbe(h,d,i);
			Entry entry = entries[j];
			if(entry == null) break;
			if(entry == NIL) continue;
			if(entry.key.equals(key)) {
				Object oldValue = entry.value;
				entries[j] = NIL;
				--size;
				return oldValue; 
			}
		}
		return null; 
	}
	
	public int size() {
		return size;
	}
	
	public class Entry{
		Object key, value;
		Entry(Object k, Object v){
			key = k;
			value = v;
		}
	}
	
	public int hash(Object key) {
		if(key == null)
			throw new IllegalArgumentException();
		return (key.hashCode() & 0x7FFFFFFF) % entries.length;
	}
	
	public int hash2(Object key) {
		if(key == null)
			throw new IllegalArgumentException();
		return 1+(key.hashCode() & 0x7FFFFFFF) % (entries.length-1);
	}
	
	public int nextProbe(int h, int d, int i) {
		return (h+(d*i)) % entries.length; 
	}
	
	public void rehash() {
		Entry[] oldEntries = entries;
		entries = new Entry[2*oldEntries.length+1];
		for(int k=0; k<oldEntries.length; k++) {
			Entry entry = oldEntries[k];
			if(entry == null || entry == NIL) continue;
			int h = hash(entry.key);
			int d = hash2(entry.key);
			for(int i=0; i<entries.length; i++) {
				int j = nextProbe(h,d,i);
				if(entries[j] == null) {
					entries[j] = entry;
					break;
				}
			}
		}
		used = size;
	}
}
