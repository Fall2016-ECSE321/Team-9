package ca.mcgill.ecse321.foodtruckmanagementsystem.controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Manager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Order;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.StaffMember;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Supply;
import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;

public class TestEventRegistrationController {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.setFilename("test"+File.separator+"ca"+File.separator+"mcgill"+File.separator+"ecse321"
				+File.separator+"foodtruckmanagementsystem"+File.separator+"persistence"+File.separator
				+File.separator+"data.xml");
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("manager", Manager.class);
		PersistenceXStream.setAlias("order", Order.class);
		PersistenceXStream.setAlias("staffmember", StaffMember.class);
		PersistenceXStream.setAlias("supply", Supply.class);
	}
	
	
	@After
	public void tearDown() throws Exception {
		// clear all registrations
		Manager m = Manager.getInstance();
		m.delete();
	}
	
	
	@Test
	public void testCreateEquipment(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getEquipments().size());
		
		String name = "grill";
		int quantity = 3;
		
		ItemController ic = new ItemController();
		try{
			ic.createEquipment(name, quantity);
		} catch (InvalidInputException e){
			fail();
		}
		
		checkResultEquipment(name, quantity, m);
		
		Manager m2 = (Manager) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		checkResultEquipment(name, quantity, m2);
	}
	
	@Test
	public void testCreateEquipmentNull(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getEquipments().size());
		
		String name1 = null;
		int quantity1 = 0;
		String name2 = "Cutting board";
		int quantity2 = 2;
		
		String error1 = null;
		String error2 = null;
		String error3 = null;
		
		ItemController ic = new ItemController();
		try {
			ic.createEquipment(name1, quantity1);
		} catch (InvalidInputException e){
			error1 = e.getMessage();
		}
		
		try {
			ic.createEquipment(name2, quantity1);
		} catch (InvalidInputException e){
			error2 = e.getMessage();
		}
		
		try {
			ic.createEquipment(name1, quantity2);
		} catch (InvalidInputException e){
			error3 = e.getMessage();
		}
		
		// check error 
		assertEquals("Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", error1);
		assertEquals("Equipment quantity cannot be empty or zero!", error2);
		assertEquals("Equipment name cannot be empty!", error3);
		
		// check no change in memory
		assertEquals(0, m.getEquipments().size());
	}
	
	@Test
	public void testCreateEquipmentNameEmpty(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getEquipments().size());
		
		String name1 = "";
		int quantity1 = 0;
		String name2 = "Cutting board";
		int quantity2 = 2;
		
		String error1 = null;
		String error2 = null;
		String error3 = null;
		
		ItemController ic = new ItemController();
		try {
			ic.createEquipment(name1, quantity1);
		} catch (InvalidInputException e){
			error1 = e.getMessage();
		}
		
		try {
			ic.createEquipment(name2, quantity1);
		} catch (InvalidInputException e){
			error2 = e.getMessage();
		}
		
		try {
			ic.createEquipment(name1, quantity2);
		} catch (InvalidInputException e){
			error3 = e.getMessage();
		}
		
		// check error 
		assertEquals("Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", error1);
		assertEquals("Equipment quantity cannot be empty or zero!", error2);
		assertEquals("Equipment name cannot be empty!", error3);
				
		// check no change in memory
		assertEquals(0, m.getEquipments().size());
	}
	
	@Test
	public void testCreateEquipmentSpaces() {
		Manager m = Manager.getInstance();
		assertEquals(0, m.getEquipments().size());
		
		String name1 = " ";
		int quantity1 = 0;
		String name2 = "Cutting board";
		int quantity2 = 2;
		
		String error1 = null;
		String error2 = null;
		String error3 = null;
		
		ItemController ic = new ItemController();
		try {
			ic.createEquipment(name1, quantity1);
		} catch (InvalidInputException e){
			error1 = e.getMessage();
		}
		
		try {
			ic.createEquipment(name2, quantity1);
		} catch (InvalidInputException e){
			error2 = e.getMessage();
		}
		
		try {
			ic.createEquipment(name1, quantity2);
		} catch (InvalidInputException e){
			error3 = e.getMessage();
		}
		
		// check error 
		assertEquals("Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", error1);
		assertEquals("Equipment quantity cannot be empty or zero!", error2);
		assertEquals("Equipment name cannot be empty!", error3);
				
		// check no change in memory
		assertEquals(0, m.getEquipments().size());
	}
	
	@Test
	public void testCreateEquipmentNegativeQuantity() {
		Manager m = Manager.getInstance();
		assertEquals(0, m.getEquipments().size());
		
		String name1 = "";
		int quantity = -1;
		String name2 = " ";
		String name3 = null;
		String name4 = "spoon";

		
		ItemController ic = new ItemController();
		String error0 = null;
		String error1 = null;
		String error2 = null;
		String error3 = null;
		try {
			ic.createEquipment(name1, quantity);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try {
			ic.createEquipment(name2, quantity);
		} catch (InvalidInputException e){
			error1 = e.getMessage();
		}
		try {
			ic.createEquipment(name3, quantity);
		} catch (InvalidInputException e){
			error2 = e.getMessage();
		}
		try {
			ic.createEquipment(name4, quantity);
		} catch (InvalidInputException e){
			error3 = e.getMessage();
		}
		
		//check error
		assertEquals("Equipment name cannot be empty! Equipment quantity cannot be negative!", error0);
		assertEquals("Equipment name cannot be empty! Equipment quantity cannot be negative!", error1);
		assertEquals("Equipment name cannot be empty! Equipment quantity cannot be negative!", error2);
		assertEquals("Equipment quantity cannot be negative!", error3);
		
		//check no change in memory
		assertEquals(0, m.getEquipments().size());
		
		
	}
	
	public void testCreateEquipmentZeroQuantity() {
		Manager m = Manager.getInstance();
		assertEquals(0, m.getEquipments().size());
		
		String name1 = "";
		int quantity = 0;
		String name2 = " ";
		String name3 = null;
		String name4 = "spoon";

		
		ItemController ic = new ItemController();
		String error0 = null;
		String error1 = null;
		String error2 = null;
		String error3 = null;
		try {
			ic.createEquipment(name1, quantity);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try {
			ic.createEquipment(name2, quantity);
		} catch (InvalidInputException e){
			error1 = e.getMessage();
		}
		try {
			ic.createEquipment(name3, quantity);
		} catch (InvalidInputException e){
			error2 = e.getMessage();
		}
		try {
			ic.createEquipment(name4, quantity);
		} catch (InvalidInputException e){
			error3 = e.getMessage();
		}
		
		//check error
		assertEquals("Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", error0);
		assertEquals("Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", error1);
		assertEquals("Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", error2);
		assertEquals("Equipment quantity cannot be zero!", error3);
		
		//check no change in memory
		assertEquals(0, m.getEquipments().size());
		
		
	}
	
	
	
	public void checkResultEquipment(String name, int quantity, Manager m2) {
		assertEquals(1, m2.getEquipments().size());
		assertEquals(name, m2.getEquipment(0).getName());
		//assertEquals(1, m2.getEquipments().size());
		assertEquals(quantity, m2.getEquipment(0).getQuantity());
	}
	
	
	
}
