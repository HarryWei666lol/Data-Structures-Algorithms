package studio3;

import java.util.LinkedList;
import java.util.List;

public class UnorderedList<T extends Comparable<T>> implements PriorityQueue<T> {

	public LinkedList<T> list;
	
	public UnorderedList() {
		list = new LinkedList<T>();
	}
	
	@Override
	public boolean isEmpty() {
		//
		// FIXME
		//
		
		return list.isEmpty();
	}

	@Override
	public void insert(T thing) {
		//
		// FIXME
		//
		list.addLast(thing);
	}

	@Override
	public T extractMin() {
		//
		// FIXME
		//
		T min = list.getFirst(); // beginning of list
		for (T thing : this.list) {
			int a = min.compareTo(thing);// min-thing
			if (a > 0) {
				min = thing;
			}
		}
		list.remove(min);// get min out of the list
		return min;
	}

}
