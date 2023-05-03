package view.personalgamearea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import model.shared.Player;

public class PersonalGameAreaWindow extends JFrame {
//	private static PersonalGameAreaWindow instance = null
	private Player currentPlayer = null;
	private PlayerInfo playerInfoDisplay; //groups information related to the current_player
	
	public PersonalGameAreaWindow(Dimension screenSize) {
		super("MyShelfie");
		Init();
		playerInfoDisplay = new PlayerInfo(this.getSize());
		this.add(playerInfoDisplay.getPlayerInfoPanel());
	}
	
//	public static PersonalGameAreaWindow getInstance() {
//		if(instance == null)
//			instance = new PersonalGameAreaWindow();
//		return instance;
//	}
	
	public void setCurrentPlayer(Player player) {
		this.currentPlayer = player;
		this.setTitle(player.getName());
		playerInfoDisplay.setPlayerName(player.getName());
		playerInfoDisplay.setBookshelfLabel(player.bookshelf.getLabel());
		playerInfoDisplay.setPersonalObjectiveCardLabel(player.objectiveCard.getLabel());
		playerInfoDisplay.setEndOfGameTile(true);
		playerInfoDisplay.setPointTile1(8);
		playerInfoDisplay.setPointTile2(2);
		playerInfoDisplay.setChair(true);
		playerInfoDisplay.setPoints(100);
	}
	
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	private void Init() {
		this.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width/2, screenSize.height-40);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setLocation(screenSize.width/2, 0);
		
		this.setVisible(true);
	}
}
