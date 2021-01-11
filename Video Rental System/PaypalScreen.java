package project_test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class PaypalScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JLabel lblCardNumber;
	private JLabel lblNameOnCard;
	private JButton btnBack;
	private JButton btnNewButton;
	private JFrame credit = new JFrame("Credit Info");
	private JLabel errorMsg;
	String name;
	String username;
	private ArrayList<Order> orders;
	double totalCost;
	

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

	/**
	 * Create the frame.
	 */
	public PaypalScreen(String name, String username, ArrayList<Order> orders, double totalCost) {
		this.name = name;
		this.username = username;
		this.orders = orders;
		this.totalCost = totalCost;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
JLabel lblEnterCreditInfo = new JLabel("Enter PayPal Credentials");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		
		errorMsg = new JLabel("");
		errorMsg.setForeground(Color.RED);
		errorMsg.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		errorMsg.setBounds(163, 159, 150, 36);
		contentPane.add(errorMsg);
		
		lblCardNumber = new JLabel("E-mail");
		
		lblNameOnCard = new JLabel("Password");
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CheckoutMain(name, username, orders, totalCost).setVisible(true); 
				
			}
		});
		
		btnNewButton = new JButton("Confirm");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String entrName=textField.getText();
				String entrPass=passwordField.getText();//depreciated method Still works
				boolean authorized=false;
				String line = "";
				String old = "";
				String newLine = "";
				
				line = "";
				try {
					File file1 = new File("./data/Customer.csv");//check if user uses paypal
					FileReader fr1 = new FileReader(file1);
					BufferedReader br1 = new BufferedReader(fr1);
					FileWriter fw = null;
					
					
					while ((line = br1.readLine()) != null) {
						String[] fields1 = line.split(",");
						
						  
						if(entrName.equals(fields1[7])&&entrPass.equals(fields1[2])) {
							int x =Integer.parseInt(fields1[6]);
							x = x+1;
							String loyalty = Integer.toString(x);
						
							
							authorized=true;
							newLine = fields1[0] + "," + fields1[1] + "," + fields1[2] + "," + fields1[3] + "," + fields1[4] + "," + fields1[5] + ","+ loyalty + "," + fields1[7];
							old = old + newLine + System.lineSeparator();
							//open customer panel
						//	dispose();
							// new PaymentSuccessScreen().setVisible(true); 
							
							
						} else{
							old = old + line + System.lineSeparator();
						}
					
					}
					br1.close();
					fw = new FileWriter(file1);
					fw.write (old);
					fw.close();
					if (authorized){
					dispose();
						 new PaymentSuccessScreen(name, username, orders, totalCost).setVisible(true); 
					}
					 
					
				}
				catch (IOException f) {
					f.printStackTrace();
				}
				if(authorized==false) {
					errorMsg.setText("Incorrect Username or Password!");
				}
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNameOnCard)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(141)
							.addComponent(lblEnterCreditInfo))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(50)
							.addComponent(btnBack)
							.addGap(176)
							.addComponent(btnNewButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(38)
							.addComponent(lblCardNumber)
							.addGap(58)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(40)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(164))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(13)
					.addComponent(lblEnterCreditInfo)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(24)
							.addComponent(lblCardNumber))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNameOnCard))
					.addGap(87)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(btnBack))
						.addComponent(btnNewButton)))
		);
		contentPane.setLayout(gl_contentPane);
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(141, Short.MAX_VALUE)
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
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnBack)
									.addGap(5)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(passwordField)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(153)
									.addComponent(btnNewButton)))))
					.addContainerGap(82, Short.MAX_VALUE))
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
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNameOnCard))
					.addPreferredGap(ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnBack)
						.addComponent(btnNewButton))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}

}