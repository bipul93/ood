// Assignment 1 Part 1: Starter Code

class Tree_Test {

	public static void main(String[] args) {
		AbsTree tr = new Tree(100);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(20);
		tr.insert(75);
		tr.insert(20);
		tr.insert(90);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(75);
		tr.insert(90);
		
		
		tr.delete(20);
		tr.delete(20);
		tr.delete(20);
		tr.delete(150);
		tr.delete(100);
		tr.delete(150);
		tr.delete(125);
		tr.delete(125);
		tr.delete(50);
		tr.delete(50);
		tr.delete(50);
		tr.delete(75);
		tr.delete(90);
		tr.delete(75);
		tr.delete(90);
	}
}

class DupTree_Test {

	public static void main(String[] args) {
		AbsTree tr = new DupTree(100);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(20);
		tr.insert(75);
		tr.insert(20);
		tr.insert(90);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(75);
		tr.insert(90);
		

		
		tr.delete(20); //root node test
		tr.delete(20);
		tr.delete(20);
		tr.delete(150);
		tr.delete(100);
		tr.delete(150);
		tr.delete(125);
		tr.delete(125);
		tr.delete(50);
		tr.delete(50);
		tr.delete(50);
		tr.delete(75);
		tr.delete(90);
		tr.delete(75);
		tr.delete(90);
		
	}
}

abstract class AbsTree {	//Node class
	public AbsTree(int n) {
		value = n;
		left = null;
		right = null;
	}

	public void insert(int n) {
		//Link the parent node to this node
	
		if (value == n) {
			count_duplicates();
			System.out.println("count "+get_count());
		}
		else if (value < n)
			if (right == null) {
				right = add_node(n);
				right.parent = this;
			} else
				right.insert(n);
		else if (left == null) {
			left = add_node(n);
			left.parent = this;
		} else
			left.insert(n);
	
	}

	public void delete(int n) {  
		AbsTree t = find(n);

		if (t == null) { // n is not in the tree
			System.out.println("Unable to delete " + n + " -- not in the tree!");
			return;
		}


		int c = t.get_count();
		if (c > 1) {
			t.set_count(c-1);
			return;
		}
		

		if (t.left == null && t.right == null) { // n is a leaf value
			if (t != this)
				case1(t);
			else
				System.out.println("Unable to delete " + n + " -- tree will become empty!");
			return;
		}
		if (t.left == null || t.right == null) { // t has one subtree only
			if (t != this) { // check whether t is the root of the tree
				case2(t);
				return;
			} else { //if its a root node with one child
				if (t.right == null)
					case3L(t);
				else
					case3R(t);
				return;
			}
		}
		// t has two subtrees; go with smallest in right subtree of t
		case3R(t);
	}

	protected void case1(AbsTree t) { // remove the leaf
		// to be filled by you
		//check for the side it is connected to.
		if(t.parent.left != null && t.parent.left.value == t.value) { //assign to left branch
			t.parent.left = null;
		}else {
			t.parent.right = null;
		}
//		t.parent = null;
//		t.left = null;
//		t.right = null;
//		t = null;
		
	}

	protected void case2(AbsTree t) { // remove internal node
		// to be filled by you
		//connect child's parent to current node's parent 
		if(t.left != null) {
			t.left.parent = t.parent;
			if(t.parent.left != null && t.parent.value == t.value) { //assign to left branch
				t.parent.left = t.left;
			}else {
				t.parent.right = t.left;
			}
		}else {
			t.right.parent = t.parent;
			if(t.parent.right != null && t.parent.right.value == t.value) {
				t.parent.right = t.right;
			}else {
				t.parent.left = t.right;
			}
		}
//		t.parent = null;
//		t.left = null;
//		t.right = null;
//		t= null;
		//Connect to the correct parent't direction

	}

	protected void case3L(AbsTree t) { // replace t.value and t.count
		// to be filled by you
		//Find maximum value node on left subtree and replace the value
		AbsTree leftMax = t.left.max();
		t.value = leftMax.value;
		//and remove the found maximum value node
		//check if there's no more children node, call case1
		if(leftMax.left == null && leftMax.right == null) {
			case1(leftMax);
		}else {
			case2(leftMax);
		}
	}

	protected void case3R(AbsTree t) { // replace t.value
		// to be filled by you
		//Find minimum value node on right subtree and replace the value
		AbsTree rightMin = t.right.min();
		t.value = rightMin.value;
		//and remove the found minimum value node
		//check if right minimum node is a leaf node
		if(rightMin.left == null && rightMin.right == null) {
			case1(rightMin);
		}else {
			case2(rightMin);
		}
	}

	private AbsTree find(int n) {
		// to be filled by you
		if (this.value == n) {
			return this;
		}
		else if (this.value < n)
			if (this.right == null) {
				return null;
			} else if(this.right.value == n){
				return this.right;
			}else {
				return this.right.find(n);
			}
		else {
			if (this.left == null) {
				return null;
			} else if(this.left.value == n) {
				return this.left;
			}else {
				return this.left.find(n);
			}
		}
	}

	public AbsTree min() {
		// to be filled by you
		if (this.left == null) {
			return this;
		}
		else {
			return this.left.min();
		}
	}

	public AbsTree max() {
		// to be filled by you
		if (this.right == null) {
			return this;
		}
		else {
			return this.right.max();
		}
	}
	
	public void check_nodes() { 
		System.out.println(this);
		AbsTree minNode = min();
		System.out.println("MIN NODE: "+minNode.value);
		AbsTree maxNode = max();
		System.out.println("MAX NODE: "+maxNode.value);
	}
	

	protected int value;
	protected AbsTree left;
	protected AbsTree right;
	protected AbsTree parent;

	protected abstract AbsTree add_node(int n);
	protected abstract void count_duplicates();
	protected abstract int get_count();
	protected abstract void set_count(int v);
}

class Tree extends AbsTree {
	public Tree(int n) {
		super(n);
	}

	protected AbsTree add_node(int n) {
		return new Tree(n);
	}

	protected void count_duplicates() {
		;
	}


	protected int get_count() {
		// to be filled by you
		return 1;
	}

	protected void set_count(int v) {
		// to be filled by you
	}
}

class DupTree extends AbsTree {
	public DupTree(int n) {
		super(n);
		count = 1;
	};

	protected AbsTree add_node(int n) {
		return new DupTree(n);
	}
	

	protected void count_duplicates() {
		count++;
	}

	protected int get_count() {
		return this.count;
	}

	protected void set_count(int v) {
		this.count = v;
	}

	protected int count;
}
