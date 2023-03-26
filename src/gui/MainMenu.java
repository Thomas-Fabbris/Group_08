package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainMenu extends JFrame {
	
	private Integer[] options = {2, 3, 4};
	private JPanel empty_space1 = new JPanel();
	private JPanel empty_space2 = new JPanel();
	private JComboBox number_of_players = new JComboBox(options);
	private JLabel number_of_players_text = new JLabel("Number of players:");
	private JTextField player1 = new JTextField("Player 1");
	private JTextField player2 = new JTextField("Player 2");
	private JTextField player3 = new JTextField("Player 3");
	private JTextField player4 = new JTextField("Player 4");
	private JButton start = new JButton("Start Game");
	
	public MainMenu() {
		super("MyShelfie Main Menu");
		InitWindow();
		InitComponents();
		this.setVisible(true);
	}
	
	private void InitComponents() {
		//create "Number of players" text and combo-box
		number_of_players_text.setFont(new Font("Dialog", Font.BOLD, 20));
		number_of_players_text.setForeground(Color.WHITE);
		
		number_of_players.setMaximumSize(new Dimension(500, 30));
		number_of_players.setSize(new Dimension(500, 30));
		number_of_players.setToolTipText("Number of players");
		number_of_players.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		this.add(number_of_players_text);
		this.add(number_of_players);
		
		//create emtpy spaces
		empty_space1.setBackground(Color.DARK_GRAY);
		empty_space2.setBackground(Color.DARK_GRAY);
		
		this.add(empty_space1);
		
		//create text boxes
		this.add(player1);
		player1.setFont(new Font("Dialog", Font.PLAIN, 16));
		player1.setVisible(false);
		
		this.add(player2);
		player2.setVisible(false);
		player2.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		this.add(player3);
		player3.setVisible(false);
		player3.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		this.add(player4);
		player4.setVisible(false);
		player4.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		initActionListeners();

		this.add(empty_space2);
		this.add(start);
	}
	
	private void initActionListeners() {
		
		number_of_players.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count = (int)number_of_players.getSelectedItem(); //where should we store this?
				System.out.println(count);
				addTextFields(count);
			}
		});
		
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//code to execute on button press
				//get player names, save number of players somewhere
			}
		});
	}
	
	//also need to hide boxes when number of player decreases
	private void addTextFields(int count) {
		switch(count) {
		case 4:
			player4.setVisible(true);
		
		case 3:
			player3.setVisible(true);
			
		case 2:
			player2.setVisible(true);
			
		case 1:
			player1.setVisible(true);
		}
	}
	
	private void InitWindow() {
		this.setResizable(false);
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(9,0));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.DARK_GRAY);
	}
}
