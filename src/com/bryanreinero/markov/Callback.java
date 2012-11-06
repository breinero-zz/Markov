package com.bryanreinero.markov;

public interface Callback <T extends Visitor<T>> {
	public void accept ( T  responder ); 
}