package geometry_objects;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.SortedSet;

import geometry_objects.points.Point;

import org.junit.jupiter.api.Test;

class SegmentTest {

	@Test
	void testHasSubSegment() {
		//Make points and segments
		Point A = new Point(0,0);
		Point B = new Point(0,5);
		Point C = new Point(0,3);
		Point D = new Point(1,8);		
		Point E = new Point(0,1);
		Point F = new Point(0.5,4);
		
		/* (Y-Axis)
		 * ^
		 * |
		 * |
		 * |  *D
		 * |
		 * |
		 * *B
		 * | *F
		 * *C
		 * |
		 * *E
		 * *A------------->(X-Axis)
		 */
		
		Segment AB = new Segment(A,B);
		Segment AC = new Segment(A,C);
		Segment AE = new Segment(A,E);
		Segment AD = new Segment(A,D);
		Segment AF = new Segment(A,F);
		
		//Testing AB, should include some, not others
		assertTrue(AB.HasSubSegment(AC));
		assertTrue(AB.HasSubSegment(AE));
		assertFalse(AB.HasSubSegment(AD));
		assertFalse(AB.HasSubSegment(AF));
		
		//Testing the inverses
		assertFalse(AC.HasSubSegment(AB));
		assertFalse(AE.HasSubSegment(AB));
		
		//Testing small segments
		assertFalse(AE.HasSubSegment(AD));
		assertFalse(AE.HasSubSegment(AC));
		assertFalse(AC.HasSubSegment(AD));
		assertFalse(AF.HasSubSegment(AD));
		
		//Testing non-vertical slope
		assertTrue(AD.HasSubSegment(AF));
		assertFalse(AD.HasSubSegment(AE));
		assertFalse(AD.HasSubSegment(AB));
		
		//Testing if they are subsegment of themselves
		assertFalse(AD.HasSubSegment(AD));
		assertFalse(AB.HasSubSegment(AB));
		
	}

	@Test
	void testCoincideWithoutOverlap() {
		//Building both points and segments
		Point A = new Point(0,0);
		Point B = new Point(0,5);
		Point C = new Point(0,3);
		Point D = new Point(1,1);
		Point E = new Point(1,5);
		Point F = new Point(0,10);	
		Point G = new Point(2,10);	
		
		/* (Y-Axis)
		 * ^
		 * |
		 * *F   *G
		 * |
		 * |
		 * |
		 * |
		 * *B *E
		 * |
		 * *C
		 * |
		 * |  *D
		 * *A------------->(X-Axis)
		 */
		
		Segment AB = new Segment(A,B);
		Segment AC = new Segment(A,C);
		Segment CB = new Segment(C,B);
		Segment DE = new Segment(D,E);
		Segment AD = new Segment(A,D);
		Segment BF = new Segment(B,F);
		Segment AF = new Segment(A,F);
		Segment CF = new Segment(C,F);
		Segment AE = new Segment(A,E);
		Segment AG = new Segment(A,G);
		
		//No overlap, some sharing endpoints
		assertTrue(AC.coincideWithoutOverlap(CF));
		assertTrue(AB.coincideWithoutOverlap(BF));
		
		//Overlapping segments, interchanging roles
		assertFalse(AB.coincideWithoutOverlap(AC));
		assertFalse(AC.coincideWithoutOverlap(AB));
		assertFalse(AF.coincideWithoutOverlap(AB));
		assertFalse(AB.coincideWithoutOverlap(AF));
		
		//Sub-segments, interchanging roles
		assertFalse(CB.coincideWithoutOverlap(AF));
		assertFalse(AF.coincideWithoutOverlap(CB));
		assertFalse(AG.coincideWithoutOverlap(AE));
		assertFalse(AE.coincideWithoutOverlap(AG));
	}
	@Test
	void testCollectOrderedPointsOnSegment() {
		Point A = new Point("A", 0,0);
		Point B = new Point("B", 0,5);
		Point C = new Point("C", 0,3);
		Point D = new Point("D", 1,1);
		Point E = new Point("E", 1,5);
		Point F = new Point("F", 0,10);
		
		/*
		 * (Y-Axis)
		 * ^
		 * |
		 * *F
		 * |
		 * |
		 * |
		 * |
		 * *B *E
		 * |
		 * *C
		 * |
		 * |  *D
		 * *A------>(X-Axis)
		 *
		 */
		
		//Populating a set of points to use for the method
		Set<Point> pointSet = new HashSet<Point>();
		pointSet.add(A);
		pointSet.add(B);
		pointSet.add(C);
		pointSet.add(D);
		pointSet.add(E);
		pointSet.add(F);
		
		//Checking for correct containment based on the segment (A, B)
		Segment s = new Segment(A, B);
		SortedSet<Point> pts = s.collectOrderedPointsOnSegment(pointSet);
		assertTrue(pts.contains(A));
		assertTrue(pts.contains(B));
		assertTrue(pts.contains(C));
		assertFalse(pts.contains(D));
		assertFalse(pts.contains(E));
		assertFalse(pts.contains(F));
		
		//Checking if reverse remains the same
		Segment s2 = new Segment(B, A);
		SortedSet<Point> pts2 = s2.collectOrderedPointsOnSegment(pointSet);
		assertTrue(pts2.contains(A));
		assertTrue(pts2.contains(B));
		assertTrue(pts2.contains(C));
		assertFalse(pts2.contains(D));
		assertFalse(pts2.contains(E));
		assertFalse(pts2.contains(F));
		
		//Non-vertical slope
		Point G = new Point("G", 10,10);
		Point H = new Point("H", 2,2);
		pointSet.add(G);
		pointSet.add(H);
		Segment s3 = new Segment(A, G);
		SortedSet<Point> pts3 = s3.collectOrderedPointsOnSegment(pointSet);
		assertTrue(pts3.contains(A));
		assertTrue(pts3.contains(D));
		assertTrue(pts3.contains(H));
		assertTrue(pts3.contains(G));
		assertFalse(pts3.contains(B));
		assertFalse(pts3.contains(C));
		assertFalse(pts3.contains(F));
		
		//Segments that share a vertex, make sure no overlap
		Segment s4 = new Segment(A, F);
		Segment s5 = new Segment(A, H);
		SortedSet<Point> pts4 = s5.collectOrderedPointsOnSegment(pointSet);
		assertTrue(pts4.contains(A));
		assertTrue(pts4.contains(D));
		assertTrue(pts4.contains(H));
		assertFalse(pts4.contains(G));
		assertFalse(pts4.contains(B));
		assertFalse(pts4.contains(C));
		assertFalse(pts4.contains(F));
		
	}
}

