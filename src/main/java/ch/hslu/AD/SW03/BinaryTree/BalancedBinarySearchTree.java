package ch.hslu.AD.SW03.BinaryTree;

import java.util.NoSuchElementException;

public class BalancedBinarySearchTree<T extends Comparable<T>> extends BinarySearchTree<T> {
	/**
	 * Add the given element to the tree.
	 * 
	 * @param element the element to add to the tree.
	 * 
	 * @return if the element could be added.
	 */
	@Override
	public boolean add(T element) {
		if(!super.add(element)) {
			return false;
		}
		
		super.balance();
		return true;
	}
	
	/**
	 * Remove the given element from the tree
	 * 
	 * @param element the element to remove from the tree
	 * 
	 * @throws NoSuchElementException
	 */
	@Override
	public boolean remove(T element) {
		if(!super.remove(element)) {
			return false;
		}
		super.balance();
		return true;
	}
}
