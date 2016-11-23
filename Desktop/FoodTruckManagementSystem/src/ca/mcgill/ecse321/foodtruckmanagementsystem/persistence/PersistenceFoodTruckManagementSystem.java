package ca.mcgill.ecse321.foodtruckmanagementsystem.persistence;

import java.util.Iterator;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Manager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.MenuItem;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.StaffMember;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Supply;

public class PersistenceFoodTruckManagementSystem {
	
	private static void initializeXStream() {
		PersistenceXStream.setFilename("data.xml");
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("supply", Supply.class);
		PersistenceXStream.setAlias("menuitem", MenuItem.class);
		PersistenceXStream.setAlias("staffmember",  StaffMember.class);
		PersistenceXStream.setAlias("manager", Manager.class);
	}
	
	public static void loadFoodTruckManagementSystemModel() {
		Manager m = Manager.getInstance();
		PersistenceFoodTruckManagementSystem.initializeXStream();
		Manager m2 = (Manager) PersistenceXStream.loadFromXMLwithXStream();
		if (m2 != null) {
			// unfortunately this creates a second RegManager object even though it is a singleton
			// copy loaded model into a singleton instance of RegManager, because this will be used throughout the application
			
			Iterator<Supply> pIt = m2.getSupplies().iterator();
			while (pIt.hasNext())
				m.addSupply(pIt.next());
			Iterator<Equipment> eIt = m2.getEquipments().iterator();
			while (eIt.hasNext())
				m.addEquipment(eIt.next());
			Iterator<MenuItem> miIt = m2.getMenus().iterator();
			while (miIt.hasNext())
				m.addMenus(miIt.next());
			Iterator<StaffMember> sIt = m2.getStaffmembers().iterator();
			while(sIt.hasNext())
				m.addStaffmember(sIt.next());
		}
		
	}

}
