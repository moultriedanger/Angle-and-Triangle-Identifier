package geometry_objects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import geometry_objects.*;
import geometry_objects.points.Point;
import geometry_objects.angle.Angle;
import geometry_objects.angle.AngleEquivalenceClasses;
import geometry_objects.angle.AngleLinkedEquivalenceClass;
import geometry_objects.angle.comparators.AngleStructureComparator;
import exceptions.FactException;

class AngleLinkedEquivalenceClassTest {
	
	public AngleLinkedEquivalenceClass build() {
		
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
		
		//BAF
		Segment af = new Segment(a,f);
		Segment ab_2 = new Segment(a,b);
		
		//CAE
		Segment ae_2 = new Segment(a,e);
		Segment ac = new Segment(a,c);
		
		//DAE
		Segment ae_3 = new Segment(a,e);
		Segment ad = new Segment(a,d);
		
		//CAF 
		Segment af_2 = new Segment(a,f);
		Segment ac_2 = new Segment(a,c);
		
		//DAF
		Segment ad_2 = new Segment(a,d);
		
		//GAB
		Segment ag = new Segment(a,g);
		
		//Add all the angles
		Angle bae = null;
		try {
			bae = new Angle(ae, ab);
		} catch (FactException l) {
	
			System.out.println("bae error");
		}
		
		Angle baf = null;
		try {
			baf = new Angle(af, ab_2);
		} catch (FactException l) {
	
			System.out.println("baf error");
		}
		
		Angle cae = null;
		try {
			cae = new Angle(ae_2, ac);
		} catch (FactException l) {
	
			System.out.println("cae error");
		}
		
		Angle dae = null;
		try {
			dae = new Angle(ae_3, ad);
		} catch (FactException l) {
	
			System.out.println("dae error");
		}
		
		Angle caf = null;
		try {
			caf = new Angle(af_2, ac_2);
		} catch (FactException l) {
	
			System.out.println("caf error");
		}
		
		Angle daf = null;
		try {
			daf = new Angle(ad_2, af);
		} catch (FactException l) {
	
			System.out.println("daf error");
		}
	
		Angle gab = null;
		try {
			gab = new Angle(ag, ab);
		} catch (FactException l) {
	
			System.out.println("daf error");
		}
	
		AngleLinkedEquivalenceClass cl = new AngleLinkedEquivalenceClass(new AngleStructureComparator());
		
		cl.add(bae);
		cl.add(baf);
		cl.add(cae);
		cl.add(dae);
		cl.add(caf);
		cl.add(daf);
		
		return cl;
	}

	/**
	 * Given the figure below:
	 * 
	 *    A-------B----C-----------D
	 *    |\
	 *    | \
	 *    |  \
	 *    |   E
	 *    |    \
	 *    |     \
	 *    G      F
	 * 
	 * Equivalence classes structure we want:
	 * 
	 *   canonical = BAE
	 *   rest = BAF, CAE, DAE, CAF, DAF
	 */
	
