package geometry_objects;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import geometry_objects.points.Point;

import org.junit.jupiter.api.Test;

class SegmentTest {

	@Test
	void testHasSubSegment() {
		Point A = new Point(0,0);
		Point B = new Point(0,5);
		Point C = new Point(0,3);
//		Point D = new Point(1,8);
		
//		Point p5 = new Point(0,0);
//		Point p6 = new Point(0,1);
		
		Segment AB = new Segment(A,B);
		Segment AC = new Segment(A,C);
		Segment CB = new Segment(C,B);
		
//		Segment s2 = new Segment(p5,p6);
		
		assertTrue(AB.HasSubSegment(AC));
		assertTrue(AB.HasSubSegment(CB));
		//assertTrue()
	}

	@Test
	void testCoincideWithoutOverlap() {
		Point A = new Point(0,0);
		Point B = new Point(0,5);
		Point C = new Point(0,3);
		Point D = new Point(1,1);
		Point E = new Point(1,5);
		Point F = new Point(0,10);
		
		
		Segment AB = new Segment(A,B);
		Segment AC = new Segment(A,C);
		Segment CB = new Segment(C,B);
		Segment DE = new Segment(D,E);
		Segment AD = new Segment(A,D);
		Segment BF = new Segment(B,F);
		
		assertTrue(AB.coincideWithoutOverlap(DE));
		assertTrue(AB.coincideWithoutOverlap(AD));
		assertTrue(AB.coincideWithoutOverlap(BF));
		
		assertFalse(AB.coincideWithoutOverlap(AC));
	}

	@Test
	void testCollectOrderedPointsOnSegment() {
		Point A = new Point(0,0);
		Point B = new Point(0,5);
		Point C = new Point(0,3);
		Point D = new Point(1,1);
		Point E = new Point(1,5);
		Point F = new Point(0,10);
		
		
		Set<Point> pointSet = new HashSet<Point>();
		pointSet.add(A);
		pointSet.add(B);
		pointSet.add(C);
		//pointSet.add(F);
		
		Segment s = new Segment(A,C);
		
		
		System.out.println(s.toString()); //collectOrderedPointsOnSegment(pointSet).
	}

}
