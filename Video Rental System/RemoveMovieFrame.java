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
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class RemoveMovieFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel inputIDEntry;
	private JLabel errorMsg;

	public RemoveMovieFrame() {
		getContentPane().setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton BackButton = new JButton("Back");
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new OperatorScreen().setVisible(true);

			}

		});

		BackButton.setBounds(31, 58, 91, 25);
		contentPane.add(BackButton);

		JList list = new JList();
		list.setBounds(196, 381, 130, -250);
		contentPane.add(list);

		textField = new JTextField();
		textField.setBounds(169, 147, 106, 22);
		contentPane.add(textField);
		textField.setColumns(10);

		inputIDEntry = new JLabel("Enter Movie ID");
		inputIDEntry.setBounds(169, 109, 100, 16);
		contentPane.add(inputIDEntry);

		JButton ConfirmButton = new JButton("Confirm");
		ConfirmButton.setBounds(169, 237, 91, 25);
		ConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String chosenidasString = textField.getText();
				boolean removed = false;

				int chosenid = Integer.parseInt(chosenidasString);
				ArrayList<String> listOfMovies = new ArrayList<String>();
				
				
				BufferedReader br1 = null;
				FileReader fr1 = null;
				String line1 = "";

				try {
					File file1 = new File("./data/Videos.csv");
					fr1 = new FileReader(file1);
					br1 = new BufferedReader(fr1);
					line1 = br1.readLine();
					while ((line1 = br1.readLine()) != null) {
						listOfMovies.add(line1);
						
					}
					br1.close();
					fr1.close();
				} catch (IOException z) {
					z.printStackTrace();
				}
				int numberOfMovies = listOfMovies.size();
				
				

				

				if (numberOfMovies == 0) {
					removed = false;
				}

				else if (chosenid == numberOfMovies) {// you are removing the last element in the array
					listOfMovies.remove(chosenid-1);
					removed = true;

					
				} 
				
				else if(chosenid < numberOfMovies){
					// removes element chosen by user and replaces it with last element

					Collections.swap(listOfMovies, chosenid - 1, numberOfMovies - 1);
					String replacement = listOfMovies.get(chosenid - 1);// original
					String pattern = Integer.toString(numberOfMovies);
					replacement = replacement.replace(pattern, Integer.toString(chosenid));
					listOfMovies.remove(numberOfMovies - 1);// remove the last element
					listOfMovies.remove(chosenid - 1);
					listOfMovies.add(chosenid - 1, replacement);
					removed = true;

				}
				
				if(removed) {
					FileWriter fw = null;
					BufferedReader br = null;
					FileReader fr = null;
					String line = "";
					String old = "";

					try {
						File file = new File("./data/Videos.csv");
						fr = new FileReader(file);
						br = new BufferedReader(fr);
						line = br.readLine();
						old = old + line + System.lineSeparator();
						for (int i = 0; i < listOfMovies.size(); i++) {

							old = old + listOfMovies.get(i) + System.lineSeparator();

						}

						fw = new FileWriter(file);
						fw.write(old);
						br.close();
						fw.close();
						fr.close();
					} catch (IOException z) {
						z.printStackTrace();
					}
					errorMsg.setText("Video Removed");
				}
				else {
					errorMsg.setText("Video does not exist");
				}

			}

		});

		contentPane.add(ConfirmButton);

		errorMsg = new JLabel("");
		errorMsg.setBounds(169, 181, 216, 16);
		contentPane.add(errorMsg);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveMovieFrame frame = new RemoveMovieFrame();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}