package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.personalgamearea.BookshelfLabel;

public class PersonalGameAreaFrame extends JFrame {

	private static final long serialVersionUID = -2242387074378483496L;

	private Dimension screenSize;
	public final int tileLength;

	private JPanel playerInfoPanel; // Contains all the info that are displayed on the GUI
	private JPanel tiles; // Contains: point tiles, chair and points
	private JPanel bookshelfAndCard; // Contains: bookshelf and personal objective card
	private JPanel playerNameAndButton; // Contains: player's name and next player button
	private JPanel warningsPanel; // Contains: warning label

	private JLabel playerName;
	private JLabel nextPlayerButton;
	private BookshelfLabel bookshelfLabel;
	private JLabel personalObjectiveCardLabel;
	private JLabel pointTiles[];
	private JLabel endOfGameTile;
	private JLabel points;
	private JLabel chair;
	private JLabel warnings;

	private ImageIcon emptyPointTileIcon;
	private ImageIcon emptyGameEndIcon;
	private ImageIcon gameEndTileIcon;

	public PersonalGameAreaFrame(Dimension screenSize) {
		super("MyShelfie");
		if (screenSize == null) {
			throw new NullPointerException(
					"screenSize cannot be set to null while creating a PersonalGameAreaFrame instance!");
		}
		this.screenSize = screenSize;
		Init();

		this.tileLength = this.getWidth() / 12;
		Dimension tileSize = new Dimension(tileLength, tileLength);
		this.emptyPointTileIcon = ImageUtils.loadImageAsIcon(tileSize, "Assets/Point_tiles/Point_empty.png");
		this.emptyGameEndIcon = ImageUtils.loadImageAsIcon(tileSize, "Assets/Point_tiles/GameEnd_emtpty.png");
		this.gameEndTileIcon = ImageUtils.loadImageAsIcon(tileSize, "Assets/Point_tiles/First_to_finish.jpg");

		playerInfoPanel = new JPanel();
		playerInfoPanel.setLayout(new FlowLayout());
		playerInfoPanel.setOpaque(false);

		// Player's name and Next player button
		playerNameAndButton = new JPanel();
		playerNameAndButton.setOpaque(false);
		playerNameAndButton.setLayout(new BoxLayout(playerNameAndButton, BoxLayout.X_AXIS));

		playerName = new JLabel();
		playerName.setForeground(Color.white);
		playerName.setFont(new Font(Font.DIALOG, Font.BOLD, this.getHeight() / 22));

		this.nextPlayerButton = new JLabel();
		nextPlayerButton.setIcon(
				ImageUtils.loadImageAsIcon(3 * tileLength, 2 * tileLength, "Assets/ArrowButton/ArrowButton.png"));

		playerNameAndButton.add(playerName);
		playerNameAndButton.add(Box.createRigidArea(new Dimension(tileLength * 2, tileLength)));
		playerNameAndButton.add(nextPlayerButton);

		// Warnings
		warningsPanel = new JPanel();
		warningsPanel.setOpaque(false);
		warningsPanel.setLayout(new BoxLayout(warningsPanel, BoxLayout.X_AXIS));

		warnings = new JLabel();
		warnings.setFont(new Font(Font.DIALOG, Font.BOLD, this.getHeight() / 40));
		warnings.setForeground(Color.RED);
		warnings.setVisible(false);
		warningsPanel.add(warnings);

		// Tiles
		tiles = new JPanel();
		tiles.setOpaque(false);
		tiles.setLayout(new BoxLayout(tiles, BoxLayout.X_AXIS));

		this.pointTiles = new JLabel[2];

		pointTiles[0] = new JLabel();
		pointTiles[1] = new JLabel();
		endOfGameTile = new JLabel();
		chair = new JLabel();
		points = new JLabel();

		chair.setSize(tileLength, tileLength);

		pointTiles[0].setSize(tileSize);
		pointTiles[1].setSize(tileSize);
		pointTiles[0].setIcon(this.emptyPointTileIcon);
		pointTiles[1].setIcon(this.emptyPointTileIcon);
		endOfGameTile.setSize(tileLength, tileLength);
		points.setForeground(Color.white);
		points.setFont(new Font(Font.DIALOG, Font.BOLD, this.getHeight() / 36));

		chair.setSize(tileSize);
		chair.setIcon(ImageUtils.loadImageAsIcon(tileSize, "Assets/Firstplayertoken.png"));
		chair.setPreferredSize(tileSize);
		chair.setMaximumSize(tileSize);

		endOfGameTile.setIcon(this.emptyGameEndIcon);

		tiles.add(chair);
		tiles.add(Box.createRigidArea(new Dimension(tileLength / 6, tileLength)));
		tiles.add(pointTiles[0]);
		tiles.add(Box.createRigidArea(new Dimension(tileLength / 6, tileLength)));
		tiles.add(pointTiles[1]);
		tiles.add(Box.createRigidArea(new Dimension(tileLength / 6, tileLength)));
		tiles.add(endOfGameTile);
		tiles.add(Box.createRigidArea(new Dimension(tileLength / 6, tileLength)));
		tiles.add(points);

		// Bookshelf and Personal objective card
		bookshelfAndCard = new JPanel();
		bookshelfAndCard.setOpaque(false);
		bookshelfAndCard.setLayout(new FlowLayout());

		bookshelfLabel = new BookshelfLabel(this.getSize());
		personalObjectiveCardLabel = new JLabel();

		personalObjectiveCardLabel.setSize(this.getHeight() / 4, this.getHeight() * 3 / 8);

		bookshelfAndCard.add(bookshelfLabel);
		bookshelfAndCard.add(personalObjectiveCardLabel);

		// Add each container in order
		int separatorHeight = (this.getHeight() - tileLength - bookshelfLabel.getHeight() - playerName.getHeight()) / 4;

		playerInfoPanel.add(playerNameAndButton);
		playerInfoPanel.add(Box.createRigidArea(new Dimension(this.getWidth(), 5)));
		playerInfoPanel.add(warningsPanel);
		playerInfoPanel.add(Box.createRigidArea(new Dimension(this.getWidth(), separatorHeight)));
		playerInfoPanel.add(tiles);
		playerInfoPanel.add(bookshelfAndCard);
		add(playerInfoPanel);
	}

	private void Init() {
		this.setResizable(false);
		this.setSize(screenSize.width / 2, screenSize.height - 40);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setLocation(screenSize.width / 2, 0);
		this.setVisible(true);
		this.setTitle("MyShelfie - Personal Game Area");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("./Assets/Icon.png"));
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

	/**
	 * Returns the label of the associated point tile taken from the card with the
	 * specified id (either 0 or 1).
	 * 
	 * @param id id of the common objective card with this point tile, can be either
	 *           0 or 1.
	 * @return
	 */
	public JLabel getPointTile(int id) {
		if (id == 0 || id == 1) {
			return pointTiles[id];
		}
		throw new IllegalArgumentException(
				"index can be either 0 or 1 while calling PersonalGameAreaFrame.getPointTile() method!");
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

	public JLabel getWarnings() {
		return warnings;
	}

	public ImageIcon getEmptyPointTileIcon() {
		return this.emptyPointTileIcon;
	}

	public ImageIcon getEmptyGameEndIcon() {
		return this.emptyGameEndIcon;
	}

	public ImageIcon getGameEndTileIcon() {
		return this.gameEndTileIcon;
	}
}
