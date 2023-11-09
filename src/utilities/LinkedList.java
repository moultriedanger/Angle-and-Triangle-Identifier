package utilities;

public class LinkedList<T> {
	
	//Create node class
	private class Node{
		private T _data;
		private Node _next;
		
		public Node(T data, Node next){
			_data = data;
			_next = next; 
		}
	}

	//Define instance variables
	protected Node _head;
	protected Node _tail;
	protected int _size;
	
	LinkedList(){
		//Create head and tail
		_tail = new Node(null, null);
		_head = new Node(null, _tail);
		
		_size = 0;
	}
	
	public boolean isEmpty() {
		return _head._next.equals(_tail);
	}
	
	public void clear() {
		this._head._next =_tail;
		this._size = 0;
	}

	public int size() {
		return this._size;
	}
	
	public void addToFront(T element) {
		Node n = new Node(element, _head._next);
		
		//Make the head point to new node
		_head._next = n;
		_size ++;
	}
	
	public boolean contains(T target) {
		if (isEmpty()|| target == null) return false;
		
		//Find node in linked list equal to target
		for(Node n = _head._next; n!= _tail; n = n._next) {
			if(n._data.equals(target)) return true;
		}
		return false;
	}
	
	public Node previous(T target){
		
		//Check if empty and if the list contains target
		if (isEmpty() || !this.contains(target)) return null;
		
		//Store the previous
		Node prev = _head;
		
		for(Node n = _head._next; n!= _tail; n = n._next) {
			if(n._data.equals(target)) {
				return prev;
			}
			//Increase previous if not found
			prev = prev._next;
		}
		return null;
	}
	
	public boolean remove(T target) {
	
		if (!contains(target)) return false;
		
		Node n = previous(target);
		//Set n to targets next. Garbage collect
		n._next = n._next._next;
		
		_size --;
		return true;
	}

	//Returns last node in list
	private Node last() {
		
		Node current = _head;
		
		while(current._next != _tail) {
			
			current = current._next;
		}
		return current;
	}
	
	public void addToBack(T element) {
		Node n = new Node(element, _tail);
		
		//Make the last point to new node
		last()._next = n;
		
		//Added node point to tail
		n._next = _tail;
		_size ++;
	}
	
	public String toString() {
		if (isEmpty()) return "";
		
		String result = "";
		Node current = _head._next;
		
		while(current != _tail) {
			//Build string
			result += current._data + " ";
			
			current = current._next;
		}
		return result.substring(0, result.length()-1);
	}

	public void reverse() {
		//Prev to trail behind head.next
		Node prev = _head;
		
		Node n = _head._next;
		
		Node temp = null;
	    
		while(n != _tail) {
			//Save n next in a temp
			temp = n._next;
			
			//Set n to its previous
			n._next = prev;
			
			n = n._next;
			prev = prev._next;
		}
		//Set n to the first new first in list. 
		_head._next = prev;
	}
}
