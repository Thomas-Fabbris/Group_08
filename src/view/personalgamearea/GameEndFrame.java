package view.personalgamearea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
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
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();

		Font winnerNameFont = new Font("Dialog", Font.PLAIN, (int) (screenSize.getWidth() / 30));
		Font winnerTextFont = new Font("Dialog", Font.PLAIN, (int) (screenSize.getWidth() / 40));

		Font leaderboardFont = new Font("Dialog", Font.PLAIN, (int) (screenSize.getWidth() / 50));
		Font playerNamesFont = new Font("Dialog", Font.PLAIN, (int) (screenSize.getWidth() / 60));
		init(screenSize);

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

//		this.setLayout(new GridLayout(9,0));

		this.winnerName = new JLabel("Gamer", JLabel.CENTER);
		winnerName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		winnerName.setForeground(Color.WHITE);
		winnerName.setFont(winnerNameFont);

		this.winnerText = new JLabel(
				"<html>is the <font color='#ffcc00'>winner</font><font color='#ffffff'>!</font></html>",
				JLabel.CENTER);
		winnerText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		winnerText.setForeground(Color.WHITE);
		winnerText.setFont(winnerTextFont);

		this.leaderboardText = new JLabel("Leaderboard:");
		leaderboardText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		leaderboardText.setForeground(Color.WHITE);
		leaderboardText.setFont(leaderboardFont);

		this.player1Name = new JLabel("q(≧▽≦q) - NA");
		player1Name.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		player1Name.setForeground(Color.WHITE);
		player1Name.setFont(playerNamesFont);

		this.player2Name = new JLabel("(*^▽^*) - NA");
		player2Name.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		player2Name.setForeground(Color.WHITE);
		player2Name.setFont(playerNamesFont);

		this.player3Name = new JLabel("(≧∇≦)ﾉ - NA");
		player3Name.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		player3Name.setForeground(Color.WHITE);
		player3Name.setFont(playerNamesFont);

		this.player4Name = new JLabel("(*^_^*) - NA");
		player4Name.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		player4Name.setForeground(Color.WHITE);
		player4Name.setFont(playerNamesFont);

		this.playerNames = new ArrayList<>(4);
		this.playerNames.add(player1Name);
		this.playerNames.add(player2Name);
		this.playerNames.add(player3Name);
		this.playerNames.add(player4Name);

		this.add(winnerName);
		this.add(winnerText);
		this.add(Box.createRigidArea(new Dimension(screenWidth, (int) (screenHeight / 20))));
		this.add(leaderboardText);
		this.add(player1Name);
		this.add(player2Name);
		this.add(player3Name);
		this.add(player4Name);
	}

	private void init(Dimension screenSize) {
		this.setResizable(false);
		this.setSize(screenSize.width / 2, screenSize.height - 40);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setLocation(screenSize.width / 2, 0);
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
