package puzzles;

public class TreeSymmetry {
	interface Node {
		int value();

		Node left();

		Node right();
	}

	public static boolean check(Node n) {
		return check(n.left(), n.right());
	}

	private static boolean check(Node left, Node right) {
		if (left == null && right == null) {
			return true;
		}
		if (left.value() != right.value() || (left.left() == null && right.right() != null) || (left.right() == null && right.left() != null)) {
			return false;
		}
		return check(left.left(), right.right()) && check(left.right(), right.left());
	}
}
