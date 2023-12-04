package geometry_objects.angle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import geometry_objects.angle.comparators.AngleStructureComparator;
import utilities.LinkedEquivalenceClass;
import utilities.EquivalenceClasses;

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
	
	private List<LinkedEquivalenceClass<Angle>> _classes;
	
	public AngleEquivalenceClasses(Comparator<Angle> comparator){
		super(comparator);
	}
	
	@Override
	public boolean add(Angle element){
		if (element == null) return false;
				
		//Check whether the element belongs to the classList. Changed to protected
		int classIndex = indexOfClass(element); //Index checks checks belonging
			
		//If element does not belong to classList, create a new class and add it to the List
		if (classIndex == -1) {
			AngleLinkedEquivalenceClass list = new AngleLinkedEquivalenceClass(_comparator);
			
			//Make the element the canonical
			list.add(element);
			
			//Add the new class to LinkedClasses
			_classes.add(list);
			return true;
			}
		
			//If element already belongs, add to corresponding class
			_classes.get(classIndex).add(element);
			return true;
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