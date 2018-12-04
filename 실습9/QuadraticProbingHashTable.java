public class QuadraticProbingHashTable {
	private Entry[] entries;
	private int size, used;
	private float loadFactor;
	private final Entry NIL = new Entry(null, null);
	
	public QuadraticProbingHashTable(int capacity, float loadFactor) {
		entries = new Entry[capacity];
		this.loadFactor = loadFactor;
	}
	
	public QuadraticProbingHashTable(int capacity) {
		this(capacity, 0.75F);
	}
	
	public QuadraticProbingHashTable() {
		this(50000);
	}
	
	public Object search(Object key) { //get -> search
		int h = hash(key);
		for(int i=0; i<entries.length; i++) {
			int j = nextProbe(h,i);
			Entry entry = entries[j];
			if(entry == null) break;
			if(entry == NIL) continue;
			if(entry.key.equals(key)) return entry.value;
		}
		return null; //Ű�� ã�� ������ ��
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
				return null; //���� ����
			}
			if(entry == NIL) continue;
			if(entry.key.equals(key)) {
				Object oldValue = entry.value;
				entries[j].value = value;
				return oldValue; //������Ʈ
			}
		}
		
		return null; //table overflow -> failure
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
				return oldValue; //����
			}
		}
		return null; //���� - Ű�� ã�� ����
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
		return (h+(i*i)) % entries.length; //���������̹Ƿ� i�� ��������
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