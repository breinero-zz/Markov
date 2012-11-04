package com.bryanreinero.merkle;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;


public class VertexTest {
	
	public class TestVisitor implements Visitor<TestVisitor> {

		@Override
		public void visit(Vertex<TestVisitor> vertex) {
			List<Callback<TestVisitor>> callbacks = vertex.getCallbacks();
			for ( Callback<TestVisitor> cb : callbacks )
				cb.accept(this);
				
		}

		@Override
		public double getRandomDouble() {
			return random.nextDouble() / java.lang.Double.MAX_VALUE;
		}
		
		@Override
		public String toString () {
			return path.toString();
		}
		
		public void addVertexToPath( String name ) {
			path.add(name);
		}
		
		private Random random = new Random();
		private ArrayList<String> path = new ArrayList<String>();
		
	}
	
	@Test
	public void testTooMuchProbablility() {
		List<Edge<TestVisitor>> edges = new ArrayList<Edge<TestVisitor>>();
		edges.add(new Edge<TestVisitor>(1.0, new Vertex<TestVisitor>(null, null) ) );
		edges.add(new Edge<TestVisitor>(1.0, new Vertex<TestVisitor>(null, null) ) );
		edges.add(new Edge<TestVisitor>(1.0, new Vertex<TestVisitor>(null, null) ) );
		
		try {
			new Vertex<TestVisitor>(edges, null);
		} catch (IllegalArgumentException expected) {
			assertTrue("Didn't catch the expected exception: " +
					"Edge probabilities do not sum to 1", expected.getMessage()
					.matches("Edge probabilities do not sum to 1"));
		}
		
	}
	
	@Test
	public void testCallBacks () {
		
		List <Callback<TestVisitor>> callbacks = new ArrayList<Callback<TestVisitor>>();
		callbacks.add(new Callback<TestVisitor>() {

			@Override
			public void accept(TestVisitor responder) {
				responder.addVertexToPath("Vertex3");
			}} );
		
		callbacks.add(new Callback<TestVisitor>() {

			@Override
			public void accept( TestVisitor responder) {
				assertEquals(responder.toString(), "[Vertex1, Vertex2, Vertex3]");
			}} 
		);
		

		// work from last to first
		Vertex<TestVisitor> vertex3 = new Vertex<TestVisitor>(null, callbacks);
		
		List <Edge<TestVisitor>> edges = new ArrayList<Edge<TestVisitor>>();
		edges.add(new Edge<TestVisitor>(1.0, vertex3));
		
		callbacks = new ArrayList<Callback<TestVisitor>>();
		callbacks.add(new Callback<TestVisitor>() {

			@Override
			public void accept(TestVisitor responder) {
				responder.addVertexToPath("Vertex2");
			}} 
		);
		
		Vertex<TestVisitor> vertex2 = new Vertex<TestVisitor>(edges, callbacks);
		
		callbacks = new ArrayList<Callback<TestVisitor>>();
		callbacks.add(new Callback<TestVisitor>() {

			@Override
			public void accept( TestVisitor responder) {
				responder.addVertexToPath("Vertex1");
			}}
		);
		
		
		edges = new ArrayList<Edge<TestVisitor>>();
		edges.add(new Edge<TestVisitor>(1.0, vertex2));
		Vertex<TestVisitor> vertex1 = new Vertex<TestVisitor>(edges, callbacks);
		
		vertex1.accept(new TestVisitor());
	}
}
