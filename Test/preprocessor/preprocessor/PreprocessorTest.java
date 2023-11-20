package preprocessor.preprocessor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import geometry_objects.Segment;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import input.InputFacade;
import input.components.FigureNode;
import preprocessor.Preprocessor;

class PreprocessorTest
{	
	@Test
	void test_crossing_symmetric_triangle() {
		//
		//Create figure, test all methods for correctness
		//
		FigureNode fig = InputFacade.extractFigure("JSON tests/crossing_symmetric_triangle.json");

		Map.Entry<PointDatabase, Set<Segment>> pair = InputFacade.toGeometryRepresentation(fig);

		PointDatabase points = pair.getKey();

		Set<Segment> segments = pair.getValue();

		Preprocessor pp = new Preprocessor(points, segments);

		assertEquals(1, pp._implicitPoints.size());

		Set<Segment> iSegments = pp.computeImplicitBaseSegments(pp._implicitPoints);

		assertEquals(4, iSegments.size());

		Set<Segment> minimalSeg = pp.identifyAllMinimalSegments(pp._implicitPoints, pp._givenSegments, iSegments);

		assertEquals(10, minimalSeg.size());

		Set<Segment> nonMin = pp.constructAllNonMinimalSegments(minimalSeg);

		assertEquals(4, nonMin.size());
	}

	@Test
	void test_irregular() {
		FigureNode fig = InputFacade.extractFigure("JSON tests/irregular_Hexagon.json");

		Map.Entry<PointDatabase, Set<Segment>> pair = InputFacade.toGeometryRepresentation(fig);

		PointDatabase points = pair.getKey();

		Set<Segment> segments = pair.getValue();

		Preprocessor pp = new Preprocessor(points, segments);

		assertEquals(2, pp._implicitPoints.size());

		Set<Segment> iSegments = pp.computeImplicitBaseSegments(pp._implicitPoints);

		assertEquals(8, iSegments.size());

		Set<Segment> minimalSeg = pp.identifyAllMinimalSegments(pp._implicitPoints, pp._givenSegments, iSegments);

		assertEquals(15, minimalSeg.size());

		Set<Segment> nonMin = pp.constructAllNonMinimalSegments(minimalSeg);

		assertEquals(4, nonMin.size());
	}

	@Test
	void test_shirt()
	{
		FigureNode fig = InputFacade.extractFigure("JSON tests/shirt_figure.json");

		Map.Entry<PointDatabase, Set<Segment>> pair = InputFacade.toGeometryRepresentation(fig);

		PointDatabase points = pair.getKey();

		Set<Segment> segments = pair.getValue();

		Preprocessor pp = new Preprocessor(points, segments);

		assertEquals(3, pp._implicitPoints.size());

		Set<Segment> iSegments = pp.computeImplicitBaseSegments(pp._implicitPoints);

		assertEquals(12, iSegments.size());

		Set<Segment> minimalSeg = pp.identifyAllMinimalSegments(pp._implicitPoints, pp._givenSegments, iSegments);

		assertEquals(16, minimalSeg.size());

		Set<Segment> nonMin = pp.constructAllNonMinimalSegments(minimalSeg);

		assertEquals(10, nonMin.size());
	}


