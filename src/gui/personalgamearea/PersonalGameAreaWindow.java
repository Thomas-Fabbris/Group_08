package gui.personalgamearea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Player;

public class PersonalGameAreaWindow extends JFrame {
	private static PersonalGameAreaWindow instance = null;
	private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private Player currentPlayer = null;
	private CurrentPlayerInfo currentPlayerInfo = new CurrentPlayerInfo(); //groups information related to the current_player
	
	public PersonalGameAreaWindow() {
		super("MyShelfie"); //TODO: pass as parameter the name of the player who's playing
		this.setLayout(new FlowLayout());
		Init();
	}
	
	public static PersonalGameAreaWindow getInstance() {
		if(instance == null)
			instance = new PersonalGameAreaWindow();
		return instance;
	}
	
	public void setCurrentPlayer(Player player) {
		this.currentPlayer = player;
		currentPlayerInfo.setBookshelfLabel(player.bookshelf.getLabel());
	}
	
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public Dimension getScreenSize() {
		return screenSize;
	}
	
	private void Init() {
		this.setResizable(false);
		this.setSize(screenSize.width/2, screenSize.height-40);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setLocation(screenSize.width/2, 0);
		this.add(currentPlayerInfo.getPanel());
		this.setVisible(true);
	}
}
