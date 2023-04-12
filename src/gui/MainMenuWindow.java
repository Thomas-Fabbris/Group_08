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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Game;
import main.IdGenerator;
import main.Player;

public class MainMenuWindow extends JDialog{
	private JPanel empty_space1 = new JPanel();
	private JPanel empty_space2 = new JPanel();
	private JComboBox<Integer> number_of_players_seletion = new JComboBox<Integer>(new Integer[] {2, 3, 4});
	private JLabel number_of_players_text = new JLabel("Number of players:");
	private JTextField[] player_names = new JTextField[4];
	private int number_of_players;
	private Player[] players = new Player[4];

	private JButton start = new JButton("Start Game");
	
	public MainMenuWindow() {
//		super("MyShelfie Main Menu");
		this.setModal(true);
		InitWindow();
		InitComponents();
		this.setVisible(true);
	}
	
	private void InitComponents() {
		//create "Number of players" text and combo-box
		number_of_players_text.setFont(new Font("Dialog", Font.BOLD, 20));
		number_of_players_text.setForeground(Color.WHITE);
		
		number_of_players_seletion.setMaximumSize(new Dimension(500, 30));
		number_of_players_seletion.setSize(new Dimension(500, 30));
		number_of_players_seletion.setToolTipText("Number of players");
		number_of_players_seletion.setFont(new Font("Dialog", Font.PLAIN, 16));
		number_of_players_seletion.setSelectedIndex(-1);
		
		this.add(number_of_players_text);
		this.add(number_of_players_seletion);
		
		//create empty spaces
		empty_space1.setBackground(Color.DARK_GRAY);
		empty_space2.setBackground(Color.DARK_GRAY);
		
		this.add(empty_space1);		
		initActionListeners();
		initTextFields();
		
		this.add(empty_space2);
		this.add(start);
	}
	
	private void initActionListeners() {
		
		number_of_players_seletion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count = (int)number_of_players_seletion.getSelectedItem(); //where should we store this?
				number_of_players = count;
				hideUnusedTextFields(count);
			}
		});
		
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player.setNumberOfPlayers(number_of_players);
				startGame();
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
	
	public int getNumberOfPlayers() {
		return this.number_of_players;
	}
	
	public Player[] getPlayers() {
		ArrayList<Player> players = new ArrayList<>();
		
		IdGenerator idgenerator = new IdGenerator();	
		for(int i = 0; i < number_of_players; i++) {
			players.add(new Player(player_names[i].getText(), idgenerator));
		}
		
		return players.toArray(new Player[0]);
	}
	
	private void startGame() {
		Game game = new Game(this);
	}

	/**
	 * @param number of the text field to get the name from (0 to 4)
	 * @return the name specified text field
	 */
	public String getPlayerNameInTextfield(int text_field) {
		if(!player_names[text_field].isVisible()) throw new IndexOutOfBoundsException();
		return player_names[text_field].getText();
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
