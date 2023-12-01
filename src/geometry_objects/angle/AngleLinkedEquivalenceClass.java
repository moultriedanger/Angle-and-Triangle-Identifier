package geometry_objects.angle;

import java.util.Comparator;

import geometry_objects.angle.comparators.AngleStructureComparator;
import utilities.LinkedEquivalenceClass;
import utilities.LinkedList;


/**
 * This implementation requires greater knowledge of the implementing Comparator.
 * 
 * According to our specifications for the AngleStructureComparator, we have
 * the following cases:
 *
 *    Consider Angles A and B
 *    * Integer.MAX_VALUE -- indicates that A and B are completely incomparable
                             STRUCTURALLY (have different measure, don't share sides, etc. )
 *    * 0 -- The result is indeterminate:
 *           A and B are structurally the same, but it is not clear one is structurally
 *           smaller (or larger) than another
 *    * 1 -- A > B structurally
 *    * -1 -- A < B structurally
 *    
 *    We want the 'smallest' angle structurally to be the canonical element of an
 *    equivalence class.
 * 
 * @author XXX
 */
public class AngleLinkedEquivalenceClass extends LinkedEquivalenceClass<Angle>
{
	public AngleLinkedEquivalenceClass(Comparator<Angle> comparator) {
		super(comparator);
	}
	@Override
	public boolean add(Angle a) {
		if (a == null) return false;
		//Canonical becomes first element
		if(this.isEmpty()) {
			_canonical = a;
			return true;
		}
		//Compare comparator and element to check equivalence
		if(_comparator.compare(_canonical, a) == AngleStructureComparator.STRUCTURALLY_INCOMPARABLE) return false;
		//if compare equals 1 then a is structurally less then the current canonical and must take it's place
		if (_comparator.compare(_canonical, a) == 1) {
			Angle fallenKing=_canonical;
			//sets canonical to given angle
			_canonical=a;
			//adds old canonical to front of linked list
			_rest.addToFront(fallenKing);
			return true;
		}
		if(_comparator.compare(_canonical, a) == -1) {
			//didn't call add because it would have checked unnecessary if statements
			_rest.addToFront(a);
			return true;
		}
		//DISCLAIMER: I'm not sure what to do when comparator equals 0 so
		//once we get word from Alvin this will likely need updating
		return false;
	}
	@Override
	public boolean belongs(Angle a) {
		if (a == null) return false;
		//Check if the canonical belongs to a class
		//DISCLAIMER: I'm not sure what to do when comparator equals 0 so
		//once we get word from Alvin this will likely need updating
		return (_comparator.compare(_canonical, a)==1 || _comparator.compare(_canonical, a)==-1);
	}
	@Override
	public boolean demoteAndSetCanonical(Angle a) {
		//Add if the list is empty
		if (this.isEmpty()) {
			this.add(a);
			return true;
		}
		//DISCLAIMER: I'm not sure what to do when comparator equals 0 so
		//once we get word from Alvin this will likely need updating
		if (_comparator.compare(_canonical, a)==1 || _comparator.compare(_canonical, a)==-1) {
			_canonical = a;
			return true;
		}
		return false;
	}
	
	//HEADS UP: Will likely have to do remove as well
	
	@Override
	public String toString() {
		if(isEmpty()) return "";

		String result = "";

		result += _canonical.toString() + " | ";

		//DISCLAIMER: May need to iterate through the LinkedList and call 
		//to string on each angle, will see in testing
		result += _rest.toString();

		return result;
	}
}