package kwaymergesort;

import timing.Ticker;

public class KWayMergeSort {
	
	/**
	 * 
	 * @param K some positive power of 2.
	 * @param input an array of unsorted integers.  Its size is either 1, or some other power of 2 that is at least K
	 * @param ticker call .tick() on this to account for the work you do
	 * @return
	 */
	public static void merging(Integer[] array, int left, int middle, int right, Ticker ticker){ // the variable left, middle, right serves as 3 different pointers
	
        int lengthHalfArray = middle - left + 1; // number of terms before mid point(including itself)
        int lengthOtherHalfArray = right - middle; // number of terms after the mid point( not including midpoint itself)
        int Left[] = new int [lengthHalfArray];
        int Right[] = new int [lengthOtherHalfArray];
   
        for (int j=0; j<=(lengthOtherHalfArray-1); ++j) {// an array consisting of elements in lengthOtherHalfArray
            Right[j] = array[middle + 1+ j];
            ticker.tick();
        }
        
        for (int i=0; i<=(lengthHalfArray-1); ++i) {// an array consisting of elements in lengthHalfArray
            Left[i] = array[left + i];
            ticker.tick();
        }
        int a = 0; // serve as pointer
        int b = 0; // serve as pointer
        int temp = left;// e.g. left is equal to 0
        while (a < lengthHalfArray && b < lengthOtherHalfArray){ // as long as the 2 conditions in bracket are fulfilled, the while loop will run its inner code
            if (Left[a] <= Right[b]){ //compare elements in one array with that of the other array
                array[temp] = Left[a];
                a++; // increment pointer a
                ticker.tick(2);
            }
            else{ // counter option
                array[temp] = Right[b];
                b++;// increment pointer b
                ticker.tick(2);
            }
            temp++;
        }
        while (!(a >= lengthHalfArray)){ // when b > lengthOtherHalfArray but a is still smaller than lengthHalfArray--> the remaining terms in Left[] are greater than all terms in Right[], 
                                          	// these elements in Left[] become the tails
            array[temp] = Left[a];
            temp++;
            a++;
            ticker.tick(3);
        }
        while (!(b >= lengthOtherHalfArray)){// when a > lengthHalfArray but b is still smaller than lengthOtherHalfArray --> the remaining terms in Right[] are greater than all terms in Left[], 
        	                                        // these elements in Right[] become the tails
            array[temp] = Right[b];
            temp++;
            b++;
            ticker.tick(3);
        }
    }
    public static void splitting(Integer[] array, int left, int right, Ticker ticker){
        if (!(left >= right)) { // if left < right
            int temp = left/2+right/2; // find the length
            ticker.tick();
            splitting(array, left, temp, ticker); // to split into the Left[]
            splitting(array , temp+1, right, ticker); //to split into the Right[]
            merging(array, left, temp, right, ticker); // arrange in order and return a single sorted array
            // more clarification needed
        }
    }
	public static Integer[] kwaymergesort(int K, Integer[] input, Ticker ticker) {
		int n = input.length;
		
		// FIXME
		
		
		// Following just copies the input as the answer
		//
		// You must replace the loop below with code that performs
		// a K-way merge sort, placing the result in ans
		//
		// The web page for this assignment provides more detail.
		//
		// Use the ticker as you normally would, to account for
		// the operations taken to perform the K-way merge sort.
		//
		Integer[] ans = new Integer[n];
		for (int i=0; i < n; ++i) { // placing all elements in Integer[] input into another such array named ans
			// not so useful in this method
			ans[i] = input[i];
			ticker.tick();
		}
		splitting(ans,0,n-1,ticker);
		return ans;
	}

}
