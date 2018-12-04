
public class TestAVLTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AVLTree avl = new AVLTree(14);
		avl.add(17);
		avl.add(11);
		avl.add(7);
		avl.add(53);
		avl.add(4);
		avl.add(13);
		avl.add(12);
		avl.add(8);
		System.out.println("Insert Data : " + avl);
		
		avl.delete(53);
		System.out.println("Delete 53 : " + avl);
		avl.delete(11);
		System.out.println("Delete 11 : " + avl);
		avl.delete(7);
		System.out.println("Delete 7 : " + avl);
		avl.delete(12);
		System.out.println("Delete 12 : " + avl);
		avl.delete(14);
		System.out.println("Delete 14 : " + avl);
		avl.delete(13);
		System.out.println("Delete 13 : " + avl);
	}
}
