package com.bryanreinero.merkle;

public interface Visitor<T extends Visitor<T>> {
	public void visit(Vertex<T> vertex);
	public double getRandomDouble();
}