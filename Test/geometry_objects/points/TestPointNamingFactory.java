package geometry_objects.points;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

/*
* Tests the PointNamingFactory
*
* @author Jalen Livingston, Moultrie DangerField, and Jack Patterson
* @date 10/29/2023
*/
class TestPointNamingFactory extends PointNamingFactory{

	
	 public PointNamingFactory build()
	 {
		 	//builds a list a PointNamingFactory
			List<Point> ptList=new ArrayList<Point>();
			Point pt0=new Point("A", 0,0);
			Point pt1=new Point(1,0);
			Point pt2=new Point("B", 78.237828,4);
			Point pt3=new Point(89,-28.7897);
			Point pt4=new Point(Math.sqrt(2),-3);
			
			ptList.add(pt0);
			ptList.add(pt1);
			ptList.add(pt2);
			ptList.add(pt3);
			ptList.add(pt4);
			
	    	return new PointNamingFactory(ptList);
	  }
	
	@Test
	void testPointNamingFactoryListOfPoint() {
		PointNamingFactory pnf=build();
		
		Point pt0=new Point("A", 0,0);
		Point pt1=new Point(1,0);
		Point pt2=new Point("B", 78.237828,4);
		Point pt3=new Point(89,-28.7897);
		Point pt4=new Point(Math.sqrt(2),-3);
		Point ptnot=new Point(-76,9);
		
		assertTrue ("Point is not contained in the pnf", pnf.contains(pt0));
		assertTrue ("Point is not contained in the pnf", pnf.contains(pt1));
		assertTrue ("Point is not contained in the pnf", pnf.contains(pt2));
		assertTrue ("Point is not contained in the pnf", pnf.contains(pt3));
		assertTrue ("Point is not contained in the pnf", pnf.contains(pt4));
		
		assertEquals ("Size is not accurate for PointNamingFactoryListOfPoint", 5, pnf.size());
		
		assertTrue ("Point is not contained in the pnf", pnf.contains(0, 0));
		assertTrue ("Point is not contained in the pnf", pnf.contains(Math.sqrt(2), -3));
		assertFalse ("Point is not contained in the pnf", pnf.contains(0, 1));
		
		assertEquals ("Name is not accurately implemented", "A", pnf.get(pt0).getName());
		assertEquals ("Name is not accurately implemented", "*_A", pnf.get(pt1).getName());
		assertEquals ("Name is not accurately implemented", "B", pnf.get(pt2).getName());
		assertEquals ("Name is not accurately implemented", "*_B", pnf.get(pt3).getName());
		assertEquals ("Name is not accurately implemented", "*_C", pnf.get(pt4).getName());
		
		assertFalse ("It is just always saying true for PointNamingFactoryListOfPoint", pnf.contains(ptnot));
	}

	@Test
	void testPutPoint() {
		PointNamingFactory pnf=new PointNamingFactory();
		Point pt0=new Point("A", 0,0);
		Point pt1=new Point(1,0);
		Point pt2=new Point("B", 78.237828,4);
		Point pt3=new Point(89,-28.7897);
		Point pt4=new Point(Math.sqrt(2),-3);
		Point ptnot=new Point(-76,9);
		pnf.put(pt0);
		pnf.put(pt1);
		pnf.put(pt2);
		pnf.put(pt3);
		pnf.put(pt4);
		
		assertTrue ("Put: Point is not contained in the pnf", pnf.contains(pt0));
		assertTrue ("Put: Point is not contained in the pnf", pnf.contains(pt1));
		assertTrue ("Put: Point is not contained in the pnf", pnf.contains(pt2));
		assertTrue ("Put: Point is not contained in the pnf", pnf.contains(pt3));
		assertTrue ("Put: Point is not contained in the pnf", pnf.contains(pt4));
		
		assertEquals ("Put: size is not accurate", 5, pnf.size());
		assertTrue ("Put: Point is not contained in the pnf", pnf.contains(0, 0));
		assertTrue ("Put: Point is not contained in the pnf", pnf.contains(Math.sqrt(2), -3));
		assertFalse ("Put: is always returning true", pnf.contains(0, 1));
		
		assertFalse ("Put: is always returning true",pnf.contains(ptnot));
	}

