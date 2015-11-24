package test;

import static org.junit.Assert.*;

import org.junit.Test;

import puzzles.BalancedParenthesis;

public class BalancedParenthesisTest {

	@Test
	public void simple() {
		String answer = BalancedParenthesis.balance("()");
		assertTrue(answer.equals("()"));
	}

	@Test
	public void harder() {
		String answer = BalancedParenthesis.balance("())");
		System.out.println(answer);
		assertTrue(answer.equals("()"));
	}

	@Test
	public void hardest() {
		String answer = BalancedParenthesis.balance(")(())(");
		System.out.println(answer);
		assertTrue(answer.equals("(())"));
	}

	@Test
	public void hardester() {
		String answer = BalancedParenthesis.balance("(())())");
		System.out.println(answer);
		assertTrue(answer.equals("(()())") || answer.equals("(())()"));
	}

}
