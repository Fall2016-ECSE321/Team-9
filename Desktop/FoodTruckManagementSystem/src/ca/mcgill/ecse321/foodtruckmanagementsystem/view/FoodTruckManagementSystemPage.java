package ca.mcgill.ecse321.foodtruckmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.ItemController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import java.awt.Toolkit;

public class FoodTruckManagementSystemPage extends JFrame {

	private JPanel contentPane;
	private JTextField equipmentName;
	private String error = "";
	private JLabel errorMessage;
	private SpinnerNumberModel model;

	/**
	 * Launch the application.
	 */
	public FoodTruckManagementSystemPage() {
		initComponents();
		refreshData();
	}

	/**
	 * Create the frame.
	 */
	public void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FoodTruckManagementSystemPage.class.getResource("/ca/mcgill/ecse321/foodtruckmanagementsystem/view/foodtruck.png")));
		setTitle("Food Truck Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1896, 955);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane addremovePane = new JScrollPane();
		addremovePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		addremovePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(addremovePane, GroupLayout.PREFERRED_SIZE, 1300, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1154, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(addremovePane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(557, Short.MAX_VALUE))
		);
		
		JPanel panel = new JPanel();
		addremovePane.setViewportView(panel);
		
		error = "";
		errorMessage = new JLabel(error);
		errorMessage.setForeground(Color.RED);
		
		JLabel equipment = new JLabel("Equipment:");
		
		equipmentName = new JTextField();
		equipmentName.setColumns(10);
		
		model = new SpinnerNumberModel(0, -1000000, 1000000, 1);
		JSpinner equipmentQuantity = new JSpinner(model);
		
		JButton btnAddEquipment = new JButton("Add Equipment");
		btnAddEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEquipmentActionPerformed(e);
			}
		});
		
		JButton btnRemoveEquipment = new JButton("Remove Equipment");
		btnRemoveEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//removeEquipmentActionPerformed(e);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(errorMessage)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(equipment)
							.addGap(11)
							.addComponent(equipmentName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addComponent(equipmentQuantity, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addComponent(btnAddEquipment)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemoveEquipment)))
					.addContainerGap(268, Short.MAX_VALUE))
		);
		
		gl_panel.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAddEquipment, equipmentQuantity});
		gl_panel.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAddEquipment, equipmentName});
		gl_panel.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {btnRemoveEquipment, equipmentQuantity});
		gl_panel.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {btnRemoveEquipment, equipmentName});
		
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(errorMessage)
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(equipment))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(equipmentName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(equipmentQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAddEquipment)
							.addComponent(btnRemoveEquipment)))
					.addContainerGap(223, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		pack();
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
	
	private void removeEquipmentActionPerformed(java.awt.event.ActionEvent evt)
	{
		/*
		ItemController c = new ItemController();
		error = "";
			try {
				c.removeEquipment(equipmentName.getText(),  model.getNumber().intValue());
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		refreshData();
		*/
	}
}
