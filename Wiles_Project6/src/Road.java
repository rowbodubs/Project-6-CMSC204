//@Author Rowan Wiles

public class Road implements Comparable<Road>{
	private Town[] destinations;
	private String name;
	private int distance;
	
	public Road() {
	}
	
	public Road(Town vertex1, Town vertex2, String name) {
		this.name = name;
		this.distance = 1;
		this.destinations = new Town[]{vertex1,vertex2};
	}
	
	public Road(Town vertex1, Town vertex2, int distance, String name) {
		this.name = name;
		this.distance = distance;
		this.destinations = new Town[]{vertex1,vertex2};
	}
	
	public String getName() {
		return name;
	}
	public int getWeight() {
		return distance;
	}
	public Town[] getDestinations() {
		return destinations;
	}
	public Town getDestination() {
		return destinations[1];
	}
	public Town getSource() {
		return destinations[0];
	}
	
	public void setName(String newName) {
		name = newName;
	}
	public void setWeight(int newDistance) {
		distance = newDistance;
	}
	public void setDestinations(Town[] newDestinations) {
		destinations = newDestinations;
	}
	public void setDestinations(Town newDestination1, Town newDestination2) {
		destinations[0] = newDestination1;
		destinations[1] = newDestination2;
	}
	
	public String toString() {
		return name + "," + distance + ";" + destinations[0] + ";" + destinations[1];
	}

	@Override
	public int compareTo(Road o) {
		return name.compareTo(o.getName());
	}
	
	@Override
	public boolean equals(Object o) {
		return isSameRoute(((Road) o).getDestinations());
	}
	
	public boolean isSameRoute(Town[] route) {
		return route[0] == destinations[0] && route[1] == destinations[1] || route[1] == destinations[0] && route[0] == destinations[1];
	}
	
	public boolean contains(Town town) {
		return town.equals(destinations[0]) ||  town.equals(destinations[1]);
	}
}
