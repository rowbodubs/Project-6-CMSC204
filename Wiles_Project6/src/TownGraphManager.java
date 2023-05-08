import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

//@Author Rowan Wiles
public class TownGraphManager implements TownGraphManagerInterface{
	
	Graph graph;
	
	TownGraphManager() {
		graph = new Graph();
	}

	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		//check if the towns are in the graph.
		if (graph.getVertex(town1) == null || graph.getVertex(town2) == null) {
			return false;
		}
		
		graph.addEdge(graph.getVertex(town1), graph.getVertex(town2), weight,  roadName);
		return true;
	}

	@Override
	public String getRoad(String town1, String town2) {
		return graph.getEdge(graph.getVertex(town1), graph.getVertex(town2)).getName();
	}

	@Override
	public boolean addTown(String v)  {
		return graph.addVertex(new Town(v));
	}

	@Override
	public Town getTown(String name) {
		return graph.getVertex(name);
	}

	@Override
	public boolean containsTown(String v) {
		//see if accessing the town will throw an error
		try {
			
			//see if trying to get the town will return null
			if (graph.getVertex(v) == null) {
				return false;
			}
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	//check if the graph has a road that connects the two towns
	public boolean containsRoadConnection(String town1, String town2) { 
		return graph.containsEdge(graph.getVertex(town1), graph.getVertex(town2));
	}

	@Override
	//return a list of every road in the graph
	public ArrayList<String> allRoads() {
		
		//get all the roads as an array
		Road[] roads = graph.edgeSet().toArray(new Road[0]);
		ArrayList<String> output = new ArrayList<String>();
		
		//put each road's name into a list
		for (Road road : roads) {
			output.add(road.getName());
		}
		
		//sort and output the list
		Collections.sort(output);
		return output;
	}

	@Override
	//remove the road that connects the two towns
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		try {
			graph.removeEdge(graph.getVertex(town1),graph.getVertex(town2),0,road);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteTown(String v) {
		return graph.removeVertex(graph.getVertex(v));
	}

	@Override
	//return a list of every town in the graph
	public ArrayList<String> allTowns() {
		
		//take every town as an array
		Town[] towns = (Town[]) graph.vertexSet().toArray(new Town[0]);
		ArrayList<String> output = new ArrayList<String>();
		
		//add each town's name to a list
		for (Town town : towns) {
			output.add(town.getName());
		}
		
		//sort and output the list
		Collections.sort(output);
		return output;
	}

	@Override
	//get the shortest path between the two towns
	public ArrayList<String> getPath(String town1, String town2) {
		return graph.shortestPath(graph.getVertex(town1), graph.getVertex(town2));
	}

	@Override
	//convert a file of roads into a graph
	public void populateTownGraph(File file) throws FileNotFoundException, IOException {
		FileReader fileReader = new FileReader(file);
		ArrayList<String> roadList = new ArrayList<String>();
		String road = "";
		int currentChar = fileReader.read();
		
		//run through each character in the file
		while (currentChar != -1) {
			
			//check if this is the end of a line
			if ((char) currentChar == '\r') {
				
				//add the road to the list of roads
				roadList.add(road);
				road = "";
				
				//ignore the newline character
				fileReader.read();
				
			} else {
				
				//add the character to the current road
				road += (char)currentChar;
			}
			
			//move to the next character
			currentChar = fileReader.read();
		}
		//add the last road
		roadList.add(road);
		
		String[] townArray;
		String[] roadArray;
		
		//go through every road string in roadList
		for (String roadString : roadList) {
			
			//seperate the two destinations of the road
			townArray = roadString.split(";");
			//seperate the road's name and distance
			roadArray = townArray[0].split(",");
			
			//check if the towns that this road connects are already in the graph
			//if they aren't, add them
			if (!containsTown(townArray[1])) {
				addTown(townArray[1]);
			}
			if (!containsTown(townArray[2])) {
				addTown(townArray[2]);
			}
			
			//add the road
			addRoad(townArray[1],townArray[2],Integer.parseInt(roadArray[1]), roadArray[0]);
		}
	}

}
