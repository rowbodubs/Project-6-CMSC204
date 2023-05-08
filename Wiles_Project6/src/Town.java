import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//@Author Rowan Wiles
public class Town implements Comparable<Town> {
    
    private String name;
    private List<String> shortestPath = new LinkedList<String>();
    private Integer distance = Integer.MAX_VALUE;
    Map<Town, Integer> adjacentNodes = new HashMap<>();
 
    public Town(String name) {
        this.name = name;
    }
    
    //deep copy
    public Town(Town copee) {
        name = copee.getName();
        shortestPath = copee.getShortestPath();
        distance = copee.getDistance();
    }

	public void setDistance(int i) {
		this.distance = i;
	}

	public int getDistance() {
		return distance;
	}

	public List<String> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(LinkedList<String> shortestPath2) {
		shortestPath = shortestPath2;
	}
    
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	public int compareTo(Town o) {
		return name.compareTo(o.getName());
	}
	
	public int hashCode() {
		switch (name.length()) {
		case 0: return 0;
		case 1: return name.charAt(0) % 50;
		default: return name.charAt(0) * name.charAt(1) % 50;
		}
	}
	
	public void addDestination(Town destination, int distance) {
        adjacentNodes.put(destination, distance);
    }
	public void removeDestination(Town destination) {
        adjacentNodes.remove(destination);
    }
	public Map<Town, Integer> getAdjacentNodes() {
		return adjacentNodes;
	}
}
