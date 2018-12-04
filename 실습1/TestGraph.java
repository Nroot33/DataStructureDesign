import java.io.*;

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
			System.out.println("- graph -"); 
			System.out.println(graph); // graph ���
			
			System.out.println("- degree -"); 
			for(int i = 0; i < lineSplit.length; i++) // �� ������ ������ �������� ���� ����Ʈ
			System.out.println(lineSplit[i]+" : " + graph.degree(lineSplit[i]));
			
			System.out.println("- findpath -"); 
			for(int i = 0; i < lineSplit.length; i++) // ���� �ٸ� �� ������ ���� ���̰� 2�� ���(path) ���
				for(int j = 0; j < lineSplit.length; j++) {
					if(i==j) continue;
					graph.findPath(lineSplit[i], lineSplit[j]); 
				}
		} catch (Exception e) { // ���� ó��
			System.out.print("error");
		}

	}
}
