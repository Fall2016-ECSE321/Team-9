package ca.mcgill.ecse321.foodtruckmanagementsystem.controller;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Manager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;

public class ItemController {

	public ItemController (){
	}
	
	public boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
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
	
	public void editEquipment(String name, int quantity){
		
	}
	
}
