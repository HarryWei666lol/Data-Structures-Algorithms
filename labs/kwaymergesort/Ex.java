package kwaymergesort;

import java.util.Arrays;

import timing.Ticker;

public class Ex {

	/**
	 * 
	 * @param K some positive power of 2.
	 * @param input an array of unsorted integers.  Its size is either 1, or some other power of 2 that is at least K
	 * @param ticker call .tick() on this to account for the work you do
	 * @return
	 */
	public static Integer[] kwaymergesort(int K, Integer[] input, Ticker ticker) {
		int n = input.length;
		ticker.tick();
		//
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
		if (n == 1) {
			return input;
		}
		else {
			Integer[] t = new Integer[n];
			ticker.tick(n);
			Integer[][] t1 = new Integer[K][n/K]; //n/k, length of each column
			ticker.tick(n);
			for (int i = 0; i < n; i+=n/K) { // distribute elements of the input array among K other arrays, each of size n/k
				// such that each of the K arrays has the same number of elements
				// this is the splitting process
				Integer[] t2 = new Integer[n/K];
				ticker.tick(n/K);
				for (int j = 0; j < n/K; j++) {
					t2[j] = input[i + j];
					ticker.tick();
				}
				t1[i/(n/K)] = kwaymergesort(K, t2, ticker);// [i/(n/k)] refers to index from 0 to k-1
				ticker.tick();
			}
			t = twoWayMerge(t1, ticker)[0]; // originally returned is a 2-D array, but we want to return the array at row number = 0 only, so this is the Yufa
			ticker.tick();
			return t; // final step of the algorithm is here
		}
	}

	public static Integer[][] twoWayMerge(Integer[][] a, Ticker ticker) {
		if (a.length == 1) {
			return a;
		}
		else {
			Integer[][] res = new Integer[a.length/2][a[0].length*2]; // a.length is no. of row; a[0].length is no. of column
			// combining 2 arrays, row length is doubled but column length is halved.
			ticker.tick(res.length * res[0].length);
			for (int i = 0; i < a.length; i+=2) { // e.g. if 0th and 1st array combine, then we deal with 2nd and 3rd array then, so we i=i+2
				res[i/2] = twoMerge(a[i], a[i+1], ticker); // 
				ticker.tick();
			}
			return twoWayMerge(res, ticker);
		}
	}

	public static Integer[] twoMerge(Integer[] a, Integer[] b, Ticker ticker) {
		int c1 = 0;
		int c2 = 0;
		int c = 0;
		ticker.tick(3);
		Integer[] res = new Integer[a.length + b.length];
		ticker.tick();
		while (c1 < a.length && c2 < b.length && c < res.length) {
			if (a[c1] < b[c2]) {
				res[c] = a[c1];
				c1++;
			}
			else {
				res[c] = b[c2];
				c2++;
			}
			c++;
			ticker.tick(3);
		}
		if (c1 < a.length) { // all of c2 has gone into the array, the leftover c1s then go in 
			for (int c11 = c1; c11 < a.length; c11++) {
				res[c] = a[c11];
				c++;
				ticker.tick(2);
			}
		}
		if (c2 < b.length) { // all of c1 has gone into the array, the leftover c2s then go in 
			for (int c22 = c2; c22 < b.length; c22++) {
				res[c] = b[c22];
				c++;
				ticker.tick(2);
			}
		}
		return res;
	}
}