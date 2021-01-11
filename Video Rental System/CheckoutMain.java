package project_test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Color;

public class CheckoutMain extends JFrame {

	private JPanel contentPane;
	private JFrame checkout = new JFrame("Checkout");
	String name;
	String username;
	private ArrayList<Order> orders;
	double totalCost;
	private JLabel errorMsg; 

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CheckoutMain frame = new CheckoutMain();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public boolean subLoyalty(String user) {
		String line = "";
		String old = "";
		String newLine = "";
		boolean success = false;
		
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
					if(x >= 5) {
						x = x-4;
						success = true;
					}
					
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
		return success;
		
	}
	
	
	
	/**
	 * Create the frame.
	 */
	public CheckoutMain(String name, String username, ArrayList<Order> orders, double totalCost) {
		this.name = name;
		this.username = username;
		this.orders = orders;
		this.totalCost = totalCost;
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
JButton btnBack = new JButton("Back");
btnBack.setBounds(28, 216, 75, 29);
btnBack.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		dispose();
		CustomerScreen c = new CustomerScreen();
		c.name = name;
		c.username = username;
		c.setVisible(true);
	}
});
		
		JButton btnCheckoutWithPaypal = new JButton("Check-out with PayPal");
		btnCheckoutWithPaypal.setBounds(53, 173, 186, 29);
		btnCheckoutWithPaypal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new PaypalScreen(name, username, orders, totalCost).setVisible(true); 
				
			}
		});
		
		JButton btnCheckoutWithCredit = new JButton("Check-out with Credit");
		btnCheckoutWithCredit.setBounds(331, 216, 185, 29);
		btnCheckoutWithCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				for(int i = 0; i < orders.size(); i++) {
				//System.out.println(orders.get(i).printOrder());
				}
				new CreditScreen(name, username, orders, totalCost).setVisible(true); 
				
				
			}
		});
	
		
		
		JLabel lblCheckout = new JLabel("Checkout");
		lblCheckout.setBounds(249, 11, 76, 39);
		lblCheckout.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Check-out with Points");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean b = subLoyalty(username);
				if(b == true) {
					dispose();
					new PaymentSuccessScreen(name, username, orders, totalCost).setVisible(true);;
				}
				else {
					errorMsg.setText("Not enough points!");
				}
			}
		});
		
		errorMsg = new JLabel("");
		errorMsg.setForeground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCheckout, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnBack)
							.addGap(222)))
					.addContainerGap(201, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(176, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnCheckoutWithPaypal)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(6)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))
							.addComponent(btnCheckoutWithCredit, Alignment.TRAILING)))
					.addGap(159))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(200)
					.addComponent(errorMsg, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
					.addGap(22))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCheckout, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnCheckoutWithPaypal)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCheckoutWithCredit)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(errorMsg)
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addComponent(btnBack)
					.addGap(22))
		);
		getContentPane().setLayout(groupLayout);
	}
}