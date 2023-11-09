package input.builder;

import java.util.List;

import input.components.*;


public class GeometryBuilder extends DefaultBuilder {
	
	public GeometryBuilder() {

	}
	
	@Override
	public FigureNode buildFigureNode(String description, PointNodeDatabase points, SegmentNodeDatabase segments) {
		
		return new FigureNode(description, points, segments);
	}
	
	@Override
	public SegmentNodeDatabase buildSegmentNodeDatabase()
    {
        return new SegmentNodeDatabase();
    }
	
    @Override
    public void addSegmentToDatabase(SegmentNodeDatabase segments, PointNode from, PointNode to)
    {
    	if (segments != null) segments.addUndirectedEdge(from, to);
    }
    
    @Override
    public SegmentNode buildSegmentNode(PointNode pt1, PointNode pt2)
    {
        return new SegmentNode(pt1,pt2);
    }
    
    @Override
    public PointNodeDatabase buildPointDatabaseNode(List<PointNode> points)
    {
    	PointNodeDatabase pnd = new PointNodeDatabase();
    	
    	for(PointNode p: points) {
    		pnd.put(p);
    	}
    	return pnd;
    }
    
    @Override
    public PointNode buildPointNode(String name, double x, double y)
    {
        return new PointNode(name, x,y);
    }
}