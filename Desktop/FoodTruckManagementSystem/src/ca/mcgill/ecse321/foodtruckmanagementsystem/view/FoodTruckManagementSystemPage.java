package ca.mcgill.ecse321.foodtruckmanagementsystem.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.ItemController;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Manager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.MenuItem;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.StaffMember;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Supply;

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
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import java.awt.Toolkit;
import javax.swing.ScrollPaneConstants;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.BoxLayout;

public class FoodTruckManagementSystemPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField staffName;	
	
	private JTextField equipmentName;
	private JTextField supplyName;
	private JTextField supplyUnit;
	private JLabel lblSupplyMessage, lblEquipmentMessage, lblStaffMemberMessage, lblscheduleMessage, lblOrderMessage, lblMenuItemMessage;
	private SpinnerNumberModel equipmentModel, supplyModel, menuPriceModel, orderModel;
	private JComboBox<String> staffMemberList, menuItemList;
	private JComboBox<String> staffroleComboBox;
	private HashMap<Integer, StaffMember> staffMembers;
	private HashMap<Integer, MenuItem> menuItems;
	private JPanel staffPanel, supplyPanel, equipmentPanel, popularityPanel;
	private JTextField menuItemName;	
	private ArrayList<JLabel> staffNamesReport;
	private ArrayList<JLabel> staffRolesReport;
	private ArrayList<JLabel> staffMemberNumbers;
	private ArrayList<JLabel> supplyNamesReport;
	private ArrayList<JLabel> supplyQuantitiesReport;
	private ArrayList<JLabel> supplyUnitReport;
	private ArrayList<JLabel> equipmentNamesReport;
	private ArrayList<JLabel> equipmentQuantitiesReport;
	private ArrayList<JLabel> equipmentNumbers;
	private ArrayList<JLabel> popularityNamesReport;
	private ArrayList<JLabel> popularityQuantitiesReport;
	private ArrayList<JLabel> popularityNumbers;
	private ArrayList<JLabel> supplyNumbers;	
	private JSpinner[] startTimes;
	private JSpinner[] endTimes;
	
	private JLabel[] lbldaysOfTheWeek = new JLabel[7];
	private String[] staffRoles = {"Cashier", "Chef", "Manager", "Inventory Clerk"};
	private String[] daysOfTheWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	private String sMessage = "";
	private String eMessage = "";
	private String schedMessage = "";
	private String smMessage = "";
	private String menuItemMessage = "";
	private String orderMessage = "";
	private Integer selectedStaffMember = -1;	
	private Integer selectedMenuItem = -1;	

	public FoodTruckManagementSystemPage() {
		initComponents();
		refreshData();
	}
	
	public void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FoodTruckManagementSystemPage.class.getResource("/ca/mcgill/ecse321/foodtruckmanagementsystem/view/foodtruck.png")));
		setTitle("Food Truck Management System");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 980);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		staffNamesReport = new ArrayList<JLabel>();
		staffRolesReport = new ArrayList<JLabel>();
		staffMemberNumbers = new ArrayList<JLabel>();
		supplyNamesReport = new ArrayList<JLabel>();
		supplyQuantitiesReport = new ArrayList<JLabel>();
		supplyUnitReport = new ArrayList<JLabel>();
		equipmentNamesReport = new ArrayList<JLabel>();
		equipmentQuantitiesReport = new ArrayList<JLabel>();
		equipmentNumbers = new ArrayList<JLabel>();
		popularityNamesReport = new ArrayList<JLabel>();
		popularityQuantitiesReport = new ArrayList<JLabel>();
		popularityNumbers = new ArrayList<JLabel>();
		supplyNumbers = new ArrayList<JLabel>();
		
		startTimes = new JSpinner[7];
		endTimes = new JSpinner[7];
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 34));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1600, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
		);
		
		// creates first tab
		JPanel tab1 = new JPanel();
		tabbedPane.addTab("Main", null, tab1, null);
		tab1.setLayout(new BoxLayout(tab1, BoxLayout.X_AXIS));
		
		JPanel orderPanel = new JPanel();
		orderPanel.setBackground(new Color(0xD3D3D3));
		tab1.add(orderPanel);
		orderPanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][][][][][][][][][][][][][][]"));
		
		JLabel lblMenuItem = new JLabel("Menu Item:");
		lblMenuItem.setFont(new Font("Tahoma", Font.PLAIN, 44));
		orderPanel.add(lblMenuItem, "cell 0 0");
		
		lblMenuItemMessage = new JLabel("");
		lblMenuItemMessage.setFont(new Font("Tahoma", Font.PLAIN, 34));
		orderPanel.add(lblMenuItemMessage, "cell 0 1 27 1,alignx left");
		
		JLabel lblItemName = new JLabel("Item Name:");
		lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 34));
		orderPanel.add(lblItemName, "cell 1 2");
		
		menuItemName = new JTextField();
		menuItemName.setFont(new Font("Tahoma", Font.PLAIN, 34));
		orderPanel.add(menuItemName, "cell 3 2,grow");
		menuItemName.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 34));
		orderPanel.add(lblPrice, "cell 1 4");
		
		menuPriceModel = new SpinnerNumberModel(0.0, 0.0, 1.0E8, 0.0);
		JSpinner menuItemPrice = new JSpinner(menuPriceModel);
		menuItemPrice.setFont(new Font("Tahoma", Font.PLAIN, 34));
		orderPanel.add(menuItemPrice, "cell 3 4,grow");			
		
		// button to add menu item to list
		JButton btnAddToMenu = new JButton("Add to Menu List");
		btnAddToMenu.setBackground(new Color(0x444344));
		btnAddToMenu.setForeground(new Color(0xFFFFFF));
		btnAddToMenu.setFont(new Font("Tahoma", Font.PLAIN, 34));
		btnAddToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				createMenuItemActionPerformed(evt);
			}
		});		
		orderPanel.add(btnAddToMenu, "cell 1 6");
		
		// button to remove menu item from list
		JButton btnRemoveItem = new JButton("Remove Item");
		btnRemoveItem.setBackground(new Color(0x444344));
		btnRemoveItem.setForeground(new Color(0xFFFFFF));
		btnRemoveItem.setFont(new Font("Tahoma", Font.PLAIN, 34));
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeMenuItemActionPerformed(e);
			}
		});
		orderPanel.add(btnRemoveItem, "cell 3 6,alignx left");				
		
		JLabel lblAddOrder = new JLabel("Add Order:");
		lblAddOrder.setFont(new Font("Tahoma", Font.PLAIN, 44));
		orderPanel.add(lblAddOrder, "cell 0 8");
		
		lblOrderMessage = new JLabel("");
		lblOrderMessage.setFont(new Font("Tahoma", Font.PLAIN, 34));
		orderPanel.add(lblOrderMessage, "cell 0 9 27 1");
		
		JLabel lblSelectItemTo = new JLabel("Select Item to Order:");
		lblSelectItemTo.setFont(new Font("Tahoma", Font.PLAIN, 34));
		orderPanel.add(lblSelectItemTo, "cell 1 10");				
		
		// list of menu items in a dropdown box
		menuItemList = new JComboBox<String>(new String[0]);
		menuItemList.setFont(new Font("Tahoma", Font.PLAIN, 34));
		menuItemList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)evt.getSource();
				selectedMenuItem = cb.getSelectedIndex();
			}
		});
		orderPanel.add(menuItemList, "cell 3 10,grow");	
		
		JLabel lblQuantity_2 = new JLabel("Quantity:");
		lblQuantity_2.setFont(new Font("Tahoma", Font.PLAIN, 34));
		orderPanel.add(lblQuantity_2, "cell 1 12");		

		orderModel = new SpinnerNumberModel(0, -100000000, 100000000, 1);
		JSpinner orderQuantity = new JSpinner(orderModel);
		orderQuantity.setFont(new Font("Tahoma", Font.PLAIN, 34));
		orderPanel.add(orderQuantity, "cell 3 12,alignx left,growy");
		
		// button to order food item
		JButton btnOrder = new JButton("Order");
		btnOrder.setBackground(new Color(0x444344));
		btnOrder.setForeground(new Color(0xFFFFFF));
		btnOrder.setFont(new Font("Tahoma", Font.PLAIN, 34));
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createOrderActionPerformed(e);
			}
		});
		orderPanel.add(btnOrder, "cell 1 14");				
		
		// creates second tab
		JPanel tab2 = new JPanel();
		tab2.setBackground(new Color(0xD3D3D3));
		tabbedPane.addTab("Inventory", null, tab2, null);
		MigLayout layout = new MigLayout("", "[][][][grow]", "[][][][][][][][][][][][][][][][][]");
		tab2.setLayout(layout);
		
		JLabel lblAddEquipment = new JLabel("Add Equipment:");
		lblAddEquipment.setFont(new Font("Tahoma", Font.PLAIN, 44));
		tab2.add(lblAddEquipment, "cell 0 0");
		
		lblEquipmentMessage = new JLabel("");
		lblEquipmentMessage.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab2.add(lblEquipmentMessage, "cell 0 1 4 1");
		
		JLabel lblEquipmentName = new JLabel("Equipment name:");
		lblEquipmentName.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab2.add(lblEquipmentName, "cell 0 2 2 1,alignx left");
		
		// accepts user input for the name of the equipment
		equipmentName = new JTextField();
		equipmentName.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab2.add(equipmentName, "flowx,cell 2 2,grow");
		equipmentName.setColumns(10);
		
		JLabel lblEquipmentQuantity = new JLabel("Equipment Quantity:");
		lblEquipmentQuantity.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab2.add(lblEquipmentQuantity, "cell 0 4");	
		
		// accepts user input for the quantity of the equipment
		equipmentModel = new SpinnerNumberModel(0, -100000000, 100000000, 1);
		JSpinner equipmentQuantity = new JSpinner(equipmentModel);
		equipmentQuantity.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab2.add(equipmentQuantity, "cell 2 4,grow");
		
		// button to add equipment item to list
		JButton btnAddEquipment = new JButton("Add Equipment");
		btnAddEquipment.setBackground(new Color(0x444344));
		btnAddEquipment.setForeground(new Color(0xFFFFFF));
		btnAddEquipment.setFont(new Font("Tahoma", Font.PLAIN, 34));
		btnAddEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEquipmentActionPerformed(e);
			}
		});		
		tab2.add(btnAddEquipment, "cell 0 6,alignx right");
		
		// button to remove equipment item from list
		JButton btnRemoveEquipment = new JButton("Remove Equipment");
		btnRemoveEquipment.setBackground(new Color(0x444344));
		btnRemoveEquipment.setForeground(new Color(0xFFFFFF));
		btnRemoveEquipment.setFont(new Font("Tahoma", Font.PLAIN, 34));
		btnRemoveEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeEquipmentActionPerformed(e);
			}
		});
		tab2.add(btnRemoveEquipment, "cell 2 6");
		
		JLabel lblAddSupply = new JLabel("Add Supply:");
		lblAddSupply.setFont(new Font("Tahoma", Font.PLAIN, 44));
		tab2.add(lblAddSupply, "cell 0 8");
		
		lblSupplyMessage = new JLabel("");
		lblSupplyMessage.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab2.add(lblSupplyMessage, "cell 0 9 4 1");
		
		JLabel lblSupplyName = new JLabel("Supply Name:");
		lblSupplyName.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab2.add(lblSupplyName, "cell 0 10");
		
		// accepts user input for name of the supply
		supplyName = new JTextField();
		supplyName.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab2.add(supplyName, "cell 2 10,grow");
		supplyName.setColumns(10);
		
		// accepts user input for the quantity of the supply
		supplyModel = new SpinnerNumberModel(0.0, -1.0E8, 1.0E8, 0.1);
		JLabel lblSupplyQuantity = new JLabel("Supply Quantity:");
		lblSupplyQuantity.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab2.add(lblSupplyQuantity, "cell 0 12");
		JSpinner supplyQuantity = new JSpinner(supplyModel);
		supplyQuantity.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab2.add(supplyQuantity, "cell 2 12,grow");
		
		JLabel lblSupplyUnit = new JLabel("Supply Unit:");
		lblSupplyUnit.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab2.add(lblSupplyUnit, "cell 0 14");
		
		// accepts user input for the unit of the supply
		supplyUnit = new JTextField();
		supplyUnit.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab2.add(supplyUnit, "cell 2 14,grow");
		supplyUnit.setColumns(10);
		
		// button to add supply item to list
		JButton btnAddSupply = new JButton("Add Supply");
		btnAddSupply.setBackground(new Color(0x444344));
		btnAddSupply.setForeground(new Color(0xFFFFFF));
		btnAddSupply.setFont(new Font("Tahoma", Font.PLAIN, 34));
		btnAddSupply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addSupplyActionPerformed(e);
			}
		});		
		tab2.add(btnAddSupply, "cell 0 16,alignx right");
		
		// button to remove supply item from list
		JButton btnRemoveSupply = new JButton("Remove Supply");
		btnRemoveSupply.setBackground(new Color(0x444344));
		btnRemoveSupply.setForeground(new Color(0xFFFFFF));
		btnRemoveSupply.setFont(new Font("Tahoma", Font.PLAIN, 34));
		btnRemoveSupply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeSupplyActionPerformed(e);
			}
		});
		tab2.add(btnRemoveSupply, "cell 2 16");
		
		// creates third tab
		JPanel tab3 = new JPanel();
		tab3.setBackground(new Color(0xD3D3D3));
		tabbedPane.addTab("Staff", null, tab3, null);
		tab3.setLayout(new MigLayout("", "[][][][][][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblAddStaff = new JLabel("Add New Staff:");
		lblAddStaff.setFont(new Font("Tahoma", Font.PLAIN, 44));
		tab3.add(lblAddStaff, "cell 0 0");
		
		lblStaffMemberMessage = new JLabel("");
		lblStaffMemberMessage.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab3.add(lblStaffMemberMessage, "cell 0 1 8 1");
		
		JLabel lblStaffName = new JLabel("Staff Name:");
		lblStaffName.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab3.add(lblStaffName, "flowx,cell 0 2,alignx trailing");
		
		// accepts user input for the name of the staff member
		staffName = new JTextField();
		staffName.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab3.add(staffName, "cell 1 2,grow");
		staffName.setColumns(10);
		
		JLabel lblSelectRole = new JLabel("Select Role:");
		lblSelectRole.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab3.add(lblSelectRole, "cell 2 2");
		
		// stores staff member roles in a dropdown box
		staffroleComboBox = new JComboBox<String>(staffRoles);
		staffroleComboBox.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab3.add(staffroleComboBox, "cell 3 2,grow");
		
		// button to add staff member to list
		JButton btnAddStaff = new JButton("Add");
		btnAddStaff.setBackground(new Color(0x444344));
		btnAddStaff.setForeground(new Color(0xFFFFFF));
		btnAddStaff.setFont(new Font("Tahoma", Font.PLAIN, 34));
		btnAddStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStaffMemberActionPerformed(e);
			}
		});
		tab3.add(btnAddStaff, "cell 5 2,alignx left");		
		
		JLabel lblRegisterStaffsWeekly = new JLabel("Register Staff's Weekly Schedule:");
		lblRegisterStaffsWeekly.setFont(new Font("Tahoma", Font.PLAIN, 44));
		tab3.add(lblRegisterStaffsWeekly, "cell 0 4 3 1");
		
		lblscheduleMessage = new JLabel("");
		lblscheduleMessage.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab3.add(lblscheduleMessage, "cell 0 5 8 1");
		
		JLabel lblSelectStaffName = new JLabel("Select Staff Name:");
		lblSelectStaffName.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab3.add(lblSelectStaffName, "cell 1 6,alignx trailing");
		
		// stores the list of staff members in a dropdown box
		staffMemberList = new JComboBox<String>(new String[0]);
		staffMemberList.setFont(new Font("Tahoma", Font.PLAIN, 34));
		staffMemberList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)evt.getSource();
				selectedStaffMember = cb.getSelectedIndex();
			}
		});
		tab3.add(staffMemberList, "cell 2 6 2 1,growx");
		
		// button to remove staff member from list
		JButton btnRemoveStaff = new JButton("Remove Staff");
		btnRemoveStaff.setBackground(new Color(0x444344));
		btnRemoveStaff.setForeground(new Color(0xFFFFFF));
		btnRemoveStaff.setFont(new Font("Tahoma", Font.PLAIN, 34));
		btnRemoveStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteStaffMemberActionPerformed(e);
			}
		});
		tab3.add(btnRemoveStaff, "cell 4 6 2 1");
		
		JLabel lblStartTime = new JLabel("Start Time");
		lblStartTime.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab3.add(lblStartTime, "cell 2 7,alignx left");
		
		JLabel lblEndTime = new JLabel("End Time");
		lblEndTime.setFont(new Font("Tahoma", Font.PLAIN, 34));
		tab3.add(lblEndTime, "cell 3 7");
		
		// creates seven labels for each day of the week
		for(int i = 0; i < daysOfTheWeek.length; i++) {
			lbldaysOfTheWeek[i] = new JLabel(daysOfTheWeek[i] + ":");
			lbldaysOfTheWeek[i].setFont(new Font("Tahoma", Font.PLAIN, 34));
			tab3.add(lbldaysOfTheWeek[i], "flowx,cell 1 " + (8+(2*i)) + ",alignx left");
		}
		
		Calendar cal = Calendar.getInstance();
		cal.set(0, 0, 0, 0, 0, 0);	
		
		JSpinner.DateEditor[] startTimeEditors = new JSpinner.DateEditor[7];
		// creates JSpinner for every day's startTime
		for(int i = 0; i < startTimes.length; i++) {
			startTimes[i] = new JSpinner(new SpinnerDateModel());
			startTimeEditors[i] = new JSpinner.DateEditor(startTimes[i], "HH:mm");
			startTimes[i].setEditor(startTimeEditors[i]);
			startTimes[i].setFont(new Font("Tahoma", Font.PLAIN, 34));
			startTimes[i].setValue(cal.getTime());
			tab3.add(startTimes[i], "cell 2 " + (8+(2*i)) +",alignx left");
		}
		
		JSpinner.DateEditor[] endTimeEditors = new JSpinner.DateEditor[7];
		// creates JSpinner for every day's endTime
		for(int i = 0; i < startTimes.length; i++) {
			endTimes[i] = new JSpinner(new SpinnerDateModel());
			endTimeEditors[i] = new JSpinner.DateEditor(endTimes[i], "HH:mm");
			endTimes[i].setEditor(endTimeEditors[i]);
			endTimes[i].setFont(new Font("Tahoma", Font.PLAIN, 34));
			endTimes[i].setValue(cal.getTime());
			tab3.add(endTimes[i], "cell 3 " +(8+(2*i)) + ",aligny center");
		}		
		
		// button to save staff member's schedule to list
		JButton btnSaveSchedule = new JButton("Save");
		btnSaveSchedule.setBackground(new Color(0x444344));
		btnSaveSchedule.setForeground(new Color(0xFFFFFF));
		btnSaveSchedule.setFont(new Font("Tahoma", Font.PLAIN, 34));
		btnSaveSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				saveScheduleActionPerformed(evt);
			}
		});
		tab3.add(btnSaveSchedule, "cell 1 22");
		
		// creates fourth tab
		JPanel tab4 = new JPanel();
		tabbedPane.addTab("Report", null, tab4, null);
		tab4.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JScrollPane staffPane = new JScrollPane();
		staffPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tab4.add(staffPane, "1, 1, 2, 2, fill, fill");
		
		// creates a panel for staff member report
		staffPanel = new JPanel();
		staffPanel.setBackground(new Color(0xBCB7C1));
		staffPane.setViewportView(staffPanel);
		staffPanel.setLayout(new MigLayout("", "[][][][][]", "[][][][][][][]"));
		
		JLabel lblStaffList = new JLabel("Staff List: ");
		lblStaffList.setFont(new Font("Tahoma", Font.PLAIN, 34));
		staffPanel.add(lblStaffList, "cell 0 0");
		
		JLabel lblStaffNumber = new JLabel("#");
		lblStaffNumber.setFont(new Font("Tahoma", Font.PLAIN, 34));
		staffPanel.add(lblStaffNumber, "cell 0 2,alignx right");
		
		JLabel lblStaffName_1 = new JLabel("Staff Name: ");
		lblStaffName_1.setFont(new Font("Tahoma", Font.PLAIN, 34));
		staffPanel.add(lblStaffName_1, "cell 2 2");
		
		JLabel lblStaffRole = new JLabel("Staff Role: ");
		lblStaffRole.setFont(new Font("Tahoma", Font.PLAIN, 34));
		staffPanel.add(lblStaffRole, "cell 4 2");
		
		JScrollPane popularityPane = new JScrollPane();
		popularityPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tab4.add(popularityPane, "3, 1, 2, 2, fill, fill");
		
		// creates a panel for the popularity report
		popularityPanel = new JPanel();
		popularityPanel.setBackground(new Color(0xE0FFFF));
		popularityPane.setViewportView(popularityPanel);
		popularityPanel.setLayout(new MigLayout("", "[][][][][]", "[][][][]"));
		
		JLabel lblPopularityList = new JLabel("Popluarity List:");
		lblPopularityList.setFont(new Font("Tahoma", Font.PLAIN, 34));
		popularityPanel.add(lblPopularityList, "cell 0 0");
		
		JLabel lblPopularityRank = new JLabel("Rank");
		lblPopularityRank.setFont(new Font("Tahoma", Font.PLAIN, 34));
		popularityPanel.add(lblPopularityRank, "cell 0 2,alignx right");
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 34));
		popularityPanel.add(lblName, "cell 2 2");
		
		JLabel lblOrderQuantity = new JLabel("Order Quantity: ");
		lblOrderQuantity.setFont(new Font("Tahoma", Font.PLAIN, 34));
		popularityPanel.add(lblOrderQuantity, "cell 4 2");
		
		JScrollPane supplyPane = new JScrollPane();
		supplyPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tab4.add(supplyPane, "1, 3, 2, 2, fill, fill");
		
		// creates a panel for the supply report
		supplyPanel = new JPanel();
		supplyPanel.setBackground(new Color(0xD3D3D3));
		supplyPane.setViewportView(supplyPanel);
		supplyPanel.setLayout(new MigLayout("", "[][][][][][][]", "[][][][]"));
		
		JLabel lblSupplyList = new JLabel("Supply List:");
		lblSupplyList.setFont(new Font("Tahoma", Font.PLAIN, 34));
		supplyPanel.add(lblSupplyList, "cell 0 0");
		
		JLabel lblNumber = new JLabel("#");
		lblNumber.setFont(new Font("Tahoma", Font.PLAIN, 34));
		supplyPanel.add(lblNumber, "cell 0 2,alignx right");
		
		JLabel lblName_1 = new JLabel("Name: ");
		lblName_1.setFont(new Font("Tahoma", Font.PLAIN, 34));
		supplyPanel.add(lblName_1, "cell 2 2");
		
		JLabel lblQuantity = new JLabel("Quantity: ");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 34));
		supplyPanel.add(lblQuantity, "cell 4 2");
		
		JLabel lblUnit = new JLabel("Unit: ");
		lblUnit.setFont(new Font("Tahoma", Font.PLAIN, 34));
		supplyPanel.add(lblUnit, "cell 6 2");
		
		JScrollPane equipmentPane = new JScrollPane();
		equipmentPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tab4.add(equipmentPane, "3, 3, 2, 2, fill, fill");
		
		// creates a panel for the equipment report
		equipmentPanel = new JPanel();
		equipmentPanel.setBackground(new Color(0xFFF0F5));
		equipmentPane.setViewportView(equipmentPanel);
		equipmentPanel.setLayout(new MigLayout("", "[][][][][]", "[][][]"));
		
		JLabel lblEquipmentList = new JLabel("Equipment List:");
		lblEquipmentList.setFont(new Font("Tahoma", Font.PLAIN, 34));
		equipmentPanel.add(lblEquipmentList, "cell 0 0");
		
		JLabel lblequipmentNumber = new JLabel("#");
		lblequipmentNumber.setFont(new Font("Tahoma", Font.PLAIN, 34));
		equipmentPanel.add(lblequipmentNumber, "cell 0 2,alignx right");
		
		JLabel lblName_2 = new JLabel("Name:");
		lblName_2.setFont(new Font("Tahoma", Font.PLAIN, 34));
		equipmentPanel.add(lblName_2, "cell 2 2");
		
		JLabel lblQuantity_1 = new JLabel("Quantity:");
		lblQuantity_1.setFont(new Font("Tahoma", Font.PLAIN, 34));
		equipmentPanel.add(lblQuantity_1, "cell 4 2");
		
		contentPane.setLayout(gl_contentPane);
	}
	
	// refreshes the data that is displayed
	public void refreshData() {
		// refreshes the error/confirmation messages
		lblMenuItemMessage.setText(menuItemMessage);
		lblOrderMessage.setText(orderMessage);
		lblEquipmentMessage.setText(eMessage);
		lblSupplyMessage.setText(sMessage);
		lblStaffMemberMessage.setText(smMessage);
		lblscheduleMessage.setText(schedMessage);		
		Manager m = Manager.getInstance();
		// refreshes the staff member dropdown box
		if(smMessage.contains("successfully") || smMessage.contains("")) {
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
		// refreshes the menu item dropdown box
		if(menuItemMessage.contains("successfully") || menuItemMessage.contains("")) {
			menuItems = new HashMap<Integer, MenuItem>();
			menuItemList.removeAllItems();
			Iterator<MenuItem> miIt = m.getMenus().iterator();
			Integer index = 0;
			while(miIt.hasNext())
			{
				MenuItem mi = miIt.next();
				menuItems.put(index, mi);
				menuItemList.addItem(mi.getName());
				index++;
			}
			selectedMenuItem = -1;
			menuItemList.setSelectedIndex(selectedMenuItem);
		}
		// loads the various reports
		loadStaffMemberReport();
		loadSupplyReport();
		loadEquipmentReport();
		loadPopularityReport();
		
		pack();
	}
	
	// resets the equipment input fields
	private void resetEquipmentData() {
		equipmentName.setText("");
		equipmentModel.setValue(0);
	}
	
	// resets the supply input fields
	private void resetSupplyData() {
		supplyName.setText("");
		supplyUnit.setText("");
		supplyModel.setValue(0.0);
	}
	
	// resets the staff member input field
	private void resetStaffMemberData() {
		staffName.setText("");
		staffroleComboBox.setSelectedIndex(0);
	}
	
	// resets the menu item input field
	private void resetMenuItemData() {
		menuItemName.setText("");
		menuPriceModel.setValue(0.0);
	}
	
	// refresh staff member schedule input fields
	private void resetScheduleData() {
		Calendar cal = Calendar.getInstance();
		cal.set(0,0,0,0,0,0);
		for(int i = 0; i < startTimes.length; i++) {
			startTimes[i].setValue(cal.getTime());
			endTimes[i].setValue(cal.getTime());
		}
	}
	
	// refresh order input fields
	private void resetOrderData() {
		menuItemList.setSelectedIndex(-1);
		orderModel.setValue(0);
	}
	
	// load report for staff members
	private void loadStaffMemberReport() {
		Manager m = Manager.getInstance();
		
		for(int i = 0; i < staffNamesReport.size(); i++) {
			staffPanel.remove(staffNamesReport.get(i));
			staffPanel.remove(staffRolesReport.get(i));
			staffPanel.remove(staffMemberNumbers.get(i));
		}
			
		staffNamesReport.clear();
		staffRolesReport.clear();
		staffMemberNumbers.clear();
		int index = 0;
		for(StaffMember sm : m.getStaffmembers()) {
			JLabel name = new JLabel(sm.getName());
			name.setFont(new Font("Tahoma", Font.PLAIN, 34));
			staffNamesReport.add(name);
			JLabel role = new JLabel(sm.getRole());
			role.setFont(new Font("Tahoma", Font.PLAIN, 34));
			staffRolesReport.add(role);
			staffPanel.add(staffNamesReport.get(index), "cell 2 " + (3+index));
			staffPanel.add(staffRolesReport.get(index), "cell 4 " + (3+index));
			if(index < 9) {
				JLabel num = new JLabel("0" + (index+1));
				num.setFont(new Font("Tahoma", Font.PLAIN, 34));
				staffMemberNumbers.add(num);
				staffPanel.add(staffMemberNumbers.get(index), "cell 0 " + (3+index) + ",alignx right");
			}
			else {
				JLabel num = new JLabel("" + index);
				num.setFont(new Font("Tahoma", Font.PLAIN, 34));
				staffMemberNumbers.add(num);
				staffPanel.add(staffMemberNumbers.get(index), "cell 0 " + (3+index) + ",alignx right");
			}	
			index++;
		}
	}
	
	// load report for supply items
	private void loadSupplyReport() {
		Manager m = Manager.getInstance();
		
		for(int i = 0; i < supplyNamesReport.size(); i++) {
			supplyPanel.remove(supplyNamesReport.get(i));
			supplyPanel.remove(supplyQuantitiesReport.get(i));
			supplyPanel.remove(supplyUnitReport.get(i));
			supplyPanel.remove(supplyNumbers.get(i));
		}

		supplyNamesReport.clear();
		supplyQuantitiesReport.clear();
		supplyUnitReport.clear();
		supplyNumbers.clear();
		int index = 0;
		for(Supply s : m.getSupplies()) {
			JLabel name = new JLabel(s.getName());
			name.setFont(new Font("Tahoma", Font.PLAIN, 34));
			supplyNamesReport.add(name);
			JLabel quantity = new JLabel("" + s.getQuantity());
			quantity.setFont(new Font("Tahoma", Font.PLAIN, 34));
			supplyQuantitiesReport.add(quantity);
			JLabel unit = new JLabel(s.getUnit());
			unit.setFont(new Font("Tahoma", Font.PLAIN, 34));
			supplyUnitReport.add(unit);
			supplyPanel.add(supplyNamesReport.get(index), "cell 2 " + (3+index));
			supplyPanel.add(supplyQuantitiesReport.get(index), "cell 4 " + (3+index));
			supplyPanel.add(supplyUnitReport.get(index), "cell 6 " + (3+index));
			if(index < 9) {
				JLabel num = new JLabel("0" + (index+1));
				num.setFont(new Font("Tahoma", Font.PLAIN, 34));
				supplyNumbers.add(num);
				supplyPanel.add(supplyNumbers.get(index), "cell 0 " + (3+index) + ",alignx right");
			}
			else {
				JLabel num = new JLabel("" + index);
				num.setFont(new Font("Tahoma", Font.PLAIN, 34));
				supplyNumbers.add(num);
				supplyPanel.add(supplyNumbers.get(index), "cell 0 " + (3+index) + ",alignx right");
			}	
			index++;
		}
	}
	
	// load report for equipment items
	private void loadEquipmentReport() {
		Manager m = Manager.getInstance();
		
		for(int i = 0; i < equipmentNamesReport.size(); i++) {
			equipmentPanel.remove(equipmentNamesReport.get(i));
			equipmentPanel.remove(equipmentQuantitiesReport.get(i));
			equipmentPanel.remove(equipmentNumbers.get(i));
		}			

		equipmentNamesReport.clear();
		equipmentQuantitiesReport.clear();
		equipmentNumbers.clear();
		int index = 0;
		for(Equipment e : m.getEquipments()) {
			JLabel name = new JLabel(e.getName());
			name.setFont(new Font("Tahoma", Font.PLAIN, 34));
			equipmentNamesReport.add(name);
			JLabel quantity = new JLabel("" + e.getQuantity());
			quantity.setFont(new Font("Tahoma", Font.PLAIN, 34));
			equipmentQuantitiesReport.add(quantity);
			equipmentPanel.add(equipmentNamesReport.get(index), "cell 2 " + (3+index));
			equipmentPanel.add(equipmentQuantitiesReport.get(index), "cell 4 " + (3+index));
			if(index < 9) {
				JLabel num = new JLabel("0" + (index+1));
				num.setFont(new Font("Tahoma", Font.PLAIN, 34));
				equipmentNumbers.add(num);
				equipmentPanel.add(equipmentNumbers.get(index), "cell 0 " + (3+index) + ",alignx right");
			}
			else {
				JLabel num = new JLabel("" + index);
				num.setFont(new Font("Tahoma", Font.PLAIN, 34));
				equipmentNumbers.add(num);
				equipmentPanel.add(equipmentNumbers.get(index), "cell 0 " + (3+index) + ",alignx right");
			}	
			index++;
		}		
	}
	
	// load report for popularity of menu items
	private void loadPopularityReport() {
		Manager m = Manager.getInstance();		
		for(int i = 0; i < popularityNamesReport.size(); i++) {
			popularityPanel.remove(popularityNamesReport.get(i));
			popularityPanel.remove(popularityQuantitiesReport.get(i));
			popularityPanel.remove(popularityNumbers.get(i));
		}
		
		popularityNamesReport.clear();
		popularityQuantitiesReport.clear();
		popularityNumbers.clear();
		
		ArrayList<MenuItem> mi = new ArrayList<MenuItem>(m.getMenus());
		Collections.sort(mi, new Comparator<MenuItem>() {
			@Override
			public int compare(MenuItem mi1, MenuItem mi2) {
				return mi2.getPopularityCounter() - mi1.getPopularityCounter();
			}
		});
		
		for(int i = 0; i < mi.size(); i++) {
			JLabel name = new JLabel(mi.get(i).getName());
			JLabel quantity = new JLabel("" + mi.get(i).getPopularityCounter());
			name.setFont(new Font("Tahoma", Font.PLAIN, 34));
			quantity.setFont(new Font("Tahoma", Font.PLAIN, 34));
			popularityNamesReport.add(name);
			popularityQuantitiesReport.add(quantity);
			popularityPanel.add(popularityNamesReport.get(i), "cell 2 " + (3+i) + ",grow");
			popularityPanel.add(popularityQuantitiesReport.get(i), "cell 4 " + (3+i));
			if(i < 9) {
				JLabel num = new JLabel("0" + (i+1));
				num.setFont(new Font("Tahoma", Font.PLAIN, 34));
				popularityNumbers.add(num);
				popularityPanel.add(popularityNumbers.get(i), "cell 0 " + (3+i) + ",alignx right");
			}
			else {
				JLabel num = new JLabel("" + i);
				num.setFont(new Font("Tahoma", Font.PLAIN, 34));
				popularityNumbers.add(num);
				popularityPanel.add(popularityNumbers.get(i), "cell 0 " + (3+i) + ",alignx right");
			}	
		}	
	}
	
	// add equipment item when btnAddEquipment is pressed
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
			lblEquipmentMessage.setForeground(new Color(0x228B22));
			eMessage = "Equipment item successfully added!";
			resetEquipmentData();
		}
		
		refreshData();
	}
	
	// remove equipment item when btnRemoveEquipment is pressed
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
			lblEquipmentMessage.setForeground(new Color(0x228B22));
			eMessage = "Equipment item successfully removed!";
			resetEquipmentData();
		}
		
		refreshData();
	}
	
	// add supply item when btnAddSupply is pressed
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
			lblSupplyMessage.setForeground(new Color(0x228B22));
			sMessage = "Supply item successfully added!";
			resetSupplyData();
		}
		
		refreshData();
	}
	
	// remove supply item when btnRemoveSupply is pressed
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
			lblSupplyMessage.setForeground(new Color(0x228B22));
			sMessage = "Supply item successfully removed!";
			resetSupplyData();
		}
		
		refreshData();
	}
	
	// add staff member when btnAddStaff is pressed
	private void addStaffMemberActionPerformed(java.awt.event.ActionEvent evt) {
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
			staffroleComboBox.setSelectedIndex(-1);
		
		if(smMessage.equals("")) {
			lblStaffMemberMessage.setForeground(new Color(0x228B22));
			smMessage = "Staff Member was successfully added!";
			resetStaffMemberData();
		}
		
		refreshData();
	}
	
	// save staff member schedule when btnSaveSchedule is pressed
	private void saveScheduleActionPerformed(java.awt.event.ActionEvent evt) {
		StaffMember staffMember = new StaffMember("","");
		ItemController c = new ItemController();
		Time[] startTimeArray = new Time[7];
		Time[] endTimeArray = new Time[7];
		schedMessage = "";
		
		if(selectedStaffMember != -1)
			staffMember = staffMembers.get(selectedStaffMember);
				
		for(int i = 0; i < startTimes.length; i++) { 
			Calendar cal = Calendar.getInstance();
			cal.setTime((Date) startTimes[i].getValue());
			Time startTime = new Time(cal.getTime().getTime());
			startTimeArray[i] = startTime;
			cal.setTime((Date) endTimes[i].getValue());
			Time endTime = new Time(cal.getTime().getTime());			
			endTimeArray[i] = endTime;
		}
				
		try {
			c.addTimeStaffMember(staffMember.getName(), startTimeArray, endTimeArray);
		} catch (InvalidInputException e) {
			lblscheduleMessage.setForeground(Color.RED);
			schedMessage = e.getMessage();
		}
		
		if(schedMessage.equals("")) {
			lblscheduleMessage.setForeground(new Color(0x228B22));
			schedMessage = "Staff Member Schdule was successfully updated!";
			resetScheduleData();
		}

		refreshData();
	}
	
	// delete staff member when btnRemoveStaff is pressed
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
			lblscheduleMessage.setForeground(new Color(0x228B22));
		}
		
		refreshData();		
	}
	
	// create menu item when btnAddToMenu is pressed
	private void createMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
		ItemController c = new ItemController();
		menuItemMessage = "";
		
		try {
			c.createMenuItem(menuItemName.getText(), menuPriceModel.getNumber().doubleValue());
		} catch (InvalidInputException e) {
			menuItemMessage = e.getMessage();
			lblMenuItemMessage.setForeground(Color.RED);			
		}
		
		if(menuItemMessage.contains("name")) 
			menuItemName.setText("");				
		if(menuItemMessage.equals("")) {
			lblMenuItemMessage.setForeground(new Color(0x228B22));
			menuItemMessage = "Menu Item was successfully added!";
			resetMenuItemData();
		}
		
		refreshData();		
	}
	
	// remove menu item when btnRemoveItem is pressed 
	private void removeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
		ItemController c = new ItemController();
		menuItemMessage = "";
		
		try {
			c.removeMenuItem(menuItemName.getText());
		} catch (InvalidInputException e) {
			menuItemMessage = e.getMessage();
			lblMenuItemMessage.setForeground(Color.RED);			
		}
		
		if(menuItemMessage.contains("name")) 
			menuItemName.setText("");
		if(menuItemMessage.equals("")) {
			lblMenuItemMessage.setForeground(new Color(0x228B22));
			menuItemMessage = "Menu Item was successfully removed!";
			resetMenuItemData();
		}
		
		refreshData();
	}
	
	// create order when btnOrder is pressed
	private void createOrderActionPerformed(java.awt.event.ActionEvent evt) {
		ItemController c = new ItemController();
		MenuItem mi = new MenuItem("", 0.0, 0);
		orderMessage = "";
		
		if(selectedMenuItem != -1) 
			mi = menuItems.get(selectedMenuItem);
		
		try {
			c.menuItemOrdered(mi.getName(), mi.getPopularityCounter() + orderModel.getNumber().intValue());
		} catch (InvalidInputException e) {
			orderMessage = e.getMessage();
			lblOrderMessage.setForeground(Color.RED);
		}
		
		if(orderMessage.contains("name")) {
			selectedMenuItem = -1;
			menuItemList.setSelectedIndex(selectedMenuItem);
		}
		if(orderMessage.contains("quantity"))
			orderModel.setValue(0);		
		if(orderMessage.equals("")) {
			lblOrderMessage.setForeground(new Color(0x228B22));
			orderMessage = "Order was successfully placed!";
			resetOrderData();
		}
		
		refreshData();
	}
}