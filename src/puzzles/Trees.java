package puzzles;

public class Trees {
	public static boolean checkBST(Node n) {
		/*
		 * Checks that a tree is a binary search tree. Starts with the root and
		 * the widest valid value interval possible, then slowly converges as
		 * you traverse the tree. This does not check for balancing (to do this,
		 * just check that |(deepest node) - (shallowest node)| <= 1
		 */
		return checkBST(n, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static boolean checkBST(Node n, int min, int max) {
		if (n == null) {
			return true;
		}

		Node left = n.left();
		boolean leftResult = true;
		if (left != null) {
			leftResult = false;
			if (left.value() > min && left.value() < max) {
				leftResult = checkBST(left, min, left.value());
			}
		}

		Node right = n.right();
		boolean rightResult = true;
		if (right != null) {
			rightResult = false;
			if (right.value() > min && right.value() < max) {
				rightResult = checkBST(right, right.value(), max);
			}
		}
		return leftResult && rightResult;
	}

	public static Node lowestCommonAncestor(Node node, int v1, int v2) {

		// LCA search for generic binary trees
		if (node == null || node.value() == v1 || node.value() == v2) { // Found
			// one!
			return node;
		} else {
			Node leftTree = lowestCommonAncestor(node.left(), v1, v2);
			Node rightTree = lowestCommonAncestor(node.right(), v1, v2);
			if (leftTree != null && rightTree != null) {
				// values we want are on both sides of the current node, so the
				// current node is the LCA
				return node;
			} else if (leftTree != null) {
				// Only leftTree is not null, so solution is on left
				return leftTree;
			} else {
				// Only right tree is not null, so solution is on right
				return rightTree;
			}
		}
	}

	public static Node tLCABST(Node node, int v1, int v2) {
		Node answer = null;
		if (node == null) {
			return null;
		}

		if (v1 < node.value() && v2 < node.value()) {
			// Both values are in the left sub-tree
			answer = tLCABST(node.left(), v1, v2);
		} else if (v1 > node.value() && v2 > node.value()) {
			// Both values are in the right sub-tree
			answer = tLCABST(node.right(), v1, v2);
		} else {
			// If both cases above fail, this is the node where the 2 values
			// split
			answer = node;
		}
		return answer;
	}

	interface Node {
		int value();

		Node left();

		Node right();
	}
}
