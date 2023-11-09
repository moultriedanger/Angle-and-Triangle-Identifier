package geometry_objects.points;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.AbstractMap.SimpleEntry;

import org.junit.jupiter.api.Test;

import geometry_objects.Segment;
import input.InputFacade;
import input.components.FigureNode;
/*
* Tests the InputFacade
*
* @author Moultrie DangerField, Jalen Livingston, and Jack Patterson
* @date 11/1/2023
*/
class TestInputFacade{

	@Test
	public void testSingleTriangle() {
		
		String file = "JSON tests/single_triangle.json";
		
		FigureNode fn;
		
		fn = InputFacade.extractFigure(file);
		
		SimpleEntry<PointDatabase, Set<Segment>> geoMap=(SimpleEntry<PointDatabase, Set<Segment>>) InputFacade.toGeometryRepresentation(fn);
		assertTrue(fn instanceof FigureNode);
		
		PointDatabase pd=geoMap.getKey();
		Point a=new Point("A", 0,0);
		Point b=new Point("B", 1, 1);
		Point c = new Point("C", 1,0);
		Point dummyPoint = new Point("Bob", 0, 4);
		
		assertTrue ("Point A does not exist", a.equals(pd.getPoint(a)));
		assertTrue ("Point B does not exist", b.equals(pd.getPoint(b)));
		assertTrue ("Point C does not exist", c.equals(pd.getPoint(c)));
		
		assertEquals ("Test point has content", null, pd.getPoint(dummyPoint));
		
		Set<Segment> segSet = geoMap.getValue();
		Segment ab = new Segment(a, b);
		Segment ac = new Segment(a, c);
		
		Segment ba = new Segment(b, a);
		
		Segment dummySeg = new Segment(a, dummyPoint);
		
		assertTrue ("The segment AB is not in segSet", segSet.contains(ab));
		assertTrue ("The segment AC is not in segSet", segSet.contains(ac));
		
		//Test undirected edge
		assertTrue ("The undirected edge BA is not in segSet", segSet.contains(ba));
		
		assertFalse ("The dummy undirected edge is not in segSet", segSet.contains(dummySeg));
	}
	@Test
	public void testCollinearLineSegments() {
		
		String file = "JSON tests/collinear_line_segments.json";
		
		FigureNode fn;
		
		fn = InputFacade.extractFigure(file);
		
		SimpleEntry<PointDatabase, Set<Segment>> geoMap=(SimpleEntry<PointDatabase, Set<Segment>>) InputFacade.toGeometryRepresentation(fn);
		assertTrue(fn instanceof FigureNode);
		
		PointDatabase pd = geoMap.getKey();
		Point a=new Point("A", 0,0);
		Point b=new Point("B", 4, 0);
		Point c=new Point("C", 9, 0);
		Point d=new Point("D", 11, 0);
		Point f = new Point("F", 26,0);
		
		Point dummyPoint = new Point("Bob", 0, 4);
		
		assertTrue ("Point A does not exist", a.equals(pd.getPoint(a)));
		assertTrue ("Point B does not exist", b.equals(pd.getPoint(b)));
		assertTrue ("Point D does not exist", d.equals(pd.getPoint(d)));
		assertTrue ("Point F does not exist", f.equals(pd.getPoint(f)));
		
		assertFalse (dummyPoint.equals(pd.getPoint(a)));
		
		Set<Segment> segSet = geoMap.getValue();
		Segment ab = new Segment(a,b);
		Segment bc = new Segment(b,c);
		Segment cd = new Segment(c,d);
		Segment af = new Segment(a, f);
		Segment cf = new Segment(c, f);
		
		assertTrue ("testDentedTrapezoid: segment set is inaccurate", segSet.contains(ab));
		assertTrue ("testDentedTrapezoid: segment set is inaccurate", segSet.contains(bc));
		assertTrue ("testDentedTrapezoid: segment set is inaccurate", segSet.contains(cd));
		
		assertFalse ("testDentedTrapezoid: segment set is always true", segSet.contains(af));
		assertFalse ("testDentedTrapezoid: segment set is always true", segSet.contains(cf));
		
	}

	@Test
	public void testDentedTrapezoid() {
		
		String file = "JSON tests/dentedtrapezoid.json";
		
		FigureNode fn;
		
		fn = InputFacade.extractFigure(file);
		
		SimpleEntry<PointDatabase, Set<Segment>> geoMap=(SimpleEntry<PointDatabase, Set<Segment>>) InputFacade.toGeometryRepresentation(fn);
		assertTrue (fn instanceof FigureNode);
		
		PointDatabase pd = geoMap.getKey();
		Point a=new Point("A", 3,3);
		Point b=new Point("B", 8, 3);
		Point c=new Point("C", 6, 4);
		Point d=new Point("D", 8, 5);
		Point e = new Point("E", 5,5);
		
		Point dummyPoint = new Point("Bob", 0, 4);
		
		assertTrue ("testDentedTrapezoid: pointDatabase is inaccurate", a.equals(pd.getPoint(a)));
		assertTrue ("testDentedTrapezoid: pointDatabase is inaccurate", b.equals(pd.getPoint(b)));
		assertTrue ("testDentedTrapezoid: pointDatabase is inaccurate", c.equals(pd.getPoint(c)));
		assertTrue ("testDentedTrapezoid: pointDatabase is inaccurate", d.equals(pd.getPoint(d)));
		assertTrue ("testDentedTrapezoid: pointDatabase is inaccurate", e.equals(pd.getPoint(e)));
		
		assertFalse ("testDentedTrapezoid: pointDatabase is always true", a.equals(pd.getPoint(e)));
		assertFalse ("testDentedTrapezoid: pointDatabase is always true", e.equals(pd.getPoint(d)));
		assertFalse ("testDentedTrapezoid: pointDatabase is always true", dummyPoint.equals(pd.getPoint(c)));
		
		
		Set<Segment> segSet = geoMap.getValue();
		Segment ab = new Segment(a,b);
		Segment ae = new Segment(a,e);
		Segment bc = new Segment(b,c);
		Segment dummy_b = new Segment(dummyPoint,b);
		
		
		Segment dc = new Segment(d, c);
		
		Segment cd = new Segment(c, d); //This is in the seg list?
		
		assertTrue ("testDentedTrapezoid: segment set is inaccurate",segSet.contains(ab));
		assertTrue ("testDentedTrapezoid: segment set is inaccurate", segSet.contains(ae));
		assertTrue ("testDentedTrapezoid: segment set is inaccurate", segSet.contains(bc));
		
		
		assertTrue ("testDentedTrapezoid: segment set is inaccurate", segSet.contains(dc));
		assertTrue ("testDentedTrapezoid: segment set is inaccurate", segSet.contains(cd));
		
		
		assertFalse ("testDentedTrapezoid: segment set is always true",segSet.contains(dummy_b));
		
	}
	
}
