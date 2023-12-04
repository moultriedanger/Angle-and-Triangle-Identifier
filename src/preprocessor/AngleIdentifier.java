package preprocessor;

import java.util.List;
import java.util.Map;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.angle.Angle;
import geometry_objects.angle.AngleEquivalenceClasses;
import geometry_objects.angle.comparators.AngleStructureComparator;
import geometry_objects.points.Point;

public class AngleIdentifier
{
	protected AngleEquivalenceClasses _angles;
	protected Map<Segment, Segment> _segments; // The set of ALL segments for this figure

	public AngleIdentifier(Map<Segment, Segment> segments)
	{
		_segments = segments;
	}

	/*
	 * Compute the figure triangles on the fly when requested; memoize results for subsequent calls.
	 */
	public AngleEquivalenceClasses getAngles()
	{
		if (_angles != null) return _angles;
		//Comparator c=AngleStructureComparator.;
		
		_angles = new AngleEquivalenceClasses(new AngleStructureComparator());
		computeAngles();
		return _angles;
	}

	private void computeAngles()
	{
		for (Segment segment1: _segments.values()) {
			for (Segment segment2: _segments.values()) {
				//Check if the segments are not the same -- we do not want to waste time comparing the same segment/ray
				if (!(segment1.equals(segment2))) {
					Point sharedvertex = segment1.sharedVertex(segment2);
					//Check if the sharedVertex is null -- a null vertex means we do not have a shared point with two segments/rays
					if (sharedvertex != null) {
						Angle angle = new Angle(segment1, segment2);
						_angles.add(angle);
					} 	
				}
			}
		}
	}
}
