package preprocessor;

import java.util.*;
import geometry_objects.Segment;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import preprocessor.delegates.ImplicitPointPreprocessor;

public class Preprocessor
{
	// The explicit points provided to us by the user.
	// This database will also be modified to include the implicit
	// points (i.e., all points in the figure).
	protected PointDatabase _pointDatabase;

	// Minimal ('Base') segments provided by the user
	protected Set<Segment> _givenSegments;

	// The set of implicitly defined points caused by segments
	// at implicit points.
	protected Set<Point> _implicitPoints;

	// The set of implicitly defined segments resulting from implicit points.
	protected Set<Segment> _implicitSegments;

	// Given all explicit and implicit points, we have a set of
	// segments that contain no other subsegments; these are minimal ('base') segments
	// That is, minimal segments uniquely define the figure.
	protected Set<Segment> _allMinimalSegments;

	// A collection of non-basic segments
	protected Set<Segment> _nonMinimalSegments;

	// A collection of all possible segments: maximal, minimal, and everything in between
	// For lookup capability, we use a map; each <key, value> has the same segment object
	// That is, key == value. 
	protected Map<Segment, Segment> _segmentDatabase;
	public Map<Segment, Segment> getAllSegments() { return _segmentDatabase; }

	public Preprocessor(PointDatabase points, Set<Segment> segments)
	{
		_pointDatabase  = points;
		_givenSegments = segments;
		
		_segmentDatabase = new HashMap<Segment, Segment>();
		
		analyze();
	}

	/**
	 * Invoke the precomputation procedure.
	 */
	public void analyze()
	{
		//
		// Implicit Points
		//
		_implicitPoints = ImplicitPointPreprocessor.compute(_pointDatabase, _givenSegments.stream().toList());

		//
		//Implicit Segments attributed to implicit points
		
		_implicitSegments = computeImplicitBaseSegments(_implicitPoints);

		//
		// Combine the given minimal segments and implicit segments into a true set of minimal segments
		//     *givenSegments may not be minimal
		//     * implicitSegmen
		//
		_allMinimalSegments = identifyAllMinimalSegments(_implicitPoints, _givenSegments, _implicitSegments);

		//
		// Construct all segments inductively from the base segments
		//
		_nonMinimalSegments = constructAllNonMinimalSegments(_allMinimalSegments);

		//
		// Combine minimal and non-minimal into one package: our database
		//
		_allMinimalSegments.forEach((segment) -> _segmentDatabase.put(segment, segment));
		_nonMinimalSegments.forEach((segment) -> _segmentDatabase.put(segment, segment));
	}

	public Set<Segment> computeImplicitBaseSegments(Set<Point> _implicitPoints){
		
		Set<Segment> impliedSeg = new HashSet<Segment>();
		
		for (Point p: _implicitPoints) {
			
			for (Segment segment: _givenSegments) {
				if(segment.pointLiesOn(p)) {
					
					impliedSeg.add(new Segment(p, segment.getPoint1()));	
					impliedSeg.add(new Segment(p, segment.getPoint2()));
				}
			}
		}
		return impliedSeg;
	}
	
	public Set<Segment> identifyAllMinimalSegments(Set<Point> _implicitPoints, Set<Segment> _givenSegments, Set<Segment> _implicitSegments){

		//Updating given segments/PDB
		Set<Segment> minimal = new HashSet<Segment>();
		
		Set<Segment> total = new HashSet<Segment>();
		total.addAll(_implicitSegments);
		total.addAll(_givenSegments);

		Set<Point> totalP = new HashSet<Point>();
		for (Point p : _implicitPoints) {
			totalP.add(p);
		}
		for (Point p : _pointDatabase.getPoints()) {
			totalP.add(p);
		}


		//Loop through segments, see if they have any other points on them.
		for(Segment s : total) {
			Point start = s.getPoint1();
			for(Point end : s.collectOrderedPointsOnSegment(totalP)){

				//Break them up into minimal segments
				if (start.equals(end))continue;
				minimal.add(new Segment(start, end));
				start = end;
			}
		}
		return minimal;
	}


	private Set<Segment> constructAllNonMinimalSegments(Set<Segment> _allMinimalSegments) {

		Set<Segment> nonMinimal = new HashSet<Segment>();
		
		for (Point start : _pointDatabase.getPoints()){
			for (Point end : _pointDatabase.getPoints()){
				if (new Segment (start, end).collectOrderedPointsOnSegment(_pointDatabase.getPoints()).size() > 2) {
					nonMinimal.add(new Segment (start, end));
				}
			}
		}
		return nonMinimal;
	}
}


