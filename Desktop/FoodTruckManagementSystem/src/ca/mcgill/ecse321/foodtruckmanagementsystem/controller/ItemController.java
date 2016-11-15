package ca.mcgill.ecse321.foodtruckmanagementsystem.controller;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Manager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.StaffMember;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Supply;
import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;

import java.sql.Date;
import java.util.*;

public class ItemController {

	public ItemController (){
	}
		
	public void createEquipment(String name, int quantity) throws InvalidInputException{
		String error = "";	
		if ((name == null || name.trim().length() == 0))
			error = error + "Equipment name cannot be empty! ";
		if(quantity == 0)
			error = error + "Equipment quantity cannot be empty or zero! ";
		if (quantity < 0)
			error = error + "Equipment quantity cannot be negative!";
		
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		
			name = name.toLowerCase();			
			
			boolean isUpdated = false;
			
			Manager m = Manager.getInstance();
	
			for(Equipment equipment : m.getEquipments())
			{
				if(name.equals(equipment.getName()))
				{
					isUpdated = true;
					equipment.setQuantity(equipment.getQuantity() + quantity);
					break;
				}
			}			
			if(!isUpdated)
			{
				m.addEquipment(new Equipment(name, quantity));
			}			
			
			PersistenceXStream.saveToXMLwithXStream(m);
		}
	
	public void removeEquipment(String name, int quantity) throws InvalidInputException{
		String error = "";
		if ((name == null || name.trim().length() == 0))
			error = error + "Equipment name cannot be empty! ";
		if(quantity == 0)
			error = error + "Equipment quantity cannot be empty or zero! ";
		if (quantity < 0)
			error = error + "Equipment quantity cannot be negative!";
		
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
				
			name = name.toLowerCase();
			boolean findName = false;
			Manager m = Manager.getInstance();
			
			for(Equipment equipment : m.getEquipments()){
				if(name.equals(equipment.getName())){
					findName = true;
					//isUpdated = true;
					if(equipment.getQuantity() - quantity > 0){
						equipment.setQuantity(equipment.getQuantity() - quantity);
					}
					else if (equipment.getQuantity() - quantity < 0){
						error = error + "Cannot remove more than " + equipment.getQuantity() + " " + equipment.getName().toString() + "'s";
						throw new InvalidInputException(error);
					}
					else{
						m.removeEquipment(equipment);
						break;
					}
				}
			}
			if(!findName){
				throw new InvalidInputException("Equipment name does not exist!");
			}
			PersistenceXStream.saveToXMLwithXStream(m);	
	}
	
	public void createSupply(String name, double quantity, String unit) throws InvalidInputException{
		String error = "";
		if ((name == null || name.trim().length() == 0)) 
			error = error + "Supply name cannot be empty! ";
		if (quantity == 0)
			error = error + "Supply quantity cannot be empty or zero! ";
		if (quantity < 0) 
			error = error + "Supply quantity cannot be negative! ";
		if ((unit == null || unit.trim().length() == 0))
			error = error + "Supply unit cannot be empty!";
		
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
			name = name.toLowerCase();
			unit = unit.toLowerCase();
			
			boolean isUpdated = false;
			
			Manager m = Manager.getInstance();
	
			for(Supply supply : m.getSupplies())
			{
				if(name.equals(supply.getName()))
				{
					if(unit.equals(supply.getUnit()))
					{
						isUpdated = true;
						supply.setQuantity(supply.getQuantity() + quantity);
						break;
					}
					else
						throw new InvalidInputException("Supply unit does not match: " + supply.getUnit());
					
				}
			}			
			if(!isUpdated)
			{
				m.addSupply(new Supply(name, quantity, unit));
			}			
			
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
			
			for(Supply supply : m.getSupplies()){
				if(name.equals(supply.getName())){
					findName = true;
					if(supply.getQuantity() - quantity > 0){
						supply.setQuantity(supply.getQuantity() - quantity);
						break;
					}
					else if (supply.getQuantity() - quantity < 0){
						error = error + "Cannot remove more than " + supply.getQuantity() + " " + supply.getUnit().toString() + " of " + name;
						throw new InvalidInputException(error);

					}
					else{
						m.removeSupply(supply);
						break;
					}
				}		
			}
			if (!findName){
				throw new InvalidInputException("Supply name does not exist!");
			}
			PersistenceXStream.saveToXMLwithXStream(m);	
	}
	
	public void createStaffMember(String name, String role) throws InvalidInputException
	{
		String error = "";
		if ((name == null || name.trim().length() == 0))
			error = error + "Staff Member name cannot be empty! ";
		if ((role == null || role.trim().length() == 0))
			error = error + "Staff Member role cannot be empty! ";
		
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
			name = name.toLowerCase();
			role = role.toLowerCase();
			
			boolean isUpdated = false;
			
			Manager m = Manager.getInstance();
	
			for(StaffMember staffmember : m.getStaffmembers())
			{
				if(name.equals(staffmember.getName()))
				{
					isUpdated = true;
					staffmember.setRole(role);
					break;
				}
			}			
			if(!isUpdated)
			{
				m.addStaffmember(new StaffMember(name, role));
			}			
			
		PersistenceXStream.saveToXMLwithXStream(m);
	}
	
	public void removeStaffMember(String name, String role) throws InvalidInputException{
		String error = "";
		if ((name == null || name.trim().length() == 0))
			error = error + "Staff Member name cannot be empty! ";
		if ((role == null || role.trim().length() == 0))
			error = error + "Staff Member role cannot be empty! ";
		
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
				
			name = name.toLowerCase();
			role= role.toLowerCase();
			boolean findName = false;
			Manager m = Manager.getInstance();
			
			for(StaffMember staffmember : m.getStaffmembers()){
				if(name.equals(staffmember.getName())){
					findName = true;
					m.removeStaffmember(staffmember);
					break;					
				}
			}
			if(!findName){
				throw new InvalidInputException("Staff Member does not exist!");
			}
			PersistenceXStream.saveToXMLwithXStream(m);	
	}	

	public void addDateStaffMember(String name, Date shift) throws InvalidInputException{
		String error = "";	
		if ((name == null || name.trim().length() == 0))
			error = error + "Staff Member name cannot be empty! ";
		if ((shift == null)){
			error = error + "Staff Member date cannot be empty!";
		}
		boolean isUpdated = false;
		Manager m = Manager.getInstance();
		
		for(StaffMember staffmember: m.getStaffmembers()){
			if(name.equals(staffmember.getName())){
				isUpdated = true;
				staffmember.addSchedule(shift);
				break;
			}
		}
		
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		if(!isUpdated){
			error = error + "Staff Member does not exist!";
		}
		
		
		
		PersistenceXStream.saveToXMLwithXStream(m);
	}
}
