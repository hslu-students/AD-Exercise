package ch.hslu.AD.SW3.BinaryTree;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.hslu.AD.SW2.ArrayStack.ArrayStack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertFalse;

/**
 * Unit test for simple App.
 */
public class BalancedBinarySearchTreeTest {

    /**
     * Rigourous Test :-)
     */
	@Test
	public void testBalanceSimple() {
		BalancedBinarySearchTree<Integer> tree = new BalancedBinarySearchTree<>();
		tree.add(5);
		tree.add(4);
		tree.add(10);
		tree.add(8);
		
		// we don't need to balance
		// tree.balance();
		
		List<Integer> expectedList = new ArrayList<>();
		expectedList.add(8);
		expectedList.add(5);
		expectedList.add(4);
		expectedList.add(10);
		
		// test if list order matches
		assertEquals(expectedList, tree.toList());
		
		// test if depth match
		assertEquals(1, tree.getDepth(8));
		assertEquals(2, tree.getDepth(5));
		assertEquals(2, tree.getDepth(10));
		assertEquals(3, tree.getDepth(4));
	}
	
	@Test
	public void testBalanceComplex() {
		BalancedBinarySearchTree<Integer> tree = new BalancedBinarySearchTree<>();
		tree.add(5);
		tree.add(4);
		tree.add(10);
		tree.add(8);
		tree.add(12);
		tree.add(7);
		tree.add(9);
		tree.add(11);
		tree.add(13);
		tree.add(14);
		tree.add(15);

		// we don't need to balance
		// tree.balance();
		
		List<Integer> expectedList = new ArrayList<>();
		expectedList.add(10);
		expectedList.add(7);
		expectedList.add(5);
		expectedList.add(4);
		expectedList.add(9);
		expectedList.add(8);
		expectedList.add(13);
		expectedList.add(12);
		expectedList.add(11);
		expectedList.add(15);
		expectedList.add(14);
		
		// test if list order matches
		assertEquals(expectedList, tree.toList());
		
		// test if depth match
		// level 1
		assertEquals(1, tree.getDepth(10));
		// level 2
		assertEquals(2, tree.getDepth(7));
		assertEquals(2, tree.getDepth(13));
		// level 3
		assertEquals(3, tree.getDepth(5));
		assertEquals(3, tree.getDepth(9));
		assertEquals(3, tree.getDepth(12));
		assertEquals(3, tree.getDepth(15));
		// level 4
		assertEquals(4, tree.getDepth(4));
		assertEquals(4, tree.getDepth(8));
		assertEquals(4, tree.getDepth(11));
		assertEquals(4, tree.getDepth(14));
	}
}
