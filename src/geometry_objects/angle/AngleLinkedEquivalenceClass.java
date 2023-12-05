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
	
	public Angle canonical() {
		return super.canonical();
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
		else if(_comparator.compare(a, _canonical) == AngleStructureComparator.STRUCTURALLY_INCOMPARABLE) return false;
		
		//if compare equals -1 then a is structurally less then the current canonical and must take it's place
		else if (_comparator.compare(a, _canonical) == -1) {
			Angle fallenKing = _canonical;
			
			//sets canonical to given angle
			_canonical = a;
			
			//adds old canonical to front of linked list
			_rest.addToFront(fallenKing);
			return true;
		}
		
		//If the angle is indeterminate or greater than the canonical
		else if (_comparator.compare(a, _canonical) == 0 || _comparator.compare(a, _canonical) == 1) {
			_rest.addToFront(a);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean belongs(Angle a) {
		if (a == null) return false;
		else if (this.isEmpty()) return true;
		
		return _comparator.compare(_canonical, a) != AngleStructureComparator.STRUCTURALLY_INCOMPARABLE;
	}
	
	@Override
	public boolean demoteAndSetCanonical(Angle a) {
		//Add if the list is empty
		if (!belongs(a)) return false;
		
		else if (this.isEmpty()) {
			this.add(a);
			return true;
		}
		
		System.out.println(_comparator.compare(a, _canonical));
		
		if (_comparator.compare(a, _canonical) != AngleStructureComparator.STRUCTURALLY_INCOMPARABLE) return false;
	
		else if (_comparator.compare(a, _canonical) == -1){
			
			Angle fallenKing = _canonical;
			
			_canonical = a;
			
			//adds old canonical to front of linked list
			_rest.addToFront(fallenKing);
			return true;
		}
		
	//Add structurally comparable element to rest of list
	_rest.addToFront(a);
	return true;
	}
	
	public boolean remove(Angle target) {
		return super.remove(target);
	}
	
	public boolean removeCanonical(Angle target) {
		return super.removeCanonical(target);
	}
		
	@Override
	public boolean contains(Angle target) {
		//Make sure target belongs
		if (!belongs(target)) return false;
		//Check if target is equal to canonical or rest of list
		return (target.equals(_canonical) || _rest.contains(target));
	}
	
	public String toString() {
		return super.toString();
	}
}