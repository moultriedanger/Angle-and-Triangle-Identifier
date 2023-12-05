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
		for (int i=0; i<segs.size(); i++) {
			Segment side1 = segs.get(i);
			for (int j=i+1; j<segs.size(); j++) {
				Point sharedVert=side1.sharedVertex(segs.get(j));
				if (sharedVert != null) {
					Segment side2=segs.get(j);
					for (int k=j+1; k<segs.size(); k++) {
						Segment side3 = segs.get(k);
						if (side3.equals(new Segment(side1.other(sharedVert), side2.other(sharedVert))) && !side3.isCollinearWith(side1)) {
							List<Segment> triangleSegs = new ArrayList<Segment>();
							triangleSegs.add(side1);
							triangleSegs.add(side2);
							triangleSegs.add(side3);
//							for (Segment s: triangleSegs) {
//								System.out.println(s.getPoint1().getName() + s.getPoint2().getName());
//							}
//							System.out.println();
							_triangles.add(new Triangle (triangleSegs));
					}
				}	
			}
		}
	}
		
		
		
//		for (Segment seg1: _segments.values()) {
//			for (Segment seg2: _segments.values()) {
//				
//				for (Segment seg3: _segments.values()) {
//					if (!(seg1.equals(seg2) && !(seg2.equals(seg3)))){
//						if ((seg1.sharedVertex(seg2) != null) && (seg2.sharedVertex(seg3) != null) && (seg3.sharedVertex(seg1) != null)){
//							
//							List<Segment> segs = new ArrayList<Segment>();
//							segs.add(seg1);
//							segs.add(seg2);
//							segs.add(seg3);
//							
//							System.out.println(seg1.getPoint1().getName() + seg1.getPoint2().getName());
//							System.out.println(seg2.getPoint1().getName() + seg2.getPoint2().getName());
//							System.out.println(seg3.getPoint1().getName() + seg3.getPoint2().getName());
//							
//							Triangle newTriangle = new Triangle(segs);
//							
//							System.out.println("high 5");
//							
//							_triangles.add(newTriangle);
//						}
//					} 
//				}
//			}
//		}
	}
}
