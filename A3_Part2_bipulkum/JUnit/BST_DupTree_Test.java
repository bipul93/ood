import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BST_DupTree_Test {

	static DupTree dtr;
	static List<Integer> al = new ArrayList<Integer>();
	static Random r = new Random();

	@BeforeAll
	public static void setup() {
	 	// code to be filled in by you
		for(int i=0; i<25; i++) {
			int rand_num = r.nextInt(25); 
			if(i == 0) {
				dtr = new DupTree(rand_num);
				al.add(rand_num);	
			}else {
				dtr.insert(rand_num);
				al.add(rand_num);
			}
		}
		System.out.println("DupTree created in Setup:");
		for (int x : dtr) 
			System.out.print(x + " ");		
		System.out.println("\nSorted Arraylist created in Setup:");
		Collections.sort(al);
		for (int x : al) 
			System.out.print(x + " ");
		System.out.println("\n-------------------------");
	}

	@AfterEach
	void check_invariant() {
		// code to be filled in by you
		assertTrue(ordered(dtr));
	}
	
	@Test
	void test_insert() {
		// code to be filled in by you
		System.out.println("Testing DupTree insert ...");
		System.out.println("Creating Arraylist iterator and Comparing elements pair-wise ...");
		Iterator<Integer> dtr_it = dtr.iterator();
		Iterator<Integer> al_it = al.iterator();
		
		while(dtr_it.hasNext()) {
			assertTrue(dtr_it.next().equals(al_it.next()));
		}
		if(al_it.hasNext()){
			System.out.println("... DupTree insert test failed");
			return;
		}
		System.out.println("... DupTree insert test passed");
		System.out.println("DupTree invariant maintained");
		System.out.println("-------------------------");
	}
	
	@Test
	void test_delete() {
		// code to be filled in by you
		int v = r.nextInt(25); 
		dtr.insert(v);
		int count = get_count(dtr, v);
		System.out.println("Testing DupTree delete: inserted value = "+v+" with count  = "+count);
		dtr.delete(v);
		int new_count = get_count(dtr, v);
		System.out.println("After DupTree delete: value = "+v+" count = "+new_count);
		if(count > 1) {
			assertTrue(count-1 == new_count);
		}
		System.out.println("DupTree delete test passed");
		System.out.println("DupTree invariant maintained");
		System.out.println("-------------------------");
				
	}		

	public int get_count(DupTree tr, int v) {
		// code to be filled in by you
		DupTree node = (DupTree) tr.find(v);
		if(node == null){
			return 0;
		}
		return node.get_count();
	}

	public boolean ordered(Tree tr) {
		// code to be filled in by you
		return 
				(tr.left == null || tr.value >= tr.left.max().value && ordered(tr.left))
							&&
				(tr.right == null || tr.value <= tr.right.min().value && ordered(tr.right));
	}
}