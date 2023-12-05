package preprocessor.preprocessor;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.angle.Angle;
import geometry_objects.angle.AngleEquivalenceClasses;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import input.components.FigureNode;
import preprocessor.AngleIdentifier;
import preprocessor.Preprocessor;
import input.InputFacade;

class AngleIdentifierTest
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

	//      A                                 
	//     / \                                
	//    B___C                               
	//   / \ / \                              
	//  /   X   \  X is not a specified point (it is implied) 
	// D________E
	//
	// This figure contains 44 angles
	//
	@Test
	void test_crossing_symmetric_triangle()
	{
		init("JSON tests/crossing_symmetric_triangle.json");

		AngleIdentifier angleIdentifier = new AngleIdentifier(_segments);

		AngleEquivalenceClasses computedAngles = angleIdentifier.getAngles();

		// The number of classes should equate to the number of 'minimal' angles
		assertEquals("Number of Angle Equivalence classes", 25, computedAngles.numClasses());

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
		// Angles we expect to find
		//
		List<Angle> expectedAngles = new ArrayList<Angle>();
		try {
			//
			//
			// Angles broken down by equivalence class
			//
			//

			// Straight angles
			//
			expectedAngles.add(new Angle(a_star_b, a_star_e));

			expectedAngles.add(new Angle(ac, ce));

			expectedAngles.add(new Angle(a_star_d, a_star_c));

			expectedAngles.add(new Angle(ab, bd));

			// right angles
			//
			expectedAngles.add(new Angle(a_star_b, a_star_d));

			expectedAngles.add(new Angle(a_star_b, a_star_c));

			expectedAngles.add(new Angle(a_star_d, a_star_e));

			expectedAngles.add(new Angle(a_star_c, a_star_e));

			// 
			//
			expectedAngles.add(new Angle(a_star_b, ab));
			expectedAngles.add(new Angle(be, ab));

			expectedAngles.add(new Angle(a_star_b, bc));
			expectedAngles.add(new Angle(be, bc));

			expectedAngles.add(new Angle(a_star_b, bd));
			expectedAngles.add(new Angle(be, bd));

			expectedAngles.add(new Angle(ac, a_star_c));
			expectedAngles.add(new Angle(ac, cd));

			expectedAngles.add(new Angle(bc, a_star_c));
			expectedAngles.add(new Angle(cd, bd));

			expectedAngles.add(new Angle(a_star_c, ce));
			expectedAngles.add(new Angle(cd, ce));

			expectedAngles.add(new Angle(a_star_d, de));
			expectedAngles.add(new Angle(cd, de));

			expectedAngles.add(new Angle(bd, de));
			expectedAngles.add(new Angle(ad, de));

			expectedAngles.add(new Angle(a_star_e, de));
			expectedAngles.add(new Angle(be, de));

			expectedAngles.add(new Angle(ce, de));
			expectedAngles.add(new Angle(ae, de));

			// Larger equivalence classes
			//
			expectedAngles.add(new Angle(ac, ab));
			expectedAngles.add(new Angle(ab, ae));
			expectedAngles.add(new Angle(ad, ae));
			expectedAngles.add(new Angle(ac, ad));

			expectedAngles.add(new Angle(a_star_d, bd));
			expectedAngles.add(new Angle(cd, bd));
			expectedAngles.add(new Angle(ad, a_star_d));
			expectedAngles.add(new Angle(cd, ad));

			expectedAngles.add(new Angle(a_star_e, ce));
			expectedAngles.add(new Angle(be, ae));
			expectedAngles.add(new Angle(be, ce));
			expectedAngles.add(new Angle(a_star_e, ce));


			// More singletons
			//
			expectedAngles.add(new Angle(ac, bc));

			expectedAngles.add(new Angle(ab, bc));

			expectedAngles.add(new Angle(bc, bd));

			expectedAngles.add(new Angle(bc, ce));			
		}
		catch (FactException te) { System.err.println("Invalid Angles in Angle test."); }

		assertEquals(expectedAngles.size(), computedAngles.size());

		//
		// Equality
		//
		for (Angle expected : expectedAngles)
		{
			assertTrue(computedAngles.contains(expected));
		}
	}

	@Test
	void test_starfigure()
	{
		//		System.out.print("hello");

		init("JSON tests/starfigure.json");


		AngleIdentifier angleIdentifier = new AngleIdentifier(_segments);

		AngleEquivalenceClasses computedAngles = angleIdentifier.getAngles();

		// The number of classes should equate to the number of 'minimal' angles
		assertEquals("Number of Angle Equivalence classes", 24, computedAngles.numClasses());
//		System.out.print(computedAngles);
		//
		// ALL original segments: 20 in this figure.
		//
		Segment ab = new Segment(_points.getPoint("A"), _points.getPoint("B"));
		Segment ad = new Segment(_points.getPoint("A"), _points.getPoint("D"));
		Segment ae = new Segment(_points.getPoint("A"), _points.getPoint("E"));
		Segment aj = new Segment(_points.getPoint("A"), _points.getPoint("J"));
		Segment ah = new Segment(_points.getPoint("A"), _points.getPoint("H"));
		Segment ag = new Segment(_points.getPoint("A"), _points.getPoint("G"));

		Segment bc = new Segment(_points.getPoint("B"), _points.getPoint("C"));
		Segment bd = new Segment(_points.getPoint("B"), _points.getPoint("D"));
		Segment be = new Segment(_points.getPoint("B"), _points.getPoint("E"));
		Segment bj = new Segment(_points.getPoint("B"), _points.getPoint("J"));
		Segment bi = new Segment(_points.getPoint("B"), _points.getPoint("I"));

		Segment cd = new Segment(_points.getPoint("C"), _points.getPoint("D"));
		Segment cj = new Segment(_points.getPoint("C"), _points.getPoint("J"));
		Segment ci = new Segment(_points.getPoint("C"), _points.getPoint("I"));

		Segment hj = new Segment(_points.getPoint("H"), _points.getPoint("J"));
		Segment hi = new Segment(_points.getPoint("H"), _points.getPoint("I"));

		Segment ij = new Segment(_points.getPoint("I"), _points.getPoint("J"));

		Segment de = new Segment(_points.getPoint("D"), _points.getPoint("E"));

		Segment ef = new Segment(_points.getPoint("E"), _points.getPoint("F"));

		Segment fg = new Segment(_points.getPoint("F"), _points.getPoint("G"));

		Segment gh = new Segment(_points.getPoint("G"), _points.getPoint("H"));
		Segment gj = new Segment(_points.getPoint("G"), _points.getPoint("J"));

		//
		// Implied minimal segments: 0 in this figure.
		//
		//
		// Non-minimal, computed segments: 0 in this figure.
		//
		//
		// Angles we expect to find
		//
		List<Angle> expectedAngles = new ArrayList<Angle>();
		try {
			//
			//
			// Angles broken down by equivalence class
			//
			//

			// Straight angles
			//
			expectedAngles.add(new Angle(ab, be));
			expectedAngles.add(new Angle(ab, bd));
			expectedAngles.add(new Angle(bd, de));

			expectedAngles.add(new Angle(bj, bc));
			expectedAngles.add(new Angle(ij, bj));
			expectedAngles.add(new Angle(bi, bc));
			expectedAngles.add(new Angle(bi, ab));

			expectedAngles.add(new Angle(aj, gj));
			expectedAngles.add(new Angle(aj, hj));
			expectedAngles.add(new Angle(hj, gh));

			// right angles
			//
			// none
			// 

			// other angles
			//
			expectedAngles.add(new Angle(ab, aj));
			expectedAngles.add(new Angle(ab, bj));
			expectedAngles.add(new Angle(ag, ae));
			expectedAngles.add(new Angle(ad, aj));
			expectedAngles.add(new Angle(ah, ab));
			expectedAngles.add(new Angle(ad, ah));

			expectedAngles.add(new Angle(ag, ab));
			expectedAngles.add(new Angle(aj, ae));
			expectedAngles.add(new Angle(ag, ad));
			expectedAngles.add(new Angle(ah, ae));

			expectedAngles.add(new Angle(ab, bc));

			expectedAngles.add(new Angle(bc, bd));
			expectedAngles.add(new Angle(bc, be));
			expectedAngles.add(new Angle(bi, hi));

			expectedAngles.add(new Angle(bj, be));
			expectedAngles.add(new Angle(be, bi));
			expectedAngles.add(new Angle(ef, be));

			expectedAngles.add(new Angle(bi, bd));
			expectedAngles.add(new Angle(bj, bd));

			expectedAngles.add(new Angle(ci, cd));
			expectedAngles.add(new Angle(cj, cd));

			expectedAngles.add(new Angle(bc, cd));
			expectedAngles.add(new Angle(cd, de));
			expectedAngles.add(new Angle(cd, ad));
			expectedAngles.add(new Angle(bd, cd));

			expectedAngles.add(new Angle(ad, de));
			expectedAngles.add(new Angle(de, ef));

			expectedAngles.add(new Angle(ef, fg));
			expectedAngles.add(new Angle(fg, gh));

			expectedAngles.add(new Angle(fg, gj));
			expectedAngles.add(new Angle(fg, ag));
			expectedAngles.add(new Angle(ef, ae));

			//outside angle
			expectedAngles.add(new Angle(gh, hi));

			expectedAngles.add(new Angle(gh, ah));

			//can't read off whiteboard
			expectedAngles.add(new Angle(hj, ij));
			expectedAngles.add(new Angle(hj, bj));


			expectedAngles.add(new Angle(hj, cj));
			expectedAngles.add(new Angle(hi, ci));

			expectedAngles.add(new Angle(ij, cj));
			expectedAngles.add(new Angle(ij, aj));
			expectedAngles.add(new Angle(ij, gj));
			expectedAngles.add(new Angle(hi, hj));
			expectedAngles.add(new Angle(hi, ah));
			expectedAngles.add(new Angle(hi, ij));

			expectedAngles.add(new Angle(gj, cj));

			expectedAngles.add(new Angle(gj, bj));

			expectedAngles.add(new Angle(aj, bj));

			expectedAngles.add(new Angle(aj, cj));			
		}
		catch (FactException te) { System.err.println("Invalid Angles in Angle test."); }

//		System.out.print(expectedAngles);
		assertEquals(expectedAngles.size(), computedAngles.size());

		//
		// Equality
		//
		for (Angle expected : expectedAngles)
		{
			assertTrue(computedAngles.contains(expected));
		}
	}
}
