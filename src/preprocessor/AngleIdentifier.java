package preprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	 * Compute the figure triangles on the fly when requested; memorize results for subsequent calls.
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
		Set<Segment> setSeg = _segments.keySet();
		List<Segment> segs= new ArrayList<Segment>(setSeg);
		for (int i=0; i<segs.size(); i++) {
			Segment segment1=segs.get(i);
			for (int j=i+1; j<segs.size(); j++) {
				Segment segment2=segs.get(j);
				//Check if the segments are not the same -- we do not want to waste time comparing the same segment/ray
				if (!(segment1.equals(segment2))) {
					Point sharedvertex = segment1.sharedVertex(segment2);
					//Check if the sharedVertex is null -- a null vertex means we do not have a shared point with two segments/rays
					if (sharedvertex != null && !segment1.pointLiesBetweenEndpoints(segment2.other(sharedvertex)) && !segment2.pointLiesBetweenEndpoints(segment1.other(sharedvertex))) {
						
						Angle angle = null;
						//System.out.println(segment1.getPoint1().getName() + segment1.getPoint2().getName() + " and " + segment2.getPoint1().getName() + segment2.getPoint2().getName());
						try {
							angle = new Angle(segment1, segment2);
						} catch (FactException e) {
							System.out.println("Compute Angle's given segments were bad");
						}
						if (!_angles.contains(angle)) _angles.add(angle);
					} 	
				}
			}
		}
	}
}

