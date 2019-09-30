import java.util.*;

// 		CONTENTS OF THIS FILE:
// 
// 1. Class GenericIterators, with methods:
//
//	- main()
//	- test1() ... test4()
//	- print()
//	- disjoint() and contains()
//
//	(only disjoint and contains require coding)
//
// 2. Generic classes AbsTreeIterator, TreeIterator, DupTreeIterator	
//
//	- complete the outline of AbsTreeIterator
//	- no changes needed for TreeIterator and DupTreeIterator
//
// 3. Generic classes AbsTree, Tree, and DupTree
//
//	- no changes are required for these three classes


// ************************************************************

public class GenericIterators {

public static void main(String[] args) {
	
	test1();
	System.out.println();
	test2();
	System.out.println();
	test3();
	System.out.println();
	test4();
}

// DON'T MODIFY test1() .. test4() METHODS

static void test1() {
	
	AbsTree<Integer> set1 = new Tree<Integer>(101);
	set1.insert(151);
	set1.insert(126);
	set1.insert(51);
	set1.insert(51);
	set1.insert(21);
	set1.insert(22);
	set1.insert(23);
	set1.insert(76);
	set1.insert(77);
	set1.insert(151);
	set1.insert(126);
	set1.insert(201);
	
	AbsTree<Integer> set2 = new Tree<Integer>(100);
	set2.insert(50);
	set2.insert(50);
	set2.insert(25);
	set2.insert(75);
	set2.insert(75);
	set2.insert(150);
	set2.insert(125);
	set2.insert(200);
	set2.insert(100);
	
	System.out.print("set1 = "); print(set1);
	System.out.print("set2 = "); print(set2);
	
	if (disjoint(set1, set2))
		System.out.println("set1 and set2 are disjoint.");
	else
		System.out.println("set1 and set2 are not disjoint.");
}


static void test2() {
	
	AbsTree<Integer> bag1 = new DupTree<Integer>(100);
	bag1.insert(150);
	bag1.insert(125);
	bag1.insert(51);
	bag1.insert(51);
	bag1.insert(31);
	bag1.insert(32);
	bag1.insert(33);
	bag1.insert(79);
	bag1.insert(79);
	bag1.insert(79);
	bag1.insert(150);
	bag1.insert(125);
	bag1.insert(200);
	
	AbsTree<Integer> bag2 = new DupTree<Integer>(100);
	bag2.insert(50);
	bag2.insert(50);
	bag2.insert(25);
	bag2.insert(75);
	bag2.insert(75);
	bag2.insert(150);
	bag2.insert(125);
	bag2.insert(200);
	bag2.insert(100);
	
	System.out.print("bag1 = "); print(bag1);
	System.out.print("bag2 = "); print(bag2);
	
	if (disjoint(bag1, bag2))
		System.out.println("bag1 and bag2 are disjoint.");
	else
		System.out.println("bag1 and bag2 are not disjoint.");
}

static void test3() {
	
	
	AbsTree<Integer> set1 = new Tree<Integer>(100);
	set1.insert(150);
	set1.insert(125);
	set1.insert(50);
	set1.insert(50);
	set1.insert(25);
	set1.insert(126);
	set1.insert(75);
	set1.insert(76);
	set1.insert(150);
	set1.insert(125);
	set1.insert(151);
	set1.insert(201);
	
	AbsTree<Integer> set2 = new Tree<Integer>(100);
	set2.insert(50);
	set2.insert(50);
	set2.insert(25);
	set2.insert(75);
	set2.insert(75);
	set2.insert(150);
	set2.insert(125);
	set2.insert(200);
	set2.insert(100);

	
	System.out.print("set1 = "); print(set1);
	System.out.print("set2 = "); print(set2);
	
	if (contains(set1, set2))
		System.out.println("set1 contains set2.");
	else
		System.out.println("set1 does not contain set2.");
}


static void test4() {

	AbsTree<Integer> bag1 = new DupTree<Integer>(100);
	bag1.insert(150);
	bag1.insert(125);
	bag1.insert(50);
	bag1.insert(50);
	bag1.insert(26);
	bag1.insert(25);
	bag1.insert(27);
	bag1.insert(75);
	bag1.insert(75);
	bag1.insert(76);
	bag1.insert(150);
	bag1.insert(125);
	bag1.insert(200);
	
	AbsTree<Integer> bag2 = new DupTree<Integer>(100);
	bag2.insert(50);
	bag2.insert(50);
	bag2.insert(25);
	bag2.insert(75);
	bag2.insert(75);
	bag2.insert(150);
	bag2.insert(125);
	bag2.insert(200);
	bag2.insert(100);
	
	System.out.print("bag1 = "); print(bag1);
	System.out.print("bag2 = "); print(bag2);

	if (contains(bag1, bag2))
		System.out.println("bag1 contains bag2.");
	else
		System.out.println("bag1 does not contain bag2.");
}

static void print(AbsTree<Integer> bs) {
	System.out.print("{ ");
	for (int x : bs) 
		System.out.print(x + " ");
	System.out.println("}");
}

static <T extends Comparable<T>> boolean disjoint(AbsTree<T> tr1, AbsTree<T> tr2) {
	Iterator<T> iter1 = tr1.iterator();
	Iterator<T> iter2 = tr2.iterator();
	
	T a; T b;
	if(iter1.hasNext())
		a = iter1.next();
	else
		return true;
	if(iter2.hasNext())
		b = iter2.next();
	else
		return true;

	// ********** fill in code here **************
	boolean condition = true;
	while(condition) {
		if (a.compareTo(b) == 0) {
			System.out.println(a +" = "+ b);
			condition = false;
			return false;
		}
		if (a.compareTo(b) > 0) {
			System.out.println(a +" > "+ b);
			if(iter2.hasNext())
				b = iter2.next();
			else
				condition = false;
		}
		if (a.compareTo(b) < 0) {
			System.out.println(a +" < "+ b);
			if(iter1.hasNext())
				a = iter1.next();
			else
				condition = false;
		}
	}
	return true;
}

static <T extends Comparable<T>> boolean contains(AbsTree<T> tr1, AbsTree<T> tr2) {
	Iterator<T> iter1 = tr1.iterator();
	Iterator<T> iter2 = tr2.iterator();
	
	T a; T b;
	if(iter1.hasNext())
		a = iter1.next();
	else
		return true;
	if(iter2.hasNext())
		b = iter2.next();
	else
		return true;

	// ********** fill in code here **************
	boolean condition = true;
	while(condition) {
		if (b.compareTo(a) == 0) {
			System.out.println(b +" = "+ a);
			if(iter2.hasNext() && iter1.hasNext()) {
				a = iter1.next();
				b = iter2.next();
			}else {
				condition = false;
			}
		}
		if (b.compareTo(a) > 0) {
			System.out.println(b +" > "+ a);
			if(iter1.hasNext())
				a = iter1.next();
			else
				condition = false;
		}
		if (b.compareTo(a) < 0) {
			System.out.println(b +" < "+ a);
			return false;
		}
	}
	return true;
	 
}

}


