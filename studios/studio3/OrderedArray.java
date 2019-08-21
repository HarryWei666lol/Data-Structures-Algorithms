package studio3;

public class OrderedArray<T extends Comparable<T>> implements PriorityQueue<T> {

	public T[] array;
	private int size;
	
	@SuppressWarnings("unchecked")
	public OrderedArray(int maxSize) {
		array = (T[]) new Comparable[maxSize];
		size = 0;
	}
	
	@Override
	public boolean isEmpty() {
		//
		// FIXME
		//
		boolean res = (size == 0); // = true or false
		return res;
	}

	@Override
	public void insert(T thing) {
		//
		// FIXME
		//
//		for (int j=0;j<size;j++) {
//			System.out.print(array[j]+" ");
//		}
//		System.out.println();
        int k = 0;
		while (k < size && array[k].compareTo(thing) < 0) {
			k++;
		}
		for (int j = size; j >= k; j--) {
			array[j + 1] = array[j];
		}
		array[k] = thing;
		size++;
	}
	
	@Override
	public T extractMin() {
		//
		// FIXME
		//
		if (size == 0) {
			return null;
		}
//		for (int j=0;j<size;j++) {
//			System.out.print(array[j]+" ");
//		}
//		System.out.println();
		T thing = array[0];
		for (int i = 1; i < size; i++) {
			array[i - 1] = array[i];
		}
		size--;
		return thing;
	}

}
