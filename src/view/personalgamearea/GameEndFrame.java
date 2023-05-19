package view.personalgamearea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameEndFrame extends JFrame {
	
	private JLabel winnerName;
	private JLabel winnerText;
	
	private JLabel leaderboardText;
	private JLabel player1Name;
	private JLabel player2Name;
	private JLabel player3Name;
	private JLabel player4Name;
	private List<JLabel> playerNames;
	
	public GameEndFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		init(screenSize);
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

//		this.setLayout(new GridLayout(9,0));
		
		this.winnerName = new JLabel("Gamer");
		this.winnerText = new JLabel("is the winnner!");
		
		this.leaderboardText = new JLabel("Leaderboard:");
		this.player1Name = new JLabel("A");
		this.player2Name = new JLabel("B");
		this.player3Name = new JLabel("C");
		this.player4Name = new JLabel("D");
		
		this.playerNames = new ArrayList<>(4);
		this.playerNames.add(player1Name);
		this.playerNames.add(player2Name);
		this.playerNames.add(player3Name);
		this.playerNames.add(player4Name);
		
		this.add(winnerName);
		this.add(winnerText);
		this.add(leaderboardText);
		this.add(player1Name);
		this.add(player2Name);
		this.add(player3Name);
		this.add(player4Name);
	}
	
	private void init(Dimension screenSize) {
		this.setResizable(false);
		this.setSize(screenSize.width/2, screenSize.height-40);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setLocation(screenSize.width/2, 0);
		this.setVisible(true);
		this.setTitle("MyShelfie - Game End Screen");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("./Assets/Icon.png"));
	}

	public JLabel getWinnerName() {
		return this.winnerName;
	}

	public List<JLabel> getPlayerNames() {
		return this.playerNames;
	}
}
