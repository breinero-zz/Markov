package com.bryanreinero.markov;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Node<T> {

    private static final Random rand = new Random();
	private Set<Edge<T>> edges = new HashSet<Edge<T>>();
	
	public void setProbabilities( Set<Edge<T>> candidates ) {

		float totalDesiredProbability = 0;


        Set<Edge<T>> newEvents = new HashSet<Edge<T>>();
		
		for ( Edge e : candidates ) {
			totalDesiredProbability += e.getProbability();
            if( e.getProbability() > 0 )
                newEvents.add( e );
        }
		
		for ( Edge e : edges )
			if( candidates.contains( e ) )
                 continue;
            else {
                e.setProbability((1 - totalDesiredProbability) * e.getProbability());
                if( e.getProbability() > 0 )
                    newEvents.add( e );
            }

        float newTotalProb = 0;
        for ( Edge e : newEvents )
            newTotalProb += e.getProbability();

        if (  newTotalProb < 1 && newTotalProb > 0 )
            for( Edge e : newEvents )
                e.setProbability( e.getProbability() / newTotalProb  );

        edges = newEvents;
	}

	@Override
	public String toString() {
		float cumulativeProbability = 0;
		StringBuffer buf = new StringBuffer("{\nevents: [\n");
		
		boolean first = true;
		for ( Edge edge : edges )  {
			if( first )
				first = false;
			else
				buf.append(",\n");
			
			cumulativeProbability+= edge.getProbability();
			buf.append( edge );
		}
		buf.append("\n],\n");
		buf.append("total: "+cumulativeProbability+"\n}");
		
		return buf.toString();
	}
	
	public T run() {
		float cumulativeProbability = 0;

		for( Edge<T> e : edges ) {
			cumulativeProbability += e.getProbability();
			if( rand.nextFloat() <=  cumulativeProbability  )
				return e.getOutcome();
		}
        return null;
    }
}
