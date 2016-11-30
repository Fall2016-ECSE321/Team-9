package ca.mcgill.ecse321.foodtruckmanagementsystem.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Time;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Manager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.MenuItem;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.StaffMember;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Supply;
import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;

public class TestFoodTruckManagementSystemController {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.setFilename("test" + File.separator + "ca"
				+ File.separator + "mcgill" + File.separator + "ecse321"
				+ File.separator + "foodtruckmanagementsystem" + File.separator
				+ "persistence" + File.separator + File.separator + "data.xml");
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("manager", Manager.class);
		PersistenceXStream.setAlias("menuitem", MenuItem.class);
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
	public void testCreateEquipment() {
		Manager m = Manager.getInstance();
		assertEquals(0, m.getEquipments().size());

		String name = "grill";
		int quantity = 3;

		ItemController ic = new ItemController();
		try {
			ic.createEquipment(name, quantity);
		} catch (InvalidInputException e) {
			fail();
		}

		checkResultEquipment(name, quantity, m);

		Manager m2 = (Manager) PersistenceXStream.loadFromXMLwithXStream();

		// check file contents
		checkResultEquipment(name, quantity, m2);
	}

	@Test
	public void testCreateEquipmentNull() {
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
		} catch (InvalidInputException e) {
			error1 = e.getMessage();
		}

		try {
			ic.createEquipment(name2, quantity1);
		} catch (InvalidInputException e) {
			error2 = e.getMessage();
		}

		try {
			ic.createEquipment(name1, quantity2);
		} catch (InvalidInputException e) {
			error3 = e.getMessage();
		}

		// check error
		assertEquals(
				"Equipment name cannot be empty! Equipment quantity cannot be empty or zero!",
				error1);
		assertEquals("Equipment quantity cannot be empty or zero!", error2);
		assertEquals("Equipment name cannot be empty!", error3);

