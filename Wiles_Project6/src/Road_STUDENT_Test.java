//@Author Rowan Wiles
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Road_STUDENT_Test {

	Town town1, town2;
	Road road;
	
	@BeforeEach
	void setUp() throws Exception {
		Town town1 = new Town("flavor town");
		Town town2 = new Town("margarittaville");
		road = new Road(town1, town2, 5, "Lake Shore Drive");
	}

	@AfterEach
	void tearDown() throws Exception {
		road = null;
		town1 = null;
		town2 = null;
	}
	
	
	@Test
	public void getNameTest() {
		assertEquals("Lake Shore Drive", road.getName());
	}
	@Test
	public void getWeightTest() {
		assertEquals(5,road.getWeight());
	}
	@Test
	public void getSourceTest() {
		Town town1 = new Town("flavor town");
		assertEquals(town1.getName(),road.getSource().getName());
	}
	@Test
	public void setNameTest() {
		road.setName("I-69");
		assertEquals("I-69",road.getName());
	}
	@Test
	public void setWeightTest() {
		road.setWeight(1);
		assertEquals(1,road.getWeight());
	}
	@Test
	public void setDestinationsTest() {
		road.setDestinations(town2, town1);
		assertEquals(road.getDestinations()[0],new Town[] {town2, town1}[0]);
	}
	@Test
	public void toStringTest() {
		assertEquals("Lake Shore Drive,5;flavor town;margarittaville", road.toString());
	}

	@Test
	public void compareToTest() {
		Road road2 = new Road();
		road2.setName("Lake Shore Drive");
		assertTrue(road2.compareTo(road) == 0);
	}
	
	@Test
	public void equalsTest() {
		Town town1 = new Town("flavor town");
		Town town2 = new Town("margarittaville");
		road = new Road(town1, town2, 5, "Lake Shore Drive");
		Road road2 = new Road(town1, town2, 5, "Lake Shore Drive");
		assertTrue(road2.equals(road));
	}
	@Test
	public void isSameRouteTest() {
		Town town1 = new Town("flavor town");
		Town town2 = new Town("margarittaville");
		road = new Road(town1, town2, 5, "Lake Shore Drive");
		assertTrue(road.isSameRoute(new Town[] {town1, town2}));
	}
	@Test
	public void containsTest() {
		Town town1 = new Town("flavor town");
		Town town2 = new Town("margarittaville");
		road = new Road(town1, town2, 5, "Lake Shore Drive");
		assertTrue(road.contains(town1));
	}

}
