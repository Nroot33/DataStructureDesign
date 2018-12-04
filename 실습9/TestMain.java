import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TestMain {
	public static void main(String args[]) {
		ArrayList<Integer> array = new ArrayList<>(3);
		Random rand = new Random();
		long start, end;

		array.add(100000);
		array.add(200000);
		array.add(500000);

		for (int m = 0; m < 3; m++) {

			int SuccessBST = 0;
			int SuccessAVL = 0;
			int SuccessQPH = 0;

			ArrayList<Integer> intValue = new ArrayList<>(array.get(m));

			for (int i = 0; i < array.get(m); i++) {
				intValue.add(rand.nextInt(200000) + 1);
				for (int j = 0; j < i; j++) { // 중복값 제거
					if (intValue.get(i) == intValue.get(j))
						i--;
				}
				Collections.shuffle(intValue);
			}

			System.out.println();
			System.out.println("n = " + array.get(m));

			BinarySearchTree BST = new BinarySearchTree(intValue.get(0));
			for (int i = 0; i < array.get(m); i++) {
				BST.insert(intValue.get(i));
			}

			AVLTree avl = new AVLTree(intValue.get(0));
			for (int i = 0; i < array.get(m); i++) {
				avl.add(intValue.get(i));
			}

			QuadraticProbingHashTable QPH = new QuadraticProbingHashTable();
			for (int i = 0; i < array.get(m); i++) {
				QPH.put(intValue.get(i), intValue.get(i));
			}

			System.out.println();
			System.out.println("***** Search *****");
			start = System.currentTimeMillis();
			for (int i = 0; i < array.get(m); i++) {
				BST.search(intValue.get(i));
				SuccessBST++;
			}
			end = System.currentTimeMillis();
			System.out.printf("BST search : %.3f\n", (double) (end - start) / 1000);
			System.out.println("BST search : " + SuccessBST);

			start = System.currentTimeMillis();
			for (int i = 0; i < array.get(m); i++) {
				avl.search(intValue.get(i));
				SuccessAVL++;
			}
			end = System.currentTimeMillis();
			System.out.printf("AVL search : %.3f\n", (double) (end - start) / 1000);
			System.out.println("AVL search : " + SuccessAVL);

			start = System.currentTimeMillis();
			for (int i = 0; i < array.get(m); i++) {
				QPH.search(intValue.get(i));
				SuccessQPH++;
			}
			end = System.currentTimeMillis();
			System.out.printf("QPH search : %.3f\n", (double) (end - start) / 1000);
			System.out.println("QPH search : " + SuccessQPH);
		}
	}
}