	@Test
	void test_implicit_crossings()
	{
		FigureNode fig = InputFacade.extractFigure("JSON tests/fully_connected_irregular_polygon.json");

		Map.Entry<PointDatabase, Set<Segment>> pair = InputFacade.toGeometryRepresentation(fig);

		PointDatabase points = pair.getKey();

		Set<Segment> segments = pair.getValue();

		Preprocessor pp = new Preprocessor(points, segments);

		// 5 new implied points inside the pentagon
		assertEquals(5, pp._implicitPoints.size());

		//
		//
		//		               D(3, 7)
		//
		//
		//   E(-2,4)       D*      E*
		//		         C*          A*       C(6, 3)
		//                      B*
		//		       A(2,0)        B(4, 0)
		//
		//		    An irregular pentagon with 5 C 2 = 10 segments



		Point a_star = new Point(56.0 / 15, 28.0 / 15);
		System.out.println("b_star - " + 16.0 / 7.0 + ", " + 8.0 / 7.0);
		Point b_star = new Point(2.2857142857142865, 1.1428571428571432);
		System.out.println("c_star - " + 8.0 / 9.0 + ", " + 56.0 / 27.0);
		Point c_star = new Point(0.8888888888888889, 2.0740740740740744);
		Point d_star = new Point(90.0 / 59, 210.0 / 59);
		Point e_star = new Point(194.0 / 55, 182.0 / 55);

		for( Point p : pp._implicitPoints) {
			System.out.println(p.getName() + " - " + p.getX() + ", " + p.getY());
		}

		assertTrue(pp._implicitPoints.contains(a_star));     
		assertTrue(pp._implicitPoints.contains(b_star));   
		assertTrue(pp._implicitPoints.contains(c_star));   
		assertTrue(pp._implicitPoints.contains(d_star));     
		assertTrue(pp._implicitPoints.contains(e_star));     

		// There are 15 implied segments inside the pentagon; see figure above

		Set<Segment> iSegments = pp.computeImplicitBaseSegments(pp._implicitPoints);

		assertEquals(15, iSegments.size());

		Set<Segment> minimalSegs = pp.identifyAllMinimalSegments(pp._implicitPoints, segments, iSegments);

		assertEquals(20, minimalSegs.size());

		Set<Segment> nonMin = pp.constructAllNonMinimalSegments(minimalSegs);

		assertEquals(15, nonMin.size());

		List<Segment> expectedISegments = new ArrayList<Segment>();

		expectedISegments.add(new Segment(points.getPoint("A"), c_star));
		expectedISegments.add(new Segment(points.getPoint("A"), b_star));

		expectedISegments.add(new Segment(points.getPoint("B"), b_star));
		expectedISegments.add(new Segment(points.getPoint("B"), a_star));

		expectedISegments.add(new Segment(points.getPoint("C"), a_star));
		expectedISegments.add(new Segment(points.getPoint("C"), e_star));

		expectedISegments.add(new Segment(points.getPoint("D"), d_star));
		expectedISegments.add(new Segment(points.getPoint("D"), e_star));

		expectedISegments.add(new Segment(points.getPoint("E"), c_star));
		expectedISegments.add(new Segment(points.getPoint("E"), d_star));

		expectedISegments.add(new Segment(c_star, b_star));
		expectedISegments.add(new Segment(b_star, a_star));
		expectedISegments.add(new Segment(a_star, e_star));
		expectedISegments.add(new Segment(e_star, d_star));
		expectedISegments.add(new Segment(d_star, c_star));

		for (Segment s : expectedISegments)
		{
			assertTrue(expectedISegments.contains(s));
		}


		//Ensure we have ALL minimal segments: 20 in this figure.
		List<Segment> expectedMinimalSegments = new ArrayList<Segment>(iSegments);

		expectedMinimalSegments.add(new Segment(points.getPoint("A"), points.getPoint("B")));
		expectedMinimalSegments.add(new Segment(points.getPoint("B"), points.getPoint("C")));
		expectedMinimalSegments.add(new Segment(points.getPoint("C"), points.getPoint("D")));
		expectedMinimalSegments.add(new Segment(points.getPoint("D"), points.getPoint("E")));
		expectedMinimalSegments.add(new Segment(points.getPoint("E"), points.getPoint("A")));

		Set<Segment> minimalSegments = pp.identifyAllMinimalSegments(pp._implicitPoints, segments, iSegments);
		assertEquals(expectedMinimalSegments.size(), minimalSegments.size());

		for (Segment minimalSeg : minimalSegments)
		{
			assertTrue(expectedMinimalSegments.contains(minimalSeg));
		}

		//
		// Construct ALL figure segments from the base segments
		//
		Set<Segment> computedNonMinimalSegments = pp.constructAllNonMinimalSegments(minimalSegments);

		//
		// All Segments will consist of the new 15 non-minimal segments.
		//
		assertEquals(15, computedNonMinimalSegments.size());

		//
		// Ensure we have ALL minimal segments: 20 in this figure.
		//
		List<Segment> expectedNonMinimalSegments = new ArrayList<Segment>();
		expectedNonMinimalSegments.add(new Segment(points.getPoint("A"), d_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("D"), c_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("A"), points.getPoint("D")));

		expectedNonMinimalSegments.add(new Segment(points.getPoint("B"), c_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("E"), b_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("B"), points.getPoint("E")));

		expectedNonMinimalSegments.add(new Segment(points.getPoint("C"), d_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("E"), e_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("C"), points.getPoint("E")));		

		expectedNonMinimalSegments.add(new Segment(points.getPoint("A"), a_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("C"), b_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("A"), points.getPoint("C")));

		expectedNonMinimalSegments.add(new Segment(points.getPoint("B"), e_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("D"), a_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("B"), points.getPoint("D")));

		//
		// Check size and content equality
		//
		assertEquals(expectedNonMinimalSegments.size(), computedNonMinimalSegments.size());

		for (Segment computedNonMinimalSegment : computedNonMinimalSegments)
		{
			assertTrue(expectedNonMinimalSegments.contains(computedNonMinimalSegment));
		}
	}
	@Test
	void test_square_with_diagonal()
	{
		FigureNode fig = InputFacade.extractFigure("JSON tests/square.json");

		Map.Entry<PointDatabase, Set<Segment>> pair = InputFacade.toGeometryRepresentation(fig);

		PointDatabase points = pair.getKey();

		Set<Segment> segments = pair.getValue();
		Preprocessor pp = new Preprocessor(points, segments);

		assertEquals(3, pp._implicitPoints.size());

		Set<Segment> iSegments = pp.computeImplicitBaseSegments(pp._implicitPoints);
		assertEquals(11, iSegments.size());

		Set<Segment> minimalSeg = pp.identifyAllMinimalSegments(pp._implicitPoints, pp._givenSegments, iSegments);
		assertEquals(21, minimalSeg.size());

		Set<Segment> nonMin = pp.constructAllNonMinimalSegments(minimalSeg);
		assertEquals(18, nonMin.size());
	}
}
