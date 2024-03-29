package studio2.lists;

import timing.Ticker;

/**
 * Your assignment is to modify this class so it uses a tail reference
 * 
 * @author roncytron and WhoAreYou
 *
 * @param <T>
 */
public class LinkedListWithTail<T> implements List<T> {
	
	private ListNode<T> head;
	private ListNode<T> tail;
	private Ticker ticker;
	private int size;//newly-added for part C
	
	public LinkedListWithTail(Ticker ticker) {
		this.head = null;   // nothing in our list yet
		this.ticker = ticker;
	}

	/**
	 * Modify this method for studio 2 so that it uses an instance
	 * variable for the tail reference, and modify code in this class
	 * so that the tail reference
	 * that always points to the end of the list.
	 * 
	 * Use that tail reference to add to the end of the list, instead
	 *   of looping to find the end.
	 */
	@Override
	public void addLast(T thing) {
		if (head == null) { // empty list, slide 176
			ListNode<T> p = new ListNode<T>();
			p.value = thing;
			head = p;
			tail = p;
			ticker.tick(4);  // for the 3 statements above
		}
		else {
			ListNode<T> q = new ListNode<T>();
			q.value = thing;
			//
			// As given, this
			// searches for the end of the list
			// Modify this code using your new tail reference
			//  and get rid of this loop!
			//
			// FIXME
			ListNode<T> p = tail ;
			p.next = q;
			tail = q;
			ticker.tick(5);  // for the 5 statements not in the loop
			//  p is where it needs to be slide 201
			  // for the 3 statements not in the loop
			size++;
		}
		
	}
	
	/**
	 * Modify this method so that getting the size of this list
	 * takes Theta(1) (constant) time.
	 */
	@Override
	public int getSize() {
//		int ans = 0;
//		for (ListNode<T> p = this.head; p != null; p = p.next) {
//			ans = ans + 1;
//			ticker.tick();
//		}
		return this.size;
	}

	/**
	 * 
	 * @param n which time, 0 is the first item
	 * @return
	 */
	@Override
	public T getItemAt(int n) {
		ListNode<T> p = head;
		for (int i=0; i < n; ++i) {
			p = p.next;
			ticker.tick();
		}
		return p.value;
	}
	
	public String toString() {
		String ans = "[ ";
		for (ListNode<T> p = head; p != null; p = p.next) {
			ans = ans + p.value + " ";
		}
		ans = ans + "]";
		return ans;
	}
	
}
