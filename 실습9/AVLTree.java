public class AVLTree {
	private int key, height;
	private AVLTree left, right;
	public static final AVLTree NIL = new AVLTree();
	private int size = 1;
	int SuccessNum = 0;
	
	public AVLTree(int key) { // AVL Ʈ�� ������
		this.key = key;
		left = right = NIL;
	}
	private AVLTree() {
		left = right = this;
		height = -1; //��Ʈ�� ���̴� -1�� ����
	}
	
	public AVLTree(int key, AVLTree left, AVLTree right) {
		this.key = key;
		this.left = left;
		this.right = right;
		height = 1 + Math.max(left.height, right.height);
	}
	
	public int size() {
		if(this == NIL)
			return 0;
		return 1 + left.size() + right.size();
	}
	
	public String toString() { // AVL Ʈ�� ����Ʈ �޼ҵ�
		if(this == NIL)
			return "";
		int balanceFactor = left.height - right.height;
		return left + " (" + key + "," + balanceFactor + ") " + right;
	}
	
	public boolean add(int key) { // AVL Ʈ�� ���� �޼ҵ�
		int oldSize = size();
		grow(key);
		return size()>oldSize;
	}
	
	public AVLTree grow(int key) { // AVL Ʈ�� Ȯ�� �޼ҵ�
		if(this == NIL)
			return new AVLTree(key);
		if(key == this.key)
			return this;
		if(key < this.key)
			left = left.grow(key);
		else
			right = right.grow(key);
		rebalance();
		height = 1 + Math.max(left.height, right.height);
		size++;
		return this;
	}
	
	public void rebalance() { // AVL Ʈ�� ������ �޼ҵ�
		if(right.height > left.height + 1) {
			if(right.left.height > right.right.height)
				right.rotateRight();
			rotateLeft();
		}
		else if (left.height > right.height+1) {
			if(left.right.height > left.left.height)
				left.rotateLeft();
			rotateRight();
		}
	}
	
	public void rotateLeft() { // AVL Right rotate �޼ҵ�
		left = new AVLTree(key, left, right.left);
		key = right.key;
		right = right.right;
	}
	
	public void rotateRight() { // AVL Left rotate �޼ҵ�
		right = new AVLTree(key, left.right, right);
		key = left.key;
		left = left.left;
	}
	
	public boolean search(int key){
		if (this == NIL) return false;
		
		if (key < this.key) {
			return left.search(key);
		}
		
		else if(key > this.key) {
			return right.search(key);
		}
		else  //key == this.key�� ���(search�� ���)
			return true;		
	}
}