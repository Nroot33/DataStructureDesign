import java.io.BufferedReader;
import java.io.FileReader;

public class TestGraph {
	public static void main(String[] args) {
		Graph graph;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("graph.txt")); // text file reading
			String line = reader.readLine(); // line 나누기
			String lineSplit[] = line.split(" "); // " "로 나눠서 lineSplit[] 저장
			graph = new Graph(lineSplit); // graph 생성
			line = reader.readLine(); // line 나누기
			while (line != null) { // 반복문
				String lineSplit1[] = line.split(" "); // " "로 나눠서 lineSplit[] 저장
				graph.add(lineSplit1[0], lineSplit1[1]); // 각 각 graph에 저장
				line = reader.readLine(); // line 나누기
			}
			System.out.println("- graph - "); 
			System.out.println(graph); // graph 출력
			
			System.out.println();
			
			System.out.print("DFS : ");
			graph.recu_dfs("A");
			
			System.out.println();
			System.out.print("DFS : ");
			graph.recu_dfs("A");
 
			System.out.println();
			System.out.println();

			System.out.println(" - recu_dfs_tree - ");
			graph.recu_dfs_tree("A");
			
			System.out.println();
			
			System.out.println(" - dfs_tree - ");
			graph.dfs_tree("A");
			
		} catch (Exception e) { // 예외 처리
			System.out.print("error");
		}

	}
}
