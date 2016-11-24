package ca.mcgill.ecse321.foodtruckmanagementsystem.persistence;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Manager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.MenuItem;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.StaffMember;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Supply;

public class TestPersistence {

	@Before
	public void setUp() throws Exception {
		Manager manager = Manager.getInstance();
		
		Equipment equipment1 = new Equipment("grill", 2);
		Equipment equipment2 = new Equipment("fridge", 1);
		
		manager.addEquipment(equipment1);
	}

	@After
	public void tearDown() throws Exception {
		Manager manager = Manager.getInstance();
		manager.delete();
	}

	@Test
	public void test() {
		Manager manager = Manager.getInstance();
		PersistenceXStream.setFilename("test"+File.separator+"ca"+File.separator+"mcgill"+File.separator+"ecse321"
										+File.separator+"foodtruckmanagementsystem"+File.separator+"persistence"+File.separator
										+File.separator+"data.xml");
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("manager", Manager.class);
		PersistenceXStream.setAlias("menuitem", MenuItem.class);
		PersistenceXStream.setAlias("staffmember", StaffMember.class);
		PersistenceXStream.setAlias("supply", Supply.class);
		if(!PersistenceXStream.saveToXMLwithXStream(manager))
			fail("Could not save file.");
		
		manager.delete();
		assertEquals(0, manager.getEquipments().size());
		
		manager = (Manager) PersistenceXStream.loadFromXMLwithXStream();
		if(manager == null)
			fail("Could not load file.");
		
		assertEquals(1, manager.getEquipments().size());
		assertEquals("grill", manager.getEquipment(0).getName());
		assertEquals(2, manager.getEquipment(0).getQuantity());		
	}

}
