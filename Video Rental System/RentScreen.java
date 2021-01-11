package project_test;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import java.io.Writer;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class RentScreen extends JFrame {
	private JPanel contentPane;
	private JLabel error_label = new JLabel();
	private JTextField textField;
	String name;
	String username;
	private ArrayList<Order> orders;
	double totalCost;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RentScreen frame = new RentScreen("Satinder");
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public RentScreen(String name, String username) {
		//Assign global variable values or initialize
		this.name = name;
		this.username = username;
		orders = new ArrayList<Order>();
		//System.out.println(name);
		totalCost = 0;
		
		//Set up the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		

		JLabel uno = new JLabel("Please call 647-620-9896 to rent a video or enter the following:");
		uno.setBounds(53, 18, 500, 50); // first is move to the left
		contentPane.add(uno);

		JLabel dos = new JLabel("Movie ID:");
		dos.setBounds(214, 80, 89, 16);
		contentPane.add(dos);

		textField = new JTextField();
		textField.setBounds(191, 108, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JTextArea cartbox = new JTextArea();
		cartbox.setBounds(40, 159, 423, 103);
		
		JButton b_uno = new JButton("Enter");
		b_uno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (orders.size()<=5) {
					String m_id = textField.getText();
					boolean exists = false;
					boolean alreadyOut = false;
					boolean more5 = false;
					FileWriter fw = null;
					BufferedReader br = null;
					FileReader fr = null;
					String line = "";
					String old = "";
					String newLine = "";
	
					try {
						File file = new File("./data/Videos.csv");
						fr = new FileReader(file);
						br = new BufferedReader(fr);
						while ((line = br.readLine()) != null) {
							String[] fields = line.split(",");
	
							if (m_id.equals(fields[2])) {
								if (fields[3].equals("Unavailable")) {
									alreadyOut = true;
									old = old + line + System.lineSeparator();
	
								} else if(orders.size() < 5){
									newLine = fields[0] + "," + fields[1] + "," + fields[2] + "," + "Unavailable";
									old = old + newLine + System.lineSeparator();
									exists = true;
									alreadyOut = false;
									error_label.setText("Added: " + fields[0]);
									orders.add(new Order(fields[0], fields[1], fields[2]));
									for (int i = 0; i < fields.length; i++) {
										//System.out.println(orders.get(0).print());
									}
									setText(cartbox);
								}
								else {
									old = old + line + System.lineSeparator();
									more5 = true;
								}
							} else {
								old = old + line + System.lineSeparator();
							}
	
						}
						fw = new FileWriter(file);
						fw.write(old);
						br.close();
						fw.close();
						fr.close();
					} catch (IOException z) {
						z.printStackTrace();
					}
					if (exists == false) {
						error_label.setText("This video does not exist. Try again.");
					}
					
					if(alreadyOut == true) {
						error_label.setText("This video is currently being rented. Try again.");
	
					}
					
					if(more5 == true) {
						error_label.setText("Cannot rent more than 5 videos.");
	
					}
	
					exists = false;
				} else {
					error_label.setText("You cannot have more than 5 videos in your cart.");
				}
			}

		});
		b_uno.setBounds(350, 108, 117, 29);
		error_label.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		error_label.setBounds(162, 125, 316, 50);
		contentPane.add(b_uno);
		contentPane.add(error_label);

		// adding back button

		JButton b_dos = new JButton("Back");
		b_dos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CustomerScreen().setVisible(true);
			}
		});

		b_dos.setBounds(40, 273, 117, 29);
		contentPane.add(b_dos);
		
		JButton btnNewButton = new JButton("Checkout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CheckoutMain c = new CheckoutMain(name, username, orders, totalCost);
				c.setVisible(true);
				//Variables you'll need: name --> username, orders --> arraylist of orders, totalCost --> total cost of orders
				//Create your class call here for payment/checkout
			}
		});
		btnNewButton.setBounds(350, 273, 117, 29);
		contentPane.add(btnNewButton);


		
		
		//Cart
		cartbox.setEditable(false);
		contentPane.add(cartbox);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				orders = new ArrayList<Order>();
				setText(cartbox);
				
			}
		});
		btnNewButton_1.setBounds(199, 273, 109, 29);
		contentPane.add(btnNewButton_1);
		
		setText(cartbox);
		
		JScrollPane scrollPane = new JScrollPane(cartbox);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(40, 159, 423, 103);
		contentPane.add(scrollPane);
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(450, 148, 13, 114);
//		contentPane.add(scrollPane);
		// relate checkout here

		
		
	}


	private void setText(JTextArea cartBox) {
		totalCost = 0;
		String text = "" + name + "'s Cart:\n\n";
		for (int i = 0; i<orders.size(); i++) {
			text += orders.get(i).printOrder() + "\n";
			totalCost += orders.get(i).getPrice();
		}
		text += "\nTotal Cost: $" + totalCost;
		cartBox.setText(text);
		
	}
}