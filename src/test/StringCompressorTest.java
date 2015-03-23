package test;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import puzzles.StringCompressor;

public class StringCompressorTest {
	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Test
	public void happy() throws Exception {
		StringCompressor s = new StringCompressor("ffrreedd");
		assertTrue(s.getAnswer().equals("f2r2e2d2"));
	}

	@Test
	public void spaces() throws Exception {
		StringCompressor s = new StringCompressor("ffrre e  dd");
		assertTrue(s.getAnswer().equals("f2r2e e 2d2"));
	}

	@Test
	public void nullInput() throws Exception {
		ex.expect(Exception.class);
		new StringCompressor(null);
	}

}
