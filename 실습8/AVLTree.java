import java.util.ArrayList;

public class AVLTree {
	private int key, height;
	private AVLTree left, right;
	public static final AVLTree NIL = new AVLTree();

	public AVLTree(int key) {
		this.key = key;
		left = right = NIL;
	}

	private AVLTree() {
		left = right = this;
		height = -1;
	}

	private AVLTree(int key, AVLTree left, AVLTree right) {
		this.key = key;
		this.left = left;
		this.right = right;
		height = 1 + Math.max(left.height, right.height);
	}

	public int size() {
		if (this == NIL)
			return 0;
		return 1 + left.size() + right.size();
	}

	public String toString() {
		if (this == NIL)
			return "";
		return left + " " + key + "(" + (this.left.height - this.right.height) + ") " + right;
	}

	public boolean add(int key) {
		int oldSize = size();
		grow(key);
		return size() > oldSize;
	}

	public AVLTree grow(int key) {
		if (this == NIL)
			return new AVLTree(key);
		if (key == this.key)
			return this;
		if (key < this.key)
			left = left.grow(key);
		else
			right = right.grow(key);
		rebalance();
		height = 1 + Math.max(left.height, right.height);
		return this;
	}

	private void rebalance() {
		if (right.height > left.height + 1) {
			if (right.left.height > right.right.height)
				right.rotateRight();
			rotateLeft();
		} else if (left.height > right.height + 1) {
			if (left.right.height > left.left.height)
				left.rotateLeft();
			rotateRight();
		}
	}

	private void rotateLeft() {
		left = new AVLTree(key, left, right.left);
		key = right.key;
		right = right.right;
	}

	private void rotateRight() {
		right = new AVLTree(key, left.right, right);
		key = left.key;
		left = left.left;
	}

	public boolean delete(int key) {
		int OldValue = size();
		remove(key);
		return OldValue > size();
	}

	private AVLTree remove(int keyValue) {
		if (keyValue == this.key) {
			if (this.right == NIL && this.left == NIL) {
				return NIL;
			} else if (this.right == NIL) {
				return left;
			} else if (this.left == NIL) {
				return right;
			} else {
				if (this.right.size() == 1) {
					this.key = this.right.key;
					this.right = NIL;
					rebalance();
					height = 1 + Math.max(left.height, right.height);
					return this;
				} else {
					ArrayList<AVLTree> stack = new ArrayList<AVLTree>();
					for (AVLTree avl = this.right;; avl = avl.left) {
						if (avl.left == NIL) {
							this.key = avl.key;
							avl.key = avl.right.key;
							avl.left = avl.right.left;
							avl.right = avl.right.right;
							stack.add(avl);
							break;
						} else if (avl.left.size() == 1) {
							this.key = avl.left.key;
							avl.left = NIL;
							stack.add(avl);
							break;
						}
						stack.add(avl);
					}
					while (!stack.isEmpty()) {
						stack.get(stack.size() - 1).rebalance();
						stack.get(stack.size() - 1).height = 1
								+ Math.max(stack.get(stack.size() - 1).left.height, stack.get(stack.size() - 1).right.height);
						stack.remove(stack.size() - 1);
					}
					return this;
				}
			}
		}
		if (keyValue < this.key)
			left = left.remove(keyValue);
		else
			right = right.remove(keyValue);
		rebalance();
		height = 1 + Math.max(left.height, right.height);
		return this;
	}
}