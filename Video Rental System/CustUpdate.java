package project_test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CustUpdate extends JFrame {

	private JPanel contentPane;
	String username;
	private JTextField user;
	private JTextField pass;
	private JTextField addr;
	private JTextField phone;
	private JLabel errorMsg;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_4;
	private JTextField emailField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UpdateProfile frame = new UpdateProfile();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public Boolean uniqueUser(String userN) {
		String line = "";
		String temp = "";
		try {
			File file = new File("./data/Customer.csv");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(",");
				if (fields[1].equals(userN)) {
					return false;
				}

			}

			FileWriter fw = new FileWriter(file);
			fw.write(temp);
			br.close();
			fw.close();
		} catch (IOException z) {
			z.printStackTrace();
		}

		return true;
	}

	/**
	 * Create the frame.
	 */
	public CustUpdate(String username) {
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Change username:");
		lblNewLabel.setBounds(54, 32, 116, 16);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Change Password:");
		lblNewLabel_1.setBounds(54, 85, 122, 16);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Change address:");
		lblNewLabel_2.setBounds(54, 140, 116, 16);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Change Phone Number:");
		lblNewLabel_3.setBounds(54, 194, 147, 16);
		contentPane.add(lblNewLabel_3);

		user = new JTextField();
		user.setBounds(267, 27, 130, 26);
		contentPane.add(user);
		user.setColumns(10);

		pass = new JTextField();
		pass.setBounds(267, 80, 130, 26);
		contentPane.add(pass);
		pass.setColumns(10);

		addr = new JTextField();
		addr.setBounds(267, 135, 130, 26);
		contentPane.add(addr);
		addr.setColumns(10);

		phone = new JTextField();
		phone.setBounds(267, 189, 130, 26);
		contentPane.add(phone);
		phone.setColumns(10);

		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Grab all user input
				String gotUser = user.getText();
				String gotPass = pass.getText();
				String gotPhone = phone.getText();
				String gotAdd = addr.getText();

				String line = "";
				String temp = "";
				String s = "";
				FileReader fr = null;
				BufferedReader br = null;
				FileWriter fw = null;
				try {
					File file = new File("./data/Customer.csv");
					fr = new FileReader(file);
					br = new BufferedReader(fr);
					while ((line = br.readLine()) != null) {
						String[] fields = line.split(",");
						if (username.equals(fields[1])) {
							if (gotPass.length() > 7 && (uniqueUser(gotUser).equals(true))) {
								if (!gotUser.isEmpty() && !gotPass.isEmpty() && !gotPhone.isEmpty()
										&& !gotAdd.isEmpty()) {
									s = fields[0] + "," + gotUser + "," + gotPass + "," + gotPhone + "," + gotAdd + ","
											+ fields[5] + "," + fields[6] + "," + fields[7];
									temp = temp + s + System.lineSeparator();
									errorMsg.setText("Success!");
								}
								else {
									temp = temp + line + System.lineSeparator();
									errorMsg.setText("Fill all!");
								}

							} else {
								temp = temp + line + System.lineSeparator();
								errorMsg.setText("Error!");

							}
						}
						else {
							temp = temp + line + System.lineSeparator();

						}

					}
					fw = new FileWriter(file);
					fw.write(temp);
					br.close();
					fw.close();
				} catch (IOException z) {
					z.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(192, 286, 117, 29);
		contentPane.add(btnNewButton);

		errorMsg = new JLabel("");
		errorMsg.setForeground(Color.RED);
		errorMsg.setBounds(43, 287, 116, 16);
		contentPane.add(errorMsg);
		
		btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginScreen().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(321, 286, 117, 29);
		contentPane.add(btnNewButton_1);
		
		lblNewLabel_4 = new JLabel("Change email: ");
		lblNewLabel_4.setBounds(54, 233, 147, 16);
		contentPane.add(lblNewLabel_4);
		
		emailField = new JTextField();
		emailField.setBounds(267, 228, 130, 26);
		contentPane.add(emailField);
		emailField.setColumns(10);
	}
}
