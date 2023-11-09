package utilities;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Comparator;

import org.junit.jupiter.api.Test;

class EquivalenceClassesTest {
	
	Comparator<Integer> c = new Comparator<Integer>()
	 {
	 //Checks divisible by 7
	public int compare(Integer x, Integer y)
	 { return x % 7 == y % 7 ? 0 : 1; }
	 };
	 
	 Comparator<Integer> c1 = new Comparator<Integer>(){
	//Checks if less than 5
	public int compare(Integer x, Integer y)
	 { return x <=5 == y<= 5 ? 0 : 1; }
	 };
	 
	 Comparator<Integer> c2 = new Comparator<Integer>(){
	public int compare(Integer x, Integer y)
	 { return x % 37 == y % 37 ? 0 : 1; }
	 };
	
	@Test
	void addTest() {
		
		EquivalenceClasses<Integer> lessTwo = new EquivalenceClasses<Integer>(c1);
				
		lessTwo.add(2);
		lessTwo.add(1);		
		lessTwo.add(-5);
		lessTwo.add(-100);
		
		lessTwo.add(7);
		lessTwo.add(11);
		lessTwo.add(13);
		lessTwo.add(15);
		
		//Two classes are created
		assertEquals(2,lessTwo.numClasses());
		
		assertTrue(lessTwo.contains(1));
		assertTrue(lessTwo.contains(-100));
		assertTrue(lessTwo.contains(7));
		assertTrue(lessTwo.contains(15));
		
		assertEquals(8, lessTwo.size());
		
		//Divide Seven Class
		EquivalenceClasses divideSeven = new EquivalenceClasses(c);
		
		//Remainder 0
		divideSeven.add(7);
		divideSeven.add(14);
		divideSeven.add(21);
		divideSeven.add(28);
		
		//Remainder 1 list
		divideSeven.add(1);
		divideSeven.add(8);
		divideSeven.add(15);
		divideSeven.add(22);
		
		//Remainder 5 list
		divideSeven.add(33);
		divideSeven.add(40);
		divideSeven.add(47);
		divideSeven.add(103);
		
		//Three separate classes added
		assertEquals(3, divideSeven.numClasses());
		
		assertEquals(12, divideSeven.size());
		
		assertTrue(divideSeven.contains(14));
		assertTrue(divideSeven.contains(22));
		assertTrue(divideSeven.contains(103));
		
		EquivalenceClasses modWeird = new EquivalenceClasses(c2);
		
		//R = 1
		//Canonical doesnt return true
		modWeird.add(260);
		modWeird.add(297);
		modWeird.add(334);
		modWeird.add(371);
		
		//R = 13
		modWeird.add(124);
		modWeird.add(161);
		modWeird.add(198);
		modWeird.add(235);
		
		//R= 22
		
		modWeird.add(725);
		modWeird.add(22);
		modWeird.add(59);
		modWeird.add(1391);
		
		assertEquals(3, modWeird.numClasses());
		
		assertEquals(12, modWeird.size());
	}
	
	@Test
	void contains() {
		EquivalenceClasses divideSeven = new EquivalenceClasses(c);
		
		//Remainder 0
		divideSeven.add(7);
		divideSeven.add(14);
		divideSeven.add(21);
		divideSeven.add(28);
		
		//Remainder 1 list
		divideSeven.add(1);
		divideSeven.add(8);
		divideSeven.add(15);
		divideSeven.add(22);
		
		//Remainder 5 list
		divideSeven.add(33);
		divideSeven.add(40);
		divideSeven.add(47);
		divideSeven.add(103);
		

		assertTrue(divideSeven.contains(103));
		assertTrue(divideSeven.contains(22));
		assertTrue(divideSeven.contains(28));
		
		assertFalse(divideSeven.contains(0));
		assertFalse(divideSeven.contains(104));
		assertFalse(divideSeven.contains(1000));
		assertFalse(divideSeven.contains(null));
	}
	
	@Test 
	void numClassTest(){
		EquivalenceClasses divideSeven = new EquivalenceClasses(c);
		
		assertEquals(0, divideSeven.numClasses());
		
		//Remainder 1 list
		divideSeven.add(1);
		divideSeven.add(8);
		divideSeven.add(15);
		divideSeven.add(22);
		
		//Remainder 5 list
		divideSeven.add(33);
		divideSeven.add(40);
		divideSeven.add(47);
		divideSeven.add(103);
		
		//Remainder 0
		divideSeven.add(7);
		divideSeven.add(14);
		divideSeven.add(21);
		divideSeven.add(28);
		
		assertEquals(3, divideSeven.numClasses());
	}
	
	@Test
	void sizeTest() {
		EquivalenceClasses divideSeven = new EquivalenceClasses(c);
		
		assertEquals(0,divideSeven.size());
		
		//Add canonical
		divideSeven.add(7);
	
		assertEquals(1, divideSeven.size());
		
		divideSeven.add(14);
		divideSeven.add(21);
		divideSeven.add(28);
		
		//Remainder 1 list
		divideSeven.add(1);
		divideSeven.add(8);
		divideSeven.add(15);
		divideSeven.add(22);
		
		assertEquals(8, divideSeven.size());
	}
}
