package com.bryanreinero.merkle;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;

import com.bryanreinero.merkle.VertexTest.TestVisitor;

public class EdgeTest {
	
	private static final String BAD_LOW_PROB_EXCEPTION_MESSAGE = "probalility less than zero";
	private static final String BAD_HIGH_PROB_EXCEPTION_MESSAGE = "probalility greater than one";
	private static final String BAD_NULL_VERTEX_EXCEPTION_MESSAGE = "vertex can not be null";
	
	@Test
	public void testConstructors() {
		
		Edge<TestVisitor> edge;
		
		try {
			edge = new Edge<TestVisitor>(-1, null);
		} catch (IllegalArgumentException expected) {
			assertTrue("Didn't catch the expected exception: "
					+ BAD_LOW_PROB_EXCEPTION_MESSAGE, expected.getMessage()
					.matches(BAD_LOW_PROB_EXCEPTION_MESSAGE));
		}
		
		try {
			edge = new Edge<TestVisitor>(2, null);
		} catch (IllegalArgumentException expected) {
			assertTrue("Didn't catch the expected exception: "
					+ BAD_HIGH_PROB_EXCEPTION_MESSAGE, expected.getMessage()
					.matches(BAD_HIGH_PROB_EXCEPTION_MESSAGE));
		}
		
		try {
			edge = new Edge<TestVisitor>(1, null);
		} catch (IllegalArgumentException expected) {
			assertTrue("Didn't catch the expected exception: "
					+ BAD_NULL_VERTEX_EXCEPTION_MESSAGE, expected.getMessage()
					.matches(BAD_NULL_VERTEX_EXCEPTION_MESSAGE));
		}
		
		Vertex<TestVisitor> vertex = new Vertex<TestVisitor>(null, null);
		edge = new Edge<TestVisitor>(1, vertex);
		
		assert( edge != null );
		assertEquals( edge.getProbability(), 1.0 ); 
	}
	
	@Test
	public void testCompares () {
		Edge<TestVisitor> baseline = new Edge<TestVisitor>(0.2, new Vertex<TestVisitor>(null, null));
		
		int score = baseline.compareTo(new Edge<TestVisitor>(0.2, new Vertex<TestVisitor>(null, null)));
		assertEquals( score, 0);
		
		score = baseline.compareTo(new Edge<TestVisitor>(0.1, new Vertex<TestVisitor>(null, null)));
		assertEquals( score, 1);
		
		score = baseline.compareTo(new Edge<TestVisitor>(0.3, new Vertex<TestVisitor>(null, null)));
		assertEquals( score, -1);
	}
}
