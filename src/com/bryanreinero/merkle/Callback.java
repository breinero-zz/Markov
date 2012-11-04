package com.bryanreinero.merkle;

public interface Callback <T extends Visitor<T>> {
	public void accept ( T  responder ); 
}