package puzzles;

import java.util.*;

public class Trie {
	private List<Node> children;

	public Trie() {
		children = new ArrayList<>();
	}

	public boolean add(String s, Node n) {
		if (s == null) {
			return false;
		}
		if (s.isEmpty()) {
			return true;
		}

		char letter = s.charAt(0);
		if (n.getChildren().contains(letter)) {

		}
		return false;
	}

	public class Node {
		private char value;
		private Set<Node> children;

		public Node(char value) {
			this(value, null);
		}

		public Node(char value, Set<Node> children) {
			this.value = value;
			if (children == null) {
				this.children = new HashSet<>();
			} else {
				this.children = children;
			}
		}

		public char getValue() {
			return value;
		}

		public Set<Node> getChildren() {
			return children;
		}

		public boolean addChild(Node n) {
			return children.add(n);
		}

		public int hashCode() {
			return Integer.parseInt(value + "");
		}

		public boolean equals(Object o) {
			if (o == null || !(o instanceof Node)) {
				return false;
			}
			return value == ((Node) o).getValue();
		}
	}
}
