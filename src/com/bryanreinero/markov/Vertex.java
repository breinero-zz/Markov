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

import java.util.Collections;
import java.util.List;

public class Vertex <T extends Visitor<T>> {
	
	public Vertex ( List<Edge<T>> edges, List<Callback<T>> callbacks) {
		
		if ( edges != null ) {

			double totalProbablility = 0;

			for ( Edge<T> edge : edges )
				totalProbablility += edge.getProbability();

			if( totalProbablility != 1 )
				throw new IllegalArgumentException( );

			_edges = edges;
			Collections.sort(_edges);
		}
		
		_callbacks = callbacks;
	}
	
	public void accept(Visitor<T> visitor) {
		visitor.visit(this);
		
		if ( _edges != null && _edges.size() > 0 )
			getNextVertex(visitor.getRandomDouble()).accept(visitor);
	}
	
	private Vertex<T> getNextVertex ( double point ) {
		if( point <= 0 )
			throw new IllegalArgumentException("point less than zero");
		if( point > 1 )
			throw new IllegalArgumentException("point greater than one");
		
		double total = 0;
		for ( Edge<T> edge : _edges ) {
			total += edge.getProbability();
			if ( point <= total )
				return edge.getVertex();
		}
		return null;
	}
	
	public List<Callback<T>> getCallbacks() {
		return _callbacks;
	}

	private List <Edge<T>> _edges = null;
	private List <Callback<T>> _callbacks = null;
}
