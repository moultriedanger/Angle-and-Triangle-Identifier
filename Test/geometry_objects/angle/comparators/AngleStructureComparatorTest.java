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
		
		//should return error
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
		
		//NOTICE: What should an equal angle return
		
		//should return error
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

}
