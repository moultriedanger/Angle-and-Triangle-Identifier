package geometry_objects.points;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

/*
* Tests the pointDatabase
*
* @author Jalen Livingston, Moultrie DangerField, and Jack Patterson
* @date 11/1/2023
* 
*/

class TestPointDatabase extends PointDatabase {

	 public PointDatabase build()
	 {
			List<Point> ptList=new ArrayList<Point>();
			Point pt0=new Point("A", 0,0);
			Point pt1=new Point(1,0);
			Point pt2=new Point("B", 78.237828,4);
			Point pt3=new Point(89,-28.7897);
			Point pt4=new Point(Math.sqrt(2),-3);
			Point pt5=new Point("C", -51.5432,15.0000);
			ptList.add(pt0);
			ptList.add(pt1);
			ptList.add(pt2);
			ptList.add(pt3);
			ptList.add(pt4);
			ptList.add(pt5);
			
	    	return new PointDatabase(ptList);
	}
	
	@Test
	void testGetPoints() {
		//Using created PointDatabase build with content already inside
		PointDatabase pd = build();
		
		Point pt0 = new Point("A", 0,0);
		Point pt1 = new Point(1,0);
		Point pt2 = new Point("B", 78.237828,4);
		Point pt3 = new Point(89,-28.7897);
		Point pt4 = new Point(Math.sqrt(2),-3);
		Point pt5 = new Point("C", -51.5432,15.0000);
		
		assertEquals(6, pd.size());
	
		Set<Point> ptSet = pd.getPoints();
		
		//The following tests below check if all points were distributed to ptSet from getPoints()
		assertTrue("Point is not containded in the pointDatabase", ptSet.contains(pt0));
		assertTrue("Point is not containded in the pointDatabase", ptSet.contains(pt1));
		assertTrue("Point is not containded in the pointDatabase", ptSet.contains(pt2));
		assertTrue("Point is not containded in the pointDatabase", ptSet.contains(pt3));
		assertTrue("Point is not containded in the pointDatabase", ptSet.contains(pt4));
		assertTrue("Point is not containded in the pointDatabase", ptSet.contains(pt5));
	
	}

	@Test
	void testSize() {
		PointDatabase pd = build();
		
		assertEquals("size is not accurate", 6, pd.size());
		
	}
	@Test
	void testPut() {
		PointDatabase pd = new PointDatabase();
		
		pd.put("A", 0,0);
		pd.put("B", 1,0);
		pd.put("*_A", 51, 51);
		pd.put("C", 78.237828,4);
		pd.put("D", 89,-28.7897);
		pd.put("*_B", Math.sqrt(2),-3);
		
		assertEquals ("size is not accurate", 6, pd.size());

	}

	@Test
	void testGetNamePoint() {
		PointDatabase pd = build();
		
		Point pt0 = new Point(Math.sqrt(2),-3);
		Point pt1 = new Point("C", -51.5432,15.0000);
		
		pd.getName(pt0);
		pd.getName(pt1);
		
		assertEquals("Name is not accurate", "*_C", pd.getName(pt0));
		assertEquals("Name is not accurate", "C", pd.getName(pt1));
	}

	@Test
	void testGetPointString() {
		PointDatabase pd = build();
		
		Point pt0 = new Point("A", 0,0);
		Point pt1 = new Point(1,0);
		Point pt2 = new Point("B", 78.237828,4);
		Point pt3 = new Point(89,-28.7897);
		Point pt4 = new Point(Math.sqrt(2),-3);
		Point pt5 = new Point("C", -51.5432,15.0000);
		assertEquals ("Name did not get point", pt0, pd.getPoint("A"));
		assertEquals ("Name did not get point", pt1, pd.getPoint("*_A"));
		assertEquals ("Name did not get point", pt2, pd.getPoint("B"));
		assertEquals ("Name did not get point", pt3, pd.getPoint("*_B"));
		assertEquals ("Name did not get point", pt4, pd.getPoint("*_C"));
		assertEquals ("Name did not get point", pt5, pd.getPoint("C"));
	}

	@Test
	void testGetPointPoint() {
		PointDatabase pd = build();
		
		Point pt0=new Point("A", 0,0);
		Point pt1=new Point(1,0);
		Point pt2=new Point("B", 78.237828,4);
		Point pt3=new Point(89,-28.7897);
		Point pt4=new Point(Math.sqrt(2),-3);
		Point pt5=new Point("C", -51.5432,15.0000);
		
		assertEquals ("Point did not get point", pt0, pd.getPoint(pt0));
		assertEquals ("Point did not get point", pt1, pd.getPoint(pt1));
		assertEquals ("Point did not get point", pt2, pd.getPoint(pt2));
		assertEquals ("Point did not get point", pt3, pd.getPoint(pt3));
		assertEquals ("Point did not get point", pt4, pd.getPoint(pt4));
		assertEquals ("Point did not get point", pt5, pd.getPoint(pt5));
	}

	@Test
	void testGetPointDoubleDouble() {
		PointDatabase pd = build();
		
		Point pt0=new Point("A", 0,0);
		Point pt1=new Point(1,0);
		Point pt2=new Point("B", 78.237828,4);
		Point pt3=new Point(89,-28.7897);
		Point pt4=new Point(Math.sqrt(2),-3);
		Point pt5=new Point("C", -51.5432,15.0000);
		
		assertEquals ("Doubles did not get point", pt0, pd.getPoint(0,0));
		assertEquals ("Doubles did not get point", pt1, pd.getPoint(1,0));
		assertEquals ("Doubles did not get point", pt2, pd.getPoint(78.237828,4));
		assertEquals ("Doubles did not get point", pt3, pd.getPoint(89,-28.7897));
		assertEquals ("Doubles did not get point", pt4, pd.getPoint(Math.sqrt(2),-3));
		assertEquals ("Doubles did not get point", pt5, pd.getPoint(-51.5432,15.0000));
	}

}
