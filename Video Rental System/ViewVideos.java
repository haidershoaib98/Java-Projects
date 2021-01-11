package project_test;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import java.io.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ViewVideos extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewVideos frame = new ViewVideos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ArrayList<String> getVideos() {
		String line = "";
		String temp = "";
		ArrayList<String> vids = new ArrayList<String>();

		// Read data from videos database
		try {
			File file = new File("./data/Videos.csv");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(",");

				temp = "Movie name: " + fields[0] + ", Genre: " + fields[1] + ", Movie ID: " + fields[2] + ", Status: " + fields[3];
				vids.add(temp);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vids;

	}

	/**
	 * Create the frame.
	 */
	public ViewVideos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Video List:");
		lblNewLabel.setBounds(219, 6, 98, 16);
		contentPane.add(lblNewLabel);

		JTextArea txtrHello = new JTextArea();
		txtrHello.setEditable(false);

		String t = "";
		// Display all videos
		ArrayList<String> v = getVideos();
		for (int j = 0; j < v.size(); j++) {

			t = t + v.get(j) + "\n";
		}
		txtrHello.setText(t);
		JScrollPane scroll = new JScrollPane(txtrHello);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(10, 90, 485, 259);
		txtrHello.setBounds(45, 47, 326, 203);
		// contentPane.add(txtrHello);
		contentPane.add(scroll);

		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginScreen().setVisible(true);

			}
		});
		btnNewButton.setBounds(10, 37, 84, 29);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(105, 38, 179, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//Search by Movie Name
		JButton btnNewButton_1 = new JButton("Search Name");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String search = textField.getText();
				if (search.equals("") || search == null) {
					String t = "";
					for (int j = 0; j < v.size(); j++) {
						t = t + v.get(j) + "\n";
					}
					txtrHello.setText(t);
				}else {
					String results = "";
					for (int i = 0; i < v.size(); i++) {
						String temp = v.get(i);
						String s = "^(Movie name: " + search + ", ).*";
						if (temp.toLowerCase().matches(s.toLowerCase())) {
							results += v.get(i) + "\n";
						}
					}
					if (results == "") {
						txtrHello.setText("No results found for Movie Name: " + search + ". Please try again.");
					} else {
						txtrHello.setText(results);
					}
				}
			}
		});
		btnNewButton_1.setBounds(294, 38, 105, 26);
		contentPane.add(btnNewButton_1);
		
		//Search by Category
		JButton btnNewButton_2 = new JButton("Search Cat");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String search = textField.getText();
				if (search.equals("") || search == null) {
					String t = "";
					for (int j = 0; j < v.size(); j++) {
						t = t + v.get(j) + "\n";
					}
					txtrHello.setText(t);
				} else {
					String results = "";
					for (int i = 0; i < v.size(); i++) {
						String temp = v.get(i);
						String s1 = "^(Movie name:).*(Genre: " + search + ", Movie ID: ).*";
						if (temp.toLowerCase().matches(s1.toLowerCase())) {
							results += v.get(i) + "\n";
						}
					}
					if (results == "") {
						txtrHello.setText("No results found for category: " + search + ". Please try again.");
					} else {
						txtrHello.setText(results);
					}
				}
				
			}
		});
		btnNewButton_2.setBounds(404, 38, 91, 26);
		contentPane.add(btnNewButton_2);

	}
}