package project_test;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class CustomerScreen extends JFrame {
	private JPanel contentPane;
	String name;
	String username;
	String password;
	String address;
	String phoneNumber;
	String ID;
	int points;
	String email;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerScreen frame = new CustomerScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public CustomerScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Rent a video");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new RentScreen(name, username).setVisible(true); 
			}
		});
		btnNewButton.setBounds(311, 19, 117, 29);
		//btnNewButton.setBou
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginScreen().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(41, 275, 117, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("View Videos");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewVideos().setVisible(true);
			}
		});
		btnNewButton_2.setBounds(41, 19, 117, 29);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Return Video");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ReturnScreen(name, username).setVisible(true);
			}
		});
		btnNewButton_3.setBounds(311, 116, 117, 29);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Checkout");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_4.setBounds(41, 116, 117, 29);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_6 = new JButton("Update Profile");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CustUpdate u = new CustUpdate(username);
				u.setVisible(true);
			}
		});
		btnNewButton_6.setBounds(41, 209, 117, 29);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_5 = new JButton("View Profile");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CustomerInfo(name, username).setVisible(true);
			}
		});
		btnNewButton_5.setBounds(311, 209, 117, 29);
		contentPane.add(btnNewButton_5);

	}
}