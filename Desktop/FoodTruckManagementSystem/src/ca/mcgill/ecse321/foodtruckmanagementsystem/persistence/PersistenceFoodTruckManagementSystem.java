package ca.mcgill.ecse321.foodtruckmanagementsystem.persistence;

import java.util.Iterator;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Manager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Order;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.StaffMember;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Supply;

public class PersistenceFoodTruckManagementSystem {
	
	private static void initializeXStream() {
		PersistenceXStream.setFilename("foodtruckmanagementsystem.xml");
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("supply", Supply.class);
		PersistenceXStream.setAlias("order", Order.class);
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
				m2.addEquipment(eIt.next());
			Iterator<Order> oIt = m2.getOrders().iterator();
			while (oIt.hasNext())
				m2.addOrder(oIt.next());
			Iterator<StaffMember> sIt = m2.getStaffmembers().iterator();
			while(sIt.hasNext())
				m2.addStaffmember(sIt.next());
		}
		
	}

}
