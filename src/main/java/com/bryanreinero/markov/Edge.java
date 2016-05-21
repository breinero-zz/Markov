package com.bryanreinero.markov;

public class Edge<T> implements Comparable<Edge> {

	private final T outcome;
	private float probability;

	public Edge(float p, T o ) {
		if (p < 0 || p > 1)
			throw new IllegalArgumentException(
					"Probability must be a value > 0 and < 1");
		if (o == null)
			throw new IllegalArgumentException("Outcome callback can't be null");

		this.probability = p;
		this.outcome = o;
	}

	public float getProbability() {
		return probability;
	}

	public void setProbability(float probability) {
		this.probability = probability;
	}

	public T getOutcome() {
		return outcome;
	}

	@Override
	public int compareTo(Edge rival) {

		if (rival.getProbability() > this.probability)
			return -1;

		if (rival.getProbability() < this.probability)
			return 1;

		return 0;
	}

	@Override
	public boolean equals(Object o) {
        return ( o instanceof Edge &&
               outcome.equals( ((Edge)o).getOutcome() )
        );
	}

    @Override
    public int hashCode() {
        return outcome.hashCode();
    }
	
	@Override
	public String toString () {
		return "{ outcome: "+outcome+", probability: "+probability+"  }";
	}
}
