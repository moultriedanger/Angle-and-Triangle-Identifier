package geometry_objects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import geometry_objects.*;
import geometry_objects.points.Point;
import geometry_objects.angle.Angle;
import exceptions.FactException;

class AngleTest {

	@Test
	void testEqualsObject(){
		
		Point p1_end1 = new Point("A",5,5);
		Point p1_vertex = new Point("B",0,0);
		Point p1_end2 = new Point("C",5,0);
				
		Point p2_end1 = new Point("D",5,5);
		Point p2_vertex = new Point("E",0,0);
		Point p2_end2 = new Point("F",5,0);
		
		Point p3_end1 = new Point("D",7,7);
		Point p3_vertex = new Point("E",0,0);
		Point p3_end2 = new Point("F",7,0);
		
		Segment a1_s1 = new Segment(p1_vertex,p1_end1);
		Segment a1_s2 = new Segment(p1_vertex,p1_end2);
		
		Segment a2_s1 = new Segment(p2_vertex,p2_end1);
		Segment a2_s2 = new Segment(p2_vertex,p2_end2);
		
		Segment a3_s1 = new Segment(p3_vertex,p3_end1);
		Segment a3_s2 = new Segment(p3_vertex,p3_end2);
		
		
		//Angle creating error shouldnt occur
		Angle a1 = null;
		try {
			a1 = new Angle(a1_s1, a1_s2);
		} catch (FactException e) {
	
			System.out.println("A1 error");
		}
		
		Angle a2 = null;
		try {
			a2 = new Angle(a2_s1, a2_s2);
		} catch (FactException e) {
		
			System.out.println("A2 error");
		}
		
		Angle a3 = null;
		try {
			a3 = new Angle(a3_s1, a3_s2);
		} catch (FactException e) {
		
			System.out.println("A3 error");
		}
		
		assertTrue(a1.equals(a1));
		assertTrue(a1.equals(a2));
		
		//A3 overlaps 
		assertFalse(a1.equals(a3));
		assertFalse(a3.equals(a1));
		assertFalse(a2.equals(a3));
		
	}

}
