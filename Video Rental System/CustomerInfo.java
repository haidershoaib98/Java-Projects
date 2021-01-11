package project_test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerInfo extends JFrame {

	private JPanel contentPane;
	String name;
	String username;
	private JTextPane textPane;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CustomerInfo frame = new CustomerInfo();
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

	public void getInfo(String username) {
		String s = "";
		String line = "";
		FileReader fr1 = null;
		BufferedReader br1 = null;
		try {
			File file1 = new File("./data/Customer.csv");
			fr1 = new FileReader(file1);
			br1 = new BufferedReader(fr1);
			line = br1.readLine();
			while ((line = br1.readLine()) != null) {
				String[] fields = line.split(",");

				if (username.equals(fields[1])) {
					s = "Name: " + fields[0] + System.lineSeparator() + "Username: " + fields[1]
							+ System.lineSeparator() + "Password: " + fields[2] + System.lineSeparator()
							+ "Phone Number: " + fields[3] + System.lineSeparator() + "Address: " + fields[4]
							+ System.lineSeparator() + "ID: " + fields[5] + System.lineSeparator()
							+ "Loyalty Points: " + fields[6] + System.lineSeparator() + "Email: " + fields[7];
					break;

				}

			}
			br1.close();
		} catch (IOException f) {
			f.printStackTrace();
		}

		textPane.setText(s);
	}

	public CustomerInfo(String name, String username) {
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Your Information:");
		lblNewLabel.setBounds(157, 21, 118, 16);
		contentPane.add(lblNewLabel);

		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(74, 59, 300, 181);
		contentPane.add(textPane);
		
		btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CustomerScreen c = new CustomerScreen();
				c.name = name;
				c.username = username;
				c.setVisible(true);
			}
		});
		btnNewButton.setBounds(19, 6, 117, 29);
		contentPane.add(btnNewButton);
		getInfo(username);
	}
}
