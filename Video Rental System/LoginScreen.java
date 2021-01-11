package project_test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;


public class LoginScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel errorMsg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(182, 49, 89, 16);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(153, 77, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(182, 151, 61, 16);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(153, 179, 137, 26);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new RegisterScreen().setVisible(true);
			}
		});
		btnNewButton.setBounds(311, 19, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//LOGIN CODE HERE<----------------------------------------------------------------
				String entrName=textField.getText();
				String entrPass=passwordField.getText();//depreciated method Still works
				boolean operator=false;
				boolean authorized=false;
				String line = "";
				FileReader fr = null;
				BufferedReader br = null;
				try {//check if user is operator
					File file = new File("./data/Operator.csv");
					fr = new FileReader(file);
					br = new BufferedReader(fr);
					line = br.readLine();
					while ((line = br.readLine()) != null) {
						String[] fields = line.split(",");
						
						if(entrName.equals(fields[2])&&entrPass.equals(fields[3])) {
							operator=true;
							authorized=true;
							//open operator panel
							dispose();
							OperatorScreen op= new OperatorScreen();
							op.name=fields[0];
							op.ID= fields[1];
							op.username=fields[2];
							op.password=fields[3];
							op.setVisible(true);
						}
					
					}
					br.close();
					//check if customer
					if(operator==false) {
						line = "";
						FileReader fr1 = null;
						BufferedReader br1 = null;
						try {
							File file1 = new File("./data/Customer.csv");//check if user is operator
							fr1 = new FileReader(file1);
							br1 = new BufferedReader(fr1);
							line = br1.readLine();
							while ((line = br1.readLine()) != null) {
								String[] fields1 = line.split(",");
								
								if(entrName.equals(fields1[1])&&entrPass.equals(fields1[2])) {
									authorized=true;
									//open customer panel
									dispose();
									CustomerScreen cus= new CustomerScreen();
									cus.name=fields1[0];
									cus.username=fields1[1];
									cus.password=fields1[2];
									cus.phoneNumber= fields1[3];
									cus.address=fields1[4];
									cus.ID= fields1[5];
									cus.points=Integer.parseInt(fields1[6]);
									cus.email = fields1[7];
									cus.setVisible(true);
								}
							
							}
							br1.close();
						}
						catch (IOException f) {
							f.printStackTrace();
						}
						
					}
				} catch (IOException z) {
					z.printStackTrace();
				}
				if(authorized==false) {
					errorMsg.setText("Not authorized user!");
				}
				//RESET authorization values
				 operator=false;
				 authorized=false;
			}
		});
		btnNewButton_1.setBounds(311, 222, 117, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("View Videos");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ViewVideos v = new ViewVideos();
				v.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(6, 222, 117, 29);
		contentPane.add(btnNewButton_2);
		
		errorMsg = new JLabel("");
		errorMsg.setForeground(Color.RED);
		errorMsg.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		errorMsg.setBounds(163, 199, 117, 36);
		contentPane.add(errorMsg);
	}
}