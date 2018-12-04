import java.io.*;

public class Graph {
	int vSize, eSize; 
	Edge e[]; 

	public Graph(String gf) { 
		BufferedReader in; 
		try {
			in = new BufferedReader(new FileReader(gf)); 

			String line = in.readLine(); 
			String[] lineArray = line.split(" "); 

			if (line != null) { 
				vSize = Integer.parseInt(lineArray[0]); 
				eSize = Integer.parseInt(lineArray[1]);
				e = new Edge[eSize]; 

				line = in.readLine(); 

				for (int i = 0; i < eSize || line != null; i++) { 
					lineArray = line.split(" "); 

					e[i] = new Edge(Integer.parseInt(lineArray[0]), Integer.parseInt(lineArray[1]), Integer.parseInt(lineArray[2]), false); // e[i] 객체 생성

					line = in.readLine(); 
				}
			}
		}

		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private class Edge {
		int v, w, weight; 
		boolean selected; 

		public Edge(int v, int w, int weight, boolean selected) { 
			this.v = v;
			this.w = w; 
			this.weight = weight; 
			this.selected = selected; 
		}
	}

	public void weightedunion(int i, int j, int[] parent) { // 합집합 구하기
		if ((-1) * parent[i] < (-1) * parent[j]) 
			parent[i] = j; 

		else 
			parent[j] = i; 
	}

	public int collapsingfind(int set, int[] parent) { // 원소가 속한 집합 구하기
		int root; 
		int temp; 

		for (root = set; parent[root] >= 0; root = parent[root]); 

		for (int i = set; parent[i] >= 0; i = temp) { 
			temp = parent[i]; 
			parent[i] = root; 
		}

		return root; 
	}

	public void kruskal() { // kruskal 알고리즘 메소드
		int parent[] = new int[vSize]; 
		Edge E[] = new Edge[eSize]; 
		Edge swap; 
		int count = 0, sum = 0; 
		
		if (eSize - 1 < vSize) { 
			System.out.println("Can not create."); // 
			return; 
		}

		for (int i = 0; i < eSize; i++) 
			E[i] = e[i]; 
		for (int i = 0; i < vSize; i++) 
			parent[i] = -1; 

		for (int i = eSize - 1; i > 0; i--) 
			for (int j = 0; j < i; j++) { 
				if (E[j].weight > E[j + 1].weight) { 
					swap = E[j]; 
					E[j] = E[j + 1]; 
					E[j + 1] = swap; 
				}
			}

		for (int i = 0; i < eSize - 1; i++) { 
			if (collapsingfind(E[i].v, parent) != collapsingfind(E[i].w, parent)) { 
				weightedunion(collapsingfind(E[i].v, parent), collapsingfind(E[i].w, parent), parent); 
				E[i].selected = true; 
			}
		}

		for (int i = 0; i < eSize; i++) 
			if (e[i].selected == true) 
				count++; 

		if (count < vSize - 1) { 
			System.out.println("Can not create.");
			return;
		}
		
		for (int i = 0; i < eSize; i++) { 
			if (e[i].selected == true) { 
				sum += e[i].weight; 
				System.out.print("(" + e[i].v + "," + e[i].w + ")  "); 
			}
		}
		System.out.println(""); 
		System.out.println( "- Min cost :" + sum); 
	}

}
