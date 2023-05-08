//@Author Rowan Wiles
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TownGraphManager_STUDENT_Test {

	TownGraphManager tgm;
	
	@BeforeEach
	void setUp() throws Exception {
		tgm = new TownGraphManager();
		tgm.addTown("Funky Town");
		tgm.addTown("Margaritaville");
		tgm.addRoad("Funky Town", "Margaritaville", 5, "I-69");
	}

	@AfterEach
	void tearDown() throws Exception {
		tgm = null;
	}

	@Test
	public void addRoadTest( ) { 
		tgm.addTown("Villesburg");
		tgm.addRoad("Margaritaville", "Villesburg", 8, "road");
		assertEquals(2,tgm.allRoads().size());
	}

	@ Test
	public void getRoadTest( ) { 
		assertEquals("I-69",tgm.getRoad("Margaritaville", "Funky Town"));
	}

	@ Test
	public void addTownTest( )  { 
		tgm.addTown("Villesburg");
		assertEquals(tgm.getTown("Villesburg").getName(),"Villesburg");
	}

	@Test
	public void getTownTest( ) { 
		assertEquals(tgm.getTown("Margaritaville").getName(),"Margaritaville");
	}

	@ Test
	public  void containsTownTest( ) { 
		assertTrue(tgm.containsTown("Margaritaville"));
	}

	@ Test
	public void containsRoadConnectionTest( ) {  
		assertTrue(tgm.containsRoadConnection("Margaritaville", "Funky Town"));
	}

	@ Test
	public void allRoadsTest() { 
		assertEquals(1,tgm.allRoads().size());
	}

	@ Test
	public void deleteRoadConnectionTest( ) { 
		tgm.deleteRoadConnection("Margaritaville", "Funky Town", "I-69");
		assertEquals(0,tgm.allRoads().size());
	}

	@ Test
	public void deleteTownTest( ) { 
		tgm.deleteTown("Margaritaville");
		assertEquals(1,tgm.allTowns().size());
	}

	@ Test
	public void allTownsTest() { 
		assertEquals(2,tgm.allTowns().size());
	}

	@ Test
	public void getPathTest( ) { 
		tgm.addTown("Villesburg");
		tgm.addRoad("Margaritaville", "Villesburg", 8, "road");
		assertEquals("[Funky Town via I-69 to Margaritaville 5 mi, Margaritaville via road to Villesburg 8 mi]",
				tgm.getPath("Funky Town", "Villesburg").toString());
	}

	@ Test
	public void populateTownGraphTest( ) throws FileNotFoundException, IOException  { 
		TownGraphManager tgm2 = new TownGraphManager();
		tgm2.populateTownGraph(new File("MD Towns(1).txt"));
		assertEquals("I270-N",tgm2.getRoad("Frederick","Clarksburg"));
		assertEquals("MD109",tgm2.getRoad("Clarksburg","Poolesville"));
		assertEquals("I270-S",tgm2.getRoad("Rockville","Bethesda"));
		assertEquals("MD190E",tgm2.getRoad("Bethesda","Potomac"));
	}

}
