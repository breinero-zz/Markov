package com.bryanreinero.markov;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by brein on 5/20/2016.
 */
public class TestEdge {

    private final String edge1Outcome = "edge1";
    private final float edge1Probability = 0.5f;
    private final Edge<String> edge1 = new Edge<>( edge1Probability, edge1Outcome );

    @Test
    public void testEdge() {
        assert( edge1Outcome.equals( edge1.getOutcome() ) );
        assertEquals( edge1Probability, edge1.getProbability(), 0.0001 );
    }

    @Test
    public void testEdgeEquals() {
        Edge<String> edge2 = new Edge<>( edge1Probability, edge1Outcome );
        assert( edge1.equals( edge1) );
    }

    @Test
    public void testEdgeProbability() {
        float newProbability = 0.9f;
        edge1.setProbability( newProbability);
        assertEquals( newProbability, edge1.getProbability(), 0.0001);
    }
}
