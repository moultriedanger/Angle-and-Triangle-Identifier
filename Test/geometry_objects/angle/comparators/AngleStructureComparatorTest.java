package geometry_objects.angle.comparators;

import static org.junit.jupiter.api.Assertions.*;

import geometry_objects.Segment;
import geometry_objects.angle.Angle;
import geometry_objects.points.*;

import org.junit.jupiter.api.Test;

import exceptions.FactException;

class AngleStructureComparatorTest extends AngleStructureComparator {

	@Test
	void horizontalAndVerticalComparatorTest() throws FactException {
		/**
		 * D
		 * |
		 * |
		 * |
		 * B  F
		 * | /
		 * |/
		 * A---C-E
		 * 
		 */
		Point A=new Point("A", 0, 0);
		Point B=new Point("B", 0, 2);
		Point C=new Point("C", 3, 0);
		Point D=new Point("D", 0, 5);
		Point E=new Point("E", 4, 0);
		Point F=new Point("F", 2, 2);
		
		Segment AB=new Segment(A, B);
		Segment AC=new Segment(A, C);
		Segment AE=new Segment(A, E);
		Segment AD=new Segment(A, D);
		Segment AF=new Segment(A, F);
		//necessary for straight angles
		Segment BD=new Segment(B, D);
		Segment CE=new Segment(C, E);
		
		Angle BAC= new Angle(AB, AC);
		Angle DAC=new Angle(AD, AC);
		Angle BAE=new Angle(AB, AE);
		Angle DAE=new Angle(AD, AE);
		Angle CAF=new Angle(AC, AF);
		Angle DAF=new Angle(AD, AF);

		
		
		//reverse rays
		Angle CAB=new Angle(AC, AB);
		Angle CAD=new Angle(AC, AD);
		Angle EAB=new Angle(AE, AB);
		Angle EAD=new Angle(AE, AD);
		Angle FAC=new Angle(AF, AC);
		Angle FAD=new Angle(AF, AD);
		
		//NOTICE: What should an equal angle return
		
		//not equivalent angles
		assertEquals (compare(BAC, CAF), AngleStructureComparator.STRUCTURALLY_INCOMPARABLE);
		assertEquals (compare(DAC, DAF), AngleStructureComparator.STRUCTURALLY_INCOMPARABLE);
		assertEquals (compare(DAE, CAF), AngleStructureComparator.STRUCTURALLY_INCOMPARABLE);
		assertEquals (compare(BAE, DAF), AngleStructureComparator.STRUCTURALLY_INCOMPARABLE);
		
		//left angle should be greater
		assertEquals (compare(DAC, BAC), 1);
		assertEquals (compare(DAC, CAB), 1);
		assertEquals (compare(BAE, BAC), 1);
		assertEquals (compare(BAE, CAB), 1);
		assertEquals (compare(DAE, DAC), 1);
		assertEquals (compare(DAE, CAD), 1);
		
		//reverse
		assertEquals (compare(CAD, BAC), 1);
		assertEquals (compare(CAD, CAB), 1);
		assertEquals (compare(EAB, BAC), 1);
		assertEquals (compare(EAB, CAB), 1);
		assertEquals (compare(EAD, DAC), 1);
		assertEquals (compare(EAD, CAD), 1);				
		
		//right angle should be greater then
		assertEquals (compare(BAC, DAC), -1);
		assertEquals (compare(CAB, DAC), -1);
		assertEquals (compare(BAC, BAE), -1);
		assertEquals (compare(CAB, BAE), -1);
		assertEquals (compare(DAC, DAE), -1);
		assertEquals (compare(CAD, DAE), -1);
		
		//reverse
		assertEquals (compare(BAC, CAD), -1);
		assertEquals (compare(CAB, CAD), -1);
		assertEquals (compare(BAC, EAB), -1);
		assertEquals (compare(CAB, EAB), -1);
		assertEquals (compare(DAC, EAD), -1);
		assertEquals (compare(CAD, EAD), -1);
		
		//bigger rays between angles alternate
		assertEquals(compare(DAC, BAE), 0);
		assertEquals(compare(BAE, DAC), 0);
		
		//reverse
		assertEquals(compare(CAD, EAB), 0);
		assertEquals(compare(EAB, CAD), 0);
		
	}
	@Test
	void slopeComparatorTest() throws FactException {
		/**
		 *		  D
		 *       /
		 *      /
		 *     B
		 *    /
		 *   /
		 * 	/
		 * A------F
		 *  \
		 *   \
		 *    C
		 *     \
		 *      \
		 *       E
		 * 
		 */
		Point A=new Point("A", -4, -1);
		Point B=new Point("B", -2, 1);
		Point C=new Point("C", -2, -2);
		Point D=new Point("D", -1, 2);
		Point E=new Point("E", 0, -3);
		Point F=new Point("F", -1, -1);
		
		Segment AB=new Segment(A, B);
		Segment AC=new Segment(A, C);
		Segment AE=new Segment(A, E);
		Segment AD=new Segment(A, D);
		Segment AF=new Segment(A, F);
		
		Angle BAC= new Angle(AB, AC);
		Angle DAC=new Angle(AD, AC);
		Angle BAE=new Angle(AB, AE);
		Angle DAE=new Angle(AD, AE);
		Angle CAF=new Angle(AC, AF);
		Angle DAF=new Angle(AD, AF);
		
		//reverse rays
		Angle CAB=new Angle(AC, AB);
		Angle CAD=new Angle(AC, AD);
		Angle EAB=new Angle(AE, AB);
		Angle EAD=new Angle(AE, AD);
		Angle FAC=new Angle(AF, AC);
		Angle FAD=new Angle(AF, AD);
		
		//not equivalent angles
		assertEquals (compare(BAC, CAF), AngleStructureComparator.STRUCTURALLY_INCOMPARABLE);
		assertEquals (compare(DAC, DAF), AngleStructureComparator.STRUCTURALLY_INCOMPARABLE);
		assertEquals (compare(DAE, CAF), AngleStructureComparator.STRUCTURALLY_INCOMPARABLE);
		assertEquals (compare(BAE, DAF), AngleStructureComparator.STRUCTURALLY_INCOMPARABLE);
		
		//left angle should be greater
		assertEquals (compare(DAC, BAC), 1);
		assertEquals (compare(DAC, CAB), 1);
		assertEquals (compare(BAE, BAC), 1);
		assertEquals (compare(BAE, CAB), 1);
		assertEquals (compare(DAE, DAC), 1);
		assertEquals (compare(DAE, CAD), 1);
		
		//reverse
		assertEquals (compare(CAD, BAC), 1);
		assertEquals (compare(CAD, CAB), 1);
		assertEquals (compare(EAB, BAC), 1);
		assertEquals (compare(EAB, CAB), 1);
		assertEquals (compare(EAD, DAC), 1);
		assertEquals (compare(EAD, CAD), 1);				
		
		//right angle should be greater then
		assertEquals (compare(BAC, DAC), -1);
		assertEquals (compare(CAB, DAC), -1);
		assertEquals (compare(BAC, BAE), -1);
		assertEquals (compare(CAB, BAE), -1);
		assertEquals (compare(DAC, DAE), -1);
		assertEquals (compare(CAD, DAE), -1);
		
		//reverse
		assertEquals (compare(BAC, CAD), -1);
		assertEquals (compare(CAB, CAD), -1);
		assertEquals (compare(BAC, EAB), -1);
		assertEquals (compare(CAB, EAB), -1);
		assertEquals (compare(DAC, EAD), -1);
		assertEquals (compare(CAD, EAD), -1);
		
		//bigger rays between angles alternate
		assertEquals(compare(DAC, BAE), 0);
		assertEquals(compare(BAE, DAC), 0);
		
		//reverse
		assertEquals(compare(CAD, EAB), 0);
		assertEquals(compare(EAB, CAD), 0);
		
	}
	
