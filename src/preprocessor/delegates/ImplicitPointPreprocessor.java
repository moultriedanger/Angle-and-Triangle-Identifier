package preprocessor.delegates;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import geometry_objects.Segment;
import geometry_objects.delegates.intersections.SegmentIntersectionDelegate;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;

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
		Set<Point> implicitPoints = new LinkedHashSet<Point>();

		//Loop through the segments, see if they intersect each other
		List<Segment> intersectionSeg = givenSegments;
		
		for (Segment s : givenSegments) {
			//Remove it so no duplicate points are made
			intersectionSeg.remove(s);
			for (Segment s2 : intersectionSeg) {
				//See if they intersect
				Point p = SegmentIntersectionDelegate.findIntersection(s, s2);
				if (p != null && givenPoints.getPoint(p) == null) implicitPoints.add(p);
			}
		}
		return implicitPoints;

	}

}
