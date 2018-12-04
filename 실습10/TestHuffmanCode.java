public class TestHuffmanCode{
	public static void main(String[] args) {
		HuffmanCode h = new HuffmanCode("text.txt");
		
		h.make();
		
		h.printTable(h.queue[0], 0, h.c);
		
		System.out.println();
		System.out.println("Encoding this String : "+ h.text);
		System.out.print("Huffman Code Stream : ");
		char ch;
		for (int i = 0; i < h.text.length(); ++i) {
			ch = h.text.charAt(i);
			h.encoding(h.queue[0], ch, 0, h.c);
		}
	}
}
