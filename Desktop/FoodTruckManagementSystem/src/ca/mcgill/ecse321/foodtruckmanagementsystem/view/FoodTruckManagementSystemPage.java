package ca.mcgill.ecse321.foodtruckmanagementsystem.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.ItemController;
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
import java.util.HashMap;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class FoodTruckManagementSystemPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField staffName;	
	
	private JTextField equipmentName;
	private JTextField supplyName;
	private JTextField supplyUnit;
	private JLabel lblSupplyMessage, lblEquipmentMessage, lblStaffMemberMessage;
	private SpinnerNumberModel equipmentModel, supplyModel;
	private JComboBox<String> staffMemberList;
	private JComboBox staffroleComboBox;
	private HashMap<Integer, StaffMember> staffMembers;
	
	private String[] staffRoles = {"Cashier", "Chef", "Inventory Clerk", "Manager"};
	private String sMessage = "";
	private String eMessage = "";
	private String schedMessage = "";
	private String smMessage = "";
	private Integer selectedStaffMember = -1;
	private Integer selectedStaffRole = -1;
	

	public FoodTruckManagementSystemPage() {
		initComponents();
		refreshData();
	}
	
	@SuppressWarnings("unchecked")
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1437, 980);
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
		tab3.add(btnAddStaff, "cell 5 2,alignx center");
		
		JLabel lblRegisterStaffsWeekly = new JLabel("Register Staff's Weekly Schedule");
		lblRegisterStaffsWeekly.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab3.add(lblRegisterStaffsWeekly, "cell 0 4 2 1");
		
		JLabel lblscheduleMessage = new JLabel("");
		tab3.add(lblscheduleMessage, "cell 1 5");
		
		JLabel lblSelectStaffName = new JLabel("Select Staff Name:");
		tab3.add(lblSelectStaffName, "cell 1 6,alignx trailing");
		
		staffMemberList = new JComboBox<String>(new String[0]);
		staffMemberList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>)evt.getSource();
				selectedStaffMember = cb.getSelectedIndex();
			}
		});
		tab3.add(staffMemberList, "cell 2 5 2 1,growx");
		
		JLabel lblStartTime = new JLabel("Start Time");
		tab3.add(lblStartTime, "cell 2 7,alignx left");
		
		JLabel lblEndTime = new JLabel("End Time");
		tab3.add(lblEndTime, "cell 3 7");
		
		JLabel lblMonday = new JLabel("Monday:");
		tab3.add(lblMonday, "flowx,cell 1 8,alignx left");
		
		JSpinner mondayStartTime = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor mondayStartTimeEditor = new JSpinner.DateEditor(mondayStartTime, "HH:mm");
		mondayStartTime.setEditor(mondayStartTimeEditor);
		tab3.add(mondayStartTime, "cell 2 8,alignx left");
		
		JSpinner mondayEndTime = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor mondayEndTimeEditor = new JSpinner.DateEditor(mondayEndTime, "HH:mm");
		mondayEndTime.setEditor(mondayEndTimeEditor);
		tab3.add(mondayEndTime, "cell 3 8,aligny center");
		
		JLabel lblTuesday = new JLabel("Tuesday:");
		tab3.add(lblTuesday, "flowx,cell 1 10,alignx left");
		
		JSpinner tuesdayStartTime = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor tuesdayStartTimeEditor = new JSpinner.DateEditor(tuesdayStartTime, "HH:mm");
		tuesdayStartTime.setEditor(tuesdayStartTimeEditor);
		tab3.add(tuesdayStartTime, "cell 2 10,alignx left");
		
		JSpinner tuesdayEndTime = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor tuesdayEndTimeEditor = new JSpinner.DateEditor(tuesdayEndTime, "HH:mm");
		tuesdayEndTime.setEditor(tuesdayEndTimeEditor);
		tab3.add(tuesdayEndTime, "cell 3 10");
		
		JLabel lblWednesday = new JLabel("Wednesday:");
		tab3.add(lblWednesday, "flowx,cell 1 12,alignx left");
		
		JSpinner wednesdayStartTime = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor wednesdayStartTimeEditor = new JSpinner.DateEditor(wednesdayStartTime, "HH:mm");
		wednesdayStartTime.setEditor(wednesdayStartTimeEditor);
		tab3.add(wednesdayStartTime, "cell 2 12");
		
		JSpinner wednesdayEndTime = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor wednesdayEndTimeEditor = new JSpinner.DateEditor(wednesdayEndTime, "HH:mm");
		wednesdayEndTime.setEditor(wednesdayEndTimeEditor);		
		tab3.add(wednesdayEndTime, "cell 3 12");
		
		JLabel lblThursday = new JLabel("Thursday:");
		tab3.add(lblThursday, "cell 1 14,alignx left");
		
		JSpinner thursdayStartTime = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor thursdayStartTimeEditor = new JSpinner.DateEditor(thursdayStartTime, "HH:mm");
		thursdayStartTime.setEditor(thursdayStartTimeEditor);
		tab3.add(thursdayStartTime, "cell 2 14");
		
		JSpinner thursdayEndTime = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor thursdayEndTimeEditor = new JSpinner.DateEditor(thursdayEndTime, "HH:mm");
		thursdayEndTime.setEditor(thursdayEndTimeEditor);
		tab3.add(thursdayEndTime, "cell 3 14");
		
		JLabel lblFriday = new JLabel("Friday:");
		tab3.add(lblFriday, "cell 1 16,alignx left");
		
		JSpinner fridayStartTime = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor fridayStartTimeEditor = new JSpinner.DateEditor(fridayStartTime, "HH:mm");
		fridayStartTime.setEditor(fridayStartTimeEditor);
		tab3.add(fridayStartTime, "cell 2 16");
		
		JSpinner fridayEndTime = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor fridayEndTimeEditor = new JSpinner.DateEditor(fridayEndTime, "HH:mm");
		fridayEndTime.setEditor(fridayEndTimeEditor);		
		tab3.add(fridayEndTime, "cell 3 16");
		
		JLabel lblSaturday = new JLabel("Saturday:");
		tab3.add(lblSaturday, "cell 1 18,alignx left");
		
		JSpinner saturdayStartTime = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor saturdayStartTimeEditor = new JSpinner.DateEditor(saturdayStartTime, "HH:mm");
		saturdayStartTime.setEditor(saturdayStartTimeEditor);
		tab3.add(saturdayStartTime, "cell 2 18");
		
		JSpinner saturdayEndTime = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor saturdayEndTimeEditor = new JSpinner.DateEditor(saturdayEndTime, "HH:mm");
		saturdayEndTime.setEditor(saturdayEndTimeEditor);
		tab3.add(saturdayEndTime, "cell 3 18");
		
		JLabel lblSunday = new JLabel("Sunday:");
		tab3.add(lblSunday, "cell 1 20,alignx left");
		
		JSpinner sundayStartTime = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor sundayStartTimeEditor = new JSpinner.DateEditor(sundayStartTime, "HH:mm");
		sundayStartTime.setEditor(sundayStartTimeEditor);
		tab3.add(sundayStartTime, "cell 2 20");
		
		JSpinner sundayEndTime = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor sundayEndTimeEditor = new JSpinner.DateEditor(sundayEndTime, "HH:mm");
		sundayEndTime.setEditor(sundayEndTimeEditor);
		tab3.add(sundayEndTime, "cell 3 20");
		
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
		Manager m = Manager.getInstance();
		if(smMessage == null || smMessage.length() == 0) {
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
		//TODO: reset ComboBox
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
		if(smMessage.contains("Staff Member role"))
			//TODO: reset role			
		if(smMessage.equals("")) {
			lblStaffMemberMessage.setForeground(Color.GREEN);
			smMessage = "Staff Member was successfully added!";
			resetStaffMemberData();
		}
		
		refreshData();
	}
	
	private void saveScheduleActionPerformed(java.awt.event.ActionEvent evt) {
		
	}
}
