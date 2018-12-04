import java.util.Stack;

public class Graph {
	int size; // ������ ����
	String[] vertices; // �������� ������ �迭
	boolean[][] a; // ���� ���
	boolean[] visit; // ���� �湮 ���θ� ǥ�����ִ� �迭
	boolean[] visit1;
	boolean[] visit2;

	public Graph(String[] args) {
		size = args.length;
		vertices = new String[size];
		System.arraycopy(args, 0, vertices, 0, size);
		a = new boolean[size][size];
		visit = new boolean[size];
		visit1 = new boolean[size];
		visit2 = new boolean[size];
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
			return "{}";
		StringBuffer buf = new StringBuffer("{" + vertex(0));

		for (int i = 1; i < size; i++)
			buf.append(", " + vertex(i));
		return buf + "}";
	}

	private String vertex(int i) {
		StringBuffer buf = new StringBuffer(vertices[i] + " : ");
		for (int j = 0; j < size; j++)
			if (a[i][j])
				buf.append(vertices[j]);

		return buf + "";
	}

	public void recu_dfs(String x) {
		int v = index(x);
		visit[v] = true; // ���� v�� �湮�ߴٰ� ��ũ
		
		System.out.print(x + " ");
		
		for (int i = 1; i < size; i++) {	
			if (visit[i] != true && a[v][i] == true)  // ������ �湮���� �ʰ� v�� ������ ���			
				recu_dfs(vertices[i]); // ���������� ���ư����� ��
		}
		
	}

	public void recu_dfs_tree(String x) {
		int v = index(x);
		visit1[v] = true; // ���� v�� �湮�ߴٰ� ��ũ
		for (int i = 1; i < size; i++) {
			if (visit1[i] != true && a[v][i] == true) { // ������ �湮���� �ʰ� v�� ������ ���
				System.out.println(vertices[i] + "��" + x + " �ڽ��̴�. ");
				recu_dfs_tree(vertices[i]); // ���������� ���ư����� ��
			}
		}
	}

	public void dfs_tree(String x) {
		Stack<Integer> Stack = new Stack<Integer>();
		int v = index(x);
		visit2[v] = true; // ���� ���� v�� �湮�ߴٰ� ��ũ
		Stack.push(v);// �޾ƿ� ��������v�� ���ÿ� push����
		int w = v, u;
		int check;

		while (!Stack.isEmpty()) { // ������ ������� ���� �� ���� ����
			check = 0; // �ʱ�ȭ
			for (u = 0; u < size; u++) {
				if (a[w][u] == true && visit2[u] != true) { // w�� �����ϸ鼭 �湮���� ���� ������ �ִ� ��� ->������ u��� ��
					visit2[u] = true; // �湮�ߴٰ� ��ũ
					System.out.println(vertices[u] + "��" + vertices[w] + " �ڽ��̴�. "); // ���
					Stack.push(u); // ���ÿ� push����
					w = u;
					check++;
					break;
				}
			}
			if (check == 0) { // w�� �����ϸ鼭 �湮���� ���� ������ ���� ���, ���ÿ� ���Ұ� �ִ� ���
				u = Stack.pop(); // ���ÿ��� ���� ����
				w = u;
			} else if (Stack.isEmpty() == true) { // w�� �����ϸ鼭 �湮���� ���� ������ ���� ���, ������ ����ִٸ� ����
				return;
			}
		}
	}
}
