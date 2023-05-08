//@Author Rowan Wiles
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Town_STUDENT_Test {

	Town town;
	@BeforeEach
	void setUp() throws Exception {
		town = new Town("Flavor Town");
	}

	@AfterEach
	void tearDown() throws Exception {
		town = null;
	}
    @Test
	public void setDistanceTest() {
    	town.setDistance(4);
    	assertEquals(town.getDistance(), 4);
	}
@Test
	public void getDistanceTest() {
		town.setDistance(4);
		assertEquals(town.getDistance(), 4);
	}
@Test
	public void getShortestPathTest() {
	LinkedList<String> list = new LinkedList<String>();
	list.add("Margarittaville");
	list.add("not flavor town");
	town.setShortestPath(list);
	assertEquals(town.getShortestPath().get(0),"Margarittaville");
	assertEquals(town.getShortestPath().get(1),"not flavor town");
	}
@Test
	public void setShortestPathTest() {
		LinkedList<String> list = new LinkedList<String>();
		list.add("Margarittaville");
		list.add("not flavor town");
		town.setShortestPath(list);
		assertEquals(town.getShortestPath().get(0),"Margarittaville");
		assertEquals(town.getShortestPath().get(1),"not flavor town");
	}
    @Test
	public void getNameTest() {
    	assertEquals("Flavor Town", town.getName());
	}
	@Test
	public void setNameTest() {
		town.setName("Fallout New Vegas");
		assertEquals("Fallout New Vegas", town.getName());
	}
	@Test
	public void toStringTest() {
		assertEquals("Flavor Town", town.toString());
	}
	@Test
	public void compareToTest() {
		Town town2 = new Town("Flavor Town"), town3 = new Town("Not Flavor Town");
		assertEquals(town2.compareTo(town),0);
		assertFalse(town3.compareTo(town) == 0);
	}
	@Test
	public void hashCodeTest() {
		assertEquals(town.hashCode(), 'F' * 'l' % 50);
	}
	
	@Test
	public void addDestinationTest() {
		Town town2 = new Town("Margarittaville");
		town.addDestination(town2,3);
		assertEquals(town.getAdjacentNodes().get(town2),3);
    }
	@Test
	public void removeDestinationTest() {
		Town town2 = new Town("Margarittaville");
		town.addDestination(town2,3);
		assertEquals(town.getAdjacentNodes().get(town2),3);
		town.removeDestination(town2);
		assertEquals(town.getAdjacentNodes().size(),0);
    }
	@Test
	public void getAdjacentNodesTest() {
		Town town2 = new Town("Margarittaville");
		town.addDestination(town2,3);
		assertEquals(town.getAdjacentNodes().get(town2),3);
	}
}
