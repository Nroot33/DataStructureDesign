import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Graph {

	int vsize, esize;
	float[][] a;
	Object[] near;
	boolean[] TV;

	public Graph(String gf) {
		BufferedReader in;
		try {

			in = new BufferedReader(new FileReader(gf));

			String line = in.readLine();
			String[] lineArray = line.split(" ");

			vsize = Integer.parseInt(lineArray[0]);// 정점 갯수

			a = new float[vsize][vsize]; // 인접행렬 크기 선언
			near = new Object[vsize]; // near 배열 크기 선언
			TV = new boolean[vsize]; // TV 배열 크기 선언

			for (int i = 0; i < vsize; i++) {
				TV[i] = false;
				near[i] = -1;
			} // 각 배열 초기화

			for (int j = 0; j < vsize; j++)
				for (int k = 0; k < vsize; k++) {
					if (j != k)
						a[j][k] = a[k][j] = Float.POSITIVE_INFINITY;
					else
						a[j][k] = 0;
				} // a 배열 초기화

			line = in.readLine();

			while (line != null) {
				lineArray = line.split(" ");
				add(Integer.parseInt(lineArray[0]), Integer.parseInt(lineArray[1]), Integer.parseInt(lineArray[2]));
				line = in.readLine();
			}
		}

		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void add(int u, int v, int weighted) {
		a[u][v] = a[v][u] = weighted;
	}

	public void prim() {

		int vertex = 0;
		int next_vertex = 0;
		float cost = 0;
	
		System.out.println("Selected vertecies");
		
		for (int i = 0; i < vsize - 1; i++) {
			int x = 0;
			int y = 0;
			float min = Float.POSITIVE_INFINITY;
					
			TV[vertex] = true;
			near[vertex] = "N";

			for (int j = 1; j < vsize; j++) {
				if (near[j] != "N") {
					if ((int)near[j] == -1 && a[vertex][j] != min)
						near[j] = vertex;
				}
			}

			for (int k = 0; k < vsize; k++) {
				for (int l = 0; l < vsize; l++) {
					if (TV[k] == true) {
						if (TV[l] == false && (int)near[l] >= 0 && a[k][l] < min) {
							min = a[k][l];
							x = k;
							y = l;
							next_vertex = l;
						}
					}
				}
			}
			System.out.print(" (" + x + ", " + y + ") : " + (int)min);
			vertex = next_vertex;
			cost =cost + min;
		}
		System.out.print("\n" + "cost : " + (int)cost);
	}
}