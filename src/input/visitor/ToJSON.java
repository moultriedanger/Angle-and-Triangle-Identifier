package input.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import input.components.*;
import input.parser.JSON_Constants;


public class ToJSON implements ComponentNodeVisitor
{

	@Override
	public Object visitFigureNode(FigureNode node, Object o) {
		JSONObject fig=new JSONObject();
		int level=0;
		JSONObject cont=new JSONObject();
		
		cont.put(JSON_Constants.JSON_DESCRIPTION, node.getDescription());

		cont.put(JSON_Constants.JSON_POINT_S, visitPointNodeDatabase(node.getPointsDatabase(), level));
		cont.put(JSON_Constants.JSON_SEGMENTS, visitSegmentDatabaseNode(node.getSegments(), level));

		fig.put(JSON_Constants.JSON_FIGURE, cont);
		
		return fig;
	}

	@Override
	public Object visitSegmentDatabaseNode(SegmentNodeDatabase node, Object o) {
		JSONArray uniqueList=new JSONArray();
		//gets the list of segments
		HashMap<PointNode, LinkedHashSet<PointNode>> adjList = node.getadjList();
		//creates a list of keys and then transfers it into a list for there to be order
		Set<PointNode> keySet=adjList.keySet();
		List<PointNode> keyList=new ArrayList<>(keySet);
		//iterates through the keys
		for (PointNode pn1: keyList) {
			//resets the segDict to empty each time, making a new object
			JSONObject segDict=new JSONObject();
			int i=keyList.indexOf(pn1);
			//resets segList, the list we add the segments
			LinkedHashSet<String> segList=new LinkedHashSet<>();
			//iterates through the values associated key
			for (PointNode pn2: adjList.get(pn1)) {
				//sees if the pairing has already been used, makes the 
				if (i<keyList.indexOf(pn2)) segList.add(pn2.getName());
			}
			//makes sure an empty list isn't being added
			if (!segList.isEmpty()) {
				//adds the pairing to the object
				segDict.put(pn1.getName(), segList);
				uniqueList.put(segDict);
			}
		}
		return uniqueList;
	}

	@Override
	public Object visitSegmentNode(SegmentNode node, Object o) {
		
		return null;
	}

	@Override
	public Object visitPointNode(PointNode node, Object o) {
		JSONObject point=new JSONObject();
		point.put(JSON_Constants.JSON_NAME, node.getName());
		point.put(JSON_Constants.JSON_X, node.getX());
		point.put(JSON_Constants.JSON_Y, node.getY());
		return point;		
	}

	@Override
	public Object visitPointNodeDatabase(PointNodeDatabase node, Object o) {
		JSONArray pnd=new JSONArray();
		LinkedHashSet<PointNode> points=node.getPoints();
		for (PointNode p: points) {
			pnd.put(visitPointNode(p,o));
		}
		return pnd;
	}
}