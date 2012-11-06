package com.bryanreinero.markov;

public class Edge<T extends Visitor<T>> implements Comparable <Edge<T>> {
	
	private final double probability;
	private final Vertex<T> vertex;
	
	public Edge ( double probability, Vertex<T> vertex ) {
		if( probability <= 0 )
			throw new IllegalArgumentException("probalility less than zero");
		if( probability > 1 )
			throw new IllegalArgumentException("probalility greater than one");
		if( vertex == null )
			throw new IllegalArgumentException("vertex can not be null");
			
		this.probability = probability;
		this.vertex = vertex;
	}

	public double getProbability() {
		return probability;
	}

	public Vertex<T> getVertex() {
		return vertex;
	}

	@Override
	public int compareTo( Edge<T> rival ) {

		if (rival.getProbability() > this.probability)
			return -1;

		if (rival.getProbability() < this.probability)
			return 1;

		return 0;
	}
}