package ca.mcgill.ecse321.foodtruckmanagementsystem.controller;

import java.io.Reader;
import java.io.StringReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Manager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;

public class ItemController {

	public ItemController (){
	}
	
	public boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}
	
	public void createEquipment(String name, int quantity) throws InvalidInputException, XMLStreamException{
		if (name == null || name.trim().length() == 0)
			throw new InvalidInputException("Equipment name cannot be empty!");
		if (isNumeric(name))
			throw new InvalidInputException("Equipment name cannot be numeric!");
		if (quantity < 0)
			throw new InvalidInputException("Equipment quantity cannot be negative!");
		if(quantity == 0)
			throw new InvalidInputException("Equipment quantity cannot be empty or zero!");
		if (quantity != (int)quantity)
			throw new InvalidInputException("Equipment quantity cannot be a non-integer value!");
		
		if(hasEquipment("foodtruckmanagementsystem.xml", name) == true){
			name = name.toLowerCase();
		}
		else{
		
			name = name.toLowerCase();
			Equipment e = new Equipment(name, quantity);
			Manager m = Manager.getInstance();
			m.addEquipment(e);
			PersistenceXStream.saveToXMLwithXStream(m);
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
