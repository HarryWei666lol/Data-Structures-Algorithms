package AVLTree.trees;

import AVLTree.nodes.AVLTreeNode;
import timing.Ticker;

import java.util.LinkedList;

public class AVLTree<T extends Comparable<T>> extends BST<T> {
	private AVLTreeNode<T> root;
	public Ticker ticker;

	public AVLTree(Ticker t) {
		super();
		this.root = null;
		this.ticker = t;

	}

	public AVLTreeNode<T> Root() {
		return root;
	}

	/**
	 * Attempts to locate a value in the Binary Search Tree.
	 * Returns the node if it exists, null otherwise
	 * Utilizes a helper function that is recursively called.
	 *
	 * @param value the value to be found
	 * @return the node element if it exists/otherwise null
	 */
	public AVLTreeNode<T> Find(T value) {
		return findHelper(value, this.root);
	}

	// helper function for find, see above for description.
	private AVLTreeNode<T> findHelper(T value, AVLTreeNode<T> curr) { // "value" is the value to be found, 
		   //"curr" is the current node to be compared to T node
        //FIXME: Find the node that has the value 'value'.
		if( curr == null) {
			return null;
		}
		else if(curr.getValue().compareTo(value) == 0) {
			return curr;
		}
		else if (curr.getValue().compareTo(value) > 0) { // curr > value
			return findHelper(value, curr.Left());
		}
		else {
			return findHelper(value, curr.Right());
		}
      
	}

	/**
	 * Attempts to insert a value into the AVL Binary Search Tree.
	 * Returns the node that was inserted.
	 *
	 * @param value the value to be inserted
	 * @return the node element that was inserted
     */
	public AVLTreeNode<T> Insert(T value) {// toInsert is the toBeInserted Value
		AVLTreeNode<T> toInsert = new AVLTreeNode<T>(value);// convert to the type "node"
        //FIXME: Insert toInsert into the tree and update any instance variables.
		this.root = insertHelper(this.root, toInsert); // find subsequent from root
		size++;
		return toInsert;
	}

	/**
	 * helper method for insertion into the AVL Binary Search Tree.
	 * Returns the (possibly different) root of the rebalanced
	 *   subtree.
	 *
	 * @param toInsert the value being inserted
     * @param node the root of the subtree to insert into.
	 * @return the node element that was inserted
	 */
	private AVLTreeNode<T> insertHelper(AVLTreeNode<T> node, AVLTreeNode<T> toInsert){
        //FIXME: insert toInsert into the tree starting at 'curr'
		if( node == null) { // tree is empty
			toInsert.height=1;	
			return toInsert;	
		}
		else if(toInsert.getValue().compareTo(node.getValue()) < 0) { // value to be inserted is smaller than root value
			node.setLeft(insertHelper(node.Left(),toInsert)); // set the parent of to be inserted node
			node.Left().setParent(node);// set this newly inserted node's parent to "node" ???
			
		}
		else { // value to be inserted is greater than root value
			node.setRight(insertHelper(node.Right(), toInsert));
			node.Right().setParent(node);
			
		}
		fixHeight(node);
		return rebalance(node);

	}

	/**
	 * Rebalances the subtree rooted at the input node (if necessary).
	 * Returns the (possibly different) root of the rebalanced
     *   subtree.
	 *
	 * @param node the root of the subtree to rebalance
	 * @return the node at the root of the rebalanced subtree
     */
    private AVLTreeNode<T> rebalance(AVLTreeNode<T> node) {
	    //FIXME: rebalance the tree starting at 'node' as needed
    	int takenInNodeBalance = node.getBalance();
    	AVLTreeNode<T> grandparent = node.Parent();
    	AVLTreeNode<T> tempo = null;
    	AVLTreeNode<T> tempoo = null;
    	if( takenInNodeBalance < 2 && takenInNodeBalance > -2) {
    		return node;
    	}
    	else if( takenInNodeBalance >= 2) {
//    		if(node.Right().getBalance()==1) {
//    			tempo = leftRotate(node); // this condition requires leftRotate
//    			tempo.setParent(grandparent);
//    		}
//    		else {
//    			tempoo = rightRotate(node.Right());
//    			node.setRight(tempoo);
//    			tempo = leftRotate(node);// this condition also requires leftRotate, so we dont have to duplicate it, hence comes the simplification
//    			tempo.setParent(grandparent);
//    			
//    		}
    		if(node.Right().getBalance() == -1) {// node is 20
    			tempoo = rightRotate(node.Right()); // tempoo stores 25 in Scenario 2
    			node.setRight(tempoo);
    		}
    		tempo = leftRotate(node);
		tempo.setParent(grandparent);
    	}
    	else { // takenInNodeBalance <= -2
    		if(node.Left().getBalance() == 1) { // node is 20
    			tempoo = leftRotate(node.Left());// tempoo stores 15 in Scenario 4
    			node.setLeft(tempoo);
    		}
    		tempo = rightRotate(node);
    		tempo.setParent(grandparent);
  
    	}
        return tempo;
	}

