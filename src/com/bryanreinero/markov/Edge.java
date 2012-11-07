/**
*    Copyright (C) 2012 Bryan Reinero
*
*    This program is free software: you can redistribute it and/or  modify
*    it under the terms of the GNU Affero General Public License, version 3,
*    as published by the Free Software Foundation.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU Affero General Public License for more details.
*
*    You should have received a copy of the GNU Affero General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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