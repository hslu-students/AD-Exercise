package ch.hslu.AD.SW3.BinaryTree;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.hslu.AD.SW2.ArrayStack.ArrayStack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertFalse;

/**
 * Unit test for simple App.
 */
public class BinarySearchTreeTest {

    /**
     * Rigourous Test :-)
     */
	@Test
    public void testAddingElements() {
    	BinarySearchTree<Integer> tree = new BinarySearchTree<>();
    	assertTrue(tree.add(1));
    	assertTrue(tree.add(2));
    	assertTrue(tree.add(1));
    }
	
	@Test
	public void testContains() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(1);
		tree.add(2);
		
		assertTrue(tree.contains(1));
		assertTrue(tree.contains(2));
		assertFalse(tree.contains(3));
		
		tree.add(3);
		assertTrue(tree.contains(3));
	}
	
	@Test
	public void testRemoveLeaf() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(5);
		tree.add(4);
		tree.add(6);
		
		assertTrue(tree.remove(6));
		assertFalse(tree.contains(6));
		assertTrue(tree.remove(4));
		assertFalse(tree.contains(4));
	}
	
	@Test
	public void testRemoveNodeWithLeftChild() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(5);
		tree.add(4);
		tree.add(3);
		tree.add(6);
		
		assertTrue(tree.remove(4));
		assertFalse(tree.contains(4));
		assertTrue(tree.contains(3));
	}
	
	@Test
	public void testRemoveNodeWithRightChild() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(5);
		tree.add(4);
		tree.add(6);
		tree.add(7);
		
		assertTrue(tree.remove(6));
		assertFalse(tree.contains(6));
		assertTrue(tree.contains(7));
	}
	
	@Test
	public void testRemoveNodeComplete() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(5);
		tree.add(4);
		tree.add(10);
		tree.add(8);
		tree.add(12);
		tree.add(7);
		tree.add(9);
		tree.add(11);
		
		assertTrue(tree.remove(10));
		assertFalse(tree.contains(10));
		// left subtree
		assertTrue(tree.contains(8));
		assertTrue(tree.contains(7));
		assertTrue(tree.contains(9));
		// right subtree
		assertTrue(tree.contains(12));
		assertTrue(tree.contains(11)); // is the replacement for 10 - the removed element
	}
	
	@Test
	public void testRemoveFirstNodeLeftChild() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(5);
		tree.add(4);
		tree.add(3);
		
		assertTrue(tree.remove(5));
		assertFalse(tree.contains(5));
		assertTrue(tree.contains(4));
		assertTrue(tree.contains(3));
	}
	
	@Test
	public void testRemoveFirstNodeRightChild() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(5);
		tree.add(6);
		tree.add(7);
		
		assertTrue(tree.remove(5));
		assertFalse(tree.contains(5));
		assertTrue(tree.contains(6));
		assertTrue(tree.contains(7));
	}
	
	@Test
	public void testRemoveFirstNodeComplete() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(5);
		tree.add(4);
		tree.add(10);
		tree.add(8);
		tree.add(12);
		tree.add(7);
		tree.add(9);
		tree.add(11);
		
		assertTrue(tree.remove(5));
		assertFalse(tree.contains(5));
		assertTrue(tree.contains(4));
		// left subtree
		assertTrue(tree.contains(10));
		assertTrue(tree.contains(8));
		assertTrue(tree.contains(7));
		assertTrue(tree.contains(9));
		// right subtree
		assertTrue(tree.contains(12));
		assertTrue(tree.contains(11)); // is the replacement for 10 - the removed element
	}
	
	@Test
	public void testNodePathLeaf() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(5);
		tree.add(4);
		tree.add(10);
		tree.add(8);
		tree.add(12);
		tree.add(7);
		tree.add(9);
		tree.add(11);
		
		List<Integer> path = tree.getPath(9);
		Iterator<Integer> it = path.iterator();
		
		assertEquals(new Integer(5), it.next());
		assertEquals(new Integer(10), it.next());
		assertEquals(new Integer(8), it.next());
		assertEquals(new Integer(9), it.next());
		assertFalse(it.hasNext());
	}
	
	@Test
	public void testNodePathMiddle() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(5);
		tree.add(4);
		tree.add(10);
		tree.add(8);
		tree.add(12);
		tree.add(7);
		tree.add(9);
		tree.add(11);
		
		List<Integer> path = tree.getPath(12);
		Iterator<Integer> it = path.iterator();
		
		assertEquals(new Integer(5), it.next());
		assertEquals(new Integer(10), it.next());
		assertEquals(new Integer(12), it.next());
		assertFalse(it.hasNext());
	}
	
	@Test
	public void testNodePathRoot() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(5);
		tree.add(4);
		tree.add(10);
		tree.add(8);
		tree.add(12);
		tree.add(7);
		tree.add(9);
		tree.add(11);
		
		List<Integer> path = tree.getPath(5);
		Iterator<Integer> it = path.iterator();
		
		assertEquals(new Integer(5), it.next());
		assertFalse(it.hasNext());
	}
	
	@Test
	public void testNodeDepth() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(5);
		tree.add(4);
		tree.add(10);
		tree.add(8);
		tree.add(12);
		tree.add(7);
		tree.add(9);
		tree.add(11);
		
		// level 1
		assertEquals(1, tree.getDepth(5));
		// level 2
		assertEquals(2, tree.getDepth(4));
		assertEquals(2, tree.getDepth(10));
		// level 3
		assertEquals(3, tree.getDepth(8));
		assertEquals(3, tree.getDepth(12));
		// level 4
		assertEquals(4, tree.getDepth(7));
		assertEquals(4, tree.getDepth(9));
		assertEquals(4, tree.getDepth(11));
	}
}
