package utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

class LinkedEquivalenceTest {
	
	Comparator<Integer> c = new Comparator<Integer>()
	 {
	 // All even integers are 'equivalent'
	// All odd integers are 'equivalent'
	public int compare(Integer x, Integer y)
	 { return x % 7 == y % 7 ? 0 : 1; }
	 };
	 
	Comparator<Integer>  c1 = new Comparator<Integer>()
	 {
	//Checks if greater than 5
	public int compare(Integer x, Integer y)
	 { return x >=5 == y>= 5 ? 0 : 1; }
	 };

	public LinkedEquivalenceClass<Integer> listBuilder(int c1, int x1, int x2, int x3, Comparator<Integer> c) {
		
		LinkedEquivalenceClass<Integer> l = new LinkedEquivalenceClass<Integer>(c);
		l.add(c1);
		l.add(x1);
		l.add(x2);
		l.add(x3);
	
		return l;
	}
	
	@Test
	void testClear() {
		LinkedEquivalenceClass<Integer> list = listBuilder(7, 21, 25, 14, c);
		
		assertEquals("7 | 14 21", list.toString());
		
		list.clear();
		
		assertEquals(0, list.size());
	}
	
	@Test
	void testClearNonCanonical() {
		//Creating a list using a different comparator
		LinkedEquivalenceClass<Integer> list = listBuilder(5, 10, 15, 25, c1);
		
		//Removing all elements from list
		list.clearNonCanonical();
		
		assertEquals(1, list.size());
	
		assertEquals(list.toString(), "5 | ");
	}
	
	@Test
	void testSize() {
		LinkedEquivalenceClass<Integer> l = new LinkedEquivalenceClass<Integer>(c1);
		
		assertEquals(0,l.size());
		
		//Check canonical adds to size
		l.add(5);
		assertEquals(1, l.size());
		
		//Add rest
		l.add(6);
		l.add(7);
		l.add(8);
		
		assertEquals(4, l.size());
	}
	
	@Test
	void testAdd() {
		 
		LinkedEquivalenceClass<Integer> l = new LinkedEquivalenceClass<Integer>(c);
		
		l.add(7);
		l.add(14);
		l.add(21);
		l.add(0);
		
		//Make sure 7 is the canonical
		assertEquals(7, l.canonical());
		
		assertTrue(l.size() == 4);
		
		//Will not add these 2
		l.add(15);
		l.add(null);
		
		assertTrue(l.size() == 4);
	}
	
	@Test
	void testContains() {
		
		LinkedEquivalenceClass<Integer> list = listBuilder(7, 14, 21, 28, c);
		
		//Testing if the canonical element is in the list AND if the compared elements are contained in the list
		assertTrue(list.contains(7));
		assertTrue(list.contains(14));
		assertTrue(list.contains(28));
		
		//Testing if an element that is not equivalent with the canonical does not exist in the list
		assertFalse(list.contains(-7));
		assertFalse(list.contains(null));
		assertFalse(list.contains(0));
	}
	
	@Test
	void testRemove() {
		LinkedEquivalenceClass<Integer> l = listBuilder(7, 14, 21, 28, c);
		
		l.remove(14);
		l.remove(21);
		
		assertTrue(l.size() == 2);
		
		l.remove(7);
		
		//Canonical is not removed
		assertTrue(l.size() == 2);
	}
	
	@Test
	void testRemoveCanonical() {
		LinkedEquivalenceClass<Integer> l = listBuilder(7, 14, 21, 28,c);
		
		//Shouldnt remove non canonical 
		assertFalse(l.removeCanonical(14));
		assertFalse(l.removeCanonical(28));
		
		//Remove canonical
		assertTrue(l.removeCanonical(7));
		
		//Make sure canonical is null
		assertEquals(null, l.canonical());
	}
	
	@Test
	void testBelongs() {
		LinkedEquivalenceClass<Integer> l = listBuilder(7, 14, 21, 28,c);
		 
		assertTrue(l.belongs(7));
		assertTrue(l.belongs(14));
		
		//Make sure these elements dont belong
		assertFalse(l.belongs(1));
		assertFalse(l.belongs(-13));
		assertFalse(l.belongs(100));
		assertFalse(l.belongs(null));
	}
	
	@Test
	void demoteAndSetCanonical() {

		LinkedEquivalenceClass<Integer> list = listBuilder(7, 14, 21, 28,c);
		
		assertEquals("7 | 28 21 14", list.toString());
	
		//Will not change canonical
		list.demoteAndSetCanonical(-1);
		assertEquals("7 | 28 21 14",list.toString());
		
		//Wont change canonical
		list.demoteAndSetCanonical(5);
		assertEquals("7 | 28 21 14",list.toString());
		
		//Make 49 the new canonical
		list.demoteAndSetCanonical(49);
		assertEquals("49 | 28 21 14",list.toString());
	}
}