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
import java.awt.Component;

public class FoodTruckManagementSystemPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField equipmentName;
	private String eError = "";
	private String sError = "";
	private JLabel equipmentError, supplyError;
	private SpinnerNumberModel model1, model2;
	private JTextField supplyName;
	private JTextField supplyUnit;

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
		//setBounds(100, 100, 900, 900);
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
					.addComponent(addremovePane, GroupLayout.PREFERRED_SIZE, 900, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(900, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(addremovePane, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(656, Short.MAX_VALUE))
		);
		
		JPanel panel = new JPanel();
		addremovePane.setViewportView(panel);
		
		eError = "";
		equipmentError = new JLabel("");
		equipmentError.setForeground(Color.RED);
		
		JLabel equipment = new JLabel("Equipment:");
		
		equipmentName = new JTextField();
		equipmentName.setColumns(10);
		
		model1 = new SpinnerNumberModel(0, -1000000, 1000000, 1);
		JSpinner equipmentQuantity = new JSpinner(model1);
		
		JButton btnAddEquipment = new JButton("Add Equipment");
		btnAddEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEquipmentActionPerformed(e);
			}
		});
		
		JButton btnRemoveEquipment = new JButton("Remove Equipment");
		btnRemoveEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeEquipmentActionPerformed(e);
			}
		});
		
		JLabel supply = new JLabel("Supply:");
		
		JButton btnAddSupply = new JButton("Add Supply");
		btnAddSupply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addSupplyActionPerformed(e);
			}
		});
		
		sError = "";
		supplyError = new JLabel("");
		supplyError.setForeground(Color.RED);
		
		supplyName = new JTextField();
		supplyName.setColumns(10);
		
		// determine what the step should be
		model2 = new SpinnerNumberModel(0, -1000000, 1000000, 0.1);
		JSpinner supplyQuantity = new JSpinner(model2);
		
		JButton btnRemoveSupply = new JButton("Remove Supply");
		btnRemoveSupply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeSupplyActionPerformed(e);
			}
		});
		
		supplyUnit = new JTextField();
		supplyUnit.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(equipmentError)
						.addComponent(supplyError)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(equipment)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(equipmentName, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(supply)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(supplyName, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(supplyQuantity)
								.addComponent(equipmentQuantity, GroupLayout.PREFERRED_SIZE, 109, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(supplyUnit, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAddSupply, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRemoveSupply, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(3))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnAddEquipment)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRemoveEquipment)))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addContainerGap(1032, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(equipmentError)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(equipment)
						.addComponent(equipmentName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(equipmentQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddEquipment)
						.addComponent(btnRemoveEquipment))
					.addGap(29)
					.addComponent(supplyError)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(supply)
						.addComponent(supplyName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(supplyQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(supplyUnit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddSupply)
						.addComponent(btnRemoveSupply))
					.addContainerGap(190, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		pack();
	}
	
	private void refreshData()
	{
		equipmentError.setText(eError);
		supplyError.setText(sError);
	}
	
	private void addEquipmentActionPerformed(java.awt.event.ActionEvent evt)
	{
		ItemController c = new ItemController();
		eError = "";
			try {
				c.createEquipment(equipmentName.getText(),  model1.getNumber().intValue());
			} catch (InvalidInputException e) {
				eError = e.getMessage();
			}
		refreshData();
	}
	
	private void removeEquipmentActionPerformed(java.awt.event.ActionEvent evt)
	{
		ItemController c = new ItemController();
		eError = "";
			try {
				c.removeEquipment(equipmentName.getText(),  model1.getNumber().intValue());
			} catch (InvalidInputException e) {
				eError = e.getMessage();
			}
		refreshData();
	}
	
	private void addSupplyActionPerformed(java.awt.event.ActionEvent evt)
	{
		ItemController c = new ItemController();
		sError = "";
		try {
			c.createSupply(supplyName.getText(), model2.getNumber().doubleValue(), supplyUnit.getText());
		} catch (InvalidInputException e) {
			sError = e.getMessage();
		}
		refreshData();
	}
	
	private void removeSupplyActionPerformed(java.awt.event.ActionEvent evt)
	{
		/*
		ItemController c = new ItemController();
		sError = "";
		try {
			c.removeSupply(supplyName.getText(), model2.getNumber().doubleValue());
		}
		*/
	}
}
