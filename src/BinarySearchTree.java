/*  -------------------------------------------------------
 *  BinarySearchTree.java
 *  -------------------------------------------------------
 *  Author:  Matthew Ferlaino
 *  Email:   mferlaino73@gmail.com
 *  Date:	 Feb 25, 2019
 *  Composite Computing Co.
 *  ------------------------------------------------------- */

// Imports
import java.io.Serializable;

//Suppress
@SuppressWarnings({"serial"})

public class BinarySearchTree implements Cloneable, Serializable{
	/*
	 * --- TreeNode Class ---
	 */
	private class TreeNode {
		// Variables
		private TreeNode left, right;
		private CentralizedAlgorithm.Node node;
		
		// No-arg Constructor
		public TreeNode() {
			node = null;
			left = right = null;		
		}
		
		// Single-arg Constructor
		public TreeNode(CentralizedAlgorithm.Node object) {
			this.node = object;
			left = right = null;	
		}

		public void beginFlood() {
			node.run();
		}
	}
	
	/*
	 * --- BinarySearchTree Class Below ---
	 */
	// Variables
	private TreeNode root;
	
	// No-arg Constructor
	public BinarySearchTree() {
		root = null;
	}
	
	/*
	 * --- BinarySearchTree Methods ---
	 * 1. insert()
	 * 6. height()
	 * 8. inPrint()
	 */
		
	// insert()
	public void insert(CentralizedAlgorithm.Node object) {
		root = insert(object, root);
	}
	
	// insert()
	private TreeNode insert(CentralizedAlgorithm.Node object, TreeNode root) {
		// Base Cases
		if (root == null) {
			root = new TreeNode(object);
			return root;
		}
		
		if (root.node.equals(object))
			return root;
		
		// Recursive Calls
		if (object.toString().compareTo(root.node.toString()) < 0)
			root.left = insert(object, root.left);
		
		else if (object.toString().compareTo(root.node.toString()) > 0)
			root.right = insert(object, root.right);
		
		return root;
	}

	// height()
	public int height() {
		return height(root);
	}
	
	// height()
	private int height(TreeNode root) {
		// Base Case
		if (root == null) 
			return 0;
		
		// Recursive Calls
		return Math.max(height(root.left), height(root.right)) + 1;
	}

	// inPrint()
	public void inPrint() {
		inPrint(root);
		System.out.println("nullity");
	}
	
	// inPrint()
	private void inPrint(TreeNode root) {
		// Base Case 
		if (root == null) 
			return;
		
		// Recursive Traversal
		inPrint(root.left);
		System.out.print("(" + root.node + ") --> ");
		inPrint(root.right);
	}

	// traverseAndRun()
	public void traverseAndRun() {
		traverseAndRun(root);
	}

	// traverseAndRun()
	private void traverseAndRun(TreeNode root) {
		try {
			// Base Case
			if (root == null)
				return;

			// Recursive Traversal
			traverseAndRun(root.left);

			Thread thread = new Thread(root.node);
			thread.start();
			thread.join();

			traverseAndRun(root.right);
		}

		catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