		// check no change in memory
		assertEquals(0, m.getEquipments().size());
	}

	@Test
	public void testCreateEquipmentNameEmpty() {
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
		} catch (InvalidInputException e) {
			error1 = e.getMessage();
		}

		try {
			ic.createEquipment(name2, quantity1);
		} catch (InvalidInputException e) {
			error2 = e.getMessage();
		}

		try {
			ic.createEquipment(name1, quantity2);
		} catch (InvalidInputException e) {
			error3 = e.getMessage();
		}

		// check error
		assertEquals(
				"Equipment name cannot be empty! Equipment quantity cannot be empty or zero!",
				error1);
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
		} catch (InvalidInputException e) {
			error1 = e.getMessage();
		}

		try {
			ic.createEquipment(name2, quantity1);
		} catch (InvalidInputException e) {
			error2 = e.getMessage();
		}

		try {
			ic.createEquipment(name1, quantity2);
		} catch (InvalidInputException e) {
			error3 = e.getMessage();
		}

		// check error
		assertEquals(
				"Equipment name cannot be empty! Equipment quantity cannot be empty or zero!",
				error1);
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
		} catch (InvalidInputException e) {
			error0 = e.getMessage();
		}
		try {
			ic.createEquipment(name2, quantity);
		} catch (InvalidInputException e) {
			error1 = e.getMessage();
		}
		try {
			ic.createEquipment(name3, quantity);
		} catch (InvalidInputException e) {
			error2 = e.getMessage();
		}
		try {
			ic.createEquipment(name4, quantity);
		} catch (InvalidInputException e) {
			error3 = e.getMessage();
		}
		String errString1 = "Equipment name cannot be empty! Equipment quantity cannot be negative!";
		String errString2 = "Equipment quantity cannot be negative!";
		// check error
		assertEquals(errString1, error0);
		assertEquals(errString1, error1);
		assertEquals(errString1, error2);
		assertEquals(errString2, error3);

		// check no change in memory
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
		} catch (InvalidInputException e) {
			error0 = e.getMessage();
		}
		try {
			ic.createEquipment(name2, quantity);
		} catch (InvalidInputException e) {
			error1 = e.getMessage();
		}
		try {
			ic.createEquipment(name3, quantity);
		} catch (InvalidInputException e) {
			error2 = e.getMessage();
		}
		try {
			ic.createEquipment(name4, quantity);
		} catch (InvalidInputException e) {
			error3 = e.getMessage();
		}

		// check error
		assertEquals(
				"Equipment name cannot be empty! Equipment quantity cannot be empty or zero!",
				error0);
		assertEquals(
				"Equipment name cannot be empty! Equipment quantity cannot be empty or zero!",
				error1);
		assertEquals(
				"Equipment name cannot be empty! Equipment quantity cannot be empty or zero!",
				error2);
		assertEquals("Equipment quantity cannot be zero!", error3);

		// check no change in memory
		assertEquals(0, m.getEquipments().size());

	}

	@Test
	public void testRemoveEquipment() {
		Manager m = Manager.getInstance();
		assertEquals(0, m.getEquipments().size());

		String name = "grill";
		int removeQuantity = 1;
		int preQuantity = 2;

		ItemController ic = new ItemController();
		try {
			ic.createEquipment(name, preQuantity);
			ic.removeEquipment(name, removeQuantity);
		} catch (InvalidInputException e) {
			fail();
		}

		checkResultEquipment(name, preQuantity - removeQuantity, m);

		Manager m2 = (Manager) PersistenceXStream.loadFromXMLwithXStream();

		// check file contents
		 checkResultEquipment(name, preQuantity - removeQuantity, m2);
	}

	@Test
	public void testRemoveEquipmentNull() {
		Manager m = Manager.getInstance();
		assertEquals(0, m.getEquipments().size());
		
		String name = null;
		String name1 = "tomato";
		
		int preQuantity = 4;
		int removeQuantity0 = 0;
		int removeQuantity1 = 1;
		
		String error0 = null;
		String error1 = null;
		String error2 = null;
		ItemController ic = new ItemController();
		try{
			ic.createEquipment(name1, preQuantity);
			ic.removeEquipment(name, removeQuantity0);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try{
			ic.createEquipment(name1, preQuantity);
			ic.removeEquipment(name, removeQuantity1);
		} catch (InvalidInputException e) {
			error1 = e.getMessage();
		}
		try{
			ic.createEquipment(name1, preQuantity);
			ic.removeEquipment(name1, removeQuantity0);
		} catch (InvalidInputException e){
			error2 = e.getMessage();
		}
		
		assertEquals("Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", error0);
		assertEquals("Equipment name cannot be empty!", error1);
		assertEquals("Equipment quantity cannot be empty or zero!", error2);
	}

	@Test
	public void testRemoveEquipmentEmpty(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getEquipments().size());
		
		String name = "";
		String name1 = "tomato";
		
		int preQuantity = 4;
		int removeQuantity0 = 0;
		int removeQuantity1 = 1;
		
		String error0 = null;
		String error1 = null;
		String error2 = null;
		ItemController ic = new ItemController();
		try{
			ic.createEquipment(name1, preQuantity);
			ic.removeEquipment(name, removeQuantity0);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try{
			ic.createEquipment(name1, preQuantity);
			ic.removeEquipment(name, removeQuantity1);
		} catch (InvalidInputException e) {
			error1 = e.getMessage();
		}
		try{
			ic.createEquipment(name1, preQuantity);
			ic.removeEquipment(name1, removeQuantity0);
		} catch (InvalidInputException e){
			error2 = e.getMessage();
		}
		
		assertEquals("Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", error0);
		assertEquals("Equipment name cannot be empty!", error1);
		assertEquals("Equipment quantity cannot be empty or zero!", error2);
	}
	
	@Test
	public void testRemoveEquipmentSpaces() {
		Manager m = Manager.getInstance();
		assertEquals(0, m.getEquipments().size());
		
		String name = " ";
		String name1 = "tomato";
		
		int preQuantity = 4;
		int removeQuantity0 = 0;
		int removeQuantity1 = 1;
		
		String error0 = null;
		String error1 = null;
		String error2 = null;
		ItemController ic = new ItemController();
		try{
			ic.createEquipment(name1, preQuantity);
			ic.removeEquipment(name, removeQuantity0);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try{
			ic.createEquipment(name1, preQuantity);
			ic.removeEquipment(name, removeQuantity1);
		} catch (InvalidInputException e) {
			error1 = e.getMessage();
		}
		try{
			ic.createEquipment(name1, preQuantity);
			ic.removeEquipment(name1, removeQuantity0);
		} catch (InvalidInputException e){
			error2 = e.getMessage();
		}
		
		assertEquals("Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", error0);
		assertEquals("Equipment name cannot be empty!", error1);
		assertEquals("Equipment quantity cannot be empty or zero!", error2);
	}
	
	@Test
	public void testRemoveEquipmentNegativeQuantity() {
		Manager m = Manager.getInstance();
		assertEquals(0, m.getEquipments().size());
		
		String name0 = null;
		String name1 = "";
		String name2 = " ";
		String name3 = "tomato";
		
		int quantityNeg = -1;
		int preQuantity = 4;
		
		String error0 = null;
		String error1 = null;
		
		ItemController ic = new ItemController();
		
		try{
			ic.createEquipment(name3, preQuantity);
			ic.removeEquipment(name0, quantityNeg);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try{
			ic.createEquipment(name3, preQuantity);
			ic.removeEquipment(name1, quantityNeg);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try{
			ic.createEquipment(name3, preQuantity);
			ic.removeEquipment(name2, quantityNeg);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try{
			ic.createEquipment(name3, preQuantity);
			ic.removeEquipment(name3, quantityNeg);
		} catch (InvalidInputException e){
			error1 = e.getMessage();
		}
		
		assertEquals("Equipment name cannot be empty! Equipment quantity cannot be negative!", error0);
		assertEquals("Equipment quantity cannot be negative!", error1);
	}

	@Test
	public void testRemoveEquipmentZeroQuantity() {
		Manager m = Manager.getInstance();
		assertEquals(0, m.getEquipments().size());
		
		String name0 = null;
		String name1 = "";
		String name2 = " ";
		String name3 = "tomato";
		
		int quantityNeg = 0;
		int preQuantity = 4;
		
		String error0 = null;
		String error1 = null;
		
		ItemController ic = new ItemController();
		
		try{
			ic.createEquipment(name3, preQuantity);
			ic.removeEquipment(name0, quantityNeg);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try{
			ic.createEquipment(name3, preQuantity);
			ic.removeEquipment(name1, quantityNeg);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try{
			ic.createEquipment(name3, preQuantity);
			ic.removeEquipment(name2, quantityNeg);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try{
			ic.createEquipment(name3, preQuantity);
			ic.removeEquipment(name3, quantityNeg);
		} catch (InvalidInputException e){
			error1 = e.getMessage();
		}
		
		assertEquals("Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", error0);
		assertEquals("Equipment quantity cannot be empty or zero!", error1);
	}

	@Test
	public void testRemoveEquipmentNegativeResult() {
		Manager m = Manager.getInstance();
		assertEquals(0, m.getEquipments().size());
		
		String name = "tomato";
		int preQuantity = 3;
		int removeQuantity = 4;
		
		String error0 = null;
		
		ItemController ic = new ItemController();
		
		try{
			ic.createEquipment(name, preQuantity);
			ic.removeEquipment(name, removeQuantity);
		} catch(InvalidInputException e){
			error0 = e.getMessage();
		}
		
		assertEquals("Cannot remove more than " + preQuantity + " " + name + "'s", error0);
	}
	
	public void checkResultEquipment(String name, int quantity, Manager m2) {
		assertEquals(1, m2.getEquipments().size());
		assertEquals(name, m2.getEquipment(0).getName());
		// assertEquals(1, m2.getEquipments().size());
		assertEquals(quantity, m2.getEquipment(0).getQuantity());
	}

	@Test
	public void testCreateSupply() {
		Manager m = Manager.getInstance();
		assertEquals(0, m.getSupplies().size());

		String name = "tomato";
		double quantity = 3;
		String unit = "kilogram";

		ItemController ic = new ItemController();
		try {
			ic.createSupply(name, quantity, unit);
		} catch (InvalidInputException e) {
			fail();
		}

		checkResultSupply(name, quantity, unit, m);

		Manager m2 = (Manager) PersistenceXStream.loadFromXMLwithXStream();

		// check file contents
		checkResultSupply(name, quantity, unit, m2);
	}

	@Test
	public void testCreateSupplyNull() {
		Manager m = Manager.getInstance();
		assertEquals(0, m.getSupplies().size());

		String name1 = null;
		int quantity1 = 0;
		String unit1 = null;
		String name2 = "tomato";
		int quantity2 = 3;
		String unit2 = "kilogram";

		String error1 = null;
		String error2 = null;
		String error3 = null;
		String error4 = null;
		String error5 = null;
		String error6 = null;
		String error7 = null;

		ItemController ic = new ItemController();
		try {
			ic.createSupply(name1, quantity1, unit1);
		} catch (InvalidInputException e) {
			error1 = e.getMessage();
		}

		try {
			ic.createSupply(name1, quantity1, unit2);
		} catch (InvalidInputException e) {
			error2 = e.getMessage();
		}
		try {
			ic.createSupply(name1, quantity2, unit1);
		} catch (InvalidInputException e) {
			error3 = e.getMessage();
		}
		try {
			ic.createSupply(name1, quantity2, unit2);
		} catch (InvalidInputException e) {
			error4 = e.getMessage();
		}
		try {
			ic.createSupply(name2, quantity1, unit1);
		} catch (InvalidInputException e) {
			error5 = e.getMessage();
		}
		try {
			ic.createSupply(name2, quantity1, unit2);
		} catch (InvalidInputException e) {
			error6 = e.getMessage();
		}
		try {
			ic.createSupply(name2, quantity2, unit1);
		} catch (InvalidInputException e) {
			error7 = e.getMessage();
		}

		// check error
		assertEquals(
				"Supply name cannot be empty! Supply quantity cannot be empty or zero! Supply unit cannot be empty!",
				error1);
		assertEquals(
				"Supply name cannot be empty! Supply quantity cannot be empty or zero!",
				error2);
		assertEquals(
				"Supply name cannot be empty! Supply unit cannot be empty!",
				error3);
		assertEquals("Supply name cannot be empty!", error4);
		assertEquals(
				"Supply quantity cannot be empty or zero! Supply unit cannot be empty!",
				error5);
		assertEquals("Supply quantity cannot be empty or zero!", error6);
		assertEquals("Supply unit cannot be empty!", error7);

		// check error
		assertEquals(0, m.getSupplies().size());
	}

	@Test
	public void testCreateSupplyStringEmpty() {
		String name1 = "";
		int quantity1 = 0;
		String unit1 = "";
		String name2 = "tomato";
		int quantity2 = 3;
		String unit2 = "kilogram";

		String error1 = null;
		String error2 = null;
		String error3 = null;
		String error4 = null;
		String error5 = null;
		String error6 = null;
		String error7 = null;

		ItemController ic = new ItemController();
		try {
			ic.createSupply(name1, quantity1, unit1);
		} catch (InvalidInputException e) {
			error1 = e.getMessage();
		}

		try {
			ic.createSupply(name1, quantity1, unit2);
		} catch (InvalidInputException e) {
			error2 = e.getMessage();
		}
		try {
			ic.createSupply(name1, quantity2, unit1);
		} catch (InvalidInputException e) {
			error3 = e.getMessage();
		}
		try {
			ic.createSupply(name1, quantity2, unit2);
		} catch (InvalidInputException e) {
			error4 = e.getMessage();
		}
		try {
			ic.createSupply(name2, quantity1, unit1);
		} catch (InvalidInputException e) {
			error5 = e.getMessage();
		}
		try {
			ic.createSupply(name2, quantity1, unit2);
		} catch (InvalidInputException e) {
			error6 = e.getMessage();
		}
		try {
			ic.createSupply(name2, quantity2, unit1);
		} catch (InvalidInputException e) {
			error7 = e.getMessage();
		}

		// check error
		assertEquals(
				"Supply name cannot be empty! Supply quantity cannot be empty or zero! Supply unit cannot be empty!",
				error1);
		assertEquals(
				"Supply name cannot be empty! Supply quantity cannot be empty or zero!",
				error2);
		assertEquals(
				"Supply name cannot be empty! Supply unit cannot be empty!",
				error3);
		assertEquals("Supply name cannot be empty!", error4);
		assertEquals(
				"Supply quantity cannot be empty or zero! Supply unit cannot be empty!",
				error5);
		assertEquals("Supply quantity cannot be empty or zero!", error6);
		assertEquals("Supply unit cannot be empty!", error7);
	}

	@Test
	public void testCreateSupplySpaces() {
		String name1 = " ";
		int quantity1 = 0;
		String unit1 = " ";
		String name2 = "tomato";
		int quantity2 = 3;
		String unit2 = "kilogram";

		String error1 = null;
		String error2 = null;
		String error3 = null;
		String error4 = null;
		String error5 = null;
		String error6 = null;
		String error7 = null;

		ItemController ic = new ItemController();
		try {
			ic.createSupply(name1, quantity1, unit1);
		} catch (InvalidInputException e) {
			error1 = e.getMessage();
		}

		try {
			ic.createSupply(name1, quantity1, unit2);
		} catch (InvalidInputException e) {
			error2 = e.getMessage();
		}
		try {
			ic.createSupply(name1, quantity2, unit1);
		} catch (InvalidInputException e) {
			error3 = e.getMessage();
		}
		try {
			ic.createSupply(name1, quantity2, unit2);
		} catch (InvalidInputException e) {
			error4 = e.getMessage();
		}
		try {
			ic.createSupply(name2, quantity1, unit1);
		} catch (InvalidInputException e) {
			error5 = e.getMessage();
		}
		try {
			ic.createSupply(name2, quantity1, unit2);
		} catch (InvalidInputException e) {
			error6 = e.getMessage();
		}
		try {
			ic.createSupply(name2, quantity2, unit1);
		} catch (InvalidInputException e) {
			error7 = e.getMessage();
		}

		// check error
		assertEquals(
				"Supply name cannot be empty! Supply quantity cannot be empty or zero! Supply unit cannot be empty!",
				error1);
		assertEquals(
				"Supply name cannot be empty! Supply quantity cannot be empty or zero!",
				error2);
		assertEquals(
				"Supply name cannot be empty! Supply unit cannot be empty!",
				error3);
		assertEquals("Supply name cannot be empty!", error4);
		assertEquals(
				"Supply quantity cannot be empty or zero! Supply unit cannot be empty!",
				error5);
		assertEquals("Supply quantity cannot be empty or zero!", error6);
		assertEquals("Supply unit cannot be empty!", error7);
	}

	@Test
	public void testCreateSupplyNegativeQuantity() {
		Manager m = Manager.getInstance();
		assertEquals(0, m.getSupplies().size());

		String name1 = "";
		String name2 = " ";
		String name3 = null;
		String name4 = "tomato";
		String unit1 = "";
		String unit2 = "";
		String unit3 = null;
		String unit4 = "kilogram";
		int quantity = -1;

		String error0 = null;
		String error1 = null;
		String error2 = null;
		String error3 = null;
		String error4 = null;
		String error5 = null;
		String error6 = null;
		String error7 = null;
		String error8 = null;
		String error9 = null;
		String error10 = null;
		String error11 = null;
		String error12 = null;
		String error13 = null;
		String error14 = null;
		String error15 = null;

		ItemController ic = new ItemController();
		try {
			ic.createSupply(name1, quantity, unit1);
		} catch (InvalidInputException e) {
			error0 = e.getMessage();
		}
		try {
			ic.createSupply(name1, quantity, unit2);
		} catch (InvalidInputException e) {
			error1 = e.getMessage();
		}
		try {
			ic.createSupply(name1, quantity, unit3);
		} catch (InvalidInputException e) {
			error2 = e.getMessage();
		}
		try {
			ic.createSupply(name1, quantity, unit4);
		} catch (InvalidInputException e) {
			error3 = e.getMessage();
		}
		try {
			ic.createSupply(name2, quantity, unit1);
		} catch (InvalidInputException e) {
			error4 = e.getMessage();
		}
		try {
			ic.createSupply(name2, quantity, unit2);
		} catch (InvalidInputException e) {
			error5 = e.getMessage();
		}
		try {
			ic.createSupply(name2, quantity, unit3);
		} catch (InvalidInputException e) {
			error6 = e.getMessage();
		}
		try {
			ic.createSupply(name2, quantity, unit4);
		} catch (InvalidInputException e) {
			error7 = e.getMessage();
		}
		try {
			ic.createSupply(name3, quantity, unit1);
		} catch (InvalidInputException e) {
			error8 = e.getMessage();
		}
		try {
			ic.createSupply(name3, quantity, unit2);
		} catch (InvalidInputException e) {
			error9 = e.getMessage();
		}
		try {
			ic.createSupply(name3, quantity, unit3);
		} catch (InvalidInputException e) {
			error10 = e.getMessage();
		}
		try {
			ic.createSupply(name3, quantity, unit4);
		} catch (InvalidInputException e) {
			error11 = e.getMessage();
		}
		try {
			ic.createSupply(name4, quantity, unit1);
		} catch (InvalidInputException e) {
			error12 = e.getMessage();
		}
		try {
			ic.createSupply(name4, quantity, unit2);
		} catch (InvalidInputException e) {
			error13 = e.getMessage();
		}
		try {
			ic.createSupply(name4, quantity, unit3);
		} catch (InvalidInputException e) {
			error14 = e.getMessage();
		}
		try {
			ic.createSupply(name4, quantity, unit4);
		} catch (InvalidInputException e) {
			error15 = e.getMessage();
		}
		
		// String error message
		String errString1 = "Supply name cannot be empty! Supply quantity cannot be negative! Supply unit cannot be empty!";
		String errString2 = "Supply name cannot be empty! Supply quantity cannot be negative!";
		String errString3 ="Supply quantity cannot be negative! Supply unit cannot be empty!";
		String errString4 = "Supply quantity cannot be negative!";
		
		// Check Error
		assertEquals(errString1, error0);
		assertEquals(errString1, error1);
		assertEquals(errString1, error2);
		assertEquals(errString2, error3);
		assertEquals(errString1, error4);
		assertEquals(errString1, error5);
		assertEquals(errString1, error6);
		assertEquals(errString2, error7);
		assertEquals(errString1, error8);
		assertEquals(errString1, error9);
		assertEquals(errString1, error10);
		assertEquals(errString2, error11);
		assertEquals(errString3, error12);
		assertEquals(errString3, error13);
		assertEquals(errString3, error14);
		assertEquals(errString4, error15);

	}

	@Test
	public void testCreateSupplyZeroQuantity() {
		Manager m = Manager.getInstance();
		assertEquals(0, m.getSupplies().size());

		String name1 = "";
		String name2 = " ";
		String name3 = null;
		String name4 = "tomato";
		String unit1 = "";
		String unit2 = "";
		String unit3 = null;
		String unit4 = "kilogram";
		int quantity = 0;

		String error0 = null;
		String error1 = null;
		String error2 = null;
		String error3 = null;
		String error4 = null;
		String error5 = null;
		String error6 = null;
		String error7 = null;
		String error8 = null;
		String error9 = null;
		String error10 = null;
		String error11 = null;
		String error12 = null;
		String error13 = null;
		String error14 = null;
		String error15 = null;

		ItemController ic = new ItemController();
		try {
			ic.createSupply(name1, quantity, unit1);
		} catch (InvalidInputException e) {
			error0 = e.getMessage();
		}
		try {
			ic.createSupply(name1, quantity, unit2);
		} catch (InvalidInputException e) {
			error1 = e.getMessage();
		}
		try {
			ic.createSupply(name1, quantity, unit3);
		} catch (InvalidInputException e) {
			error2 = e.getMessage();
		}
		try {
			ic.createSupply(name1, quantity, unit4);
		} catch (InvalidInputException e) {
			error3 = e.getMessage();
		}
		try {
			ic.createSupply(name2, quantity, unit1);
		} catch (InvalidInputException e) {
			error4 = e.getMessage();
		}
		try {
			ic.createSupply(name2, quantity, unit2);
		} catch (InvalidInputException e) {
			error5 = e.getMessage();
		}
		try {
			ic.createSupply(name2, quantity, unit3);
		} catch (InvalidInputException e) {
			error6 = e.getMessage();
		}
		try {
			ic.createSupply(name2, quantity, unit4);
		} catch (InvalidInputException e) {
			error7 = e.getMessage();
		}
		try {
			ic.createSupply(name3, quantity, unit1);
		} catch (InvalidInputException e) {
			error8 = e.getMessage();
		}
		try {
			ic.createSupply(name3, quantity, unit2);
		} catch (InvalidInputException e) {
			error9 = e.getMessage();
		}
		try {
			ic.createSupply(name3, quantity, unit3);
		} catch (InvalidInputException e) {
			error10 = e.getMessage();
		}
		try {
			ic.createSupply(name3, quantity, unit4);
		} catch (InvalidInputException e) {
			error11 = e.getMessage();
		}
		try {
			ic.createSupply(name4, quantity, unit1);
		} catch (InvalidInputException e) {
			error12 = e.getMessage();
		}
		try {
			ic.createSupply(name4, quantity, unit2);
		} catch (InvalidInputException e) {
			error13 = e.getMessage();
		}
		try {
			ic.createSupply(name4, quantity, unit3);
		} catch (InvalidInputException e) {
			error14 = e.getMessage();
		}
		try {
			ic.createSupply(name4, quantity, unit4);
		} catch (InvalidInputException e) {
			error15 = e.getMessage();
		}
		
		// String error 
		String errString1 = "Supply name cannot be empty! Supply quantity cannot be empty or zero! Supply unit cannot be empty!";
		String errString2 = "Supply name cannot be empty! Supply quantity cannot be empty or zero!";
		String errString3 = "Supply quantity cannot be empty or zero! Supply unit cannot be empty!";
		String errString4 = "Supply quantity cannot be empty or zero!";
		
		// Check Error
		assertEquals(errString1, error0);
		assertEquals(errString1, error1);
		assertEquals(errString1, error2);
		assertEquals(errString2, error3);
		assertEquals(errString1, error4);
		assertEquals(errString1, error5);
		assertEquals(errString1, error6);
		assertEquals(errString2, error7);
		assertEquals(errString1, error8);
		assertEquals(errString1, error9);
		assertEquals(errString1, error10);
		assertEquals(errString2, error11);
		assertEquals(errString3, error12);
		assertEquals(errString3, error13);
		assertEquals(errString3, error14);
		assertEquals(errString4, error15);
	}

	@Test
	public void testRemoveSupply(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getSupplies().size());
		
		String name = "tomato";
		int preQuantity = 2;
		int removeQuantity = 1;
		String unit = "kilogram";
		
		
		ItemController ic = new ItemController();
		
		try{
			ic.createSupply(name, preQuantity, unit);
			ic.removeSupply(name, removeQuantity);
		} catch(InvalidInputException e){
			fail();
		}
		
		checkResultSupply(name, preQuantity - removeQuantity, unit, m);
		
		Manager m2 = (Manager) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		checkResultSupply(name, preQuantity - removeQuantity, unit, m2);
	}
	
	@Test 
	public void testRemoveSupplyNull() {
		Manager m = Manager.getInstance();
		assertEquals(0, m.getSupplies().size());
		
		String name = null;
		String name1 = "tomato";
		String unit = "kgs";
		
		int preQuantity = 4;
		int removeQuantity0 = 0;
		int removeQuantity1 = 1;
		
		String error0 = null;
		String error1 = null;
		String error2 = null;
		ItemController ic = new ItemController();
		try{
			ic.createSupply(name1, preQuantity, unit);
			ic.removeSupply(name, removeQuantity0);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try{
			ic.createSupply(name1, preQuantity, unit);
			ic.removeSupply(name, removeQuantity1);
		} catch (InvalidInputException e) {
			error1 = e.getMessage();
		}
		try{
			ic.createSupply(name1, preQuantity, unit);
			ic.removeSupply(name1, removeQuantity0);
		} catch (InvalidInputException e){
			error2 = e.getMessage();
		}
		
		assertEquals("Supply name cannot be empty! Supply quantity cannot be empty or zero!", error0);
		assertEquals("Supply name cannot be empty!", error1);
		assertEquals("Supply quantity cannot be empty or zero!", error2);
	}
	
	@Test
	public void testRemoveSupplyEmpty() {
		Manager m = Manager.getInstance();
		assertEquals(0, m.getSupplies().size());
		
		String name = "";
		String name1 = "tomato";
		String unit = "kgs";
		
		int preQuantity = 4;
		int removeQuantity0 = 0;
		int removeQuantity1 = 1;
		
		String error0 = null;
		String error1 = null;
		String error2 = null;
		ItemController ic = new ItemController();
		try{
			ic.createSupply(name1, preQuantity, unit);
			ic.removeSupply(name, removeQuantity0);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try{
			ic.createSupply(name1, preQuantity, unit);
			ic.removeSupply(name, removeQuantity1);
		} catch (InvalidInputException e) {
			error1 = e.getMessage();
		}
		try{
			ic.createSupply(name1, preQuantity, unit);
			ic.removeSupply(name1, removeQuantity0);
		} catch (InvalidInputException e){
			error2 = e.getMessage();
		}
		
		assertEquals("Supply name cannot be empty! Supply quantity cannot be empty or zero!", error0);
		assertEquals("Supply name cannot be empty!", error1);
		assertEquals("Supply quantity cannot be empty or zero!", error2);
	}
	
	@Test
	public void testRemoveSupplySpaces(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getSupplies().size());
		
		String name = " ";
		String name1 = "tomato";
		String unit = "kgs";
		
		int preQuantity = 4;
		int removeQuantity0 = 0;
		int removeQuantity1 = 1;
		
		String error0 = null;
		String error1 = null;
		String error2 = null;
		ItemController ic = new ItemController();
		try{
			ic.createSupply(name1, preQuantity, unit);
			ic.removeSupply(name, removeQuantity0);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try{
			ic.createSupply(name1, preQuantity, unit);
			ic.removeSupply(name, removeQuantity1);
		} catch (InvalidInputException e) {
			error1 = e.getMessage();
		}
		try{
			ic.createSupply(name1, preQuantity, unit);
			ic.removeSupply(name1, removeQuantity0);
		} catch (InvalidInputException e){
			error2 = e.getMessage();
		}
		
		assertEquals("Supply name cannot be empty! Supply quantity cannot be empty or zero!", error0);
		assertEquals("Supply name cannot be empty!", error1);
		assertEquals("Supply quantity cannot be empty or zero!", error2);
	}

	@Test
	public void testRemoveSupplyNegativeQuantity(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getEquipments().size());
		
		String name0 = null;
		String name1 = "";
		String name2 = " ";
		String name3 = "tomato";
		String unit = "kgs";
		
		int quantityNeg = -1;
		int preQuantity = 4;
		
		String error0 = null;
		String error1 = null;
		
		ItemController ic = new ItemController();
		
		try{
			ic.createSupply(name3, preQuantity, unit);
			ic.removeSupply(name0, quantityNeg);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try{
			ic.createSupply(name3, preQuantity, unit);
			ic.removeSupply(name1, quantityNeg);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try{
			ic.createSupply(name3, preQuantity, unit);
			ic.removeSupply(name2, quantityNeg);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try{
			ic.createSupply(name3, preQuantity, unit);
			ic.removeSupply(name3, quantityNeg);
		} catch (InvalidInputException e){
			error1 = e.getMessage();
		}
		
		assertEquals("Supply name cannot be empty! Supply quantity cannot be negative!", error0);
		assertEquals("Supply quantity cannot be negative!", error1);
	}
	
	@Test
	public void testRemoveSupplyZeroQuantity(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getEquipments().size());
		
		String name0 = null;
		String name1 = "";
		String name2 = " ";
		String name3 = "tomato";
		String unit = "kgs";
		
		int quantityNeg = 0;
		int preQuantity = 4;
		
		String error0 = null;
		String error1 = null;
		
		ItemController ic = new ItemController();
		
		try{
			ic.createSupply(name3, preQuantity, unit);
			ic.removeSupply(name0, quantityNeg);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try{
			ic.createSupply(name3, preQuantity, unit);
			ic.removeSupply(name1, quantityNeg);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try{
			ic.createSupply(name3, preQuantity, unit);
			ic.removeSupply(name2, quantityNeg);
		} catch (InvalidInputException e){
			error0 = e.getMessage();
		}
		try{
			ic.createSupply(name3, preQuantity, unit);
			ic.removeSupply(name3, quantityNeg);
		} catch (InvalidInputException e){
			error1 = e.getMessage();
		}
		
		assertEquals("Supply name cannot be empty! Supply quantity cannot be empty or zero!", error0);
		assertEquals("Supply quantity cannot be empty or zero!", error1);
	}

	@Test
	public void testRemoveSupplyNegativeResult(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getSupplies().size());
		
		String name = "tomato";
		String unit = "kgs";
		double preQuantity = 3.0;
		double removeQuantity = 5.0;
		
		String error0 = null;
		
		ItemController ic = new ItemController();
		
		try{
			ic.createSupply(name, preQuantity, unit);
			ic.removeSupply(name, removeQuantity);
		} catch(InvalidInputException e){
			error0 = e.getMessage();
		}
		
		assertEquals("Cannot remove more than " + preQuantity + " " + unit + " of " + name, error0);
	}
	
	@Test
	public void testCreateSupplyDifferentUnit() {
		Manager m = Manager.getInstance();
		assertEquals(0, m.getSupplies().size());
		
		String name = "tomato";
		String unit = "kgs";
		String wrongUnit = "lbs";
		double quantity = 3.0;
		double addedQuantity = 1.5;
		
		
		String error0 = null;
		
		ItemController ic = new ItemController();
		
		try{
			ic.createSupply(name, quantity, unit);
		} catch(InvalidInputException e) {
			error0 = e.getMessage();
		}
		
		assertEquals(null, error0);
		assertEquals(1,m.getSupplies().size());
		
		error0 = null;
		try{
			ic.createSupply(name, addedQuantity, wrongUnit);
		} catch(InvalidInputException e) {
			error0 = e.getMessage();
		}
		
		assertEquals(1, m.getSupplies().size());
		assertEquals(error0, "Supply unit does not match: " + unit);
	}
	
	public void checkResultSupply(String name, double quantity, String unit,
			Manager m2) {
		assertEquals(1, m2.getSupplies().size());
		assertEquals(name, m2.getSupply(0).getName());
		assertEquals(quantity, m2.getSupply(0).getQuantity(), 0.00);
		assertEquals(unit, m2.getSupply(0).getUnit());
	}

	@Test
	public void testCreateStaffMember(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getStaffmembers().size());
		
		String name = "john";
		String role = "cashier";
		
		ItemController ic = new ItemController();
		try {
			ic.createStaffMember(name, role);
		} catch (InvalidInputException e) {
			fail();
		}
		
		checkResultStaffMember(name, role, m);
		Manager m2 = (Manager) PersistenceXStream.loadFromXMLwithXStream();
		checkResultStaffMember(name, role, m2);
	}
	
	@Test
	public void testCreateStaffMemberEmpty(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getStaffmembers().size());
		
		String name1 = "";
		String name2 = "james";
		String role1 = "";
		String role2 = "cook";
		
		String error1 = null;
		String error2 = null;
		String error3 = null;
		
		ItemController ic = new ItemController();
		try{
			ic.createStaffMember(name1, role1);
		}catch(InvalidInputException e){
			error1 = e.getMessage();
		}
		
		try{
			ic.createStaffMember(name2, role1);
		}catch(InvalidInputException e){
			error2 = e.getMessage();
		}
		
		try{
			ic.createStaffMember(name1, role2);
		}catch(InvalidInputException e){
			error3 = e.getMessage();
		}
		
		//Check error
		assertEquals("Staff member name cannot be empty! Staff member role cannot be empty!", error1);
		assertEquals("Staff member role cannot be empty!", error2);
		assertEquals("Staff member name cannot be empty!", error3);
	}
	
	@Test
	public void testCreateStaffMemberNull(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getStaffmembers().size());
		
		String name1 = null;
		String name2 = "john";
		String role1 = null;
		String role2 = "cashier";
		
		String error1 = null;
		String error2 = null;
		String error3 = null;
		
		ItemController ic = new ItemController();
		try{
			ic.createStaffMember(name1, role1);
		}catch(InvalidInputException e){
			error1 = e.getMessage();
		}
		
		try{
			ic.createStaffMember(name2, role1);
		}catch(InvalidInputException e){
			error2 = e.getMessage();
		}
		
		try{
			ic.createStaffMember(name1, role2);
		}catch(InvalidInputException e){
			error3 = e.getMessage();
		}
		
		//Check error
		assertEquals("Staff member name cannot be empty! Staff member role cannot be empty!", error1);
		assertEquals("Staff member role cannot be empty!", error2);
		assertEquals("Staff member name cannot be empty!", error3);
	}
	
	@Test
	public void testCreateStaffMemberSpaces(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getStaffmembers().size());
		
		String name1 = " ";
		String name2 = "john";
		String role1 = " ";
		String role2 = "cashier";
		
		String error1 = null;
		String error2 = null;
		String error3 = null;
		
		ItemController ic = new ItemController();
		try{
			ic.createStaffMember(name1, role1);
		}catch(InvalidInputException e){
			error1 = e.getMessage();
		}
		
		try{
			ic.createStaffMember(name2, role1);
		}catch(InvalidInputException e){
			error2 = e.getMessage();
		}
		
		try{
			ic.createStaffMember(name1, role2);
		}catch(InvalidInputException e){
			error3 = e.getMessage();
		}
		
		//Check error
		assertEquals("Staff member name cannot be empty! Staff member role cannot be empty!", error1);
		assertEquals("Staff member role cannot be empty!", error2);
		assertEquals("Staff member name cannot be empty!", error3);
	}
	
	@Test
	public void testCreateStaffMemberExists(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getStaffmembers().size());
		
		String name = "john";
		String role = "cashier";
		String error1 = null;
		
		ItemController ic = new ItemController();
		try{
			ic.createStaffMember(name, role);
			ic.createStaffMember(name, role);
		} catch(InvalidInputException e){
			error1 = e.getMessage();
		}
		
		assertEquals("Staff member already exists!", error1);
		checkResultStaffMember(name, role, m);
	}
	
	@Test
	public void testRemoveStaffMember(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getStaffmembers().size());
		
		String name = "john";
		String role = "cashier";
		
		ItemController ic = new ItemController();
		
		try{
			ic.createStaffMember(name, role);
		}catch(InvalidInputException e){
			fail();
		}
		
		assertEquals(1, m.getStaffmembers().size());
		assertEquals(name, m.getStaffmember(0).getName());
		assertEquals(role, m.getStaffmember(0).getRole());
		
		try {
			ic.removeStaffMember(name);
		} catch (InvalidInputException e) {
			fail();
		}
		
		assertEquals(0, m.getStaffmembers().size());
		Manager m2 = (Manager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(0, m2.getStaffmembers().size());
	}
	
	@Test
	public void testRemoveStaffMemberEmpty(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getStaffmembers().size());
		
		String name1 = "";
		String name2 = "james";
		String role2 = "cook";
		
		String error1 = null;

		ItemController ic = new ItemController();
		try{
			ic.createStaffMember(name2, role2);
			ic.removeStaffMember(name1);
		}catch(InvalidInputException e){
			error1 = e.getMessage();
		}
		
		//Check error
		assertEquals("Staff member name cannot be empty!", error1);
		
	}
	
	@Test
	public void testRemoveStaffMemberNull(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getStaffmembers().size());
		
		String name1 = null;
		String name2 = "james";
		String role2 = "cook";
		
		String error1 = null;
		
		ItemController ic = new ItemController();
		try{
			ic.createStaffMember(name2, role2);
			ic.removeStaffMember(name1);
		}catch(InvalidInputException e){
			error1 = e.getMessage();
		}
		
		//Check error
		assertEquals("Staff member name cannot be empty!", error1);
		
	}
	
	public void testRemoveStaffMemberSpaces(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getStaffmembers().size());
		
		String name1 = " ";
		String name2 = "james";
		String role2 = "cook";
		
		String error1 = null;

		
		ItemController ic = new ItemController();
		try{
			ic.createStaffMember(name2, role2);
			ic.removeStaffMember(name1);
		}catch(InvalidInputException e){
			error1 = e.getMessage();
		}
		
		//Check error
		assertEquals("Staff member name cannot be empty!", error1);
	}

	@Test
	public void testRemoveStaffMemberExist(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getStaffmembers().size());
		
		String name1 = "john";
		String name2 = "james";
		String role1 = "cook";
		
		String error1 = null;
		
		ItemController ic = new ItemController();
		try{
			ic.createStaffMember(name1, role1);
			ic.removeStaffMember(name2);
		} catch(InvalidInputException e){
			error1 = e.getMessage();
		}
		
		assertEquals("Staff member does not exist!", error1);
	}
	
	public void checkResultStaffMember(String name, String role, Manager m2){
		assertEquals(1, m2.getStaffmembers().size());
		assertEquals(name, m2.getStaffmember(0).getName());
		assertEquals(role, m2.getStaffmember(0).getRole());
	}
	
	public void checkResultRemovedStaffMember(String name, String role, Manager m2){
		assertEquals(0, m2.getStaffmembers().size());
		assertEquals(name, m2.getStaffmember(0).getName());
		assertEquals(role, m2.getStaffmember(0).getRole());
	}
	
	@Test
	public void testAddTimeStaffMember(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getStaffmembers().size());
		
		String name = "john";
		String role = "cashier";
		Time[] startTime = {new Time(110000), new Time(110000), new Time(110000), new Time(110000), new Time(110000), new Time(110000), new Time(110000)};
		Time[] endTime = {new Time(130000), new Time(130000), new Time(130000), new Time(130000), new Time(130000), new Time(130000), new Time(130000)};
		
		ItemController ic = new ItemController();
		
		try{
			ic.createStaffMember(name, role);
			ic.addTimeStaffMember(name, startTime, endTime);
		} catch(InvalidInputException e){
			fail();
		}
	}
	
	@Test
	public void testAddTimeStaffMemberEmpty(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getStaffmembers().size());
		
		String name1 = "";
		Time[] startTime = {new Time(160000), new Time(160000), new Time(160000), new Time(160000), new Time(160000), new Time(160000), new Time(160000)};
		Time[] endTime = {new Time(180000), new Time(180000), new Time(180000), new Time(180000), new Time(180000), new Time(180000), new Time(180000)};
		
		String error1 = null;		
		
		String nameError = "Staff member name cannot be empty! ";
		ItemController ic = new ItemController();
		
		try{
			ic.addTimeStaffMember(name1, startTime, endTime);
		} catch(InvalidInputException e){
			error1 = e.getMessage();
		}
		
		assertEquals(nameError, error1);
	}

	@Test
	public void testAddTimeStaffMemberNull(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getStaffmembers().size());
		
		String name1 = null;
		
		Time[] startTime = {new Time(160000), new Time(160000), new Time(160000), new Time(160000), new Time(160000), new Time(160000), new Time(160000)};
		Time[] endTime = {new Time(180000), new Time(180000), new Time(180000), new Time(180000), new Time(180000), new Time(180000), new Time(180000)};
		
		String error = null;
		
		String nameError = "Staff member name cannot be empty! ";
		ItemController ic = new ItemController();
		
		try{
			ic.addTimeStaffMember(name1, startTime, endTime);
		} catch(InvalidInputException e){
			error = e.getMessage();
		}
		
		assertEquals(nameError, error);
	}

	@Test
	public void testAddTimeStaffMemberSpaces(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getStaffmembers().size());
		
		String name1 = " ";
		String name2 = "james";
		
		Time[] startTime = {new Time(160000), new Time(160000), new Time(160000), new Time(160000), new Time(160000), new Time(160000), new Time(160000)};
		Time[] endTime = {new Time(180000), new Time(180000), new Time(180000), new Time(180000), new Time(180000), new Time(180000), new Time(180000)};
		
		String error1 = null;
		
		String nameError = "Staff member name cannot be empty! ";
		ItemController ic = new ItemController();
		
		try{
			ic.addTimeStaffMember(name1, startTime, endTime);
		} catch(InvalidInputException e){
			error1 = e.getMessage();
		}
		
		try{
			ic.addTimeStaffMember(name2, startTime, endTime);
		} catch(InvalidInputException e){
			fail();
		}
		
		assertEquals(nameError, error1);
	}
	
	@Test
	public void testAddTimeStaffMemberEndBeforeStart(){
		Manager m = Manager.getInstance();
		assertEquals(0, m.getStaffmembers().size());
		
		String name = "james";
		Time[] startTime = {new Time(180000), new Time(180000), new Time(180000), new Time(180000), new Time(180000), new Time(180000), new Time(180000)};
		Time[] endTime = {new Time(140000), new Time(140000), new Time(140000), new Time(140000), new Time(140000), new Time(140000), new Time(140000)};
		
		String error = null;
		ItemController ic = new ItemController();
		
		try{
			ic.addTimeStaffMember(name, startTime, endTime);
		} catch(InvalidInputException e){
			error = e.getMessage();
		}
		
		assertEquals("End time must be greater than start time!", error);
	}
	
	@Test
	public void testCreateMenuItem() {
		Manager m = Manager.getInstance();
		assertEquals(0, m.getMenus().size());

		String name = "cheese burger";
		double price = 3.0;

		ItemController ic = new ItemController();
		try {
			ic.createMenuItem(name, price);
		} catch (InvalidInputException e) {
			fail();
		}

		checkResultMenuItem(name, price, m);

		Manager m2 = (Manager) PersistenceXStream.loadFromXMLwithXStream();

		// check file contents
		checkResultMenuItem(name, price, m2);
	}
	
	@Test
	public void testCreateMenuItemNull() {
		Manager m = Manager.getInstance();
		String error = "";
		assertEquals(0, m.getMenus().size());
		
		String name = null;
		double price = 3.0;

		ItemController ic = new ItemController();
		try {
			ic.createMenuItem(name, price);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals(error, "Menu Item name cannot be empty!");
	}
	
	@Test
	public void testCreateMenuItemEmpty() {
		Manager m = Manager.getInstance();
		String error = "";
		assertEquals(0, m.getMenus().size());
		
		String name = "";
		double price = 3.0;

		ItemController ic = new ItemController();
		try {
			ic.createMenuItem(name, price);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals(error, "Menu Item name cannot be empty!");
	}
	
	@Test
	public void testCreateMenuItemSpaces() {
		Manager m = Manager.getInstance();
		String error = "";
		assertEquals(0, m.getMenus().size());
		
		String name = "   ";
		double price = 3.0;

		ItemController ic = new ItemController();
		try {
			ic.createMenuItem(name, price);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals(error, "Menu Item name cannot be empty!");
	}
	
	@Test
	public void testCreateMenuItemPriceNegative() {
		Manager m = Manager.getInstance();
		String error = "";
		assertEquals(0, m.getMenus().size());
		
		String name = "burger";
		double price = -3.0;

		ItemController ic = new ItemController();
		try {
			ic.createMenuItem(name, price);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals(error, "Menu Item price cannot be negative!");
	}
	
//	@Test
//	public void testCreateMenuItemPriceZeroInt() {
//		Manager m = Manager.getInstance();
//		String error = "";
//		assertEquals(0, m.getEquipments().size());
//		
//		String name = "burger";
//		int tmp = 3;
//		double price = tmp;
//
//		ItemController ic = new ItemController();
//		try {
//			ic.createMenuItem(name, price);
//		} catch (InvalidInputException e) {
//			error = e.getMessage();
//		}
//		
//		assertEquals(error, "");
//	}
	
//	@Test
//	public void testCreateMenuItemPriceZeroFloat() {
//	}
	
//	@Test
//	public void testCreateMenuItemPriceNull() {
//		
//		
//	}
	
	@Test
	public void testCreateMenuItemPriceZero() {
		Manager m = Manager.getInstance();
		String error = "";
		assertEquals(0, m.getMenus().size());
		
		String name = "burger";
		double price = 0;

		ItemController ic = new ItemController();
		try {
			ic.createMenuItem(name, price);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals(error, "Menu Item price cannot be empty or zero!");
	}
	
//	@Test
//	public void testCreateMenuItemPriceEmpty() {
//	}
	
//	@Test
//	public void testCreateMenuPriceSpaces() {
//	}
	
	@Test
	public void testRemoveMenuItem() {
		Manager m = Manager.getInstance();
		assertEquals(0, m.getMenus().size());
		
		String name = "burger";
		double price = 3.0;

		ItemController ic = new ItemController();
		try {
			ic.createMenuItem(name, price);
		} catch (InvalidInputException e) {
			fail();
		}
		
		assertEquals(1, m.getMenus().size());
		
		try {
			ic.removeMenuItem(name);
		} catch (InvalidInputException e) {
			fail();
		}
		
		assertEquals(0, m.getMenus().size());
	}
	
	@Test
	public void testRemoveMenuItemNull() {
		Manager m = Manager.getInstance();
		String error = "";
		assertEquals(0, m.getMenus().size());
		
		ItemController ic = new ItemController();
		String name = null;

		try {
			ic.removeMenuItem(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals(error, "Menu item name cannot be empty!");
	}
	
	@Test
	public void testRemoveMenuItemEmpty() {
		Manager m = Manager.getInstance();
		String error = "";
		assertEquals(0, m.getMenus().size());
		
		ItemController ic = new ItemController();
		String name = "";

		try {
			ic.removeMenuItem(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals(error, "Menu item name cannot be empty!");	
	}
	
	@Test
	public void testRemoveMenuItemSpaces() {
		Manager m = Manager.getInstance();
		String error = "";
		assertEquals(0, m.getMenus().size());
		
		ItemController ic = new ItemController();
		String name = "   ";

		try {
			ic.removeMenuItem(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals(error, "Menu item name cannot be empty!");
	}
	
	@Test
	public void testRemoveMenuItemDoesNotExist() {
		Manager m = Manager.getInstance();
		String error = "";
		assertEquals(0, m.getMenus().size());
		
		ItemController ic = new ItemController();
		String name = "burger";

		try {
			ic.removeMenuItem(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals(error, "Menu Item name does not exist!");
	}
	
	public void checkResultMenuItem(String name, double price, Manager m2) {
		assertEquals(1, m2.getMenus().size());
		assertEquals(name, m2.getMenus(0).getName());
		assertEquals(price, m2.getMenus(0).getPrice(), 0.0);
	}
		
	@Test
	public void testMenuItemOrdered() {
		Manager m = Manager.getInstance();
		ItemController ic = new ItemController();
		
		assertEquals(0, m.getMenus().size());
		String name = "burger";
		double price = 3.0;
		int quantity = 24;
		
		try {
			ic.createMenuItem(name, price);
		} catch (InvalidInputException e) {
			fail();
		}
		
		assertEquals(1, m.getMenus().size());
		
		try {
			ic.menuItemOrdered(name, quantity);
		} catch (InvalidInputException e) {
			fail();
		}
		
		assertEquals(quantity, m.getMenus(0).getPopularityCounter());		
	}
	
	@Test
	public void testMenuItemOrderedAlreadyHasQuantity() {
		Manager m = Manager.getInstance();
		ItemController ic = new ItemController();
		
		assertEquals(0, m.getMenus().size());
		String name = "burger";
		double price = 3.0;
		int quantity = 24;
		int newQuantity = 4;
		
		try {
			ic.createMenuItem(name, price);
		} catch (InvalidInputException e) {
			fail();
		}
		
		assertEquals(1, m.getMenus().size());
		
		try {
			ic.menuItemOrdered(name, quantity);
		} catch (InvalidInputException e) {
			fail();
		}
		
		assertEquals(quantity, m.getMenus(0).getPopularityCounter());
		
		try {
			ic.menuItemOrdered(name, newQuantity);
		} catch (InvalidInputException e) {
			fail();
		}
		
		assertEquals(quantity+newQuantity, m.getMenus(0).getPopularityCounter());
	}
	
	@Test
	public void testMenuItemOrderedNull() {
		Manager m = Manager.getInstance();
		ItemController ic = new ItemController();
		String error = "";
		
		assertEquals(0, m.getMenus().size());
		String name = "burger";
		String nameNull = null;
		double price = 3.0;
		int quantity = 24;
		
		try {
			ic.createMenuItem(name, price);
		} catch (InvalidInputException e) {
			fail();
		}
		
		assertEquals(1, m.getMenus().size());
		
		try {
			ic.menuItemOrdered(name, quantity);
		} catch (InvalidInputException e) {
			fail();
		}
		
		assertEquals(quantity, m.getMenus(0).getPopularityCounter());
		
		try {
			ic.menuItemOrdered(nameNull, quantity);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals(error, "Order name cannot be empty!");
		
	}
	
	@Test
	public void testMenuItemOrderedEmpty() {
		Manager m = Manager.getInstance();
		ItemController ic = new ItemController();
		String error = "";
		
		assertEquals(0, m.getMenus().size());
		String name = "burger";
		String nameEmpty = "";
		double price = 3.0;
		int quantity = 24;
		
		try {
			ic.createMenuItem(name, price);
		} catch (InvalidInputException e) {
			fail();
		}
		
		assertEquals(1, m.getMenus().size());
		
		try {
			ic.menuItemOrdered(name, quantity);
		} catch (InvalidInputException e) {
			fail();
		}
		
		assertEquals(quantity, m.getMenus(0).getPopularityCounter());
		try {
			ic.menuItemOrdered(nameEmpty, quantity);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals(error, "Order name cannot be empty!");
	}
	
	@Test
	public void testMenuItemOrderedSpaces() {
		Manager m = Manager.getInstance();
		ItemController ic = new ItemController();
		String error = "";
		
		assertEquals(0, m.getMenus().size());
		String name = "burger";
		String nameSpaces = "   ";
		double price = 3.0;
		int quantity = 24;
		
		try {
			ic.createMenuItem(name, price);
		} catch (InvalidInputException e) {
			fail();
		}
		
		assertEquals(1, m.getMenus().size());
		
		try {
			ic.menuItemOrdered(name, quantity);
		} catch (InvalidInputException e) {
			fail();
		}
		
		assertEquals(quantity, m.getMenus(0).getPopularityCounter());
		try {
			ic.menuItemOrdered(nameSpaces, quantity);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals(error, "Order name cannot be empty!");
	}
	
	@Test
	public void testMenuItemOrderedDoesNotExist() {
		Manager m = Manager.getInstance();
		ItemController ic = new ItemController();
		String error = "";
		
		assertEquals(0, m.getMenus().size());
		String name = "burger";
		String nameDoesNotExist = "cheese burger";
		double price = 3.0;
		int quantity = 24;
		
		try {
			ic.createMenuItem(name, price);
		} catch (InvalidInputException e) {
			fail();
		}
		
		assertEquals(1, m.getMenus().size());
		
		try {
			ic.menuItemOrdered(name, quantity);
		} catch (InvalidInputException e) {
			fail();
		}
		
		assertEquals(quantity, m.getMenus(0).getPopularityCounter());
		
		try {
			ic.menuItemOrdered(nameDoesNotExist, quantity);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals(error, "Order name does not exist!");
	}
	
//	@Test
//	public void testMenuItemOrderedQuantityNull() {
//	}
	
//	@Test
//	public void testMenuItemOrderQuantitySpaces() {
//	}
	
//	@Test
//	public void testMenuItemOrderedQuantityEmpty() {
//	}
	
	@Test
	public void testMenuItemOrderedQuantityNegative() {
		Manager m = Manager.getInstance();
		ItemController ic = new ItemController();
		String error = "";
		
		assertEquals(0, m.getMenus().size());
		String name = "burger";
		int quantity = -3;
		
		
		try {
			ic.menuItemOrdered(name, quantity);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals(error, "Order quantity cannot be negative!");
	}
	
	@Test
	public void testMenuItemOrderedZero() {
		Manager m = Manager.getInstance();
		ItemController ic = new ItemController();
		String error = "";
		
		assertEquals(0, m.getMenus().size());
		String name = "burger";
		int quantity = 0;
		
		
		try {
			ic.menuItemOrdered(name, quantity);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals(error, "Order quantity cannot be empty or zero!");
	}
}