//***********************  GENERIC TREE ITERATORS ***************************

class AbsTreeIterator<T extends Comparable<T>> implements Iterator<T> {

	public AbsTreeIterator(AbsTree<T> root) {	
		// fill in the code
		stack_left_spine(root);	
	}
	
	public boolean hasNext() {
		// fill in the code
		return !stack.empty();
	}
	
	public T next() {
		// fill in the code
		AbsTree<T> topNode = stack.peek();
		if (topNode.get_count() > 1 && count != topNode.get_count()) {
			count++;
			return topNode.value;
		}else {
			topNode = stack.pop();
			if(topNode.right != null) {
				stack_left_spine(topNode.right);
			}
			count = 1;
		}
		return topNode.value;
	}
	
	private void stack_left_spine(AbsTree<T> node) {
		// fill in the code
		boolean emptynode = false;
		while (!emptynode) {
			stack.push(node);
			if(node.left == null)
				emptynode = true;
			else
				node = node.left;
		}
	}
	
	private Stack<AbsTree<T>> stack = new Stack<AbsTree<T>>();
	// + any other private fields that you want
	private int count = 1;
}


// NO CHANGES NEEDED FOR TreeIterator<T> and DupTreeIterator<T>

class TreeIterator<T extends Comparable<T>> extends AbsTreeIterator<T> {
	public TreeIterator(AbsTree<T> t) {
		super(t);
	}
}

class DupTreeIterator<T extends Comparable<T>> extends AbsTreeIterator<T> {
	public DupTreeIterator(AbsTree<T> t) {
		super(t);
	}
}


//****************** GENERIC ABSTREE, TREE, AND DUPTREE ********************

abstract class AbsTree<T extends Comparable<T>> implements Iterable<T> {

	public AbsTree(T v) {
		value = v;
		left = null;
		right = null;
	}

	public void insert(T v) {
		if (value.compareTo(v) == 0)
			count_duplicates();
		if (value.compareTo(v) > 0)
			if (left == null)
				left = add_node(v);
			else
				left.insert(v);
		else if (value.compareTo(v) < 0)
			if (right == null)
				right = add_node(v);
			else
				right.insert(v);
	}

	public Iterator<T> iterator() {
		return create_iterator();
	}

	protected abstract AbsTree<T> add_node(T n);
	protected abstract void count_duplicates();
	protected abstract int get_count();
	protected abstract Iterator<T> create_iterator();
	
	protected T value;
	protected AbsTree<T> left;
	protected AbsTree<T> right;
}


class Tree<T extends Comparable<T>> extends AbsTree<T> {
	public Tree(T n) {
		super(n);
	}
	
	public Iterator<T> create_iterator() {
		return new AbsTreeIterator<T>(this);
	}

	protected AbsTree<T> add_node(T n) {
		return new Tree<T>(n);
	}

	protected void count_duplicates() {
		;
	}
	
	protected int get_count() {
		return 1;
	}
}


class DupTree<T extends Comparable<T>> extends AbsTree<T> {
	public DupTree(T n) {
		super(n);
		count = 1;
	};

	public Iterator<T> create_iterator() {
		return new AbsTreeIterator<T>(this);   // to do
	}
	
	protected AbsTree<T> add_node(T n) {
		return new DupTree<T>(n);
	}

	protected void count_duplicates() {
		count++;
	}
	
	protected int get_count() {
		return count;
	}

	protected int count;
}

