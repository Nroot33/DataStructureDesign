import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class TestHashTable {
	public static void main(String args[]) {
		int lineNumber = 0;
		LinearProbingHashTable LPH = new LinearProbingHashTable(101);
		DoubleHashingHashTable DHT = new DoubleHashingHashTable(101);
		QuadraticProbingHashTable QPH = new QuadraticProbingHashTable(101);
		SeparateChainingHashTable SCHT = new SeparateChainingHashTable(101);

		try {
			BufferedReader in = new BufferedReader(new FileReader("C://Caesar.txt"));
			String line = in.readLine();
			while (line != null) {
				lineNumber++;
				String word = line.toUpperCase();
				LPH.put(word, 1);
				QPH.put(word, 1);
				DHT.put(word, 1);
				SCHT.put(word, 1);
				line = in.readLine();
			}
			in.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("***** Collision Count *****");
		LPH.get_count();
		QPH.get_count();
		DHT.get_count();
		SCHT.get_count();
		System.out.println();
		System.out.println("***** Word Count *****");
		System.out.println("I : " +"LPH("+LPH.get("I")+") "+"QPH("+QPH.get("I")+") "+"DHT("+DHT.get("I")+") "+"SCHT("+SCHT.get("I")+")");
		System.out.println("You : " +"LPH("+LPH.get("YOU")+") "+"QPH("+QPH.get("YOU")+") "+"DHT("+DHT.get("YOU")+") "+"SCHT("+SCHT.get("YOU")+")");
		System.out.println("he : " +"LPH("+LPH.get("HE")+") "+"QPH("+QPH.get("HE")+") "+"DHT("+DHT.get("HE")+") "+"SCHT("+SCHT.get("HE")+")");
		System.out.println("Brutus : " +"LPH("+LPH.get("BRUTUS")+") "+"QPH("+QPH.get("BRUTUS")+") "+"DHT("+DHT.get("BRUTUS")+") "+"SCHT("+SCHT.get("BRUTUS")+")");
		System.out.println("evil : " +"LPH("+LPH.get("EVIL")+") "+"QPH("+QPH.get("EVIL")+") "+"DHT("+DHT.get("EVIL")+") "+"SCHT("+SCHT.get("EVIL")+")");
		System.out.println("the : " +"LPH("+LPH.get("THE")+") "+"QPH("+QPH.get("THE")+") "+"DHT("+DHT.get("THE")+") "+"SCHT("+SCHT.get("THE")+")");
		System.out.println("and : " +"LPH("+LPH.get("AND")+") "+"QPH("+QPH.get("AND")+") "+"DHT("+DHT.get("AND")+") "+"SCHT("+SCHT.get("AND")+")");
	}
}
