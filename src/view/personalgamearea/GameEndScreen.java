package view.personalgamearea;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameEndScreen extends JPanel {

	private JPanel gameEndScreen; // container which holds all elements displayed on the GUI
	
	private JLabel winnerName;
	private JLabel winnerText;
	
	private JLabel player1Name;
	private JLabel player2Name;
	private JLabel player3Name;
	private JLabel player4Name;
	private List<JLabel> playerNames;
	
	public GameEndScreen(Dimension personalGameAreaFrameSize) {
		this.gameEndScreen.setSize(personalGameAreaFrameSize);
		
		this.winnerName = new JLabel();
		this.winnerText = new JLabel("is the winnner!");
		
		this.player1Name = new JLabel();
		this.player2Name = new JLabel();
		this.player3Name = new JLabel();
		this.player4Name = new JLabel();
		
		this.playerNames = new ArrayList<>(4);
		this.playerNames.add(player1Name);
		this.playerNames.add(player2Name);
		this.playerNames.add(player3Name);
		this.playerNames.add(player4Name);
	}

	public JLabel getWinnerName() {
		return this.winnerName;
	}

	public List<JLabel> getPlayerNames() {
		return this.playerNames;
	}

	public JPanel getGameEndScreen() {
		return gameEndScreen;
	}
}