	/**
	 * Performs a standard right-rotation on a subtree rooted
     *   at the input node.
     * This node corresponds to node 'y' on the left half of
     *   slide 158 of the Lecture 9 notes.
	 * Returns the (possibly different) root of the rebalanced
     *   subtree.
	 *
	 * @param parent the root of the subtree to rotate
	 * @return the new root of the rotated subtree; i.e. the 
     *         node taking the place of parent
	 * <p>
     */
	private AVLTreeNode<T> rightRotate(AVLTreeNode<T> parent) {
	    //FIXME: rotate a parent node to the right
		AVLTreeNode<T> temp = parent.Left().Right();
		AVLTreeNode<T> temp2 = parent.Left();
		temp2.setRight(parent);
		parent.setLeft(temp);
		fixHeight(parent);
		fixHeight(temp2);
		
		return temp2; // returns root of the newly-rebalanced subtree
	}

	/**
	 * Performs a standard left-rotation on a subtree rooted
     *   at the input node.
     * This node corresponds to node 'x' on the right half of
     *   slide 158 of the Lecture 9 notes.
	 * Returns the (possibly different) root of the rebalanced
     *   subtree.
	 *
	 * @param parent the root of the subtree to rotate
	 * @return the new root of the rotated subtree; i.e. the 
     *         node taking the place of parent
     */
	private AVLTreeNode<T> leftRotate(AVLTreeNode<T> parent) {
        //FIXME: rotate a parent node to the left
		AVLTreeNode<T> temp = parent.Right().Left();
		AVLTreeNode<T> temp2 = parent.Right();
		temp2.setLeft(parent);
		parent.setRight(temp);
		fixHeight(parent);
		fixHeight(temp2);
		
		return temp2;//returns root of the newly-rebalanced subtree
		
	}

	/**
	 * Recompute the height of the input node and store in its
     *   corresponding instance variable.
	 *
	 * @param node the node whose height is computed
     */
	private void fixHeight(AVLTreeNode<T> node){
	    //FIXME: fix the height variable of a node
        // Recommended: use a helper method to compute the height
        //   of any subtrees necessary (see below).
		int leftHeight = fixHeightHelper(node.Left());
		int rightHeight = fixHeightHelper(node.Right());
		if(leftHeight > rightHeight) { // compare the node's left side's height with the node's right side's height
			node.height = leftHeight +1; // +1 accounts for the node height 
		}
		else {
			node.height = rightHeight +1;
		}
	}
	
	private int fixHeightHelper(AVLTreeNode<T> node) {
		if(node == null) {
			return 0;
		}
		else {
			return node.height;
		}
	}

	//FIXME (recommended): create a helper method to determine the height of a subtree.

	public boolean isEmpty() {
		return this.root == null;
	}

	public AVLTreeNode<T> minimum() {
		return minimumOfSubtree(this.root);
	}

	public AVLTreeNode<T> maximum() {
		return maximumOfSubtree(this.root);
	}

	public AVLTreeNode<T> minimumOfSubtree(AVLTreeNode<T> curr) {
		if (curr == null) {
			return null;
		}
		while (curr.Left() != null) {
			curr = curr.Left();
		}
		return curr;
	}

	public AVLTreeNode<T> maximumOfSubtree(AVLTreeNode<T> curr) {
		if (curr == null) {
			return null;
		}
		while (curr.Right() != null) {
			curr = curr.Right();
		}
		return curr;
	}

	public LinkedList<T> InorderTraversal(AVLTreeNode<T> curr) {
		if (curr == null) {
			return new LinkedList<T>();
		}
		LinkedList<T> list = InorderTraversal(curr.Left());
		list.addLast(curr.getValue());
		list.addAll(InorderTraversal(curr.Right()));
		return list;
	}

	public void PrintTree() {
		InorderTraversal(this.root);
	}

}
