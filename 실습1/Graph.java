
public class Graph {

	int size;
	String[] vertices;
	boolean[][] a;

	public Graph(String[] args) {
		size = args.length;
		vertices = new String[size];
		System.arraycopy(args, 0, vertices, 0, size);
		a = new boolean[size][size];
	}

	public void add(String v, String w) {
		int i = index(v), j = index(w);
		a[i][j] = a[j][i] = true;
	}

	private int index(String v) {
		for (int i = 0; i < size; i++)
			if (vertices[i].equals(v))
				return i;
		return a.length;
	}

	public String toString() {
		if (size == 0)
			return "{ }";
		StringBuffer buf = new StringBuffer("{" + vertex(0));
		for (int i = 1; i < size; i++)
			buf.append(" , " + vertex(i));
		return buf + "}";
	}

	private String vertex(int i) {
		StringBuffer buf = new StringBuffer(vertices[i] + "-");
		for (int j = 0; j < size; j++)
			if (a[i][j])
				buf.append(vertices[j]);
		return buf + "";
	}

	public int degree(String v) { // dgree 메소드
		int i = index(v);
		int num = 0;
		for (int j = 0; j < size; j++) {
			if (a[i][j])
				num++;
		}
		return num;
	}

	public void findPath(String v, String w) { // findpath() 메소드
		int i = index(v), j = index(w);
		int n = 0;
		String x;
		for (int k = 0; k < size; k++) {
			if (a[i][k] && a[k][j]) {
				x = vertices[k];
				System.out.println(v + " -> " + w + " : " + v + " - " + x + " - " + w);
			}
			else
				n++;
		}
		if(n == size) 
			System.out.println(v + " -> " + w + " : " + "No path");
		return;
	}
}
