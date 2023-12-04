package preprocessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.Triangle;

public class TriangleIdentifier
{
	protected Set<Triangle>         _triangles;
	protected Map<Segment, Segment> _segments; // The set of ALL segments for this figure.

	public TriangleIdentifier(Map<Segment, Segment> segments)
	{
		_segments = segments;
	}
	
	/*
	 * Compute the figure triangles on the fly when requested;
	 * memoize results for subsequent calls.
	 */
	public Set<Triangle> getTriangles() throws FactException
	{
		if (_triangles != null) return _triangles;

		_triangles = new HashSet<Triangle>();

		computeTriangles();

		return _triangles;
	}

	private void computeTriangles() throws FactException
	{
		for (Segment seg1: _segments.values()) {
			for (Segment seg2: _segments.values()) {
				for (Segment seg3: _segments.values()) {
					if (!(seg1.equals(seg2) && !(seg2.equals(seg3)))){
						if ((seg1.sharedVertex(seg2) != null) && (seg2.sharedVertex(seg3) != null) && (seg3.sharedVertex(seg1) != null)){
							
							List<Segment> segs = new ArrayList<Segment>();
							segs.add(seg1);
							segs.add(seg2);
							segs.add(seg3);
							
							Triangle newTriangle = new Triangle(segs);
							
							_triangles.add(newTriangle);
						}
					} 
				}
			}
		}
	}
}
