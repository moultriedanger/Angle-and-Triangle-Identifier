package input.parser;


import static org.junit.jupiter.api.Assertions.*;


import java.util.AbstractMap;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import input.builder.GeometryBuilder;
import input.components.ComponentNode;
import input.components.FigureNode;
import input.exception.ParseException;
import input.visitor.ToJSON;
import input.visitor.UnparseVisitor;

class JSONParserTest
{
	public static ComponentNode runFigureParseTest(String filename)
	{
		GeometryBuilder g=new GeometryBuilder();
		
		JSONParser parser=new JSONParser(g);

		String figureStr = utilities.io.FileUtilities.readFileFilterComments(filename);
		
		return parser.parse(figureStr);
	}
	
	@Test
	void empty_json_string_test()
	{
		JSONParser parser = new JSONParser(null);

		assertThrows(ParseException.class, () -> { parser.parse("{}"); });
	}

	@Test
	void single_triangle_test()
	{
		//
		// The input String ("single_triangle.json") assumes the file is
		// located at the top-level of the project. If you move your input
		// files into a folder, update this String with the path:
		//                                       e.g., "my_folder/single_triangle.json"
		//
		ComponentNode node = JSONParserTest.runFigureParseTest("JSON tests/single_triangle.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparse=new UnparseVisitor();
		unparse.visitFigureNode((FigureNode) node, new AbstractMap.SimpleEntry<StringBuilder, Integer> (sb, 0));
		System.out.println(sb.toString());
		
		ToJSON jsonMaker=new ToJSON();
		int level=3;
		JSONObject jason=(JSONObject) jsonMaker.visitFigureNode((FigureNode) node, level);
		System.out.println(jason.toString(level));
	}
	@Test
	void collinear_line_segments_test()
	{

		ComponentNode node = JSONParserTest.runFigureParseTest("JSON tests/collinear_line_segments.json");

		assertTrue(node instanceof FigureNode);
		
		
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparse=new UnparseVisitor();
		unparse.visitFigureNode((FigureNode) node, new AbstractMap.SimpleEntry<StringBuilder, Integer> (sb, 0));
		System.out.println(sb.toString());
		
		ToJSON jsonMaker=new ToJSON();
		int level=2;
		JSONObject jason=(JSONObject) jsonMaker.visitFigureNode((FigureNode) node, level);
		System.out.println(jason.toString(level));
	}
	@Test
	void crossing_symmetric_triangle_test()
	{

		ComponentNode node = JSONParserTest.runFigureParseTest("JSON tests/crossing_symmetric_triangle.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparse=new UnparseVisitor();		
		unparse.visitFigureNode((FigureNode) node, new AbstractMap.SimpleEntry<StringBuilder, Integer> (sb, 0));
		System.out.println(sb.toString());
		
		ToJSON jsonMaker=new ToJSON();
		int level=2;
		JSONObject jason=(JSONObject) jsonMaker.visitFigureNode((FigureNode) node, level);
		System.out.println(jason.toString(level));
	}
	@Test
	void fully_connected_irregular_polygon_test()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("JSON tests/fully_connected_irregular_polygon.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparse=new UnparseVisitor();
		unparse.visitFigureNode((FigureNode) node, new AbstractMap.SimpleEntry<StringBuilder, Integer> (sb, 0));
		System.out.println(sb.toString());
		
		ToJSON jsonMaker=new ToJSON();
		int level=2;
		JSONObject jason=(JSONObject) jsonMaker.visitFigureNode((FigureNode) node, level);
		System.out.println(jason.toString(level));
	}
	@Test
	void dentedtrapezoidtest()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("JSON tests/dentedtrapezoid.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparse=new UnparseVisitor();
		unparse.visitFigureNode((FigureNode) node, new AbstractMap.SimpleEntry<StringBuilder, Integer> (sb, 0));
		System.out.println(sb.toString());
		
		ToJSON jsonMaker=new ToJSON();
		int level=2;
		JSONObject jason=(JSONObject) jsonMaker.visitFigureNode((FigureNode) node, level);
		System.out.println(jason.toString(level));
	}
	@Test
	void irregular_Hexagon()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("JSON tests/irregular_Hexagon.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparse=new UnparseVisitor();
		unparse.visitFigureNode((FigureNode) node, new AbstractMap.SimpleEntry<StringBuilder, Integer> (sb, 0));
		System.out.println(sb.toString());
		
		
		ToJSON jsonMaker=new ToJSON();
		int level=2;
		JSONObject jason=(JSONObject) jsonMaker.visitFigureNode((FigureNode) node, level);
		System.out.println(jason.toString(level));
	}
	@Test
	void parallelogram()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("JSON tests/parallelogram.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparse=new UnparseVisitor();
		unparse.visitFigureNode((FigureNode) node, new AbstractMap.SimpleEntry<StringBuilder, Integer> (sb, 0));
		System.out.println(sb.toString());
		
		ToJSON jsonMaker=new ToJSON();
		int level=2;
		JSONObject jason=(JSONObject) jsonMaker.visitFigureNode((FigureNode) node, level);
		System.out.println(jason.toString(level));
	}	
}
