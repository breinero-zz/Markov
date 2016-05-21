package com.bryanreinero.markov;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by brein on 5/20/2016.
 */
public class TestNode {

    private final Node node1 = new Node();
    private final String edge1Outcome = "edge1";
    private final float edge1Probability = 1f;
    private final Edge<String> edge1 = new Edge<>( edge1Probability, edge1Outcome );

    @Test
    public void testNode() {
        Set<Edge> edges = new HashSet<>();
        edges.add( edge1 );
        node1.setProbabilities( edges );
        assertEquals( edge1Outcome, node1.run() );
    }
}
