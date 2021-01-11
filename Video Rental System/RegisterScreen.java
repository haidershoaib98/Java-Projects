package project_test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class RegisterScreen extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField username;
	private JTextField phonenumber;
	private JTextField address;
	private JTextField pw;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterScreen frame = new RegisterScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ArrayList<String> getCustomers() {
		String line = "";
		String temp = "";
		ArrayList<String> cus = new ArrayList<String>();

		try {
			File file = new File("./data/Customer.csv");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(",");

				temp = "Name: " + fields[0] + ", Username: " + fields[1] + ", Password: " + fields[2]
						+ ", Phone Number: " + fields[3] + ", Address: " + fields[4] + ", ID: " + fields[5]
						+ ", Loyalty Points: " + fields[6] + ", email: " + fields[7];
				cus.add(temp);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cus;

	}

	public void writeCustomers(String name, String username, String pw, String phone, String address, int ID,
			int points, String email) {
		String line = "";
		String s1 = "";
		FileWriter fw = null;
		try {
			File file = new File("./data/Customer.csv");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				s1 = s1 + line + System.lineSeparator();
			}
			s1 = s1 + name + "," + username + "," + pw + "," + phone + "," + address + "," + ID + "," + points + "," + email
					+ System.lineSeparator();
			fw = new FileWriter(file);
			fw.write(s1);
			br.close();
			fw.close();
			fr.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public RegisterScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(193, 36, 61, 16);
		contentPane.add(lblNewLabel);

		name = new JTextField();
		name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		name.setBounds(156, 64, 130, 26);
		contentPane.add(name);
		name.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(182, 102, 93, 16);
		contentPane.add(lblNewLabel_1);

		username = new JTextField();
		username.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		username.setBounds(156, 130, 130, 26);
		contentPane.add(username);
		username.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(182, 168, 61, 16);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Phone Number");
		lblNewLabel_3.setBounds(177, 237, 93, 16);
		contentPane.add(lblNewLabel_3);

		phonenumber = new JTextField();
		phonenumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		phonenumber.setBounds(156, 260, 130, 26);
		contentPane.add(phonenumber);
		phonenumber.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setBounds(193, 297, 61, 16);
		contentPane.add(lblNewLabel_4);

		address = new JTextField();
		address.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		address.setBounds(156, 325, 130, 26);
		contentPane.add(address);
		address.setColumns(10);

		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Grab all user input
				String gotName = name.getText();
				String gotUser = username.getText();
				String gotPass = pw.getText();
				String gotPhone = phonenumber.getText();
				String gotAdd = address.getText();
				String gotEmail = textField.getText();
				Boolean flag1 = true;

				// Error checking to make sure all fields were entered
				if (name.getText().equals("") || username.getText().equals("") || pw.getText().equals("")
						|| phonenumber.getText().equals("") || address.getText().equals("")
						|| textField.getText().equals("")) {
					flag1 = false;
					lblNewLabel_7.setText("Enter all info!");

				}
				// Make sure the user name is unique and password is at least 8 characters long
				Boolean flag = true;
				int idCount = 1;
				if (flag1 == true) {
					if (gotPass.length() > 7) {
						lblNewLabel_6.setText("");

						if (gotPhone.matches("^[0-9]{10}$")) {
							lblNewLabel_8.setText("");

							ArrayList<String> v = getCustomers();
							for (int j = 0; j < v.size(); j++) {
								idCount++;
								if (v.get(j).contains("Username: " + gotUser)) {
									flag = false;
									lblNewLabel_5.setText("Username exists!");
									lblNewLabel_7.setText("Error!");
									break;

								} else {
									lblNewLabel_5.setText("");

								}

							}
						} else {
							flag = false;
							lblNewLabel_8.setText("Should be: xxxxxxxxxx");
							lblNewLabel_7.setText("Error!");

						}

						if (flag == true) {
							lblNewLabel_7.setText("Success!");
							writeCustomers(gotName, gotUser, gotPass, gotPhone, gotAdd, idCount, 0, gotEmail);

						}
					} else {
						lblNewLabel_6.setText("At least 8 characters!");
						lblNewLabel_7.setText("Error!");
					}

				}

			}
		});
		btnNewButton.setBounds(316, 431, 117, 29);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Go back to login page
				dispose();
				new LoginScreen().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(22, 16, 117, 29);
		contentPane.add(btnNewButton_1);

		pw = new JTextField();
		pw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pw.setBounds(156, 196, 130, 26);
		contentPane.add(pw);
		pw.setColumns(10);

		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_5.setBounds(305, 130, 100, 37);
		contentPane.add(lblNewLabel_5);

		lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_6.setBounds(303, 177, 130, 67);
		contentPane.add(lblNewLabel_6);

		lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setForeground(Color.GREEN);
		lblNewLabel_7.setBounds(344, 21, 89, 16);
		contentPane.add(lblNewLabel_7);

		lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_8.setBounds(298, 266, 120, 16);
		contentPane.add(lblNewLabel_8);

		JButton btnNewButton_2 = new JButton("Call Operator");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_9.setText("Call: 647-620-9896");
			}
		});
		btnNewButton_2.setBounds(22, 64, 117, 29);
		contentPane.add(btnNewButton_2);

		lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setForeground(Color.RED);
		lblNewLabel_9.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_9.setBounds(32, 103, 121, 16);
		contentPane.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Email");
		lblNewLabel_10.setBounds(193, 369, 61, 16);
		contentPane.add(lblNewLabel_10);

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textField.setBounds(156, 397, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
