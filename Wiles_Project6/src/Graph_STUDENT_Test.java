//@Author Rowan Wiles
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Graph_STUDENT_Test {

	Graph graph;
	Town town1, town2, town3;
	
	@BeforeEach
	void setUp() throws Exception {
		graph = new Graph();
		town1 = new Town("Funky Town");
		town2 = new Town("Palm Springs");
		town3 = new Town("Margaritaville");
		graph.addVertex(town2);
		graph.addVertex(town1);
		graph.addEdge(town1, town2, 5, "Road St.");
	}

	@AfterEach
	void tearDown() throws Exception {
		graph = null;
		town1 = null;
		town2 = null;
		town3 = null;
	}
	
	
	@Test
	public void getVertexTest() {
		assertEquals(graph.getVertex("Funky Town"),town1);
	}

	@Test
	public void getEdgeTest() {
		assertEquals(graph.getEdge(town2, town1),new Road(town1, town2, 5, "Road St."));
	}

	@Test
	public void addEdgeTest() { 
		graph.addVertex(town3);
		graph.addEdge(town1, town3, 3, "Lake Shore Drive");
		assertEquals(new Road(town1, town3, 3, "Lake Shore Drive"), graph.getEdge(town1, town3));
	}

	@Test
	public void addVertexTest() {
		graph.addVertex(town3);
		assertEquals(graph.getVertex("Margaritaville"),town3);
	}

	@Test
	public void containsEdgeTest() {
		assertTrue(graph.containsEdge(town2, town1));
	}

	@Test
	public void containsVertexTest() {
		assertTrue(graph.containsVertex(town1));
	}

	@Test
	public void edgeSetTest() {
		assertEquals(1,graph.edgeSet().size());
	}

	@Test
	public void edgesOfTest( ) { 
		assertEquals(1, graph.edgesOf(town1).size());
	}

	@Test
	public void removeEdgeTest( ) { 
		graph.removeEdge(town2, town1, 5, "Road St.");
		assertEquals(0, graph.edgesOf(town1).size());
	}

	@Test
	public void removeVertexTest( ) {
		graph.removeVertex(town1);
		assertEquals(1,graph.vertexSet().size());
	}

	@Test
	public void vertexSetTest() { 
		assertEquals(2,graph.vertexSet().size());
	}

	@Test
	public void shortestPathTest( ) { 
		graph.addEdge(town1, town3, 3, "Lake Shore Drive");
		graph.dijkstraShortestPath(town2);
		assertEquals(graph.shortestPath(town2, town3).toString(),
				"[Palm Springs via Road St. to Funky Town 5 mi, Funky Town via Lake Shore Drive to Margaritaville 3 mi]");
	}

	@Test
	public void dijkstraShortestPathTest( ) { 
		graph.addEdge(town1, town3, 3, "Lake Shore Drive");
		graph.dijkstraShortestPath(town2);
		assertEquals(graph.shortestPath(town2, town3).toString(),
				"[Palm Springs via Road St. to Funky Town 5 mi, Funky Town via Lake Shore Drive to Margaritaville 3 mi]");
	}
}
