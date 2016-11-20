package ca.mcgill.ecse321.foodtruckmanagementsystem.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.ItemController;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Manager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.StaffMember;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import java.awt.Toolkit;

public class FoodTruckManagementSystemPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField staffName;	
	
	private JTextField equipmentName;
	private JTextField supplyName;
	private JTextField supplyUnit;
	private JLabel lblSupplyMessage, lblEquipmentMessage, lblStaffMemberMessage, lblscheduleMessage;
	private SpinnerNumberModel equipmentModel, supplyModel;
	private JComboBox<String> staffMemberList;
	private JComboBox staffroleComboBox;
	private HashMap<Integer, StaffMember> staffMembers;
	
	private JSpinner[] startTimes = new JSpinner[7];
	private JSpinner[] endTimes = new JSpinner[7];	
	private JLabel[] lbldaysOfTheWeek = new JLabel[7];
	private String[] staffRoles = {"Cashier", "Chef", "Inventory Clerk", "Manager"};
	private String[] daysOfTheWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	private String sMessage = "";
	private String eMessage = "";
	private String schedMessage = "";
	private String smMessage = "";
	private Integer selectedStaffMember = -1;	

	public FoodTruckManagementSystemPage() {
		initComponents();
		refreshData();
	}
	
	public void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FoodTruckManagementSystemPage.class.getResource("/ca/mcgill/ecse321/foodtruckmanagementsystem/view/foodtruck.png")));
		setTitle("Food Truck Management System");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 980);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1338, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
		);
		
		JPanel tab1 = new JPanel();
		tabbedPane.addTab("Main", null, tab1, null);
		
		JPanel tab2 = new JPanel();
		tabbedPane.addTab("Inventory", null, tab2, null);
		MigLayout layout = new MigLayout("", "[][][][grow]", "[][][][][][][][][][][][][][][][][]");
		tab2.setLayout(layout);
		
		JLabel lblAddEquipment = new JLabel("Add Equipment");
		lblAddEquipment.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab2.add(lblAddEquipment, "cell 0 0");
		
		lblEquipmentMessage = new JLabel("");
		tab2.add(lblEquipmentMessage, "cell 0 1 4 1");
		
		JLabel lblEquipmentName = new JLabel("Equipment name:");
		tab2.add(lblEquipmentName, "cell 0 2 2 1,alignx left");
		
		equipmentName = new JTextField();
		tab2.add(equipmentName, "flowx,cell 2 2,growx");
		equipmentName.setColumns(10);
		
		JLabel lblEquipmentQuantity = new JLabel("Equipment Quantity:");
		tab2.add(lblEquipmentQuantity, "cell 0 4");
		
		
		JButton btnAddEquipment = new JButton("Add Equipment");
		btnAddEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEquipmentActionPerformed(e);
			}
		});
		equipmentModel = new SpinnerNumberModel(0, -100000000, 100000000, 1);
		JSpinner equipmentQuantity = new JSpinner(equipmentModel);
		tab2.add(equipmentQuantity, "cell 2 4,growx");
		tab2.add(btnAddEquipment, "cell 0 6,alignx right");
		
		JButton btnRemoveEquipment = new JButton("Remove Equipment");
		btnRemoveEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeEquipmentActionPerformed(e);
			}
		});
		tab2.add(btnRemoveEquipment, "cell 2 6");
		
		JLabel lblAddSupply = new JLabel("Add Supply");
		lblAddSupply.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab2.add(lblAddSupply, "cell 0 8");
		
		lblSupplyMessage = new JLabel("");
		tab2.add(lblSupplyMessage, "cell 0 9 4 1");
		
		JLabel lblSupplyName = new JLabel("Supply Name:");
		tab2.add(lblSupplyName, "cell 0 10");
		
		supplyName = new JTextField();
		tab2.add(supplyName, "cell 2 10,growx");
		supplyName.setColumns(10);
		
		JLabel lblSupplyQuantity = new JLabel("Supply Quantity:");
		tab2.add(lblSupplyQuantity, "cell 0 12");
		
		supplyModel = new SpinnerNumberModel(0.0, -1.0E8, 1.0E8, 0.1);
		JSpinner supplyQuantity = new JSpinner(supplyModel);
		tab2.add(supplyQuantity, "cell 2 12,growx");
		
		JLabel lblSupplyUnit = new JLabel("Supply Unit:");
		tab2.add(lblSupplyUnit, "cell 0 14");
		
		JButton btnAddSupply = new JButton("Add Supply");
		btnAddSupply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addSupplyActionPerformed(e);
			}
		});
		
		supplyUnit = new JTextField();
		tab2.add(supplyUnit, "cell 2 14,growx");
		supplyUnit.setColumns(10);
		tab2.add(btnAddSupply, "cell 0 16,alignx right");
		
		JButton btnRemoveSupply = new JButton("Remove Supply");
		btnRemoveSupply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeSupplyActionPerformed(e);
			}
		});
		tab2.add(btnRemoveSupply, "cell 2 16");
		
		JPanel tab3 = new JPanel();
		tabbedPane.addTab("Staff", null, tab3, null);
		tab3.setLayout(new MigLayout("", "[][][][][][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblAddStaff = new JLabel("Add New Staff");
		lblAddStaff.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab3.add(lblAddStaff, "cell 0 0");
		
		lblStaffMemberMessage = new JLabel("");
		tab3.add(lblStaffMemberMessage, "cell 0 1 8 1");
		
		JLabel lblStaffName = new JLabel("Staff Name:");
		tab3.add(lblStaffName, "flowx,cell 0 2,alignx trailing");
		
		JButton btnAddStaff = new JButton("Add");
		btnAddStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStaffMemberActionPerformed(e);
			}
		});
		
		staffName = new JTextField();
		tab3.add(staffName, "cell 1 2,alignx left");
		staffName.setColumns(10);
		
		JLabel lblSelectRole = new JLabel("Select Role:");
		tab3.add(lblSelectRole, "cell 2 2");
		
		staffroleComboBox = new JComboBox(staffRoles);
		tab3.add(staffroleComboBox, "cell 3 2");
		tab3.add(btnAddStaff, "cell 5 2,alignx left");
		
		JLabel lblRegisterStaffsWeekly = new JLabel("Register Staff's Weekly Schedule");
		lblRegisterStaffsWeekly.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab3.add(lblRegisterStaffsWeekly, "cell 0 4 2 1");
		
		lblscheduleMessage = new JLabel("");
		tab3.add(lblscheduleMessage, "cell 0 5 8 1");
		
		JLabel lblSelectStaffName = new JLabel("Select Staff Name:");
		tab3.add(lblSelectStaffName, "cell 1 6,alignx trailing");
		
		JSpinner.DateEditor[] startTimeEditors = new JSpinner.DateEditor[7];
		for(int i = 0; i < startTimes.length; i++) {
			startTimes[i] = new JSpinner(new SpinnerDateModel());
			startTimeEditors[i] = new JSpinner.DateEditor(startTimes[i], "h:mm a");
			startTimes[i].setEditor(startTimeEditors[i]);
			startTimes[i].setValue(new Date(0,0,0,0,0,0));
			tab3.add(startTimes[i], "cell 2 " + (8+(2*i)) +",alignx left");
		}
		
		JSpinner.DateEditor[] endTimeEditors = new JSpinner.DateEditor[7];
		for(int i = 0; i < startTimes.length; i++) {
			endTimes[i] = new JSpinner(new SpinnerDateModel());
			endTimeEditors[i] = new JSpinner.DateEditor(endTimes[i], "h:mm a");
			endTimes[i].setEditor(endTimeEditors[i]);
			endTimes[i].setValue(new Date(0,0,0,0,0,0));
			tab3.add(endTimes[i], "cell 3 " +(8+(2*i)) + ",aligny center");
		}
		
		staffMemberList = new JComboBox<String>(new String[0]);
		staffMemberList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>)evt.getSource();
				selectedStaffMember = cb.getSelectedIndex();
			}
		});
		tab3.add(staffMemberList, "cell 2 6 2 1,growx");
		
		for(int i = 0; i < daysOfTheWeek.length; i++) {
			lbldaysOfTheWeek[i] = new JLabel(daysOfTheWeek[i] + ":");
			tab3.add(lbldaysOfTheWeek[i], "flowx,cell 1 " + (8+(2*i)) + ",alignx left");
		}
		
		JButton btnRemoveStaff = new JButton("Remove Staff");
		btnRemoveStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteStaffMemberActionPerformed(e);
			}
		});
		tab3.add(btnRemoveStaff, "cell 4 6 2 1");
		
		JLabel lblStartTime = new JLabel("Start Time");
		tab3.add(lblStartTime, "cell 2 7,alignx left");
		
		JLabel lblEndTime = new JLabel("End Time");
		tab3.add(lblEndTime, "cell 3 7");
		
		JButton btnSaveSchedule = new JButton("Save");
		btnSaveSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				saveScheduleActionPerformed(evt);
			}
		});
		tab3.add(btnSaveSchedule, "cell 1 22");
		
		JPanel tab4 = new JPanel();
		tabbedPane.addTab("Report", null, tab4, null);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void refreshData() {
		lblEquipmentMessage.setText(eMessage);
		lblSupplyMessage.setText(sMessage);
		lblStaffMemberMessage.setText(smMessage);
		lblscheduleMessage.setText(schedMessage);
		Manager m = Manager.getInstance();
		if(smMessage.contains("successfully")) {
			staffMembers = new HashMap<Integer, StaffMember>();
			staffMemberList.removeAllItems();
			Iterator<StaffMember> smIt = m.getStaffmembers().iterator();
			Integer index = 0;
			while(smIt.hasNext())
			{
				StaffMember sm = smIt.next();
				staffMembers.put(index, sm);
				staffMemberList.addItem(sm.getName());
				index++;
			}
			selectedStaffMember = -1;
			staffMemberList.setSelectedIndex(selectedStaffMember);
		}
		
		pack();
	}
	
	private void resetEquipmentData() {
		equipmentName.setText("");
		equipmentModel.setValue(0);
	}
	
	private void resetSupplyData() {
		supplyName.setText("");
		supplyUnit.setText("");
		supplyModel.setValue(0.0);
	}
	
	private void resetStaffMemberData() {
		staffName.setText("");
	}
	
	private void addEquipmentActionPerformed(java.awt.event.ActionEvent evt) {
		ItemController c = new ItemController();
		eMessage = "";
		
		try {
			c.createEquipment(equipmentName.getText(),  equipmentModel.getNumber().intValue());
		} catch (InvalidInputException e) {
			eMessage = e.getMessage();
			lblEquipmentMessage.setForeground(Color.RED);
		}
		
		if(eMessage.contains("Equipment name"))
			equipmentName.setText("");
		if(eMessage.contains("Equipment quantity"))
			equipmentModel.setValue(0);
		if(eMessage.equals("")) {
			lblEquipmentMessage.setForeground(Color.GREEN);
			eMessage = "Equipment item successfully added!";
			resetEquipmentData();
		}
		
		refreshData();
	}
	
	private void removeEquipmentActionPerformed(java.awt.event.ActionEvent evt) {
		ItemController c = new ItemController();
		eMessage = "";
		
		try {
			c.removeEquipment(equipmentName.getText(), equipmentModel.getNumber().intValue());
		} catch (InvalidInputException e) {
			eMessage = e.getMessage();
			lblEquipmentMessage.setForeground(Color.RED);
		}	
		
		if(eMessage.contains("Equipment name"))
			equipmentName.setText("");
		if(eMessage.contains("Equipment quantity") || eMessage.contains("remove more than"))
			equipmentModel.setValue(0.0);	
		if(eMessage.equals("")) {
			lblEquipmentMessage.setForeground(Color.GREEN);
			eMessage = "Equipment item successfully removed!";
			resetEquipmentData();
		}
		
		refreshData();
	}
	
	private void addSupplyActionPerformed(java.awt.event.ActionEvent evt) {
		ItemController c = new ItemController();
		sMessage = "";
		
		try {
			c.createSupply(supplyName.getText(), supplyModel.getNumber().doubleValue(), supplyUnit.getText());
		} catch (InvalidInputException e) {
			sMessage = e.getMessage();
			lblSupplyMessage.setForeground(Color.RED);
		}	
		
		if(sMessage.contains("Supply name"))
			supplyName.setText("");
		if(sMessage.contains("Supply quantity"))
			supplyModel.setValue(0.0);
		if(sMessage.contains("Supply unit"))
			supplyUnit.setText("");		
		if(sMessage.equals("")) {
			lblSupplyMessage.setForeground(Color.GREEN);
			sMessage = "Supply item successfully added!";
			resetSupplyData();
		}
		
		refreshData();
	}
	
	private void removeSupplyActionPerformed(java.awt.event.ActionEvent evt) {
		ItemController c = new ItemController();
		sMessage = "";
		
		try {
			c.removeSupply(supplyName.getText(), supplyModel.getNumber().doubleValue());
		} catch (InvalidInputException e) {
			sMessage = e.getMessage();
			lblSupplyMessage.setForeground(Color.RED);
		}	
		
		if(sMessage.contains("Supply name"))
			supplyName.setText("");
		if(sMessage.contains("Supply quantity") || sMessage.contains("remove more than"))
			supplyModel.setValue(0.0);	
		if(sMessage.equals("")) {
			lblSupplyMessage.setForeground(Color.GREEN);
			sMessage = "Supply item successfully removed!";
			resetSupplyData();
		}
		
		refreshData();
	}
	
	private void addStaffMemberActionPerformed(java.awt.event.ActionEvent evt) {
		// add error handling for staff member's name
		ItemController c = new ItemController();
		smMessage = "";
		
		try {
			c.createStaffMember(staffName.getText(), staffroleComboBox.getSelectedItem().toString());
		} catch (InvalidInputException e) {
			smMessage = e.getMessage();
			lblStaffMemberMessage.setForeground(Color.RED);			
		}
		
		if(smMessage.contains("Staff Member name")) 
			staffName.setText("");		
		//if(smMessage.contains("Staff Member role"))
			//TODO: reset role			
		if(smMessage.equals("")) {
			lblStaffMemberMessage.setForeground(Color.GREEN);
			smMessage = "Staff Member was successfully added!";
			resetStaffMemberData();
		}
		
		refreshData();
	}
	
	private void saveScheduleActionPerformed(java.awt.event.ActionEvent evt) {
		StaffMember staffMember = new StaffMember("","");
		ItemController c = new ItemController();
		schedMessage = "";
		
		if(selectedStaffMember != -1)
			staffMember = staffMembers.get(selectedStaffMember);
				
		for(int i = 0; i < startTimes.length; i++) { 
			Calendar cal = Calendar.getInstance();
			cal.setTime((Date) startTimes[i].getValue());
			Time startTime = new Time(cal.getTime().getTime());
			cal.setTime((Date) startTimes[i].getValue());
			Time endTime = new Time(cal.getTime().getTime());
			try {
				c.addTimeStaffMember(staffMember.getName(), startTime, endTime);
			} catch (InvalidInputException e) {
				schedMessage += e.getMessage();
			}
		}
				
		if(schedMessage.equals("")) {
			lblscheduleMessage.setForeground(Color.GREEN);
			schedMessage = "Staff Member Schdule was successfully updated!";
		}
		else {
			lblscheduleMessage.setForeground(Color.RED);
			String[] split = schedMessage.split("! ");
			Set<String> stringSet = new HashSet<>(Arrays.asList(split));
			String[] filteredArray = stringSet.toArray(new String[0]);
			schedMessage = "";
			for(int i = 0; i < filteredArray.length; i++)
				schedMessage += filteredArray[i] + "! ";
		}
		
		refreshData();
	}
	
	private void deleteStaffMemberActionPerformed(java.awt.event.ActionEvent evt) {
		ItemController c = new ItemController();
		StaffMember staffMember = new StaffMember("","");
		schedMessage = "";
		
		if(selectedStaffMember != -1) 
			staffMember = staffMembers.get(selectedStaffMember);
		
		try {
			c.removeStaffMember(staffMember.getName());
		} catch (InvalidInputException e) {
			schedMessage = e.getMessage();
			lblscheduleMessage.setForeground(Color.RED);
		}
		
		if(schedMessage.contains("Staff member name")) {
			selectedStaffMember = -1;
			staffMemberList.setSelectedIndex(selectedStaffMember);
		}
		if(schedMessage.equals("")) {
			schedMessage = "Staff Member successfully removed!";
			lblscheduleMessage.setForeground(Color.GREEN);
		}
		
		refreshData();		
	}
}
