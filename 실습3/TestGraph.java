import java.io.BufferedReader;
import java.io.FileReader;

public class TestGraph {
	public static void main(String[] args) {
		Graph graph;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("graph.txt")); // text file reading
			String line = reader.readLine(); // line ������
			String lineSplit[] = line.split(" "); // " "�� ������ lineSplit[] ����
			graph = new Graph(lineSplit); // graph ����
			line = reader.readLine(); // line ������
			while (line != null) { // �ݺ���
				String lineSplit1[] = line.split(" "); // " "�� ������ lineSplit[] ����
				graph.add(lineSplit1[0], lineSplit1[1]); // �� �� graph�� ����
				line = reader.readLine(); // line ������
			}
			System.out.println("- graph - "); 
			System.out.println(graph); // graph ���
			
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
			
		} catch (Exception e) { // ���� ó��
			System.out.print("error");
		}

	}
}
