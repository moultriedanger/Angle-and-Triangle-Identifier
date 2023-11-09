package preprocessor.delegates;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import geometry_objects.Segment;
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

        // TODO
		//Loop through the segments, see if they intersect each other
		List<Segment> intersectionSeg = givenSegments;
		for (Segment s : givenSegments) {
			intersectionSeg.remove(s);
			for (Segment s2 : intersectionSeg) {
				//See if they intersect
				if (intersect method call here) implicitPoints.add(intersection method call);
			}
		}

		return implicitPoints;
	}

}
