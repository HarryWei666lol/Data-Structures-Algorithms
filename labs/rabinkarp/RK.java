package rabinkarp;

public class RK {

	//
	// Be sure to look at the write up for this assignment
	//  so that you get full credit by satisfying all
	//  of its requirements
	//
	private int hashValue; // hash value
	private int exp; // value of 31^m mod 511
	private char[] array; // window of the long given input
	private int size; // size of the window


	/**
	 * Rabin-Karp string matching for a window of the specified size
	 * @param m size of the window
	 */
	public RK(int m) { // constructor
		this.size = m;
		this.array = new char[m];
		this.hashValue = 0; // 
		this.exp = 1; // 
		for(int i=0; i<m; i++) {
			this.exp=(this.exp*31)%511; // after first time, (the remainder *31) % 511
			// find the remainder of 31^m (mod 511)
		}
	}


	/**
	 * Compute the rolling hash for the previous m-1 characters with d appended.
	 * @param d the next character in the target string
	 * @return
	 */
	public int nextCh(char d) {
		int firstElementOfPreviousWindow=array[0];
		for(int i=0; i<size; i++) { // remove the 1st element in the original window 
			//and add the new char which is d to maintain the window of size e.g. 4
			if(i < size -1) { // move the 3 elements one index forward in the window
				array[i]=array[i+1];
			}
			else { // add in the new d
				array[i]=d;
			}
//			if(i==size-1) { // add in the new d
//				array[i]=d;
//			}
//			else {// move the 3 elements one index forward in the window
//				array[i]=array[i+1];
//			}
		}
		hashValue = (hashValue*31+511-(exp*firstElementOfPreviousWindow)%511+d)%511; // the original formula is h=(31h-31^m*c+d)(mod 511)
		// 31h-31^m*c could be negative when m is large e.g. 200 and h is small e.g. 1, so we need to make sure 31^m*c is not out of bound by mod 511 first, then this result will be between 1 and 55
		// then + 511 to h*31 to make sure 31h-31^m*c is always positive, never negative
		// the h on RHS is the computed hash from the last call of this method
		return hashValue;
	}

}
