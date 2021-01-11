package project_test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PaymentSuccessScreen extends JFrame {

	private JPanel contentPane;
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
//					PaymentSuccessScreen frame = new PaymentSuccessScreen();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public void writeOrders(String name, String username, ArrayList<Order> orders) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		String vids[] = new String[5];
		for (int i = 0; i < orders.size(); i++) {

			vids[i] = orders.get(i).getVideos();

		}

		String line = "";
		String s1 = "";
		try {
			File file = new File("./data/Orders.csv");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				s1 = s1 + line + System.lineSeparator();
			}
			s1 = s1 + name + "," + username + "," + vids[0] + "," + vids[1] + "," + vids[2] + "," + vids[3] + ","
					+ vids[4] + System.lineSeparator();
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

	public PaymentSuccessScreen(String name, String username, ArrayList<Order> orders, double totalCost) {
		this.name = name;
		this.username = username;
		this.orders = orders;
		this.totalCost = totalCost;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Payment Successful!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));

		writeOrders(name, username, orders);

		JLabel lblYourOrderIs = new JLabel("Your Order is now being shipped, thanks for shopping!");

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new LoginScreen().setVisible(true);
			}
		});

		JLabel lblNewLabel_1 = new JLabel("You have earned 1 point for this order!");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(95).addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(169).addComponent(btnLogOut))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(50).addComponent(lblYourOrderIs)))
				.addContainerGap(44, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap(112, Short.MAX_VALUE).addComponent(lblNewLabel_1).addGap(85)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(51).addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblYourOrderIs).addGap(18)
						.addComponent(lblNewLabel_1).addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
						.addComponent(btnLogOut).addGap(34)));
		contentPane.setLayout(gl_contentPane);
	}
}