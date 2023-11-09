package geometry_objects.points;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PointTest {

	@Test
	void testCompareTo() {
		
		//P1 less than p2
		Point p1 = new Point(0,1);
		Point p2 = new Point(0,2);
		
		assertEquals("p1 is bigger than p2", -1,p1.compareTo(p2));
		
		Point p3 = new Point(0,1);
		Point p4 = new Point(1,2);
		
		assertEquals("p3 is bigger than p4", -1,p3.compareTo(p4));
		
		//Same x, lesser y
		Point p5 = new Point(1,1);
		Point p6 = new Point(1,2);
		
		assertEquals("p5 is bigger than p6", -1,p5.compareTo(p6));
		
		
		//Same x, greater y
		Point p7 = new Point(0,2);
		Point p8 = new Point(0,1);
		
		assertEquals("p7 is smaller than p8", 1,p7.compareTo(p8));
		
		
		//Equal
		Point p9 = new Point(0,2);
		Point p10 = new Point(0,2);
		
		assertEquals("p9 is not equal to p10", 0,p9.compareTo(p10));
	}

	@Test
	void testEqualsObject() {
		Point p1 = new Point(0,0);
		Point p2 = new Point(0,0);
		
		assertTrue(p1.equals(p2));
		
		//Test different point names
		Point p3 = new Point("point",1,1);
		Point p4 = new Point("point26",1,1);
		
		assertTrue("These points are not equivalent in position", p3.equals(p4));
		
		assertFalse("These points are equivalent in position" ,p1.equals(p3));
	}

}
