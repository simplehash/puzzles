package puzzles;

public class TreeLCA {
	public static Node recursive(Node node, int v1, int v2) {
		Node answer = null;
		if (node == null) {
			return null;
		}

		if (v1 < node.value && v2 < node.value) {
			// Both values are in the left sub-tree
			answer = recursive(node.left, v1, v2);
		} else if (v1 > node.value && v2 > node.value) {
			// Both values are in the right sub-tree
			answer = recursive(node.right, v1, v2);
		} else {
			// If both cases above fail, this is the node where the 2 values
			// split
			answer = node;
		}
		return answer;
	}

	public static Node loop(Node node, int v1, int v2) {
		Node answer = node;
		while (answer != null && (v1 < answer.value && v2 < answer.value) || (v1 > answer.value && v2 > answer.value)) {
			if (v1 < answer.value && v2 < answer.value) {
				answer = answer.left;
			} else if (v1 > answer.value && v2 > answer.value) {
				answer = answer.right;
			}
		}
		return answer;
	}

	public class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int value) {
			this.value = value;
			left = null;
			right = null;
		}
	}
}
