package ca.mcgill.ecse321.foodtruckmanagementsystem.controller;

import java.io.Reader;
import java.io.StringReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Manager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceFoodTruckManagementSystem;
import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;

public class ItemController {

	public ItemController (){
	}
	
	public boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}
	
	public void createEquipment(String name, int quantity) throws InvalidInputException{
		if ((name == null || name.trim().length() == 0)&& (quantity == 0))
			throw new InvalidInputException("Equipment name and quantity cannot be empty!");
		else if ((name == null || name.trim().length() == 0))
			throw new InvalidInputException("Equipment name cannot be empty!");
		else if (quantity < 0)
			throw new InvalidInputException("Equipment quantity cannot be a negative value!");
		else if(quantity == 0)
			throw new InvalidInputException("Equipment quantity cannot be empty or zero!");
		else{
			//name = name.toLowerCase();
			
			Equipment e = new Equipment(name, quantity);
			Manager m2 = Manager.getInstance();
			//PersistenceFoodTruckManagementSystem.initializeXStream();
			//Manager m2 = (Manager) PersistenceXStream.loadFromXMLwithXStream();
			
			boolean flag = false;
			System.out.println(m2.numberOfEquipments());
			System.out.println(name);
			/*for(int i = 0; i<m2.numberOfEquipments(); i++){
				System.out.println(m2.getEquipment(i).getName());
				if(name == m2.getEquipment(i).getName()){
					System.out.println(m2.getEquipment(i).getQuantity());
					m2.getEquipment(i).setQuantity(quantity + m2.getEquipment(i).getQuantity());
					System.out.println(m2.getEquipment(i).getQuantity());
					flag = true;
					break;
				}
			}*/
			
			m2.addEquipment(e);
			PersistenceXStream.saveToXMLwithXStream(m2);
		
		
		}
	}
	
	public static boolean hasEquipment(String documentName, String equipmentName) throws XMLStreamException{
		    Reader reader = new StringReader(documentName);
		    XMLStreamReader xml = XMLInputFactory.newFactory().createXMLStreamReader(reader);
		    try {
		      while (xml.hasNext()) {
		        if (xml.next() == XMLStreamConstants.START_ELEMENT
		            && equipmentName.equals(xml.getLocalName())) {
		          return true;
		        }
		      }
		    } finally {
		      xml.close();
		    }
		    return false;
	}
	
	
	public void editEquipment(String name, int quantity){
		
	}
	
}
