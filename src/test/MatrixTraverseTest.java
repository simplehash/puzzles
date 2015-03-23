package test;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import puzzles.MatrixTraverse;

public class MatrixTraverseTest {
	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Test
	public void happy() throws Exception {
		String[][] matrix = new String[][] { { ">", " ", "@" } };
		MatrixTraverse m = new MatrixTraverse(matrix);
		assertTrue(m.traverse());
	}

	@Test
	public void multiRow() throws Exception {
		String[][] matrix = new String[][] { { ">", " ", "v" },
				{ "@", " ", "<" } };
		MatrixTraverse m = new MatrixTraverse(matrix);
		assertTrue(m.traverse());
	}

	@Test
	public void loop() throws Exception {
		String[][] matrix = new String[][] { { ">", " ", "v" },
				{ "^", " ", "<" } };
		ex.expect(Exception.class);
		MatrixTraverse m = new MatrixTraverse(matrix);
		m.traverse();
	}

	@Test
	public void offBoard() throws Exception {
		String[][] matrix = new String[][] { { ">", " ", "v" },
				{ " ", " ", "<" } };
		ex.expect(Exception.class);
		MatrixTraverse m = new MatrixTraverse(matrix);
		m.traverse();
	}
}
