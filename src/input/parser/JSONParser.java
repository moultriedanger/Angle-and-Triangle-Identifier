package input.parser;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import input.builder.DefaultBuilder;
import input.components.*;
import input.components.SegmentNodeDatabase;
import input.exception.ParseException;

public class JSONParser
{
	protected ComponentNode  _astRoot;
	protected DefaultBuilder _builder;

//	public JSONParser()
//	{
//		_astRoot = null;
//		_builder = null;
//	}
	
	public JSONParser(DefaultBuilder builder)
	{
		_astRoot = null;
		_builder = builder;
	}

	private void error(String message)
	{
		throw new ParseException("Parse error: " + message);
		
	}

	public ComponentNode parse(String str) throws ParseException
	{
//		if(_builder==null) {
//			_builder.buildFigureNode(null, null, null);
//		}
		// Parsing is accomplished via the JSONTokenizer class.
		JSONTokener tokenizer = new JSONTokener(str);
		JSONObject  JSONFigure = (JSONObject)tokenizer.nextValue();
		if (!JSONFigure.has("Figure")) error("Figure does not exist in JSON file");
		JSONObject fig = JSONFigure.getJSONObject("Figure");
		
		String desc = fig.getString("Description");
		JSONArray jPoints = fig.getJSONArray("Points");
		
		PointNodeDatabase pnd = getPND(jPoints);
		
		SegmentNodeDatabase snd = handleSegments(pnd, fig);
		
		return _builder.buildFigureNode(desc, pnd, snd);
	}

	/*
	 * Creates a pointnodedatabse using from the JSON file
	 *@param JSONArray the array of points in the JSON file
	*/
	public PointNodeDatabase getPND(JSONArray jPoints) {
		
		ArrayList<PointNode> lst = new ArrayList<PointNode>();
		
		for(Object item: jPoints)
		{
			JSONObject jObj = (JSONObject) item;
			String name = jObj.getString("name");
			Double x = jObj.getDouble("x");
			Double y = jObj.getDouble("y");			
			
			lst.add(_builder.buildPointNode(name, x, y));
		}
		return _builder.buildPointDatabaseNode(lst);
	}
	
	/*
	 * Finds the segments JSONObject and then iterates through it to make a segmentNodeDatabase
	 *@param points if a pointnodedatabase so we can call getPoint method
	 *@param fig is so we can have assess to the JSON File
	*/
	private SegmentNodeDatabase handleSegments(PointNodeDatabase points, JSONObject fig)
	{
		JSONArray json_adjLists = fig.getJSONArray("Segments");
		
		SegmentNodeDatabase snd = _builder.buildSegmentNodeDatabase();
		
		for (int a = 0; a < json_adjLists.length(); a++){
			
			//gets a single dictionary in adj list
			JSONObject dict = json_adjLists.getJSONObject(a);
			
			//dictionary key array. Only 1 element
			JSONArray key = dict.names();
			
			//Create array of values associated with key
			JSONArray values = dict.getJSONArray(key.get(0).toString());
			
			PointNode pt1 = points.getPoint(key.get(0).toString());
			
			for(int i = 0; i<values.length(); i++) {
				//Create an edge with the key and value
				_builder.addSegmentToDatabase(snd, pt1, points.getPoint(values.get(i).toString()));
			}
		}
		return snd;
	}
}