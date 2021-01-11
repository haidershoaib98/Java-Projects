package project_test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTree;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class OperatorScreen extends JFrame{
	private JPanel contentPane;
	
	String name;
	String username;
	String password;
	String ID;       
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OperatorScreen frame = new OperatorScreen();
					frame.setVisible(true);
				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public OperatorScreen() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton RegisterUserButton = new JButton("Register User");
		RegisterUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new RegisterUserFromOperator().setVisible(true);
				dispose();
				
			}
		});
		RegisterUserButton.setBounds(46, 68, 173, 25);
		contentPane.add(RegisterUserButton);
		
		JButton RegisterMovieButton = new JButton("Register Movie");
		RegisterMovieButton.setBounds(46, 140, 173, 25);
		RegisterMovieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AddMovieFrame().setVisible(true);
				
			}
		});
		RegisterUserButton.setBounds(46, 68, 173, 25);
		
		
		contentPane.add(RegisterMovieButton);
		
		JButton RemoveMovieButton = new JButton("Remove Movie");
		RemoveMovieButton.setBounds(46, 215, 173, 25);
		RemoveMovieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new RemoveMovieFrame().setVisible(true);
				
			}
		});
		
		
		
		
		contentPane.add(RemoveMovieButton);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginScreen().setVisible(true);
			}
		});
		btnNewButton.setBounds(348, 263, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("User Orders");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ViewCustOrder().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(321, 138, 117, 29);
		contentPane.add(btnNewButton_1);
		
		
		
		
		
		
		
	}
}