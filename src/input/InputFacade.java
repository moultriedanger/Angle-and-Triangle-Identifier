/**
* provides a simple way to extract figure and create a dictionary of PointDatabases and Segments
*
* @author Moultrie Dangerfield, Jalen Livingston, and Jack Patteson
* @date 10/31/23
*/
package input;

import static org.junit.jupiter.api.Assertions.*;


import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import geometry_objects.Segment;
import input.builder.GeometryBuilder;
import input.components.FigureNode;
import input.components.PointNodeDatabase;
import input.components.SegmentNodeDatabase;
import input.components.PointNode;
import input.parser.JSONParser;

public class InputFacade
{
	/**
	 * A utility method to acquire a figure from the given JSON file:
	 *     Constructs a parser
	 *     Acquries an input file string.
	 *     Parses the file.
     *
	 * @param filepath the path/name defining the input file
	 * @return a FigureNode object corresponding to the input file.
	 */
	public static FigureNode extractFigure(String filepath)
	{

		GeometryBuilder g=new GeometryBuilder();
		 
		JSONParser parser=new JSONParser(g);
	
		String figureStr = utilities.io.FileUtilities.readFileFilterComments(filepath);
		
		return (FigureNode) parser.parse(figureStr);
	}
	
	/**
	 * 1) Convert the PointNode and SegmentNode objects to a Point and Segment objects 
	 *    (those classes have more meaningful, geometric functionality).
	 * 2) Return the points and segments as a pair.
     *
	 * @param fig -- a populated FigureNode object corresponding to a geometry figure
	 * @return a point database and a set of segments
	 */
	public static Map.Entry<PointDatabase, Set<Segment>> toGeometryRepresentation(FigureNode fig)
	{
		//gets pnd and snd so the PointDatabase and segment Set can be made
		PointNodeDatabase pnd=fig.getPointsDatabase();
		SegmentNodeDatabase snd=fig.getSegments();
		
		PointDatabase pd=getPointDatabase(pnd);
		Set<Segment> segSet=getSegments(snd, pd);
		
		//creates a simple entry of a map to be returned
		SimpleEntry<PointDatabase, Set<Segment>> geoMap= new AbstractMap.SimpleEntry<>(pd, segSet);
		
		return geoMap;
	}

	/**
	 * @param the PointNode database from figure, which contains all the pointNodes and thus points
	 * @return A pointDatabase based on the points that were in the PointNodeDatabase
	 */
    private static PointDatabase getPointDatabase(PointNodeDatabase pnd){
    	//gets list of pointNodes and converts them to a list of Points
    	LinkedHashSet<PointNode> lhs=pnd.getPoints();
    	List<Point> ptList=new ArrayList<Point>();
    	for (PointNode pndPT: lhs) {
    		Point pt=new Point(pndPT.getName(), pndPT.getX(), pndPT.getY());
    		ptList.add(pt);
    	}
    	//constructs a pointDatabase based on the list of Points
    	return new PointDatabase(ptList);
    }
    /**
	 * @param the PointNode database from figure, which contains all the pointNodes and thus points
	 * @param the SegmentNode database from figure, which contains all the segmentNodes and thus segments
	 * @return A set of Segments based on the segments that were in the SegmentNodeDatabase
	 */
    private static Set<Segment> getSegments(SegmentNodeDatabase snd, PointDatabase pd){
    	//gets the list of segments from the SND and then makes it iterable
    	HashMap<PointNode, LinkedHashSet<PointNode>> adjList=snd.getadjList();
		Set<PointNode> setKey = adjList.keySet();
		Set<Segment> segSet= new LinkedHashSet<>();
		//iterates over the keys of the segments turning PointNodes into Points
		for(PointNode pn1: setKey) {
			Point pt1=pd.getPoint(pn1.getX(), pn1.getY());
			//iterates through associated PointNodes of pn1, changing them to Points
			for (PointNode pn2: (adjList.get(pn1))){
				Point pt2=pd.getPoint(pn2.getX(), pn2.getY());
				//makes segments and adds points to the segments set
				Segment seg=new Segment(pt1, pt2);
				segSet.add(seg);
			}
		}
		return segSet;
    }
}
