package preprocessor.delegates;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import geometry_objects.Segment;
import geometry_objects.delegates.intersections.SegmentIntersectionDelegate;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import geometry_objects.points.PointNamingFactory;

public class ImplicitPointPreprocessor
{
	/**
	 * It is possible that some of the defined segments intersect
	 * and points that are not named; we need to capture those
	 * points and name them.
	 * 
	 * Algorithm:
	 *    loop through each, see if intersect
	 */
	public static Set<Point> compute(PointDatabase givenPoints, List<Segment> givenSegments)
	{
		PointNamingFactory pnf = new PointNamingFactory();
		Set<Point> implicitPoints = new LinkedHashSet<Point>();
		for (Segment s : givenSegments) {
			//System.out.println(s.getPoint1().getName()+s.getPoint2().getName());
			for (Segment s2 : givenSegments) {
				Point p = SegmentIntersectionDelegate.findIntersection(s, s2);
				if (p != null && givenPoints.getPoint(p) == null) {
					implicitPoints.add(pnf.put(p));
					givenPoints.put(null, p.getX(), p.getY());
				}
					
			}
		}
		return implicitPoints;
	}

}
