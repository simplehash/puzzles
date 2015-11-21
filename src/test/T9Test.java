package test;

import org.junit.Test;
import puzzles.SetStuff;

import java.util.Set;

import static org.junit.Assert.assertEquals;

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
		SetStuff.t9(null);
		SetStuff.t9("");
		SetStuff.t9("asf");
	}

	public void base(String numbers) {
		Set<String> answer = SetStuff.t9(numbers);
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
