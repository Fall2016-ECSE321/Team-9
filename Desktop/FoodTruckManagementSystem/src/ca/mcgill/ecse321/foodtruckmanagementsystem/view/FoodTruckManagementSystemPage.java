package ca.mcgill.ecse321.foodtruckmanagementsystem.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.ItemController;
import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.ItemController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FoodTruckManagementSystemPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField equipmentName;
	private JLabel errorMessage, equipmentLabel;
	private JSpinner equipmentQuantity;
	private SpinnerNumberModel model;
	private int equipmentCount = 0;
	private String error = null;

	public FoodTruckManagementSystemPage(){
		initComponents();
		refreshData();
	}

	public void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FoodTruckManagementSystemPage.class.getResource("/ca/mcgill/ecse321/foodtruckmanagementsystem/view/foodtruck.png")));
		setTitle("Food Truck Management Software");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1111, 303);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tab = new JTabbedPane();
		getContentPane().add(tab);
		
		errorMessage = new JLabel("");
		errorMessage.setForeground(Color.RED);
		
		equipmentLabel = new JLabel("Equipment:");
		
		equipmentName = new JTextField();
		equipmentName.setColumns(10);
		
		JButton btnAddEquipment = new JButton("Add Equipment");
		btnAddEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEquipmentActionPerformed(e);
			}
		});
		
		model = new SpinnerNumberModel(0, -1000000, 1000000, 1);
		equipmentQuantity = new JSpinner(model);		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(errorMessage)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(equipmentLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(equipmentName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(equipmentQuantity, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addComponent(btnAddEquipment)))
					.addContainerGap(312, Short.MAX_VALUE))
		);
		
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAddEquipment, equipmentQuantity});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAddEquipment, equipmentName});
		
		
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(errorMessage)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(equipmentLabel)
						.addComponent(equipmentName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(equipmentQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddEquipment))
					.addContainerGap(136, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void refreshData()
	{
		errorMessage.setText(error);
	}
	 
	
	private void addEquipmentActionPerformed(java.awt.event.ActionEvent evt)
	{
		ItemController c = new ItemController();
		error = "";
			try {
				c.createEquipment(equipmentName.getText(),  model.getNumber().intValue());
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		refreshData();
	}
}
