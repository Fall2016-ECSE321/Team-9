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
import java.awt.Dimension;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

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
		contentPane = new JPanel();
		setPreferredSize(new Dimension(900, 900));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane addremovePane = new JScrollPane();
		addremovePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		addremovePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(addremovePane, GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(addremovePane, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(646, Short.MAX_VALUE))
		);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize( new Dimension(0, 200) );
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
		
		JButton button = new JButton("Remove Equipment");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(equipmentError)
						.addComponent(supplyError)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(supply)
							.addGap(22)
							.addComponent(supplyName, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(supplyQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(supplyUnit, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddSupply)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemoveSupply))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(equipment)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(equipmentName, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(equipmentQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddEquipment)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button)))
					.addGap(396))
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
						.addComponent(button))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(supplyError)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(supply)
						.addComponent(btnRemoveSupply)
						.addComponent(btnAddSupply)
						.addComponent(supplyUnit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(supplyQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(supplyName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(51))
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
	
	private void resetEquipmentData()
	{
		equipmentName.setText("");
		model1.setValue(0);
	}
	
	private void resetSupplyData()
	{
		supplyName.setText("");
		supplyUnit.setText("");
		model2.setValue(0.0);	
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
		
		if(eError.contains("Equipment name"))
			equipmentName.setText("");
		if(eError.contains("Equipment quantity"))
			model1.setValue(0);
		if(eError.equals(""))
			resetEquipmentData();
		
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
		
		if(sError.contains("Supply name"))
			supplyName.setText("");
		if(sError.contains("Supply quantity"))
			model2.setValue(0.0);
		if(sError.contains("Supply unit"))
			supplyUnit.setText("");		
		if(sError.equals(""))
			resetSupplyData();
		
		refreshData();
	}
	
	private void removeSupplyActionPerformed(java.awt.event.ActionEvent evt)
	{
		ItemController c = new ItemController();
		sError = "";
		
		try {
			c.removeSupply(supplyName.getText(), model2.getNumber().doubleValue());
		} catch (InvalidInputException e)
		{
			sError = e.getMessage();
		}	
		
		if(sError.contains("Supply name"))
			supplyName.setText("");
		if(sError.contains("Supply quantity") || sError.contains("remove more than"))
			model2.setValue(0.0);	
		if(sError.equals(""))
			resetSupplyData();
		
		refreshData();
	}
}
