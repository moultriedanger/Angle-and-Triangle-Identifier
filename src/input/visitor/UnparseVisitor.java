package input.visitor;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import input.components.*;
import utilities.io.StringUtilities;

//
// This file implements a Visitor (design pattern) with 
// the intent of building an unparsed, String representation
// of a geometry figure.
//
public class UnparseVisitor implements ComponentNodeVisitor
{
	@Override
	public Object visitFigureNode(FigureNode node, Object o)
	{
		// Unpack the input object containing a Stringbuilder and an indentation level
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();

		sb.append("Figure\n{\n");
		sb.append(StringUtilities.indent(level+1));
		sb.append("Description : " + node.getDescription() + "\n");
		sb.append(StringUtilities.indent(level+1));
		sb.append("Points:\n" + StringUtilities.indent(level+1) + "{\n");
		
		node.getPointsDatabase().accept(this, pair);
		
		node.getSegments().accept(this, pair);
		

        return sb;
	}

	@Override
	public Object visitSegmentDatabaseNode(SegmentNodeDatabase node, Object o)
	{
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o); 
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();
		
		sb.append(StringUtilities.indent(level+1));
		sb.append("Segments:\n");
		sb.append(StringUtilities.indent(level+1));
		sb.append("{");
		
		HashMap<PointNode, LinkedHashSet<PointNode>> adjList=node.getadjList();
		Set<PointNode> setKey = adjList.keySet();
		for(PointNode pn1: setKey) {
			sb.append("\n" + StringUtilities.indent(level+2));
			sb.append(pn1.getName()+ " : ");
		
			for (PointNode pn2: (adjList.get(pn1))){
				sb.append(pn2.getName() + " ");
			}
		}
		return sb;
	}

	/**
	 * This method should NOT be called since the segment database
	 * uses the Adjacency list representation
	 */
	@Override
	public Object visitSegmentNode(SegmentNode node, Object o)
	{
		return null;
	}

	@Override
	public Object visitPointNodeDatabase(PointNodeDatabase node, Object o)
	{
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o); 
		StringBuilder sb = pair.getKey();
		int level=pair.getValue();
		LinkedHashSet<PointNode> points=node.getPoints();
		for (PointNode p: points) {
			p.accept(this, pair);
		}
		sb.append(StringUtilities.indent(level+1));
		sb.append("}\n");
		return sb;
		
	}
	
	@Override
	public Object visitPointNode(PointNode node, Object o)
	{
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o); 
		StringBuilder sb = pair.getKey();
		int level=pair.getValue();
		
		sb.append(StringUtilities.indent(level+2));
		sb.append(node.toString());
		return sb;
	}
}