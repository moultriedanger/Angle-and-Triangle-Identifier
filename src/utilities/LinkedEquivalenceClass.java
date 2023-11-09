package utilities;
import java.util.Comparator;
public class LinkedEquivalenceClass<T>{

	protected T _canonical;
	protected Comparator<T> _comparator;
	protected LinkedList<T> _rest;

	public LinkedEquivalenceClass(Comparator<T> comparator) {
		_canonical = null;
		_comparator = comparator;
		_rest = new LinkedList<T>();
	}

	//Get canonical
	public T canonical() {
		return _canonical;
	}

	//Make sure _cannonical is null
	public boolean isEmpty() {
		return _canonical == null && _rest.isEmpty();
	}

	//Removes canonical and non canonical
	public void clear() {
		_canonical = null;
		_rest.clear();
	}

	public void clearNonCanonical() {
		_rest.clear();
	}

	//@Moultrie
	public int size() {
		int canonical_val = 0;

		//Add 1 to size if canonical is not null
		if(_canonical != null) {
			canonical_val = 1;
		}
		//Return size of linked list + canonical
		return _rest.size() + canonical_val;
	}

	public boolean add(T element) {
		if (element == null) return false;

		//Canonical becomes first element
		if(this.isEmpty()) {
			_canonical = element;
			return true;
		}
		//Compare comparator and element to check equivalence
		else if(_comparator.compare(_canonical, element) == 0) {
			_rest.addToFront(element);
			return true;
		}
		return false;
	}

	public boolean contains(T target) {
		//Make sure target belongs
		if (!belongs(target)) return false;
		//Check if target is equal to canonical or rest of list
		return (target.equals(_canonical) || _rest.contains(target));
	}

	public boolean belongs(T target) {
		if (target == null) return false;
		//Check if the canonical belongs to a class
		return (_comparator.compare(this._canonical, target)==0);
	}

	public boolean remove(T element) {
		if(element == null || element.equals(_canonical)) {
			return false;
		}
		return _rest.remove(element);
	}

	//Check this
	public boolean removeCanonical(T element) {
		if(_canonical == null || !element.equals(_canonical)) return false;

		//Remove by setting to null
		_canonical = null;
		return true;
	}

	//Can we make this shorter
	public boolean demoteAndSetCanonical(T element) {
		//Add if the list is empty
		if (this.isEmpty()) {
			this.add(element);
			return true;
		}
		else if(_comparator.compare(_canonical, element) == 0) {
			_canonical = element;
			return true;
		}
		return false;
	}

	public String toString() {
		if(isEmpty()) return "";

		String result = "";

		result += _canonical + " | ";

		result += _rest.toString();

		return result;
	}
}



