package preprocessor.preprocessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import geometry_objects.Segment;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import input.InputFacade;
import input.components.FigureNode;
import preprocessor.delegates.ImplicitPointPreprocessor;

import org.junit.jupiter.api.Test;

class ImplicitPointPreprocessorTest {

	@Test
	void testComputeCrossingTriangle() {

		//Constructing given points and segments for the symmetric crossing triangle
		PointDatabase pd = new PointDatabase();

		Point D = new Point("D", 0,0);
		Point E = new Point("E", 6,0);
		Point B = new Point("B", 2,4);
		Point C = new Point("C", 4,4);
		Point A = new Point("A", 3,6);

		pd.put("D", 0, 0);
		pd.put("E", 6, 0);
		pd.put("B", 2, 4);
		pd.put("C", 4, 4);
		pd.put("A", 3, 6);

		List<Segment> givenSegments = new ArrayList<Segment>();

		Segment AB = new Segment(A,B);
		Segment AC = new Segment(A,C);

		Segment BC = new Segment(B,C);
		Segment BD = new Segment(B,D);
		Segment BE = new Segment(B,E);

		Segment CD = new Segment(C,D);
		Segment CE = new Segment(C,E);
		Segment DE = new Segment(D,E);

		givenSegments.add(AB);
		givenSegments.add(AC);

		givenSegments.add(BC);
		givenSegments.add(BD);
		givenSegments.add(BE);

		givenSegments.add(CD);
		givenSegments.add(CE);
		givenSegments.add(DE);

		Set<Point> implicitPoints = ImplicitPointPreprocessor.compute(pd, givenSegments);

		//Checking siza and containment for the correct point
		assertTrue(implicitPoints.size() == 1);
		assertTrue(implicitPoints.contains(new Point("*_A", 3, 3)));

	}
	@Test
	void testComputeIrregularHexagon() {

		//Constructing given points and segments for the symmetric crossing triangle
		PointDatabase pd = new PointDatabase();

		Point A = new Point("A", 0,0);
		Point B = new Point("B", -2,3);
		Point C = new Point("C", 2,2);
		Point D = new Point("D", 5,4);
		Point E = new Point("E", 5,0);
		Point F = new Point("F", 3,1);

		pd.put("A", 0, 0);
		pd.put("B", -2, 3);
		pd.put("C", 2, 2);
		pd.put("D", 5, 4);
		pd.put("E", 5, 0);
		pd.put("F", 3, 1);

		List<Segment> givenSegments = new ArrayList<Segment>();

		Segment AB = new Segment(A,B);
		Segment AC = new Segment(A,C);
		Segment AF = new Segment(A,F);
		
		Segment BC = new Segment(B,C);
		Segment BF = new Segment(B,F);

		Segment CD = new Segment(C,D);
		Segment CE = new Segment(C,E);
		Segment CF = new Segment(C,F);
		
		Segment DE = new Segment(D,E);
		Segment DF = new Segment(D,F);
		
		Segment EF = new Segment(E,F);

		givenSegments.add(AB);
		givenSegments.add(AC);
		givenSegments.add(AF);

		givenSegments.add(BC);
		givenSegments.add(BF);
		
		givenSegments.add(CD);
		givenSegments.add(CE);
		givenSegments.add(CF);
		
		givenSegments.add(DE);
		givenSegments.add(DF);
		
		givenSegments.add(EF);

		Set<Point> implicitPoints = ImplicitPointPreprocessor.compute(pd, givenSegments);
		
		//Checking siza and containment for the correct point
		assertEquals(2, implicitPoints.size());
		assertTrue(implicitPoints.contains(new Point("*_A", 1.5714285714285716, 1.5714285714285716)));
		assertTrue(implicitPoints.contains(new Point("*_B", 3.1538461538461537, 1.230769230769231)));

	}

}
