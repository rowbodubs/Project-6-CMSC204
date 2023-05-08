import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

//@Author Rowan Wiles
public class Graph implements GraphInterface<Town,Road>{
	
	private ArrayList<Town> towns;
	private ArrayList<Road> roads;
	
	public Graph() {
		towns = new ArrayList<Town>();
		roads = new ArrayList<Road>();
	}
	
	public Town getVertex(String name) {
		
		//iterate through every town and return the town if it has the name passed as a parameter.
		for (Town town : towns) {
			if (town.getName().equals(name)) {
				return town;
			}
		}
		return null;
	}

	@Override
	//get the edge that connects the two towns
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		Town[] route = new Town[] {sourceVertex,destinationVertex};
		
		//iterate through every road
		for (Road road : roads) {
			
			//check if the road connects the two towns passed as arguments
			if (road.isSameRoute(route)) {
				
				//check if the towns are the wrong way around and if so swap them
				if (!road.getDestinations()[0].equals(route[0])) {
					road.setDestinations(road.getDestinations()[1],road.getDestinations()[0]);
				}
				
				return road;
			}
		}
		return null;
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		
		//creat and add the new road
		Road newRoad = new Road( sourceVertex, destinationVertex,  weight, description);
		roads.add(newRoad);
		
		//add each connected town to the others adjacency list
		sourceVertex.addDestination(destinationVertex,weight);
		destinationVertex.addDestination(sourceVertex,weight);
		
		return newRoad;
	}

	@Override
	public boolean addVertex(Town v) {
		
		boolean output = towns.add(v);
		
		return output;
	}

	@Override
	//check if there's a road that connects the two towns passed as arguments
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		Town[] route = new Town[] {sourceVertex,destinationVertex};
		
		//iterate through each road
		for (Road road : roads) {
			
			//check if this road connects the two passed towns
			if (road.isSameRoute(route)) {
				return true;
			}
		}
		return false;
	}

	@Override
	//check if this graph has the specified town
	public boolean containsVertex(Town v) {
		
		//iterate through each town
		for (Town town : towns) {
			
			//if a town has the same name as the town passed as an argument return true
			if (town.compareTo(v) == 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	//return a set containing every road
	public Set<Road> edgeSet() {
		return  new HashSet<Road>(roads);
	}

	@Override
	//return every road connecting to the specified town
	public Set<Road> edgesOf(Town vertex) {
		HashSet<Road> output = new HashSet<Road>();
		
		//iterate through each road
		for (Road road : roads) {
			
			//if the road connects the town add it to the output
			if (road.contains(vertex)) {
				output.add(road);
			}
		}
		return output;
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		
		Road output = getEdge(sourceVertex,destinationVertex);
		roads.remove(getEdge(sourceVertex,destinationVertex));
		
		//remove each town from the other's adjacency list
		sourceVertex.removeDestination(destinationVertex);
		destinationVertex.removeDestination(sourceVertex);
		
		return output;
	}

	@Override
	public boolean removeVertex(Town v) {
		return towns.remove(v);
	}

	@Override
	//return a set containing each town
	public Set<Town> vertexSet() {
		return new HashSet<Town>(towns);
	}

	@Override
	//return the shortest path from one town to another
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		
		//calculate the shortest path from the source to everysingle other town
		dijkstraShortestPath(sourceVertex);
		
		ArrayList<String> output = new ArrayList<String>();
		//iterate through each element of the shortest path from the source to the specified town
		for (String town : destinationVertex.getShortestPath()) {
			//add each String to the list
			output.add(town);
		}
		return output;
	}

	//These last three methods use elements from the Geeks for Geeks turorial that can be found here: https://www.baeldung.com/java-dijkstra
	//The code was commented and edited for my purposes
	
	@Override
	//find the shortest paths from the sourceVertex to every other town
	public void dijkstraShortestPath(Town sourceVertex) {
		
		//set every towns distance to infinity
		for (Town town : towns) {
			town.setDistance(Integer.MAX_VALUE);
		}
		
		//set the source's distance to 0
		sourceVertex.setDistance(0);

	    Set<Town> settledNodes = new HashSet<>();
	    Set<Town> unsettledNodes = new HashSet<>();

	    unsettledNodes.add(sourceVertex);
	    
	    //while there are unsettled nodes
	    while (unsettledNodes.size() != 0) {
	    	
	    	//set the current node to the closest town
	        Town currentNode = getLowestDistanceNode(unsettledNodes);
	     
	        unsettledNodes.remove(currentNode);
	        
	        //for each town adjacent to the current node
	        for (Entry < Town, Integer> adjacencyPair: 
	          currentNode.getAdjacentNodes().entrySet()) {
	        	
	        	//set adjacent node to the current adjacent town
	            Town adjacentNode = adjacencyPair.getKey();
	            //set edgeWeight to the distance between currentNode and the adjacent node
	            Integer edgeWeight = adjacencyPair.getValue();
	            
	            //if the current adjacent node is unsettled
	            if (!settledNodes.contains(adjacentNode)) {
	            	
	                calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
	                unsettledNodes.add(adjacentNode);
	            }
	        }
	        
	        settledNodes.add(currentNode);
	    }
	}

	//return the closest node to the source node
	private static Town getLowestDistanceNode(Set < Town > unsettledNodes) {
		
		Town lowestDistanceNode = null;
	    int lowestDistance = Integer.MAX_VALUE;
	    
	    //iterate through every unsettled node
	    for (Town node: unsettledNodes) {
	    	
	        int nodeDistance = node.getDistance();
	        
	        //if the current node is closer than the current lowest replace the current lowest
	        if (nodeDistance < lowestDistance) {
	            lowestDistance = nodeDistance;
	            lowestDistanceNode = node;
	        }
	    }
	    
	    return lowestDistanceNode;
	}
	
	
	private void calculateMinimumDistance(Town evaluationNode,Integer edgeWeight, Town sourceNode) {
		
		Integer sourceDistance = sourceNode.getDistance();
		
		//check if the current path's length is better or worse than the new path
		if (sourceDistance + edgeWeight < evaluationNode.getDistance()) {
			//if it's better
			
			evaluationNode.setDistance(sourceDistance + edgeWeight);
			
			//add the new town to the shortest path
			LinkedList<String> shortestPath = new LinkedList<String>(sourceNode.getShortestPath());
			shortestPath.add(sourceNode.toString() + " via " + getEdge(evaluationNode,sourceNode).getName() + " to " + evaluationNode.toString() + " " + edgeWeight + " mi");
			
			evaluationNode.setShortestPath(shortestPath);
			}
		}
}
