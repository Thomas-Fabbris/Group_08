package view.commongamearea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.TileSelectionHintDialog;

public class MainMenuWindow extends JDialog {

	private JPanel empty_space1 = new JPanel();
	private JPanel empty_space2 = new JPanel();
	private JComboBox<Integer> number_of_players_selection = new JComboBox<Integer>(new Integer[] { 2, 3, 4 });
	private JLabel number_of_players_text = new JLabel("Set the number of players");
	private JTextField[] player_names = new JTextField[4];
	private int number_of_players;
	private ArrayList<String> players;
	private JCheckBox tutorialEnabledCheckbox;

	private JButton start = new JButton("Start Game");

	public MainMenuWindow(ArrayList<String> players) {
		if (players == null) {
			throw new NullPointerException("players cannot be set to null when creating a MainMenuWindow instance!");
		}
		this.players = players;
		this.setModal(true);
		InitWindow();
		InitComponents();
		this.setVisible(true);
	}

	private void initActionListeners() {

		number_of_players_selection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count = (int) number_of_players_selection.getSelectedItem();
				number_of_players = count;
				hideUnusedTextFields(count);
			}
		});

		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});

		for (JTextField txt : player_names) {
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
		System.out.println("[" + this.getClass().getSimpleName() + "] Closing main menu...");

		// fill the list with player names
		this.getPlayers(this.players);

		// close menu
		this.dispose();
	}

	private void initTextFields() {
		for (int i = 0; i < player_names.length; i++) {
			player_names[i] = new JTextField(String.format("Player name %d", i + 1));
			player_names[i].setFont(new Font("Dialog", Font.PLAIN, 16));
			player_names[i].setHorizontalAlignment(SwingConstants.CENTER);
			this.add(player_names[i]);
			if (i > 1)
				player_names[i].setVisible(false);
		}
	}

	private void hideUnusedTextFields(int count) {

		// show text fields that players will use
		for (int i = 0; i < count; i++) {
			player_names[i].setVisible(true);
		}

		// we don't need to hide any text field if the player count is maximum
		if (count == player_names.length)
			return;

		// hide text fields that will not be used
		for (int i = count; i < player_names.length; i++) {
			player_names[i].setVisible(false);
		}
	}

	public int getNumberOfPlayers() {
		return this.number_of_players;
	}

	private void getPlayers(ArrayList<String> players) {
		if (players == null) {
			throw new NullPointerException(
					"players cannot be set to null when calling MainMenuWindow.getPlayers() method!");
		}
		for (int i = 0; i < number_of_players; i++) {
			players.add(player_names[i].getText());
		}
	}

	/**
	 * @param number of the text field to get the name from (0 to 4)
	 * @return the name specified text field
	 */
	public String getPlayerNameInTextfield(int text_field) {
		if (text_field >= 0 && text_field <= 4) {
			if (!player_names[text_field].isVisible()) {
				throw new IndexOutOfBoundsException();
			}
			return player_names[text_field].getText();
		}
		throw new IllegalArgumentException(
				"text_field must be between 0 and 4 when calling MainMenuWindow.getPlayerNameInTextField() method!");
	}

	private void InitWindow() {
		this.setResizable(false);
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(10, 0));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("./Assets/Icon.png"));
		this.setTitle("MyShelfie - Select Players");
	}

	private void InitComponents() {
		// create "Number of players" text and combo-box
		number_of_players_text.setFont(new Font("Dialog", Font.BOLD, 20));
		number_of_players_text.setForeground(Color.WHITE);
		number_of_players_text.setHorizontalAlignment(SwingConstants.CENTER);

		number_of_players_selection.setMaximumSize(new Dimension(500, 30));
		number_of_players_selection.setSize(new Dimension(500, 30));
		number_of_players_selection.setToolTipText("Number of players");
		number_of_players_selection.setFont(new Font("Dialog", Font.PLAIN, 16));
		number_of_players_selection.setSelectedIndex(-1);

		this.add(number_of_players_text);
		this.add(number_of_players_selection);

		// create empty spaces
		empty_space1.setBackground(Color.DARK_GRAY);
		empty_space2.setBackground(Color.DARK_GRAY);

		this.add(empty_space1);
		initTextFields();
		initActionListeners();

		this.add(empty_space2);

		// Add checkbox
		this.tutorialEnabledCheckbox = new JCheckBox("Enable tile selection tutorial");
		tutorialEnabledCheckbox.setSize(new Dimension(500, 30));
		tutorialEnabledCheckbox.setFont(new Font("Dialog", Font.BOLD, 18));
		tutorialEnabledCheckbox.setForeground(Color.WHITE);
		tutorialEnabledCheckbox.setBackground(Color.DARK_GRAY);
		tutorialEnabledCheckbox.setToolTipText("Recommended if you are playing for the first time");
		addCheckboxListener();
		this.add(tutorialEnabledCheckbox);

		start.setEnabled(false);
		this.add(start);
	}

	private void addCheckboxListener() {
		tutorialEnabledCheckbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				TileSelectionHintDialog.enabled = e.getStateChange() == 1; // 1 when checked, 2 when unchecked
			}
		});
	}

	private void changeButtonState(JTextField txt) {
		if (txt == null) {
			throw new NullPointerException(
					"txt cannot be set to null when calling MainMenuWindow.changeButtonState() method!");
		}
		if (!start.isEnabled()) {
			if (number_of_players_selection.getSelectedIndex() != -1) {
				if (txt.getText().length() > 0) {
					start.setEnabled(true);
				}
			}

		}

	}
}
