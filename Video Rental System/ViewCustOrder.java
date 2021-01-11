package project_test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ViewCustOrder extends JFrame {

	private JPanel contentPane;
	private JTextField custName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCustOrder frame = new ViewCustOrder();
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
	
	public ArrayList<String> custOrder(String username) {
		ArrayList<String> o = new ArrayList<String>();
		String line = "";
		String temp = "";

		// Read data from videos database
		try {
			File file = new File("./data/Orders.csv");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			line = br.readLine();
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
	public ViewCustOrder() {
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

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(45, 69, 365, 158);
		contentPane.add(scrollPane);

		JLabel lblNewLabel = new JLabel("Customer username:");
		lblNewLabel.setBounds(121, 25, 141, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new OperatorScreen().setVisible(true);
			}
		});
		btnNewButton.setBounds(16, 239, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Find order");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> o1 = new ArrayList<String>();
				String name = custName.getText();

				o1 = custOrder(name);
				String s = "";
				for(int i = 0; i < o1.size(); i++) {
					s = s + o1.get(i) + System.lineSeparator();
				}
				textArea.setText(s);

			}
		});
		btnNewButton_1.setBounds(327, 239, 117, 29);
		contentPane.add(btnNewButton_1);
		
		custName = new JTextField();
		custName.setBounds(260, 20, 130, 26);
		contentPane.add(custName);
		custName.setColumns(10);
	}

}
