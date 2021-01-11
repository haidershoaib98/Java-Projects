package project_test;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CreditScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblCardNumber;
	private JLabel lblNameOnCard;
	private JLabel lblExpiryDate;
	private JLabel lblCvv;
	private JButton btnBack;
	private JButton btnNewButton;
	private JFrame credit = new JFrame("Credit Info");
	private JLabel errorMsg;
	String name;
	String username;
	private ArrayList<Order> orders;
	double totalCost;
	private JLabel error;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CreditScreen frame = new CreditScreen();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public void addLoyalty(String user) {
		String line = "";
		String old = "";
		String newLine = "";
		
		line = "";
		try {
			File file2 = new File("./data/Customer.csv");
			FileReader fr2 = new FileReader(file2);
			BufferedReader br2 = new BufferedReader(fr2);
			FileWriter fw = null;
			
			
			while ((line = br2.readLine()) != null) {
				String[] fields1 = line.split(",");
				
				  
				if(user.equals(fields1[1])) {
					int x =Integer.parseInt(fields1[6]);
					x = x+1;
					String loyalty = Integer.toString(x);
				
					
					newLine = fields1[0] + "," + fields1[1] + "," + fields1[2] + "," + fields1[3] + "," + fields1[4] + "," + fields1[5] + ","+ loyalty + "," + fields1[7];
					old = old + newLine + System.lineSeparator();
					
					
				} else{
					old = old + line + System.lineSeparator();
				}
			
			}
			br2.close();
			fw = new FileWriter(file2);
			fw.write (old);
			fw.close();
			
			 
			
		}
		catch (IOException f) {
			f.printStackTrace();
		}
		
	}
	
	

	/**
	 * Create the frame.
	 */
	public CreditScreen(String name, String username, ArrayList<Order> orders, double totalCost) {
		this.name = name;
		this.username = username;
		this.orders = orders;
		this.totalCost = totalCost;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
JLabel lblEnterCreditInfo = new JLabel("Enter Credit Info");
lblEnterCreditInfo.setBounds(200, 11, 103, 16);
		
		textField = new JTextField();
		textField.setBounds(144, 48, 270, 26);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(144, 92, 270, 26);
		textField_1.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(144, 136, 80, 26);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(298, 136, 67, 26);
		textField_4.setColumns(10);
		
		lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setBounds(43, 53, 83, 16);
		
		lblNameOnCard = new JLabel("Name on Card");
		lblNameOnCard.setBounds(37, 97, 89, 16);
		
		lblExpiryDate = new JLabel("Expiry Date");
		lblExpiryDate.setBounds(51, 141, 72, 16);
		
		lblCvv = new JLabel("CVV");
		lblCvv.setBounds(255, 141, 25, 16);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(51, 232, 75, 29);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CheckoutMain(name, username, orders, totalCost).setVisible(true); 
				
			}
		});
		
		btnNewButton = new JButton("Confirm");
		btnNewButton.setBounds(298, 231, 116, 30);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String entrNumber=textField.getText();
				String entrName=textField_1.getText();//depreciated method Still works
				String entrExpiry=textField_3.getText();
				String entrCvv=textField_4.getText();
				boolean authorized=false;
				String line = "";
				
				
				try {
					File file1 = new File("./data/CreditCard.csv");
					FileReader fr1 = new FileReader(file1);
					BufferedReader br1 = new BufferedReader(fr1);
					String custName = "";
					line = br1.readLine();
					while ((line = br1.readLine()) != null) {
						String[] fields1 = line.split(",");
						
						
						if(entrNumber.equals(fields1[0])&&entrName.equals(fields1[1]) && entrExpiry.equals(fields1[2]) && entrCvv.equals(fields1[3])) {
							authorized=true;
							custName = entrName;
							addLoyalty(username);
							
						}
					
					}
					
				
					br1.close();
					
				}
				catch (IOException f) {
					f.printStackTrace();
				}
				if(authorized==false) {
					error.setText("Incorrect Information!");
				}
				else if(authorized == true) {
					dispose();
					new PaymentSuccessScreen(name, username, orders, totalCost).setVisible(true); 
				}
				
			}
		});
		contentPane.setLayout(null);
		
		error = new JLabel("");
		error.setBounds(219, 185, 165, 16);
		contentPane.add(error);
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(235, Short.MAX_VALUE)
					.addComponent(lblEnterCreditInfo)
					.addGap(192))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addComponent(lblCardNumber)
							.addGap(18)
							.addComponent(textField))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNameOnCard)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(btnBack)
									.addComponent(lblExpiryDate)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_1)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addGap(31)
									.addComponent(lblCvv)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnNewButton)
										.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))))))
					.addContainerGap(76, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEnterCreditInfo)
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCardNumber))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNameOnCard))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblExpiryDate)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCvv)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnBack)
						.addComponent(btnNewButton))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}

}