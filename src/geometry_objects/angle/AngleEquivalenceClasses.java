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
	private List<AngleLinkedEquivalenceClass> _classes;
	
	public AngleEquivalenceClasses(Comparator<Angle> comparator){
		super(comparator);
		_classes = new ArrayList<AngleLinkedEquivalenceClass>();
	}
	
	@Override
	public boolean add(Angle element){
		if (element == null) return false;
				
		//Check whether the element belongs to the classList. Changed to protected
		int classIndex = indexOfClass(element); //Index checks checks belonging
			
		//If element does not belong to classList, create a new class and add it to the List
		if (classIndex == -1) {
			AngleLinkedEquivalenceClass list = new AngleLinkedEquivalenceClass(new AngleStructureComparator());
			
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
	
	@Override
	public boolean contains(Angle target) {
		for (AngleLinkedEquivalenceClass equivalenceClass : _classes) {
			if (equivalenceClass.contains(target)){
				return true;
			}
		}
		return false;
	}

	protected int indexOfClass(Angle element) {
		
		for(int i = 0; i < _classes.size(); i ++) {
			if (_classes.get(i).belongs(element)) {
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public int size() {
		
		int total = 0;
		for(AngleLinkedEquivalenceClass c: _classes) {
			//Add size of each class to total
			total += c.size();
		}
        return total;
    }

	@Override
	public int numClasses() {
		return _classes.size();
	}
	
	@Override
	public String toString() {	
		
		String result = "";
		
		for(AngleLinkedEquivalenceClass l : _classes) {
			result += l.toString()+ ",\n";
		}
		return result;
	}
}