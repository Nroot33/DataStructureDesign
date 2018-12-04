public class LinearProbingHashTable {
	private Entry[] entries;
	private int size, used;
	private float loadFactor;
	private final Entry NIL = new Entry(null, null);
	int collisionNum = 0; //충돌 횟수 세주는 변수
	
	public LinearProbingHashTable(int capacity, float loadFactor) {
		entries = new Entry[capacity];
		this.loadFactor = loadFactor;
	}
	
	public LinearProbingHashTable(int capacity) {
		this(capacity, 0.75F);
	}
	
	public LinearProbingHashTable() {
		this(101);
	}
	
	public Object get(Object key) {
		int h = hash(key);
		for(int i=0; i<entries.length; i++) {
			int j = nextProbe(h,i);
			Entry entry = entries[j];
			if(entry == null) break;
			if(entry == NIL) continue;
			if(entry.key.equals(key)) return entry.value;
		}
		return null; //키를 찾지 못했을 때
	}
	
	public Object put(Object key, Object value) {
		if(used > loadFactor*entries.length) rehash();
		int h = hash(key);
		for(int i=0; i<entries.length; i++) {
			int j = nextProbe(h,i);
			Entry entry = entries[j];
			if(entry == null) {
				entries[j] = new Entry(key, value);
				++size;
				++used;
				return null; 
			}
			if(entry == NIL) {continue;	}		
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
		System.out.println("LinearProb : " + collisionNum);		
	}
	
	
	public Object remove(Object key) {
		int h = hash(key);
		for(int i=0; i<entries.length; i++) {
			int j = nextProbe(h,i);
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
	
	public int nextProbe(int h, int i) {
		return (h+i) % entries.length; //선형조사
	}
	
	public void rehash() {
		Entry[] oldEntries = entries;
		entries = new Entry[2*oldEntries.length+1];
		for(int k=0; k<oldEntries.length; k++) {
			Entry entry = oldEntries[k];
			if(entry == null || entry == NIL) continue;
			int h = hash(entry.key);
			for(int i=0; i<entries.length; i++) {
				int j = nextProbe(h,i);
				if(entries[j] == null) {
					entries[j] = entry;
					break;
				}
			}
		}
		used = size;
	}
}
