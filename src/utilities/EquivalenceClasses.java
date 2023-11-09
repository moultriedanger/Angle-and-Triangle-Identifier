package utilities;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class EquivalenceClasses<T> {
	private Comparator<T> _comparator;
	private List<LinkedEquivalenceClass<T>> _classes;
	
	public EquivalenceClasses(Comparator<T> comparator){
		_comparator = comparator;
		_classes = new ArrayList<LinkedEquivalenceClass<T>>();
	}
	
	public boolean add(T element){

		if (element == null) return false;
		
		//Check whether the element belongs to the classList
		int classIndex = indexOfClass(element);
		
		//If element does not belong to classList, create a new class and add it to the List
		if (classIndex == -1) {
			LinkedEquivalenceClass<T> list = new LinkedEquivalenceClass<T>(_comparator);
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
	
	public boolean contains(T target) {
		for (LinkedEquivalenceClass<T> equivalenceClass : _classes) {
			if (equivalenceClass.contains(target)) {
				return true;
			}
		}
		return false;
	}
	
	public int size() {
		int total = 0;
		for(LinkedEquivalenceClass<T> c: _classes) {
			//Add size of each class to total
			total += c.size();
		}
        return total;
    }
	
	public int numClasses() {
		return _classes.size();
	}
	
	private int indexOfClass(T element) {
		
		for(int i = 0; i < _classes.size(); i ++) {
			if (_classes.get(i).belongs(element)) {
				return i;
			}
		}
		return -1;
	}
	
	public String toString(){
		String result = "";
		
		for(LinkedEquivalenceClass<T> l : _classes) {
			result += l.toString()+ ",\n";
		}
		return result;
	}
}