	@Test
	void testAdd(){
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
		
		//BAF
		Segment af = new Segment(a,f);
		Segment ab_2 = new Segment(a,b);
		
		//CAE
		Segment ae_2 = new Segment(a,e);
		Segment ac = new Segment(a,c);
		
		//DAE
		Segment ae_3 = new Segment(a,e);
		Segment ad = new Segment(a,d);
		
		//CAF 
		Segment af_2 = new Segment(a,f);
		Segment ac_2 = new Segment(a,c);
		
		//DAF
		Segment ad_2 = new Segment(a,d);
		
		//GAB
		Segment ag = new Segment(a,g);
		
		
		//Add all the angles
		Angle bae = null;
		
		try {
			bae = new Angle(ae, ab);
		} catch (FactException l) {
	
			System.out.println("bae error");
		}
		
		Angle baf = null;
		try {
			baf = new Angle(af, ab_2);
		} catch (FactException l) {
	
			System.out.println("baf error");
		}
		
		Angle cae = null;
		try {
			cae = new Angle(ae_2, ac);
		} catch (FactException l) {
	
			System.out.println("cae error");
		}
		
		Angle dae = null;
		try {
			dae = new Angle(ae_3, ad);
		} catch (FactException l) {
	
			System.out.println("dae error");
		}
		
		Angle caf = null;
		try {
			caf = new Angle(af_2, ac_2);
		} catch (FactException l) {
	
			System.out.println("caf error");
		}
		
		Angle daf = null;
		try {
			daf = new Angle(ad_2, af);
		} catch (FactException l) {
	
			System.out.println("daf error");
		}
	
		Angle gab = null;
		try {
			gab = new Angle(ag, ab);
		} catch (FactException l) {
	
			System.out.println("daf error");
		}
	
		AngleLinkedEquivalenceClass cl = new AngleLinkedEquivalenceClass(new AngleStructureComparator());

		cl.add(baf);
		cl.add(cae);
		
		assertEquals (2, cl.size());
		
		
		//This should be the new canonical
		cl.add(bae);
		assertEquals (3, cl.size());
		assertTrue(cl.contains(bae));
		
		cl.add(dae);
		cl.add(caf);
		cl.add(daf);
		
		assertEquals (6, cl.size());
		assertTrue (cl.contains(daf));
		
		cl.add(gab);
		assertEquals (6, cl.size());
	}

	@Test
	void testBelongs() {
		//Simple test
		Point a = new Point("A", 0, 6);
		Point b = new Point("B", 3, 6);
		Point c = new Point("C", 4, 6);
		Point d = new Point("D", 6, 6);
		Point e = new Point("E", 1, 5);
		Point f = new Point("F", 2, 4);
		
		//BAE
		Segment ae = new Segment(a,e);
		Segment ab = new Segment(a,b);
		
		//BAF
		Segment af = new Segment(a,f);
		Segment ab_2 = new Segment(a,b);
		
		//CAE
		Segment ae_2 = new Segment(a,e);
		Segment ac = new Segment(a,c);
		
		//DAE
		Segment ae_3 = new Segment(a,e);
		Segment ad = new Segment(a,d);
		
		//CAF 
		Segment af_2 = new Segment(a,f);
		Segment ac_2 = new Segment(a,c);
		
		//DAF
		Segment ad_2 = new Segment(a,d);
		Segment ac_3 = new Segment(a,c);
		
		
		//Add all the angles
		Angle bae = null;
		try {
			bae = new Angle(ae, ab);
		} catch (FactException l) {
	
			System.out.println("bae error");
		}
		
		Angle baf = null;
		try {
			baf = new Angle(af, ab_2);
		} catch (FactException l) {
	
			System.out.println("baf error");
		}
		
		Angle cae = null;
		try {
			cae = new Angle(ae_2, ac);
		} catch (FactException l) {
	
			System.out.println("cae error");
		}
		
		Angle dae = null;
		try {
			cae = new Angle(ae_3, ad);
		} catch (FactException l) {
	
			System.out.println("dae error");
		}
		
		Angle caf = null;
		try {
			caf = new Angle(af_2, ac_2);
		} catch (FactException l) {
	
			System.out.println("caf error");
		}
		
		
	}
	
