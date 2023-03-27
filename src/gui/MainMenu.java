package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Main;
import main.Game;
import main.IdGenerator;
import main.Player;

public class MainMenu extends JFrame {
	
	private JPanel empty_space1 = new JPanel();
	private JPanel empty_space2 = new JPanel();
	private JComboBox<Integer> number_of_players = new JComboBox<Integer>(new Integer[] {2, 3, 4});
	private JLabel number_of_players_text = new JLabel("Number of players:");
	private JTextField[] player_names = new JTextField[4];
//	private JTextField player1 = new JTextField("Player 1");
//	private JTextField player2 = new JTextField("Player 2");
//	private JTextField player3 = new JTextField("Player 3");
//	private JTextField player4 = new JTextField("Player 4");
	private ArrayList<Player> players = new ArrayList<Player>();
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
		number_of_players.setSelectedIndex(-1);
		
		this.add(number_of_players_text);
		this.add(number_of_players);
		
		//create emtpy spaces
		empty_space1.setBackground(Color.DARK_GRAY);
		empty_space2.setBackground(Color.DARK_GRAY);
		
		this.add(empty_space1);		
		initActionListeners();
		initTextFields();
		
		this.add(empty_space2);
		this.add(start);
	}
	
	private void initActionListeners() {
		
		number_of_players.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count = (int)number_of_players.getSelectedItem(); //where should we store this?
				Player.setNumberOfPlayers(count);
				hideUnusedTextFields(count);
			}
		});
		
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createPlayers();
				
				//Starts the game's execution
				MainMenu.this.dispose();
				Game game = new Game();
				game.StartGame();
			}
		});
	}
	
	private void initTextFields() {		
		for (int i = 0; i < player_names.length; i++) {
			player_names[i] = new JTextField(String.format("Player name %d", i+1));
			player_names[i].setFont(new Font("Dialog", Font.PLAIN, 16));
			this.add(player_names[i]);
			if(i > 1) player_names[i].setVisible(false);
		}
	}
	
	private void hideUnusedTextFields(int count) {
		
		//show text fields that players will use
		for (int i = 0; i < count; i++) {
			player_names[i].setVisible(true);
		}
		
		//we don't need to hide any text field if the player count is maximum
		if(count == player_names.length) return;
		
		//hide text fields that will not be used
		for (int i = count; i < player_names.length; i++) {
			player_names[i].setVisible(false);
		}
	}
	
	private void createPlayers() {
		
		IdGenerator idgenerator = new IdGenerator();
		
		for(int i = 0; i < Player.getNumberOfPlayers(); i++) {
			Player.players[i] = new Player(player_names[i].getText(), idgenerator);
//			players.add(new Player(player_names[i].getText(), idgenerator));
		}
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	private void InitWindow() {
		this.setResizable(false);
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(9,0));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(Color.DARK_GRAY);
	}
}
