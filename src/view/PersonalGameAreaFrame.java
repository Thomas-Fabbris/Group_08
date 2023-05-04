package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.personalgamearea.BookshelfLabel;

public class PersonalGameAreaFrame extends JFrame {
	
	Dimension screenSize;

	private JPanel playerInfoPanel; //Contains all the info that are displayed on the GUI
	private JPanel tiles; //Contains: point tiles, chair and points
	private JPanel bookshelfAndCard; //Contains: bookshelf and personal objective card
	private JPanel playerNameAndButton; //Contains: player's name and next player button
	
	private JLabel playerName;
	private JLabel nextPlayerButton;
	private BookshelfLabel bookshelfLabel;
	private JLabel personalObjectiveCardLabel;
	private JLabel pointTile1;
	private JLabel pointTile2;
	private JLabel endOfGameTile;
	private JLabel points;
	private JLabel chair;
	
	public PersonalGameAreaFrame(Dimension screenSize) {
		super("MyShelfie");
		this.screenSize = screenSize;
		Init();
		
		this.playerInfoPanel = new JPanel();
		this.playerInfoPanel.setLayout(new FlowLayout());
		this.playerInfoPanel.setOpaque(false);
		
		//Player's name and Next player button
		this.playerNameAndButton = new JPanel();
		this.playerNameAndButton.setOpaque(false);
		this.playerNameAndButton.setLayout(new BoxLayout(playerNameAndButton, BoxLayout.X_AXIS));
		
		this.playerName = new JLabel();
		playerName.setForeground(Color.white);
		playerName.setFont(new Font(Font.DIALOG, Font.BOLD, this.getHeight()/22));
		
		this.nextPlayerButton = new JLabel();
		
		this.playerNameAndButton.add(playerName);
		this.playerNameAndButton.add(nextPlayerButton);
				
		//Tiles
		this.tiles = new JPanel();
		this.tiles.setOpaque(false);
		this.tiles.setLayout(new BoxLayout(tiles, BoxLayout.X_AXIS));
		
		this.pointTile1 = new JLabel();		
		this.pointTile2 = new JLabel();
		this.endOfGameTile = new JLabel();
		this.chair = new JLabel();
		this.points = new JLabel();
		
		Dimension tileSize = new Dimension(this.getWidth()/22, this.getWidth()/22);
		pointTile1.setSize(tileSize);
		pointTile2.setSize(tileSize);
		endOfGameTile.setSize(tileSize);
		chair.setSize(tileSize);
		points.setForeground(Color.white);
		points.setFont(new Font(Font.DIALOG, Font.BOLD, this.getHeight()/36));
		
		this.tiles.add(pointTile1);
		this.tiles.add(pointTile2);
		this.tiles.add(endOfGameTile);
		this.tiles.add(points);
		this.tiles.add(chair);

		//Bookshelf and Personal objective card
		this.bookshelfAndCard = new JPanel();
		this.bookshelfAndCard.setOpaque(false);
		this.bookshelfAndCard.setLayout(new FlowLayout());
		
		this.bookshelfLabel = new BookshelfLabel(this.getSize());
		this.personalObjectiveCardLabel = new JLabel();
		
		personalObjectiveCardLabel.setSize(this.getHeight()/4, this.getHeight()*3/8);
		
		this.bookshelfAndCard.add(bookshelfLabel);
		this.bookshelfAndCard.add(personalObjectiveCardLabel);
		
		//Add each container in order
		this.playerInfoPanel.add(playerNameAndButton);
		this.playerInfoPanel.add(tiles);
		this.playerInfoPanel.add(bookshelfAndCard);
		this.add(playerInfoPanel);
	}
	
	private void Init() {
		this.setResizable(false);
		this.setSize(screenSize.width/2, screenSize.height-40);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setLocation(screenSize.width/2, 0);
		this.setVisible(true);
	}

	public JLabel getPlayerName() {
		return playerName;
	}

	public JLabel getNextPlayerButton() {
		return nextPlayerButton;
	}

	public JLabel getPersonalObjectiveCardLabel() {
		return personalObjectiveCardLabel;
	}

	public JLabel getPointTile1() {
		return pointTile1;
	}

	public JLabel getPointTile2() {
		return pointTile2;
	}

	public JLabel getPoints() {
		return points;
	}

	public JLabel getChair() {
		return chair;
	}

	public BookshelfLabel getBookshelfLabel() {
		return bookshelfLabel;
	}
}
