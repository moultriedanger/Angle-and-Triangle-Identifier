package preprocessor.preprocessor;

import static org.junit.jupiter.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	void testCompute() {
		 PointDatabase pd = new PointDatabase();
		 
		 pd.put("D", 0, 0);
		 pd.put("E", 6, 0);
		 pd.put("B", 2, 4);
		 pd.put("C", 4, 4);
		 pd.put("A", 3, 6);
		 
		 Point D = new Point("D", 0,0);
		 Point E = new Point("E", 6,0);
		 Point B = new Point("B", 2,4);
		 Point C = new Point("C", 4,4);
		 Point A = new Point("A", 3,6);
		 
		 
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
		 
		 System.out.println(implicitPoints.toString());
		 
	}

}