	@Test
	void testPutDoubleDouble() {
		PointNamingFactory pnf=new PointNamingFactory();
		pnf.put("C", 0, 0);
		pnf.put(Math.sqrt(2), -98);
		pnf.put("A",-6, -7);
		pnf.put(67, 8);
		
		
		assertTrue ("Put Double: Point is not contained in the pnf", pnf.contains(0, 0));
		assertTrue ("Put Double: Point is not contained in the pnf", pnf.contains(Math.sqrt(2),-98));
		assertTrue ("Put Double: Point is not contained in the pnf", pnf.contains(-6, -7));
		assertTrue ("Put Double: Point is not contained in the pnf", pnf.contains(67,8));
		
		assertEquals ("Put Double: Name is not accurate", "C", pnf.get(0, 0).getName());
		assertEquals ("Put Double: Name is not accurate", "*_A", pnf.get(Math.sqrt(2),-98).getName());
		assertEquals ("Put Double: Name is not accurate", "A", pnf.get(-6,-7).getName());
		assertEquals ("Put Double: Name is not accurate", "*_B", pnf.get(67, 8).getName());
		assertEquals ("Put Double: size is not accurate", 4, pnf.size());
		
		assertFalse ("Put Double: is always returning true", pnf.contains(5,5));
		assertFalse ("Put Double: is always returning true", pnf.contains(0, 1));
		
	}

	@Test
	void testGetDoubleDouble() {
		PointNamingFactory pnf=build();
		
		Point pt0=new Point("A", 0,0);
		Point pt1=new Point(1,0);
		Point pt2=new Point("B", 78.237828,4);
		Point pt3=new Point(89,-28.7897);
		Point pt4=new Point(Math.sqrt(2),-3);
		
		assertEquals ("GetDoubleDoble: is not working", pt0, pnf.get(0, 0));
		assertEquals ("GetDoubleDoble: is not working", pt1, pnf.get(1, 0));
		assertEquals ("GetDoubleDoble: is not working", pt2, pnf.get(78.237828,4));
		assertEquals ("GetDoubleDoble: is not working", pt3, pnf.get(89,-28.7897));
		assertEquals ("GetDoubleDoble: is not working", pt4, pnf.get(Math.sqrt(2),-3));
	}

	@Test
	void testGetPoint() {
		PointNamingFactory pnf=build();
		
		Point pt0=new Point("A", 0,0);
		Point pt1=new Point(1,0);
		Point pt2=new Point("B", 78.237828,4);
		Point pt3=new Point(89,-28.7897);
		Point pt4=new Point(Math.sqrt(2),-3);
		Point ptnot=new Point(-76,9);
		
		assertEquals ("GetPoint: it's not working", pt0, pnf.get(pt0));
		assertEquals ("GetPoint: it's not working", pt1, pnf.get(pt1));
		assertEquals ("GetPoint: it's not working", pt2, pnf.get(pt2));
		assertEquals ("GetPoint: it's not working", pt3, pnf.get(pt3));
		assertEquals ("GetPoint: it's not working", pt4, pnf.get(pt4));
	}

	@Test
	void testContainsDoubleDouble() {
		PointNamingFactory pnf=build();
		
		assertTrue ("ContainDoubleDouble: it's not working", pnf.contains(0, 0));
		assertTrue ("ContainDoubleDouble: it's not working", pnf.contains(1, 0));
		assertTrue ("ContainDoubleDouble: it's not working", pnf.contains(78.237828,4));
		assertTrue ("ContainDoubleDouble: it's not working", pnf.contains(89,-28.7897));
		assertTrue ("ContainDoubleDouble: it's not working", pnf.contains(Math.sqrt(2),-3));
		
		assertFalse("ContainDoubleDouble: is always not working", pnf.contains(-2,-5));
		
	}

	@Test
	void testContainsPoint() {
		PointNamingFactory pnf=build();
		
		Point pt0=new Point("A", 0,0);
		Point pt1=new Point(1,0);
		Point pt2=new Point("B", 78.237828,4);
		Point pt3=new Point(89,-28.7897);
		Point pt4=new Point(Math.sqrt(2),-3);
		Point ptnot=new Point(-76,9);
		Point ptnot2=new Point("F", 9.89,89.872);
		
		assertTrue ("ContainPoint: is not working", pnf.contains(pt0));
		assertTrue ("ContainPoint: is not working", pnf.contains(pt1));
		assertTrue ("ContainPoint: is not working", pnf.contains(pt2));
		assertTrue ("ContainPoint: is not working", pnf.contains(pt3));
		assertTrue ("ContainPoint: is not working", pnf.contains(pt4));
		
		assertFalse("ContainPoint: is always working", pnf.contains(ptnot));
		assertFalse("ContainPoint: is always working", pnf.contains(ptnot2));
	}

