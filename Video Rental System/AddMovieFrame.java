package project_test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class AddMovieFrame extends JFrame {

	private JPanel contentPane;
	private JTextField title;
	private JTextField genre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMovieFrame frame = new AddMovieFrame();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddMovieFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Title");// label above the title
		lblNewLabel.setBounds(190, 55, 50, 16);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Genre");// label above the genre
		lblNewLabel_1.setBounds(190, 156, 50, 16);
		contentPane.add(lblNewLabel_1);

		title = new JTextField();// text field for title

		title.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		title.setBounds(160, 87, 106, 22);
		contentPane.add(title);
		title.setColumns(10);

		genre = new JTextField();
		genre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		genre.setBounds(160, 193, 106, 22);
		contentPane.add(genre);
		genre.setColumns(10);

		JButton ConfirmButton = new JButton("Confirm");
		ConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String titleEntry = title.getText();
				String genreEntry = genre.getText();
				int IDEntry = 1 + getMovies().size();
				String statusEntry = "Available";

				writeMovies(titleEntry, genreEntry, IDEntry, statusEntry);

			}

		});

		ConfirmButton.setBounds(160, 285, 91, 25);
		contentPane.add(ConfirmButton);

		JButton BackButton = new JButton("Back");

		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new OperatorScreen().setVisible(true);

			}

		});

		BackButton.setBounds(12, 51, 91, 25);
		contentPane.add(BackButton);

	}

	public static ArrayList<String> getMovies() {
		String line = "";
		String temp = "";
		ArrayList<String> cus = new ArrayList<String>();

		try {
			File file = new File("./data/Videos.csv");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(",");

				temp = "Videos: " + fields[0] + ", Genre: " + fields[1] + ", ID: " + fields[2] + ", Status: "
						+ fields[3];
				cus.add(temp);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cus;

	}

	public void writeMovies(String Videos, String Genre, int ID, String Status) {
		String line = "";
		String s1 = "";
		FileWriter fw = null;
		try {
			File file = new File("./data/Videos.csv");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				s1 = s1 + line + System.lineSeparator();
			}
			s1 = s1 + Videos + "," + Genre + "," + ID + "," + Status + System.lineSeparator();
			fw = new FileWriter(file);
			fw.write(s1);
			br.close();
			fw.close();
			fr.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}