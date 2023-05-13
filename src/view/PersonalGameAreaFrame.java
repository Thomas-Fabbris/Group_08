package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.personalgamearea.BookshelfLabel;

public class PersonalGameAreaFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2242387074378483496L;

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
		
		int tileLength = this.getWidth()/12;
		
		playerInfoPanel = new JPanel();
		playerInfoPanel.setLayout(new FlowLayout());
		playerInfoPanel.setOpaque(false);
		
		//Player's name and Next player button
		playerNameAndButton = new JPanel();
		playerNameAndButton.setOpaque(false);
		playerNameAndButton.setLayout(new BoxLayout(playerNameAndButton, BoxLayout.X_AXIS));
		
		playerName = new JLabel();
		playerName.setForeground(Color.white);
		playerName.setFont(new Font(Font.DIALOG, Font.BOLD, this.getHeight()/22));
		
		this.nextPlayerButton = new JLabel();
		nextPlayerButton.setIcon(ImageUtils.loadImageAsIcon(3 * tileLength, 2 * tileLength, "Assets/ArrowButton/ArrowButton.png"));
		
		playerNameAndButton.add(playerName);
		playerNameAndButton.add(Box.createRigidArea(new Dimension(3*tileLength, tileLength)));
		playerNameAndButton.add(nextPlayerButton);
				
		//Tiles
		tiles = new JPanel();
		tiles.setOpaque(false);
		tiles.setLayout(new BoxLayout(tiles, BoxLayout.X_AXIS));
		
		pointTile1 = new JLabel();
		pointTile2 = new JLabel();
		endOfGameTile = new JLabel();
		chair = new JLabel();
		points = new JLabel();
		
		chair.setSize(tileLength, tileLength);
		pointTile1.setSize(tileLength, tileLength);
		pointTile2.setSize(tileLength, tileLength);
		endOfGameTile.setSize(tileLength, tileLength);
		points.setForeground(Color.white);
		points.setFont(new Font(Font.DIALOG, Font.BOLD, this.getHeight()/36));
		
		chair.setIcon(ImageUtils.loadImageAsIcon(tileLength, tileLength, "Assets/Firstplayertoken.png"));
		pointTile1.setIcon(ImageUtils.loadImageAsIcon(tileLength, tileLength, "Assets/Point_tiles/4p.jpg"));
		pointTile2.setIcon(ImageUtils.loadImageAsIcon(tileLength, tileLength, "Assets/Point_tiles/8p.jpg"));
		endOfGameTile.setIcon(ImageUtils.loadImageAsIcon(tileLength, tileLength, "Assets/Point_tiles/First_to_finish.jpg"));
		
		tiles.add(chair);
		tiles.add(Box.createRigidArea(new Dimension(tileLength/6, tileLength)));
		tiles.add(pointTile1);
		tiles.add(Box.createRigidArea(new Dimension(tileLength/6, tileLength)));
		tiles.add(pointTile2);
		tiles.add(Box.createRigidArea(new Dimension(tileLength/6, tileLength)));
		tiles.add(endOfGameTile);
		tiles.add(Box.createRigidArea(new Dimension(tileLength/6, tileLength)));
		tiles.add(points);

		//Bookshelf and Personal objective card
		bookshelfAndCard = new JPanel();
		bookshelfAndCard.setOpaque(false);
		bookshelfAndCard.setLayout(new FlowLayout());
		
		bookshelfLabel = new BookshelfLabel(this.getSize());
		personalObjectiveCardLabel = new JLabel();
		
		personalObjectiveCardLabel.setSize(this.getHeight()/4, this.getHeight()*3/8);
		
		bookshelfAndCard.add(bookshelfLabel);
		bookshelfAndCard.add(personalObjectiveCardLabel);
		
		//Add each container in order
		int separatorHeight = (this.getHeight() - tileLength - bookshelfLabel.getHeight() - playerName.getHeight())/4;

		playerInfoPanel.add(playerNameAndButton);
		playerInfoPanel.add(Box.createRigidArea(new Dimension(this.getWidth(), separatorHeight)));
		playerInfoPanel.add(tiles);
		playerInfoPanel.add(bookshelfAndCard);
		add(playerInfoPanel);
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

	public JLabel getEndOfGameTile() {
		return endOfGameTile;
	}
}
