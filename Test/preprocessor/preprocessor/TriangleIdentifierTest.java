package preprocessor.preprocessor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.Triangle;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import input.components.FigureNode;
import preprocessor.Preprocessor;
import preprocessor.TriangleIdentifier;
import input.InputFacade;

class TriangleIdentifierTest
{
	protected PointDatabase _points;
	protected Preprocessor _pp;
	protected Map<Segment, Segment> _segments;
	
	protected void init(String filename)
	{
		FigureNode fig = InputFacade.extractFigure(filename);

		Map.Entry<PointDatabase, Set<Segment>> pair = InputFacade.toGeometryRepresentation(fig);

		_points = pair.getKey();

		_pp = new Preprocessor(_points, pair.getValue());

		_pp.analyze();

		_segments = _pp.getAllSegments();
	}
	
//	      A                                 
//	     / \                                
//	    B___C                               
//	   / \ / \                              
//	  /   X   \  X is not a specified point (it is implied) 
//	 D_________E
//	
//	 This figure contains 12 triangles
	
	@Test
	void test_crossing_symmetric_triangle()
	{
		init("JSON tests/crossing_symmetric_triangle.json");
		

		TriangleIdentifier triIdentifier = new TriangleIdentifier(_segments);

		Set<Triangle> computedTriangles = null;
		try {
			computedTriangles = triIdentifier.getTriangles();
		} catch (FactException e) {
			System.out.println("Symmetric Triangle's triangles not computed");
		}
//		System.out.println(computedTriangles);

		assertEquals(12, computedTriangles.size());

		//
		// ALL original segments: 8 in this figure.
		//
		Segment ab = new Segment(_points.getPoint("A"), _points.getPoint("B"));
		Segment ac = new Segment(_points.getPoint("A"), _points.getPoint("C"));
		Segment bc = new Segment(_points.getPoint("B"), _points.getPoint("C"));

		Segment bd = new Segment(_points.getPoint("B"), _points.getPoint("D"));
		Segment ce = new Segment(_points.getPoint("C"), _points.getPoint("E"));
		Segment de = new Segment(_points.getPoint("D"), _points.getPoint("E"));

		Segment be = new Segment(_points.getPoint("B"), _points.getPoint("E"));
		Segment cd = new Segment(_points.getPoint("C"), _points.getPoint("D"));

		//
		// Implied minimal segments: 4 in this figure.
		//
		Point a_star = _points.getPoint(3,3);

		Segment a_star_b = new Segment(a_star, _points.getPoint("B"));
		Segment a_star_c = new Segment(a_star, _points.getPoint("C"));
		Segment a_star_d = new Segment(a_star, _points.getPoint("D"));
		Segment a_star_e = new Segment(a_star, _points.getPoint("E"));

		//
		// Non-minimal, computed segments: 2 in this figure.
		//
		Segment ad = new Segment(_points.getPoint("A"), _points.getPoint("D"));
		Segment ae = new Segment(_points.getPoint("A"), _points.getPoint("E"));

		//
		// Triangles we expect to find
		//
		List<Triangle> expectedTriangles = new ArrayList<Triangle>();
		try {
			expectedTriangles.add(new Triangle(Arrays.asList(ab, bc, ac)));
			expectedTriangles.add(new Triangle(Arrays.asList(bd, a_star_d, a_star_b)));
			expectedTriangles.add(new Triangle(Arrays.asList(bc, a_star_b, a_star_c)));
			expectedTriangles.add(new Triangle(Arrays.asList(ce, a_star_c, a_star_e)));
			expectedTriangles.add(new Triangle(Arrays.asList(de, a_star_d, a_star_e)));

			expectedTriangles.add(new Triangle(Arrays.asList(bd, cd, bc)));
			expectedTriangles.add(new Triangle(Arrays.asList(ce, be, bc)));

			expectedTriangles.add(new Triangle(Arrays.asList(bd, be, de)));
			expectedTriangles.add(new Triangle(Arrays.asList(ce, cd, de)));

			expectedTriangles.add(new Triangle(Arrays.asList(ab, be, ae)));
			expectedTriangles.add(new Triangle(Arrays.asList(ac, cd, ad)));

			expectedTriangles.add(new Triangle(Arrays.asList(ad, de, ae)));
		}
		catch (FactException te) { System.err.println("Invalid triangles in triangle test."); }

		assertEquals(expectedTriangles.size(), computedTriangles.size());
		
		for (Triangle computedTriangle : computedTriangles)
		{
			assertTrue(expectedTriangles.contains(computedTriangle));
		}
		
	}
	@Test
	void testOfSquare() {
	//  B----H--------J------C
	//  |    |        |    / |
	//  |    |        |*B /  |
	//  |    |*C      | /    |
	//  E - -|- - - - / - - -F
	//  |    |      / |      |
	//  |    |    /   |      |
	//  |    |*A/     |      |
	//  |    /        |      |
	//  | /  |        |      |
	//  A----G--------I------D

		init("JSON tests/square.json");
		

		TriangleIdentifier triIdentifier = new TriangleIdentifier(_segments);

		Set<Triangle> computedTriangles = null;
		try {
			computedTriangles = triIdentifier.getTriangles();
		} catch (FactException e) {
			System.out.println("Square's triangles not computed");
		}
		
		assertEquals(9, computedTriangles.size());
		

//		//
//		// ALL original segments: 8 in this figure.
//		//
		Segment ab = new Segment(_points.getPoint("A"), _points.getPoint("B"));
		Segment ac = new Segment(_points.getPoint("A"), _points.getPoint("C"));
		Segment ad = new Segment(_points.getPoint("A"), _points.getPoint("D"));
		Segment ae = new Segment(_points.getPoint("A"), _points.getPoint("E"));
		Segment ai = new Segment(_points.getPoint("A"), _points.getPoint("I"));
		Segment ag = new Segment(_points.getPoint("A"), _points.getPoint("G"));

		Segment bc = new Segment(_points.getPoint("B"), _points.getPoint("C"));
		Segment be = new Segment(_points.getPoint("B"), _points.getPoint("E"));
		Segment bh = new Segment(_points.getPoint("B"), _points.getPoint("H"));
		Segment bj = new Segment(_points.getPoint("B"), _points.getPoint("J"));
		
		Segment cd = new Segment(_points.getPoint("C"), _points.getPoint("D"));
		Segment cf = new Segment(_points.getPoint("C"), _points.getPoint("F"));
		Segment ch = new Segment(_points.getPoint("C"), _points.getPoint("H"));
		Segment cj = new Segment(_points.getPoint("C"), _points.getPoint("J"));

		Segment df = new Segment(_points.getPoint("D"), _points.getPoint("F"));
		Segment dg = new Segment(_points.getPoint("D"), _points.getPoint("G"));
		Segment di = new Segment(_points.getPoint("D"), _points.getPoint("I"));
		
		Segment ef = new Segment(_points.getPoint("E"), _points.getPoint("F"));
		
		Segment gh = new Segment(_points.getPoint("G"), _points.getPoint("H"));
		Segment gi = new Segment(_points.getPoint("G"), _points.getPoint("I"));
		
		Segment hj = new Segment(_points.getPoint("H"), _points.getPoint("J"));
		
		Segment ij = new Segment(_points.getPoint("I"), _points.getPoint("J"));

		//
		// Implied minimal segments: 4 in this figure.
		//
		Point a_star = _points.getPoint(1,1);
		Point b_star = _points.getPoint(3,3);
		Point c_star = _points.getPoint(1,3);

		Segment a_star_a = new Segment(a_star, _points.getPoint("A"));
		Segment a_star_g = new Segment(a_star, _points.getPoint("G"));
		Segment a_star_b_star = new Segment(a_star, b_star);
		Segment a_star_c_star = new Segment(a_star, c_star);

		
		
		Segment b_star_c = new Segment(b_star, _points.getPoint("C"));
		Segment b_star_f = new Segment(b_star, _points.getPoint("F"));
		Segment b_star_j = new Segment(b_star, _points.getPoint("J"));
		Segment b_star_i = new Segment(b_star, _points.getPoint("I"));
		Segment b_star_c_star = new Segment(b_star, c_star);
		
		Segment c_star_c = new Segment(c_star, _points.getPoint("E"));
		Segment c_star_h = new Segment(c_star, _points.getPoint("H"));


		
		

		Segment a_star_d = new Segment(a_star, _points.getPoint("D"));
		Segment a_star_e = new Segment(a_star, _points.getPoint("E"));

		//
		// Non-minimal, computed segments: 2 in this figure.
		//
		Segment a_star_c = new Segment(a_star, _points.getPoint("C"));
		Segment a_star_h = new Segment(a_star, _points.getPoint("H"));
		
		Segment b_star_a = new Segment(b_star, _points.getPoint("A"));
		Segment b_star_e = new Segment(b_star, _points.getPoint("E"));
		
		Segment c_star_g = new Segment(c_star, _points.getPoint("G"));
		Segment c_star_f = new Segment(c_star, _points.getPoint("F"));
		
		//
		// Triangles we expect to find
		//
		List<Triangle> expectedTriangles = new ArrayList<Triangle>();
		try {
			expectedTriangles.add(new Triangle(Arrays.asList(a_star_a, a_star_g, ag)));
			expectedTriangles.add(new Triangle(Arrays.asList(a_star_b_star, a_star_c_star, b_star_c_star)));
			expectedTriangles.add(new Triangle(Arrays.asList(ac, ab, bc)));
			expectedTriangles.add(new Triangle(Arrays.asList(ae, b_star_a, b_star_e)));
			expectedTriangles.add(new Triangle(Arrays.asList(b_star_c, b_star_j, cj)));

			expectedTriangles.add(new Triangle(Arrays.asList(ac, cd, ad)));
			expectedTriangles.add(new Triangle(Arrays.asList(b_star_c, b_star_f, cf)));

			expectedTriangles.add(new Triangle(Arrays.asList(a_star_h, a_star_c, ch)));
			expectedTriangles.add(new Triangle(Arrays.asList(b_star_a, b_star_i, ai)));

		}
		catch (FactException te) { System.err.println("Invalid triangles in triangle test."); }

		assertEquals(expectedTriangles.size(), computedTriangles.size());
		
		for (Triangle computedTriangle : computedTriangles)
		{
			assertTrue(expectedTriangles.contains(computedTriangle));
		}
	}
}
