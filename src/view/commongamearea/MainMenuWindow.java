package view.commongamearea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MainMenuWindow extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3919760497868695631L;
	private JPanel empty_space1 = new JPanel();
	private JPanel empty_space2 = new JPanel();
	private JComboBox<Integer> number_of_players_seletion = new JComboBox<Integer>(new Integer[] {2, 3, 4});
	private JLabel number_of_players_text = new JLabel("Set the number of players");
	private JTextField[] player_names = new JTextField[4];
	private int number_of_players;
	private ArrayList<String> players;
	
	private JButton start = new JButton("Start Game");
	
	public MainMenuWindow(ArrayList<String> players) {
		this.players = players;
		this.setModal(true);
		InitWindow();
		InitComponents();
		this.setVisible(true);
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
				startGame();
			}
		});
		
		for(JTextField txt : player_names) {
			txt.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					changeButtonState(txt);
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					changeButtonState(txt);
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					changeButtonState(txt);
				}
			});
		}
		
		
	}
	
	private void startGame() {
		System.out.println("[" +this.getClass().getSimpleName()+ "] Closing main menu...");
		
		//fill the list with player names
		this.getPlayers(this.players);
		
		//close menu
		this.dispose();
	}
	
	private void initTextFields() {
		for (int i = 0; i < player_names.length; i++) {
			player_names[i] = new JTextField(String.format("Player name %d", i+1));
			player_names[i].setFont(new Font("Dialog", Font.PLAIN, 16));
			player_names[i].setHorizontalAlignment(SwingConstants.CENTER);
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
	
	private void getPlayers(ArrayList<String> players) {	
		for(int i = 0; i < number_of_players; i++) {
			players.add(player_names[i].getText());
		}
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
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("./Assets/Icon.png"));
		this.setTitle("MyShelfie - Select Players");
	}
	
	private void InitComponents() {
		//create "Number of players" text and combo-box
		number_of_players_text.setFont(new Font("Dialog", Font.BOLD, 20));
		number_of_players_text.setForeground(Color.WHITE);
		number_of_players_text.setHorizontalAlignment(SwingConstants.CENTER);
		
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
		initTextFields();
		initActionListeners();
		
		this.add(empty_space2);
		
		start.setEnabled(false);
		this.add(start);
	}
	
	private void changeButtonState(JTextField txt) {
		if(!start.isEnabled()) {
			if(txt.getText().length() > 0) {
				start.setEnabled(true);
			}
		}
		
	}
}
