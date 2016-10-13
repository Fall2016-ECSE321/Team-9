package ca.mcgill.ecse321.foodtruckmanagementsystem.application;

import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.*;
import ca.mcgill.ecse321.foodtruckmanagementsystem.view.FoodTruckManagementSystemPage;


public class FoodTruckManagementSystem {

	public static void main(String [] args) {
		//load model 
		//PersistenceFoodTruckManagementSystem.loadFoodTruckManagementSystemModel();
	
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FoodTruckManagementSystemPage().setVisible(true);
			}
		});
	
	}
	
}
