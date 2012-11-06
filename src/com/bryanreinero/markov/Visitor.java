package com.bryanreinero.markov;

public interface Visitor<T extends Visitor<T>> {
	public void visit(Vertex<T> vertex);
	public double getRandomDouble();
}