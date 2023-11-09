package input.components;

import input.visitor.ComponentNodeVisitor;
import utilities.math.MathUtilities;

/**
 * A 2D Point (x, y).
 * @author Moultrie Dangerfield and Jack Patterson
 * @version 09/07/2023
 */
public class PointNode implements ComponentNode
{
	protected static final String ANONYMOUS = "__UNNAMED";

	protected double _x;
	public double getX() { return this._x; }

	protected double _y; 
	public double getY() { return this._y; }

	protected String _name; 
	public String getName() { return _name; }

	/**
	 * Create a new Point with the specified coordinates.
	 * @param x The X coordinate
	 * @param y The Y coordinate
	 */
	public PointNode(double x, double y)
	{
		this(ANONYMOUS, x, y);
	}

	/**
	 * Create a new Point with the specified coordinates.
	 * @param name -- The name of the point. (Assigned by the UI)
	 * @param x -- The X coordinate
	 * @param y -- The Y coordinate
	 */
	public PointNode(String name, double x, double y)
	{
		_x= x;
		_y= y;
		_name= name;
	}

	@Override
	public int hashCode()
	{
		return Double.valueOf(_x).hashCode() + Double.valueOf(_y).hashCode();
	}
	/**
	 * Checks if the x and y coordinate are equal based on if they are in the same epsilon..
	 * @param x The X coordinate
	 * @param y The Y coordinate
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PointNode)) {
			return false;
		}
		
		PointNode that = (PointNode)obj;
		
		//check if objects' x and y values are not equal
		if(!MathUtilities.doubleEquals(that._x, this._x)) {
			return false;
		}
		if(!MathUtilities.doubleEquals(that._y, this._y)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString()
    {
		return "Point(" + _name  + ")(" + String.valueOf(_x) + "," + String.valueOf(_y) + ")\n";
	}
	/* Builds a string to describe a figure, uses the FigureNode class, the pointnodedatabase class, and the segmentnodedatabase class.
	 *@param stingbuilder is the string that gets built
	 *@param level is amount of indentations
	*/

	public Object accept(ComponentNodeVisitor node, Object o) {
		return node.visitPointNode(this, o); 
	}

}