	@Test
	void testGetAllPoints() {
		PointNamingFactory pnf=build();
		
		Point pt0=new Point("A", 0,0);
		Point pt1=new Point(1,0);
		Point pt2=new Point("B", 78.237828,4);
		Point pt3=new Point(89,-28.7897);
		Point pt4=new Point(Math.sqrt(2),-3);
		Point ptnot=new Point(-76,9);
		Point ptnot2=new Point("F", 9.89,89.872);
		
		assertEquals (5, pnf.size());
		
		Set<Point> ptSet=pnf.getAllPoints();
		
		assertEquals("GetAllPoints: size is not accurate", pnf.size(), ptSet.size());
		assertTrue("GetAllPoints: doesn't work", ptSet.contains(pt0));
		assertTrue("GetAllPoints: doesn't work", ptSet.contains(pt1));
		assertTrue("GetAllPoints: doesn't work", ptSet.contains(pt2));
		assertTrue("GetAllPoints: doesn't work", ptSet.contains(pt3));
		assertTrue("GetAllPoints: doesn't work", ptSet.contains(pt4));
		
		assertFalse("GetAllPoints: always works", ptSet.contains(ptnot));
		assertFalse("GetAllPoints: always works",ptSet.contains(ptnot2));
		
		
	}

	@Test
	void testClear() {
		PointNamingFactory pnf=build();
		
		Point pt0=new Point("A", 0,0);
		
		assertEquals ("Clear: size doesn't work", 5, pnf.size());
		pnf.clear();
		assertEquals("Clear: clear doesn't work", 0, pnf.size());
		pnf.clear();
		assertEquals("Clear: double clear doesn't work", 0, pnf.size());
		pnf.put(pt0);
		pnf.put(2, 0);
		assertEquals("Clear: put after clear doesn't work", 2, pnf.size());
		
	}

	@Test
	void testSize() {
		PointNamingFactory pnf=new PointNamingFactory();
		Point pt0=new Point("A", 0,0);
		Point pt1=new Point(1,0);
		Point pt2=new Point("B", 78.237828,4);
		Point pt3=new Point(89,-28.7897);
		Point pt4=new Point(Math.sqrt(2),-3);
		
		pnf.put(pt0);
		assertEquals("Size: doesn't work", 1, pnf.size());
		
		pnf.put(pt1);
		assertEquals("Size: doesn't work", 2, pnf.size());
		
		pnf.put(pt2);
		assertEquals("Size: doesn't work", 3, pnf.size());
		
		pnf.put(pt3);
		assertEquals("Size: doesn't work", 4, pnf.size());
		
		pnf.put(pt4);
		assertEquals("Size: doesn't work", 5, pnf.size());
		
		pnf.put(pt1);
		pnf.put(pt0);
		assertEquals("Size: doesn't work", 5, pnf.size());
		
		pnf.put("D", 78, 9);
		pnf.put(78, 9);
		assertEquals("Size: doesn't work", 6, pnf.size());
		
		pnf.clear();
		assertEquals("Size: doesn't work", 0, pnf.size());
		
		pnf.put(65,7);
		assertEquals("Size: doesn't work", 1, pnf.size());
		
		
	}

	@Test
	void testToString() {
		PointNamingFactory pnf=new PointNamingFactory();
		Point pt0=new Point("A", 0,0);
		Point pt1=new Point(583,-3);
		Point pt2=new Point(-76.3542,9);
		Point pt3=new Point("C",-48.51,-9.637);
		
		pnf.put(pt0);
		pnf.put(pt1);
		pnf.put(pt2);
		pnf.put(pt3);
		
		assertEquals ("toString: doesn't work", "A(0.0, 0.0) *_A(583.0, -3.0) *_B(-76.3542, 9.0) C(-48.51, -9.637) ", pnf.toString());

	}

}
