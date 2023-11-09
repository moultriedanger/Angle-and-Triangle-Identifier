package input.components;
import java.util.ArrayList;

import utilities.io.*;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

import java.util.Set;

import input.visitor.ComponentNodeVisitor;
/**
 * Allows for us to keep track of the relationships between segmentNodes, using maps
 * @author Moultrie Dangerfield and Jack Patterson
 * @version 09/07/2023
 */
public class SegmentNodeDatabase implements ComponentNode{
	protected HashMap<PointNode, LinkedHashSet<PointNode>> _adjLists;
	public HashMap<PointNode, LinkedHashSet<PointNode>> getadjList() { return _adjLists; }

	public SegmentNodeDatabase() {
		_adjLists= new HashMap<PointNode, LinkedHashSet<PointNode>>();
	}
	public SegmentNodeDatabase(HashMap<PointNode, LinkedHashSet<PointNode>> map) {
		_adjLists= new HashMap<PointNode, LinkedHashSet<PointNode>>(map);
	}
	/*
	 * Reports the number of undirected edges
	 *@return int of undirected edges
	 */
	public int numUndirectedEdges() {
		if(_adjLists.isEmpty())return 0;

		int count=0;
		Set<PointNode> setKey = _adjLists.keySet();
		for(PointNode pn1: setKey) {
			for (PointNode pn2: _adjLists.get(pn1)){
				if (_adjLists.get(pn2).contains(pn1)) {
					count++;
				}
			}
		}
		return count/2;
	}
	
	/*
	 * Creates a one way connection between pointnodes
	 *@param PointNode
	 *@param Pointnode
	 */
	public void addDirectedEdge(PointNode pn1, PointNode pn2) {
		//		if (pn1.equals(pn2)) {return;}
		//checks if the key value pn1 exist, if not it creates it and adds pn2 to it's values
		if (!_adjLists.containsKey(pn1)) {
			LinkedHashSet<PointNode> pnSet= new LinkedHashSet<PointNode>();
			_adjLists.put(pn1, pnSet);
		}
		LinkedHashSet<PointNode> pnSet=_adjLists.get(pn1);
		//adds pn2 to the key, if key exists and doesn't contain the pn2
		if(!pnSet.contains(pn2)) {
			_adjLists.get(pn1).add(pn2);
		}
	}
	/*
	 * Creates a two way connection between pointnodes
	 *@param PointNode
	 *@param Pointnode
	 */
	public void addUndirectedEdge(PointNode pn1, PointNode pn2) {
		addDirectedEdge(pn1, pn2);	
		addDirectedEdge(pn2, pn1);	
	}
	/*
	 * Adds a list of pointnodes to the value set connected to a pointnode key
	 *@param PointNode
	 *@param list<PointNode>
	 */
	public void addAdjencyList(PointNode pn, List<PointNode> valueList) {
		for (PointNode value: valueList) {
			addDirectedEdge(pn, value);
		}

	}
	/*
	 * Creates a list of the every connection between pointnodes
	 *@return List<SegmentNode>
	 */
	public List<SegmentNode> asSegmentList() {
		List<SegmentNode> segList = new ArrayList<SegmentNode>();
		if(_adjLists.isEmpty()){
			return segList;
		}
		Set<PointNode> setKey = _adjLists.keySet();
		for(PointNode pn1: setKey) {
			//loops through the values of the key equal to pn1
			for (PointNode pn2: (_adjLists.get(pn1))){
				SegmentNode sn=new SegmentNode(pn1, pn2);
				segList.add(sn);
			}
		}
		return segList;
	}
	/*
	 * Creates a list of every connection between pointNodes without repitition.
	 *@return List<SegmentNode>
	 */
	public List<SegmentNode> asUniqueSegmentList(){
		List<SegmentNode> segList = new ArrayList<>();
		if(_adjLists.isEmpty()){
			return segList;
		}
		Set<PointNode> setKey = _adjLists.keySet();
		for(PointNode pn1: setKey) {
			//loops through the values of the key equal to pn1
			for (PointNode pn2: (_adjLists.get(pn1))){
				SegmentNode sn = new SegmentNode(pn1, pn2);
				SegmentNode snOpp = new SegmentNode(pn2, pn1);
				if (!(segList.contains(snOpp))) {
					segList.add(sn);
				}
			}
		}
		return segList;
	}
	
	public Object accept(ComponentNodeVisitor node, Object o) {
		return node.visitSegmentDatabaseNode(this, o); 
	}
}