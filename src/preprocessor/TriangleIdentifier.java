package preprocessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.Triangle;
import geometry_objects.points.Point;

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
		Set<Segment> setSeg = _segments.keySet();
		List<Segment> segs= new ArrayList<Segment>(setSeg);
		//gets first side of the traingle
		for (int i=0; i<segs.size(); i++) {
			Segment side1 = segs.get(i);
			for (int j=i+1; j<segs.size(); j++) {
				Point sharedVert=side1.sharedVertex(segs.get(j));
				//if side1 and the segment at index j share a point then find the third side
				if (sharedVert != null) {
					Segment side2=segs.get(j);
					for (int k=j+1; k<segs.size(); k++) {
						Segment side3 = segs.get(k);
						//if segment at index k is the segment between side1 and side2 other points and side3 isn't collinear with them
						if (side3.equals(new Segment(side1.other(sharedVert), side2.other(sharedVert))) && !side3.isCollinearWith(side1)) {
							List<Segment> triangleSegs = new ArrayList<Segment>();
							triangleSegs.add(side1);
							triangleSegs.add(side2);
							triangleSegs.add(side3);
							//computes triangle with the given
							_triangles.add(new Triangle (triangleSegs));
						}
					}	
				}
			}
		}
	}
}
