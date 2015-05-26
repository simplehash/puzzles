package test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import puzzles.T9;

public class T9Test {

	@Test
	public void simple() {
		base("3");
	}

	@Test
	public void harder() {
		base("34");
	}

	@Test
	public void evenHarder() {
		base("3733");
	}

	@Test
	public void invalid() {
		T9.go(null);
		T9.go("");
		T9.go("asf");
	}

	public void base(String numbers) {
		Set<String> answer = T9.go(numbers);
		System.out.print(numbers + ": ");
		for (String s : answer) {
			System.out.print(s + " ");
		}
		int expectedResults = 1;
		for (char c : numbers.toCharArray()) {
			int value = Integer.parseInt(c + "");
			if (value == 7 || value == 9) {
				expectedResults *= 4;
			} else if (value != 0 && value != 1) {
				expectedResults *= 3;
			}
		}
		System.out.println("");
		assertEquals(answer.size(), expectedResults);
	}

}
