import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BST_Tree_Test {

	static Tree tr;
	static TreeSet<Integer> ts = new TreeSet<Integer>();
	static Random r = new Random();

	@BeforeAll
	public static void setup() {
		// code to be filled in by you
		for(int i=0; i<25; i++) {
			int rand_num = r.nextInt(25); 
			if(i == 0) {
				tr = new Tree(rand_num);
				ts.add(rand_num);	
			}else {
				tr.insert(rand_num);
				ts.add(rand_num);
			}
		}
		System.out.println("Tree created in Setup:");
		for (int x : tr) 
			System.out.print(x + " ");	
		System.out.println("\nTreeSet created in Setup:");
		for (int x : ts) 
			System.out.print(x + " ");
		System.out.println("\n-------------------------");
	}		 

	@AfterEach
	void check_invariant() {
		// code to be filled in by you 
		assertTrue(ordered(tr));
		
	}
		
	@Test
	void test_insert() {
		// code to be filled in by you
		System.out.println("Testing Tree insert ...");
		System.out.println("Creating TreeSet iterator and Comparing elements pair-wise ...");
		Iterator<Integer> tr_it = tr.iterator();
		Iterator<Integer> ts_it = ts.iterator();
		
		while(tr_it.hasNext()) {
			assertTrue(tr_it.next().equals(ts_it.next()));
		}
		if(ts_it.hasNext()){
			return;
		}
		System.out.println("... Tree insert test passed");
		System.out.println("Tree invariant maintained");
	}
		
	public boolean ordered(Tree tr) {
		// code to be filled in by you	
		return 
			(tr.left == null || tr.value > tr.left.max().value && ordered(tr.left))
						&&
			(tr.right == null || tr.value < tr.right.min().value && ordered(tr.right));
	}

}