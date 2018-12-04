public class BinarySearchTree {
	private Comparable key;
	private BinarySearchTree left,right;
	int SuccessNum = 0;
	
	public BinarySearchTree(Comparable key){ //������
		this.key = key;
		this.left = null; 
		this.right = null;
	}
	
	public boolean insert(Comparable key){
		if(this.key == null){
			BinarySearchTree bst = new BinarySearchTree(key);
			this.key = key;
			//System.out.println(key + "\t���� ����");
			return true;
		}
		else if(key.compareTo(this.key) < 0){
			if(left != null){
				left.insert(key);
			}
			else { 
				left = new BinarySearchTree(key);
				//System.out.println(key + "\t���� ����");
				return true;
			}
		}
		else if(key.compareTo(this.key) > 0){ 
			if(right != null){
				right.insert(key);
			}
			else {
				right = new BinarySearchTree(key);
				//System.out.println(key + "\t���� ����");
			}
		}
		else{
			//System.out.println(key + "\t���� ����");
			return false;
		}
		return true;
	}
	
	public void inorder(){ 
		if(left != null)
			left.inorder();
			System.out.print(key + " ");
		if(right != null)
			right.inorder();			
	}
	
	
	public boolean search(Comparable key){
		if(key.compareTo(this.key) < 0){ 
			if(left != null){
				left.search(key);
			}
			else {
				//System.out.println(key + "  :  Ʈ�� �� �������� ����");
				return false;
			}
		}
		else if(key.compareTo(this.key) > 0){ 
			if(right != null){
				right.search(key);
			}
			else {
				//System.out.println(key + "  :  Ʈ�� �� �������� ����");
				return false;
			}
		}
		else {
			//System.out.println(key + "  :  Ʈ�� �� ������");
		}
		return true;				
	}
}