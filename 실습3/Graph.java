import java.util.Stack;

public class Graph {
	int size; // 정점의 개수
	String[] vertices; // 정점들을 저장할 배열
	boolean[][] a; // 인접 행렬
	boolean[] visit; // 정점 방문 여부를 표시해주는 배열
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
		visit[v] = true; // 정점 v를 방문했다고 마크
		
		System.out.print(x + " ");
		
		for (int i = 1; i < size; i++) {	
			if (visit[i] != true && a[v][i] == true)  // 정점을 방문하지 않고 v와 인접한 경우			
				recu_dfs(vertices[i]); // 제귀적으로 돌아가도록 함
		}
		
	}

	public void recu_dfs_tree(String x) {
		int v = index(x);
		visit1[v] = true; // 정점 v를 방문했다고 마크
		for (int i = 1; i < size; i++) {
			if (visit1[i] != true && a[v][i] == true) { // 정점을 방문하지 않고 v와 인접한 경우
				System.out.println(vertices[i] + "는" + x + " 자식이다. ");
				recu_dfs_tree(vertices[i]); // 제귀적으로 돌아가도록 함
			}
		}
	}

	public void dfs_tree(String x) {
		Stack<Integer> Stack = new Stack<Integer>();
		int v = index(x);
		visit2[v] = true; // 시작 정점 v를 방문했다고 마크
		Stack.push(v);// 받아온 시작정점v를 스택에 push해줌
		int w = v, u;
		int check;

		while (!Stack.isEmpty()) { // 스택이 비어있지 않을 때 까지 수행
			check = 0; // 초기화
			for (u = 0; u < size; u++) {
				if (a[w][u] == true && visit2[u] != true) { // w에 인접하면서 방문하지 않은 정점이 있는 경우 ->정점을 u라고 함
					visit2[u] = true; // 방문했다고 마크
					System.out.println(vertices[u] + "는" + vertices[w] + " 자식이다. "); // 출력
					Stack.push(u); // 스택에 push해줌
					w = u;
					check++;
					break;
				}
			}
			if (check == 0) { // w에 인접하면서 방문되지 않은 정점이 없는 경우, 스택에 원소가 있는 경우
				u = Stack.pop(); // 스택에서 원소 삭제
				w = u;
			} else if (Stack.isEmpty() == true) { // w에 인접하면서 방문되지 않은 정점이 없는 경우, 스택이 비어있다면 종료
				return;
			}
		}
	}
}
