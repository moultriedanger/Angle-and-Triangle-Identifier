package geometry_objects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.FactException;
import geometry_objects.angle.Angle;
import geometry_objects.angle.AngleEquivalenceClasses;
import geometry_objects.angle.AngleLinkedEquivalenceClass;
import geometry_objects.angle.comparators.AngleStructureComparator;
import geometry_objects.points.Point;

class AngleEquivalenceClassesTest {

	
	@Test
	void testAdd() {
		
		AngleEquivalenceClasses classList = new AngleEquivalenceClasses(new AngleStructureComparator());
		
		//Simple test
		Point a = new Point("A", 0, 6);
		Point b = new Point("B", 3, 6);
		Point c = new Point("C", 4, 6);
		Point d = new Point("D", 6, 6);
		Point e = new Point("E", 1, 5);
		Point f = new Point("F", 2, 4);
		Point g = new Point("G", 0, 4);
		
		//BAE
		Segment ae = new Segment(a,e);
		Segment ab = new Segment(a,b);
		Segment af = new Segment(a,f);
		Segment ac = new Segment(a,c);
		Segment ad = new Segment(a,d);
		Segment ag = new Segment(a,g);
		
		
		
		
		//Make all the angles
		Angle bae = null;
		try {
			bae = new Angle(ae, ab);
		} catch (FactException l) {
	
			System.out.println("bae error");
		}
		
		Angle baf = null;
		try {
			baf = new Angle(af, ab);
		} catch (FactException l) {
	
			System.out.println("baf error");
		}
		
		Angle cae = null;
		try {
			cae = new Angle(ae, ac);
		} catch (FactException l) {
	
			System.out.println("cae error");
		}
		
		Angle dae = null;
		try {
			dae = new Angle(ae, ad);
		} catch (FactException l) {
	
			System.out.println("dae error");
		}
		
		Angle caf = null;
		try {
			caf = new Angle(af, ac);
		} catch (FactException l) {
	
			System.out.println("caf error");
		}
		
		Angle daf = null;
		try {
			daf = new Angle(ad, af);
		} catch (FactException l) {
	
			System.out.println("daf error");
		}
	
		Angle gab = null;
		try {
			gab = new Angle(ag, ab);
		} catch (FactException l) {
	
			System.out.println("daf error");
		}
		
		//GAB
		Angle gad = null;
		try {
			gad = new Angle(ag, ad);
		} catch (FactException l) {
	
			System.out.println("daf error");
		}
				
		//This should be the new canonical
		classList.add(bae);
		classList.add(daf);
		classList.add(baf);
		
		classList.add(gab);
		classList.add(gad);

		assertEquals(2, classList.numClasses());
		assertEquals(5,classList.size());
		
//		System.out.println(classList.toString());
		
//		cl.add(dae);
//		cl.add(caf);
//		cl.add(daf);
//		assertEquals (6, cl.size());
//		assertTrue (cl.contains(daf));
//		System.out.println(cl.toString());
//		
//		cl.add(gab);
//		assertEquals (6, cl.size());
	}
	
	
//	@Test
//	void testSize() {
//		
//	}

//	@Test
//	void testNumClasses() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testToString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testAngleEquivalenceClasses() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testAddAngle() {
//		fail("Not yet implemented");
//	}
//
	@Test
	void testContainsAngle() {
		/**
		 * D
		 * |
		 * |
		 * |
		 * B  F
		 * | /
		 * |/
		 * A---C-E
		 * 
		 */
		AngleEquivalenceClasses equivClass = new AngleEquivalenceClasses(new AngleStructureComparator());

		Point A=new Point("A", 0, 0);
		Point B=new Point("B", 0, 2);
		Point C=new Point("C", 3, 0);
		Point D=new Point("D", 0, 5);
		Point E=new Point("E", 4, 0);
		Point F=new Point("F", 2, 2);
		
		Segment AB=new Segment(A, B);
		Segment AC=new Segment(A, C);
		Segment AE=new Segment(A, E);
		Segment AD=new Segment(A, D);
		Segment AF=new Segment(A, F);
		
		//necessary for straight angles
		Segment BD=new Segment(B, D);
		Segment CE=new Segment(C, E);
		
		Angle BAC = null;
		try {
			new Angle(AB, AC);
		} catch (FactException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Angle DAC = null;
		try {
			new Angle(AD, AC);
		} catch (FactException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Angle BAE = null;
		try {
			new Angle(AB, AE);
		} catch (FactException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Angle DAE = null;
		try {
			new Angle(AD, AE);
		} catch (FactException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Angle CAF = null;
		try {
			new Angle(AC, AF);
		} catch (FactException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Angle DAF = null;
		try {
			new Angle(AD, AF);
		} catch (FactException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		equivClass.add(BAC);
		equivClass.add(DAC);
		equivClass.add(BAE);
		equivClass.add(DAE);
		equivClass.add(CAF);
		equivClass.add(DAF);
		
		System.out.println(equivClass.toString());
		System.out.println(equivClass.size());
		
		assertTrue(equivClass.contains(BAC));
		assertTrue(equivClass.contains(DAC));
		assertTrue(equivClass.contains(BAE));
		assertTrue(equivClass.contains(DAE));
		assertTrue(equivClass.contains(CAF));
		assertTrue(equivClass.contains(DAF));
		
		
		
		
		//reverse rays
		try {
			Angle CAB=new Angle(AC, AB);
		} catch (FactException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Angle CAD=new Angle(AC, AD);
		} catch (FactException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Angle EAB=new Angle(AE, AB);
		} catch (FactException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Angle EAD=new Angle(AE, AD);
		} catch (FactException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Angle FAC=new Angle(AF, AC);
		} catch (FactException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Angle FAD=new Angle(AF, AD);
		} catch (FactException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
