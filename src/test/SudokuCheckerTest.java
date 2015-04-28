package test;

import static org.junit.Assert.*;

import org.junit.Test;

import puzzles.SudokuChecker;

public class SudokuCheckerTest {
	SudokuChecker sc;

	@Test
	public void good() {
		sc = new SudokuChecker("963174258178325649254689731821437596496852317735961824589713462317246985642598173");
		assertEquals(sc.getResult(), 1);
	}

	@Test
	public void bad() {
		sc = new SudokuChecker("123456789123456789123456789123456789123456789123456789123456789123456789123456789");
		assertEquals(sc.getResult(), 0);
	}

}
