package ca.mcgill.ecse321.foodtruckmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Manager;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Button;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class FoodTruckManagementSystemPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEquipment;
	private JTextField textField;
	private JTextField txtT;
	private int equipmentCount = 0;

	/**
	 * Launch the application.
	 */
	public FoodTruckManagementSystemPage() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FoodTruckManagementSystemPage.class.getResource("/ca/mcgill/ecse321/foodtruckmanagementsystem/view/foodtruck.png")));
		initComponents();
		refreshData();
	}

	/**
	 * Create the frame.
	 */
	public void initComponents() {
		setTitle("Food Truck Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 841, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtEquipment = new JTextField();
		txtEquipment.setEditable(false);
		txtEquipment.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		txtEquipment.setText("Equipment:");
		txtEquipment.setColumns(10);
		txtEquipment.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		textField = new JTextField();
		textField.setColumns(10);
		
		txtT = new JTextField();
		txtT.setText("" + equipmentCount);
		txtT.setColumns(10);
		
		JButton button = new JButton("-1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(equipmentCount > 1)
				{
					equipmentCount = Integer.parseInt(txtT.getText()) - 1;
					refreshData();
				}
			}
		});
		
		JButton button_1 = new JButton("+1");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equipmentCount = Integer.parseInt(txtT.getText()) + 1;
				refreshData();
			}
		});	
		
		JButton btnAddEquipment = new JButton("Add Equipment");
		btnAddEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				if(name != null && equipmentCount > 0)
				{
					Manager manager = Manager.getInstance();
					equipmentCount = Integer.parseInt(txtT.getText());
					Equipment equipment = new Equipment(name, equipmentCount);
					manager.addEquipment(equipment);
					System.out.println(manager.getEquipment(0).toString());
					clearEquipmentData();
				}				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtT, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addComponent(button_1))
						.addComponent(txtEquipment, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddEquipment))
					.addContainerGap(284, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtEquipment, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button)
						.addComponent(txtT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddEquipment)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		pack();
	}
	
	private void refreshData()
	{
		txtT.setText("" + equipmentCount);
	}
	
	private void clearEquipmentData()
	{
		equipmentCount = 0;
		textField.setText("");
		refreshData();
	}
}
