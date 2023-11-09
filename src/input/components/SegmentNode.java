package input.components;

/**
 * Describes a line between 2 points
 * @author Moultrie Dangerfield and Jack Patterson
 * @version 09/07/2023
 */
public class SegmentNode
{
	protected PointNode _point1;
	protected PointNode _point2;
	
	public PointNode getPoint1() { return _point1; }
	public PointNode getPoint2() { return _point2; }
	
	public SegmentNode(PointNode pt1, PointNode pt2)
	{
		_point1 = pt1;
		_point2 = pt2;
	}
	/*
	* Says if a SegmentNode is made of the same points, then it is equal
	*@param PointNode
	*@return boolean
	*/
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) return false;
		if (!(obj instanceof SegmentNode)) return false;
		
		SegmentNode that = (SegmentNode)obj;
		
		if (that._point1.equals(this._point1) && that._point2.equals(this._point2))
			return true;
			
		if (that._point1.equals(this._point2) && that._point2.equals(this._point1))
			return true;
			
		return false;	
	}
	/*
	* lists the name, x, and y value of both points
	*@param PointNode
	*@return string
	*/
	@Override
    public String toString()
    {
		return _point1.toString() + _point2.toString();
	}
}