package ca.mcgill.ecse321.foodtruckmanagementsystem.controller;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Manager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.MenuItem;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.StaffMember;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Supply;
import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class ItemController {

	public ItemController () {
	}
		
	public void createEquipment(String name, int quantity) throws InvalidInputException {
		String error = "";	
		if((name == null || name.trim().length() == 0))
			error = error + "Equipment name cannot be empty! ";
		if(quantity == 0)
			error = error + "Equipment quantity cannot be empty or zero! ";
		if(quantity < 0)
			error = error + "Equipment quantity cannot be negative!";
		
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);		
		
		name = name.toLowerCase();	
		
		boolean isUpdated = false;	
		
		Manager m = Manager.getInstance();
	
		for(Equipment equipment : m.getEquipments()) {
			if(name.equals(equipment.getName())) {
				isUpdated = true;
				equipment.setQuantity(equipment.getQuantity() + quantity);
				break;
			}
		}
		
		if(!isUpdated)
			m.addEquipment(new Equipment(name, quantity));
			
		PersistenceXStream.saveToXMLwithXStream(m);
	}
	
	public void removeEquipment(String name, int quantity) throws InvalidInputException {
		String error = "";
		if((name == null || name.trim().length() == 0))
			error = error + "Equipment name cannot be empty! ";
		if(quantity == 0)
			error = error + "Equipment quantity cannot be empty or zero! ";
		if(quantity < 0)
			error = error + "Equipment quantity cannot be negative!";
		
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
				
		name = name.toLowerCase();
		
		boolean findName = false;
		
		Manager m = Manager.getInstance();
			
		for(Equipment equipment : m.getEquipments()) {
			if(name.equals(equipment.getName())) {
				findName = true;
				if(equipment.getQuantity() - quantity > 0) {
					equipment.setQuantity(equipment.getQuantity() - quantity);
				}
				else if(equipment.getQuantity() - quantity < 0) {
					error = error + "Cannot remove more than " + equipment.getQuantity() + " " + equipment.getName().toString() + "'s";
					throw new InvalidInputException(error);
				}
				else {
					m.removeEquipment(equipment);
					break;
				}
			}
		}
			
		if(!findName)
			throw new InvalidInputException("Equipment name does not exist!");
			
		PersistenceXStream.saveToXMLwithXStream(m);	
	}
	
	public void createSupply(String name, double quantity, String unit) throws InvalidInputException {
		String error = "";
		if((name == null || name.trim().length() == 0)) 
			error = error + "Supply name cannot be empty! ";
		if(quantity == 0)
			error = error + "Supply quantity cannot be empty or zero! ";
		if(quantity < 0) 
			error = error + "Supply quantity cannot be negative! ";
		if((unit == null || unit.trim().length() == 0))
			error = error + "Supply unit cannot be empty!";
		
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		name = name.toLowerCase();
		unit = unit.toLowerCase();
			
		boolean isUpdated = false;
			
		Manager m = Manager.getInstance();
	
		for(Supply supply : m.getSupplies()) {
			if(name.equals(supply.getName())) {
				if(unit.equals(supply.getUnit())) {
					isUpdated = true;
					supply.setQuantity(supply.getQuantity() + quantity);
					break;
				}
				else
					throw new InvalidInputException("Supply unit does not match: " + supply.getUnit());
			}
		}
		
		if(!isUpdated)
			m.addSupply(new Supply(name, quantity, unit));				
			
		PersistenceXStream.saveToXMLwithXStream(m);
	}
	
	public void removeSupply(String name, double quantity) throws InvalidInputException{
		String error ="";
		if ((name == null || name.trim().length() == 0))
			error = error + "Supply name cannot be empty! ";
		if(quantity == 0)
			error = error + "Supply quantity cannot be empty or zero! ";
		if(quantity < 0)
			error = error + "Supply quantity cannot be negative! ";
		
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		name = name.toLowerCase();
		
		boolean findName = false;
		
		Manager m = Manager.getInstance();
			
		for(Supply supply : m.getSupplies()) {
			if(name.equals(supply.getName())) {
				findName = true;
				if(supply.getQuantity() - quantity > 0) {
					supply.setQuantity(supply.getQuantity() - quantity);
					break;
				}
				else if(supply.getQuantity() - quantity < 0) {
					error = error + "Cannot remove more than " + supply.getQuantity() + " " + supply.getUnit().toString() + " of " + name;
					throw new InvalidInputException(error);
				}
				else {
					m.removeSupply(supply);
					break;
				}
			}		
		}
		
		if(!findName)
			throw new InvalidInputException("Supply name does not exist!");

		PersistenceXStream.saveToXMLwithXStream(m);	
	}
	
	public void createStaffMember(String name, String role) throws InvalidInputException {
		String error = "";
		if((name == null || name.trim().length() == 0))
			error = error + "Staff member name cannot be empty! ";
		if((role == null || role.trim().length() == 0))
			error = error + "Staff member role cannot be empty! ";
		
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		name = name.toLowerCase();
		role = role.toLowerCase();
			
		boolean isUpdated = false;
			
		Manager m = Manager.getInstance();
	
		for(StaffMember staffmember : m.getStaffmembers()) {
			if(name.equals(staffmember.getName())) {
				if(role.equals(staffmember.getRole())) {
					throw new InvalidInputException(error + "Staff member already exists!") ;
				}
				isUpdated = true;
				staffmember.setRole(role);
				break;
			}
		}		
		
		if(!isUpdated)		
			m.addStaffmember(new StaffMember(name, role));			
			
		PersistenceXStream.saveToXMLwithXStream(m);
	}
	
	public void removeStaffMember(String name) throws InvalidInputException {
		String error = "";
		if ((name == null || name.trim().length() == 0))
			error = error + "Staff member name cannot be empty! ";
		
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
				
		name = name.toLowerCase();
		boolean findName = false;
		Manager m = Manager.getInstance();
			
		for(StaffMember staffmember : m.getStaffmembers()) {
			if(name.equals(staffmember.getName())) {
				findName = true;
				m.removeStaffmember(staffmember);
				break;					
			}
		}
		
		if(!findName)
			throw new InvalidInputException("Staff member does not exist!");
			
		PersistenceXStream.saveToXMLwithXStream(m);	
	}	

	public void addTimeStaffMember(String name, Time startTime, Time endTime) throws InvalidInputException {
		String error = "";
		Time defaultTime = new Time(0000);	

		Manager m = Manager.getInstance();
		
		if(name == null || name.trim().length() == 0)
			error = error + "Staff member name cannot be empty! ";
		if(startTime == null)
			error = error + "Start time cannot be empty! ";
		if(endTime == null)
			error = error + "End time cannot be empty! ";
		
		if(startTime != null && endTime != null) {
			if(!startTime.equals(defaultTime) && !endTime.equals(defaultTime) && startTime.equals(endTime)) {
				error = error + "End time cannot be equal to start time! ";
			}
		}
		
		
		if(startTime != null && endTime != null) {
			if (endTime.before(startTime)) {
				error = error + "End time cannot be before start time!";
			}
		}
		
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		name = name.toLowerCase();
		
		for(StaffMember staffmember: m.getStaffmembers()) {
			if(name.equals(staffmember.getName())) {
				if(startTime.equals(defaultTime) && endTime.equals(defaultTime)) {
					staffmember.addStartTime(null);
					staffmember.addEndTime(null);
					break;
				}
				else {
					staffmember.addStartTime(startTime);
					staffmember.addEndTime(endTime);
					break;
				}
			}
		}
			
		PersistenceXStream.saveToXMLwithXStream(m);
	}
	
	// how to handle decimal?
	public void createMenuItem(String menuItemName, double menuItemPrice) throws InvalidInputException {
		String error = "";
		
		if (menuItemName == null || menuItemName.trim().length() == 0)
			error = error + "Menu item name cannot be empty! ";
		if(menuItemPrice == 0)
			error = error + "Item price cannot be empty or zero! ";
		if (menuItemPrice < 0)
			error = error + "Item price cannot be negative!";
		
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		menuItemName = menuItemName.toLowerCase();	
		
		boolean isUpdated = false;		
		
		Manager m = Manager.getInstance();
	
		for(MenuItem menuItem : m.getMenus()) {
			if(menuItemName.equals(menuItem.getName())) {
				if(menuItemPrice == menuItem.getPrice()) {
					error = "Menu already exists at price: " + menuItemPrice;
					throw new InvalidInputException(error);
				}
				else {
					isUpdated = true;
					menuItem.setPrice(menuItemPrice);
					break;	
				}					
			}
		}
		
		if(!isUpdated)
			m.addMenus(new MenuItem(menuItemName, menuItemPrice, 0));			
			
		PersistenceXStream.saveToXMLwithXStream(m);
	}
	
	public void removeMenuItem(String menuItemName) throws InvalidInputException {
		String error = "";
		
		if (menuItemName == null || menuItemName.trim().length() == 0)
			error = "Menu item name cannot be empty! ";
		
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		menuItemName = menuItemName.toLowerCase();			
		boolean isUpdated = false;			
		Manager m = Manager.getInstance();
		
		for(MenuItem menuItem : m.getMenus()) {
			if(menuItemName.equals(menuItem.getName())) {
				m.removeMenus(menuItem);
				isUpdated = true;
				break;
			}
		}	
		
		if(!isUpdated)
			throw new InvalidInputException("Menu item does not exist!");		
			
		PersistenceXStream.saveToXMLwithXStream(m);		
	}
	
	public void menuItemOrdered(String orderName, int orderQuantity) throws InvalidInputException {
		String error = "";
		
		if (orderName == null || orderName.trim().length() == 0)
			error = error + "Order name cannot be empty! ";
		if(orderQuantity == 0)
			error = error + "Order quantity cannot be empty or zero! ";
		if (orderQuantity < 0)
			error = error + "Order quantity cannot be negative!";
		
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		orderName = orderName.toLowerCase();
		
		boolean isUpdated = false;			
		
		Manager m = Manager.getInstance();
		
		for(MenuItem menuItem : m.getMenus()) {
			if(orderName.equals(menuItem.getName())) {
				menuItem.setPopularityCounter(menuItem.getPopularityCounter() + orderQuantity);
				isUpdated = true;
				break;
			}
		}	
		
		if(!isUpdated)
			throw new InvalidInputException("Order item does not exist!");		
			
		PersistenceXStream.saveToXMLwithXStream(m);		
	}
	
}