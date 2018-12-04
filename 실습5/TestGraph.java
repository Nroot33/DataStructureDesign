import java.io.BufferedReader;
import java.io.FileReader;

public class TestGraph {
	public static void main(String[] args) {
			Graph graph = new Graph("c:\\graph.txt");
			graph.prim();
	}
}
