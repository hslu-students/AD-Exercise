package ch.hslu.AD.SW3.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {
	
	/**
	 * Maximum allowed children for each node.
	 */
	private static int TREE_ORDER = 2;
	
	private Node root;
	
	public int getOrder() {
		return TREE_ORDER; 
	};
	
	public int getDegree(T element) {
		Node node = contains(root, element);
		if(node == null) {
			throw new NoSuchElementException();
		}
		
		return node.getDegree();
	}
	
	public List<T> getPath(T element) {
		Node node = contains(root, element);
		if(node == null) {
			throw new NoSuchElementException();
		}
		
		return node.getPath();
	}
	
	public int getDepth(T element) {
		Node node = contains(root, element);
		if(node == null) {
			throw new NoSuchElementException();
		}
		
		return node.getDepth();
	}
	
	public boolean add(T element) {
		if(element == null) {
			throw new NullPointerException("Failed to create Node with 'null' element");
		}
		
		if(root == null) {
			// we insert the first element as root node
			root = new Node(element, null);
			return true;
		}
		
		return insert(root, element);
	}
	
	private boolean insert(Node root, T element) {
		if(root == null) {
			// we are forced to abort because we cannot insert here
			// because we don't know the root's predecessor.
			return false; 
		}
		
		if(root.getElement().equals(element)) {
			// we already have this value in the tree.
			return true;
		}
		
		if(element.compareTo(root.getElement()) < 0) {
			if(root.left == null) {
				// insert node here because we don't have this branch yet.
				root.left = new Node(element, root);
				return true;
			}
			return insert(root.left, element);
		}
		
		if(element.compareTo(root.getElement()) > 0) {
			if(root.right == null) {
				// insert node here because we don't have this branch yet.
				root.right = new Node(element, root);
			}
			return insert(root.right, element);
		}
		
		// This can happened if the equals/compareTo contract is not respected
		// by type T. In case equals() returns false and compareTo() returns 0.
		// Someone screwed up, but ... "wasn't me"
		return false;
	}
	
	public boolean remove(T element) {
		try {
			root = remove(root, element);
		} catch (NoSuchElementException e) {
			return false;
		}
		
		return true;
	}
	
	private Node remove(Node root, T element) {
		if(root == null) {
			// we probably have an empty tree or messed up insertion
			throw new NoSuchElementException();
		}
		
		if(element.compareTo(root.getElement()) < 0) {
			if(root.left == null) {
				// "You know nothing, John Snow" 
				throw new NoSuchElementException();
			}
			root.left = remove(root.left, element);
		} else if(element.compareTo(root.getElement()) > 0) {
			if(root.right == null) {
				// "You know nothing, John Snow" 
				throw new NoSuchElementException();
			}
			root.right = remove(root.right, element);
		} else {
			if(!root.getElement().equals(element)) {
				throw new NoSuchElementException(); // someone screwed up the T compareTo/equals contract ...
			}
			
			if(root.isLeaf()) {
				// in case the node does not have any child / is a leaf.
				root = null;
			} else if(root.right == null) {
				// in case the node only has a left child
				// fix up parent node
				root.left.parent = root.parent;
				root = root.left;
			} else if(root.left == null) {
				// in case the node only has a right child
				// fix up parent node
				root.right.parent = root.parent;
				root = root.right;
			} else {
				// in case the node has two children
				// get the replacement node
				Node replacement = getLeftMost(root.right);
				// TODO(TF): remove replacement node
				replacement.parent = root.parent;
				replacement.left = root.left;
				replacement.right = remove(root.right, replacement.getElement());
				root = replacement;
			}
		}
		return root;
	}
	
	private Node getLeftMost(Node root) {
		if(root.left == null) {
			return root;
		}
		
		return getLeftMost(root.left);
	}
	
	public boolean contains(T element) {
		return contains(root, element) != null;
	}
	
	private Node contains(Node root, T element) {
		if(root == null) {
			// we probably have an empty tree or messed up insertion
			return null;
		}
		
		if(root.getElement().equals(element)) {
			// we've found the searched element
			return root;
		}
		
		if(element.compareTo(root.getElement()) < 0) {
			if(root.left == null) {
				// "You know nothing, John Snow" 
				return null;
			}
			return contains(root.left, element);
		}
		
		if(element.compareTo(root.getElement()) > 0) {
			if(root.right == null) {
				// "You know nothing, John Snow"
				return null;
			}
			return contains(root.right, element);
		}
		
		// FIXME(TF): we messed the the insertion up
		return null;
	}
	
	private class Node {
		public Node parent;
		public Node left;
		public Node right;
		private T element;
		
		public Node(T element, Node parent) {
			if(element == null) {
				throw new NullPointerException("Failed to create Node with 'null' element");
			}
			
			this.parent = parent;
			this.element = element;
		}
		
		public T getElement() {
			return element;
		}
		
		public boolean isLeaf() {
			return left == null && right == null;
		}
		
		public int getDegree() {
			if(isLeaf()) {
				return 0;
			}
			if(left != null && right != null) {
				return 2;
			}
			return 1;
		}
		
		public List<T> getPath() {
			List<T> path = new ArrayList<>();
			
			Node current = this;
			while(current != null) {
				path.add(0, current.getElement());
				current = current.parent;
			}
			return path;
		}
		
		public int getDepth() {
			int depth = 0;
			Node current = this;
			while(current != null) {
				depth++;
				current = current.parent;
			}
			return depth;
		}
	}
}