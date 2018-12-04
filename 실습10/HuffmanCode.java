import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HuffmanCode extends HeapPriorityQueue{
	public Entry entry[] = new Entry[31];	
	public char c[] = new char[100];	
	public String text;
	
	public HuffmanCode(String file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine().toUpperCase();	
			text = line;
			while(line != null) {
				for(int i = 0; i < line.length(); ++i) {	
					if ('A' <= line.charAt(i) && line.charAt(i) <= 'Z') { 
						if (entry[line.charAt(i) - 'A'] == null)
							entry[line.charAt(i) - 'A'] = new Entry();
						entry[line.charAt(i) - 'A'].str = line.charAt(i);
						++entry[line.charAt(i) - 'A'].count;
					} else if (line.charAt(i) == ' ') {	
						if (entry[26] == null)
							entry[26] = new Entry();
						entry[26].str = line.charAt(i);						
						++entry[26].count;
					} else if (line.charAt(i) == '\'' ) {	
						if (entry[27] == null)
							entry[27] = new Entry();
						entry[27].str = line.charAt(i);						
						++entry[27].count;
					} else if (',' < line.charAt(i) && line.charAt(i) < '.') {	
						if (entry[line.charAt(i) - 16] == null)
							entry[line.charAt(i) - 16] = new Entry();
						entry[line.charAt(i) - 16].str = line.charAt(i);
						++entry[line.charAt(i) - 16].count;
					} 
				}
				line = reader.readLine();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}	
		for (int i = 0; i < entry.length; ++i) {	
			if(entry[i] != null) {
				if (queue[size()] == null)
					queue[size()] = new Entry();
				queue[size()].addQueue(entry[i]);
			}
		}
		
	}
	
	public void make() {	
		Entry e = new Entry();
		Entry left; Entry right;
		while(size() > 1) {
			left = (Entry) best();	remove();
			right = (Entry) best();	remove();
			e = new Entry(left, right);
			if (size() != 0)
				queue[size()-1].addQueue(e);
			else {
				if (queue[size()] == null)
					queue[size()] = new Entry();
				queue[size()].addQueue(e);			
			}	
		}
	}
	
	public void printTable(Entry e, int index, char []c) {

		if ( e.left != null) {
			c[index] = e.left.binary;
			printTable(e.left, index+1, c);
		}
		if (e.right != null) {
			c[index] = e.right.binary;
			printTable(e.right, index+1, c);
		}
		if (e.left == null && e.right == null) {
			System.out.print(e.str+" : ");
			for(int i = 0; i < index; ++i)
				System.out.print(c[i]);
			System.out.println();
		}
	}
	
	public void encoding(Entry e, char str, int index, char []c) {

		if ( e.left != null) {
			c[index] = e.left.binary;
			encoding(e.left, str, index+1, c);
		}
		if (e.right != null) {
			c[index] = e.right.binary;
			encoding(e.right, str, index+1, c);
		}
		if (e.left == null && e.right == null && str == e.str) {
			for(int i = 0; i < index; ++i)
				System.out.print(c[i]);
			System.out.print(" ");
		}
	}
	
}
