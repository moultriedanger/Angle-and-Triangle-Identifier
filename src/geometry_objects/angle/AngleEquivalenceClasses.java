package geometry_objects.angle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import geometry_objects.angle.comparators.AngleStructureComparator;
import utilities.LinkedEquivalenceClass;
import utilities.EquivalenceClasses;
//import utilities.eq_classes.EquivalenceClasses;

/**
 * Given the figure below:
 * 
 *    A-------B----C-----------D
 *     \
 *      \
 *       \
 *        E
 *         \
 *          \
 *           F
 * 
 * Equivalence classes structure we want:
 * 
 *   canonical = BAE
 *   rest = BAF, CAE, DAE, CAF, DAF
 */
public class AngleEquivalenceClasses extends EquivalenceClasses<Angle>
{
	public AngleEquivalenceClasses(Comparator<Angle> comparator){
		super(comparator);
	}
	
	public boolean add(Angle element){
		
		return super.add(element);
		//Do i need to make the index of class method? Or will add call 
		//it from equivalence classes
	}
	
	public boolean contains(Angle element) {
		return super.contains(element);
	}
	
	public int size() {
		return super.size();
	}
	
	public int numClasses() {
		return super.numClasses();
	}
	
	public String toString() {
		return super.toString();
	}
	
	
	
}