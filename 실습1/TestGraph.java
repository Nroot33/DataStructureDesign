import java.io.*;

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
			System.out.println("- graph -"); 
			System.out.println(graph); // graph 출력
			
			System.out.println("- degree -"); 
			for(int i = 0; i < lineSplit.length; i++) // 각 정점에 인접한 정점들의 수를 프린트
			System.out.println(lineSplit[i]+" : " + graph.degree(lineSplit[i]));
			
			System.out.println("- findpath -"); 
			for(int i = 0; i < lineSplit.length; i++) // 서로 다른 두 정점에 대해 길이가 2인 경로(path) 출력
				for(int j = 0; j < lineSplit.length; j++) {
					if(i==j) continue;
					graph.findPath(lineSplit[i], lineSplit[j]); 
				}
		} catch (Exception e) { // 예외 처리
			System.out.print("error");
		}

	}
}