	@Test
	void testRemove() {
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
		
		//BAF
		Segment af = new Segment(a,f);
		Segment ab_2 = new Segment(a,b);
		
		//CAE
		Segment ae_2 = new Segment(a,e);
		Segment ac = new Segment(a,c);
		
		//DAE
		Segment ae_3 = new Segment(a,e);
		Segment ad = new Segment(a,d);
		
		//CAF 
		Segment af_2 = new Segment(a,f);
		Segment ac_2 = new Segment(a,c);
		
		//DAF
		Segment ad_2 = new Segment(a,d);
		
		//GAB
		Segment ag = new Segment(a,g);
		
		//Add all the angles
		Angle bae = null;
		try {
			bae = new Angle(ae, ab);
		} catch (FactException l) {
	
			System.out.println("bae error");
		}
		
		Angle baf = null;
		try {
			baf = new Angle(af, ab_2);
		} catch (FactException l) {
	
			System.out.println("baf error");
		}
		
		Angle cae = null;
		try {
			cae = new Angle(ae_2, ac);
		} catch (FactException l) {
	
			System.out.println("cae error");
		}
		
		Angle dae = null;
		try {
			dae = new Angle(ae_3, ad);
		} catch (FactException l) {
	
			System.out.println("dae error");
		}
		
		Angle caf = null;
		try {
			caf = new Angle(af_2, ac_2);
		} catch (FactException l) {
	
			System.out.println("caf error");
		}
		
		Angle daf = null;
		try {
			daf = new Angle(ad_2, af);
		} catch (FactException l) {
	
			System.out.println("daf error");
		}
	
		Angle gab = null;
		try {
			gab = new Angle(ag, ab);
		} catch (FactException l) {
	
			System.out.println("daf error");
		}
	
		AngleLinkedEquivalenceClass cl = new AngleLinkedEquivalenceClass(new AngleStructureComparator());

		cl.add(bae);
		cl.add(baf);
		cl.add(cae);
		cl.add(dae);
		cl.add(caf);
		cl.add(daf);
		
		assertEquals(6, cl.size());
		
		cl.remove(baf);
		assertEquals(5, cl.size());
		assertFalse(cl.contains(baf));
	
		cl.remove(cae);
		cl.remove(dae);
		assertEquals(3, cl.size());
		
		//canonical not removed
		cl.remove(bae);
		assertEquals(3, cl.size());
	}
	

	@Test
	void testDemoteAndSetCanonical() {
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
		
		//BAF
		Segment af = new Segment(a,f);
		Segment ab_2 = new Segment(a,b);
		
		//CAE
		Segment ae_2 = new Segment(a,e);
		Segment ac = new Segment(a,c);
		
		//DAE
		Segment ae_3 = new Segment(a,e);
		Segment ad = new Segment(a,d);
		
		//CAF 
		Segment af_2 = new Segment(a,f);
		Segment ac_2 = new Segment(a,c);
		
		//DAF
		Segment ad_2 = new Segment(a,d);
		
		//GAB
		Segment ag = new Segment(a,g);
		
		//Add all the angles
		Angle bae = null;
		try {
			bae = new Angle(ae, ab);
		} catch (FactException l) {
	
			System.out.println("bae error");
		}
		
		Angle baf = null;
		try {
			baf = new Angle(af, ab_2);
		} catch (FactException l) {
	
			System.out.println("baf error");
		}
		
		Angle cae = null;
		try {
			cae = new Angle(ae_2, ac);
		} catch (FactException l) {
	
			System.out.println("cae error");
		}
		
		Angle dae = null;
		try {
			dae = new Angle(ae_3, ad);
		} catch (FactException l) {
	
			System.out.println("dae error");
		}
		
		Angle caf = null;
		try {
			caf = new Angle(af_2, ac_2);
		} catch (FactException l) {
	
			System.out.println("caf error");
		}
		
		Angle daf = null;
		try {
			daf = new Angle(ad_2, af);
		} catch (FactException l) {
	
			System.out.println("daf error");
		}
	
		Angle gab = null;
		try {
			gab = new Angle(ag, ab);
		} catch (FactException l) {
	
			System.out.println("daf error");
		}
	
		AngleLinkedEquivalenceClass cl = new AngleLinkedEquivalenceClass(new AngleStructureComparator());
		
			
		//cl.demoteAndSetCanonical(baf);
//		
//		cl.demoteAndSetCanonical(baf);
//		cl.demoteAndSetCanonical(bae);
		
		cl.add(baf);
		cl.demoteAndSetCanonical(bae);
//		
		//System.out.println(cl.canonical());
//		
		//cl.add(bae);
		
		//assertEquals(cae,cl.canonical());

		
		
//		
//		cl.add(dae);
//		cl.add(caf);
//		cl.add(daf);
		
		
		//cl.demoteAndSetCanonical(bae);
	}
//	
//	@Test
//	void testToString() {
//		fail("Not yet implemented");
//	}

}
