package puzzles;

public class Trees {
	public static boolean isSymmetric(TreeNode n) {
		if (n == null) {
			return true;
		}
		return isSymmetric(n.left(), n.right());
	}

	private static boolean isSymmetric(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		}
		if (left != null && right != null && left.value() == right.value()) {
			return isSymmetric(left.left(), right.right()) && isSymmetric(left.right(), right.left());
		}
		return false;
	}

	public static boolean checkBST(TreeNode n) {
		/*
		 * Checks that a tree is a binary search tree. Starts with the root and
		 * the widest valid value interval possible, then slowly converges as
		 * you traverse the tree. This does not check for balancing (to do this,
		 * just check that |(deepest node) - (shallowest node)| <= 1
		 */
		return checkBST(n, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static boolean checkBST(TreeNode n, int min, int max) {
		if (n == null) {
			return true;
		}

		TreeNode left = n.left();
		boolean leftResult = true;
		if (left != null) {
			leftResult = false;
			if (left.value() > min && left.value() < max) {
				leftResult = checkBST(left, min, left.value());
			}
		}

		TreeNode right = n.right();
		boolean rightResult = true;
		if (right != null) {
			rightResult = false;
			if (right.value() > min && right.value() < max) {
				rightResult = checkBST(right, right.value(), max);
			}
		}
		return leftResult && rightResult;
	}

	private static boolean checkBSTShorter(TreeNode n, int min, int max) {
		if (n == null) {
			return true;
		}
		if (n.value() <= max && n.value() >= min) {
			return checkBSTShorter(n.left(), min, n.value()) && checkBSTShorter(n.right(), n.value(), max);
		}
		return false;
	}

	public static TreeNode lowestCommonAncestor(TreeNode node, int v1, int v2) {
		// LCA search for generic binary trees
		if (node == null || node.value() == v1 || node.value() == v2) { // Found
			// one!
			return node;
		} else {
			TreeNode leftTree = lowestCommonAncestor(node.left(), v1, v2);
			TreeNode rightTree = lowestCommonAncestor(node.right(), v1, v2);
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

	public static TreeNode tLCABST(TreeNode node, int v1, int v2) {
		TreeNode answer = null;
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
}
