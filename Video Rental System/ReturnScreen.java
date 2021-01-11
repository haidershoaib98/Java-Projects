package project_test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ReturnScreen extends JFrame {

	private JPanel contentPane;
	String name;
	String username;
	private JLabel msg;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ReturnScreen frame = new ReturnScreen();
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

	public ArrayList<String> custOrder(String username) {
		ArrayList<String> o = new ArrayList<String>();
		String line = "";

		try {
			File file = new File("./data/Orders.csv");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(",");
				if (username.equals(fields[1])) {
					o.add(line);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return o;

	}
	
	public boolean orderReturn(String username) {
		boolean exists = false;
		FileWriter fw = null;
		BufferedReader br = null;
		FileReader fr = null;
		String line = "";
		String old = "";

		try {
			File file = new File("./data/Orders.csv");
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(",");
				if(username.equals(fields[1])) {
					exists = true;
				}
				else {
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
		
		
		
		return exists;
	}
	
	public void updateVids(ArrayList<String> o2) {
		ArrayList<String> temp = new ArrayList<String>();
		for(int j = 0; j < o2.size(); j++) {
			String[] fields1 = o2.get(j).split(",");
			
			temp.add(fields1[2]);
			temp.add(fields1[3]);
			temp.add(fields1[4]);
			temp.add(fields1[5]);
			temp.add(fields1[6]);
			
		}
		
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
				String[] fields2 = line.split(",");
				if(temp.contains(fields2[0])) {
					newLine = fields2[0] + "," + fields2[1] + "," + fields2[2] + "," + "Available";
					old = old + newLine + System.lineSeparator();
				}
				else {
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
		
		
	}

	public ReturnScreen(String name, String username) {
		this.name = name;
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(398, 69, -351, 164);
		contentPane.add(textArea);

		ArrayList<String> o1 = new ArrayList<String>();
		o1 = custOrder(username);
		String s = "";
		for(int i = 0; i < o1.size(); i++) {
			String[] f = o1.get(i).split(",");
			s = s + "Name: " + f[0] + ", Username: " + f[1] + ", Video 1: " + f[2] + ", Video 2: " + f[3] + ", Video 3: " + f[4] +
					", Video 4: " + f[5] + ", Video 5: " + f[6] +  System.lineSeparator();
			
		}
		textArea.setText(s);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(45, 69, 365, 158);
		contentPane.add(scrollPane);

		JLabel lblNewLabel = new JLabel("Your Order:");
		lblNewLabel.setBounds(190, 25, 110, 16);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Return");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> co = new ArrayList<String>();
				co = custOrder(username);
				updateVids(co);
				Boolean b = orderReturn(username);
				if(b == true) {
					msg.setText("Return confirmed!");
				}
				else {
					msg.setText("Nothing to return!");
				}

			}
		});
		btnNewButton.setBounds(316, 237, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CustomerScreen c = new CustomerScreen();
				c.name = name;
				c.username = username;
				c.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(20, 20, 117, 29);
		contentPane.add(btnNewButton_1);
		
		msg = new JLabel("");
		msg.setBounds(68, 242, 166, 16);
		contentPane.add(msg);
	}
}