	@Test
	void lineComparatorTest() throws FactException {
		/**
		 *             E
		 *            /
		 *           D
		 *          /
		 *         /
		 *        /
		 *       C
		 *      /|
		 *     / |
		 *    B  |
		 *   /   |
		 *  /    |
		 * A     |
		 *       F
		 */
		Point A=new Point("A", -3, -3);
		Point B=new Point("B", -1, -1);
		Point C=new Point("C", 1, 1);
		Point D=new Point("D", 4, 4);
		Point E=new Point("E", 5, 5);
		Point F=new Point("F", 1, -4);
		
		Segment AB=new Segment(B, A);
		Segment BD=new Segment(B, D);
		Segment AC=new Segment(C, A);
		Segment CE=new Segment(C, E);
		Segment BC=new Segment(C, B);
		Segment CD=new Segment(C, D);
		Segment CF=new Segment(C, F);
		
		Angle ABC=new Angle(AB, BC);
		Angle ABD=new Angle(AB, BD);
		Angle ACE=new Angle(AC, CE);
		Angle BCD=new Angle(BC, CD);
		Angle BCF=new Angle(BC, CF);
		
		
		Angle CBA=new Angle(BC, AB);
		Angle DBA=new Angle(BD, AB);
		Angle ECA=new Angle(CE, AC);
		Angle DCB=new Angle(CD, BC);
		Angle FCB=new Angle(CF, BC);
		
		//not equivalent angles
		assertEquals (compare(BCD, BCF), AngleStructureComparator.STRUCTURALLY_INCOMPARABLE);
		assertEquals (compare(BCF, BCD), AngleStructureComparator.STRUCTURALLY_INCOMPARABLE);
		assertEquals (compare(DCB, BCF), AngleStructureComparator.STRUCTURALLY_INCOMPARABLE);
		assertEquals (compare(DCB, FCB), AngleStructureComparator.STRUCTURALLY_INCOMPARABLE);
		
		//left angle is greater then right
		//only one ray is bigger the other is equal
		assertEquals (compare(ABD, ABC), 1);
		assertEquals (compare(ABD, CBA), 1);
		assertEquals (compare(DBA, ABC), 1);
		assertEquals (compare(DBA, CBA), 1);
		//both rays are bigger
		assertEquals (compare(ACE, BCD), 1);
		assertEquals (compare(ACE, DCB), 1);
		assertEquals (compare(ECA, BCD), 1);
		assertEquals (compare(ECA, DCB), 1);

		//right angle is greater then left
		//only one ray is bigger the other is equal
		assertEquals (compare(ABC, ABD), -1);
		assertEquals (compare(CBA, ABD), -1);
		assertEquals (compare(ABC, DBA), -1);
		assertEquals (compare(CBA, DBA), -1);
		//both rays are bigger
		assertEquals (compare(BCD, ACE), -1);
		assertEquals (compare(DCB, ACE), -1);
		assertEquals (compare(BCD, ECA), -1);
		assertEquals (compare(DCB, ECA), -1);
		
		//NO TEST: It is not possible for bigger rays between straight angles to alternate
	}
}
