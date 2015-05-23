package puzzles;

public class TreeLCA {
	public static Node treeRecursive(Node node, int v1, int v2) {
		// LCA search for generic binary trees
		if (node == null || node.value() == v1 || node.value() == v2) { // Found
																	// one!
			return node;
		} else {
			Node leftTree = treeRecursive(node.left(), v1, v2);
			Node rightTree = treeRecursive(node.right(), v1, v2);
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

	public static Node BSTrecursive(Node node, int v1, int v2) {
		Node answer = null;
		if (node == null) {
			return null;
		}

		if (v1 < node.value() && v2 < node.value()) {
			// Both values are in the left sub-tree
			answer = BSTrecursive(node.left(), v1, v2);
		} else if (v1 > node.value() && v2 > node.value()) {
			// Both values are in the right sub-tree
			answer = BSTrecursive(node.right(), v1, v2);
		} else {
			// If both cases above fail, this is the node where the 2 values
			// split
			answer = node;
		}
		return answer;
	}

	public static Node BSTloop(Node node, int v1, int v2) {
		Node answer = node;
		while (answer != null && (v1 < answer.value() && v2 < answer.value()) || (v1 > answer.value() && v2 > answer.value())) {
			if (v1 < answer.value() && v2 < answer.value()) {
				answer = answer.left();
			} else if (v1 > answer.value() && v2 > answer.value()) {
				answer = answer.right();
			}
		}
		return answer;
	}
	
	interface Node {
		int value();
		Node left();
		Node right();
	}
}
