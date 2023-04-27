package gui.personalgamearea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Adds to a JPanel all the information that need to be displayed about a player in the personal game area
public class PlayerInfo {
	private Dimension windowSize;
	private int tileLength;
	private JPanel playerInfo; //Contains all the info that are displayed on the GUI
	private JPanel tiles; //Contains: point tiles, chair and points
	private JPanel bookshelfAndCard; //Contains: bookshelf and personal objective card 
	private JPanel playerNameAndButton; //Contains: player's name and next player button
	private JLabel playerName;
	private JLabel nextPlayerButton;
	private BookshelfLabel bookshelfLabel;
	private PersonalObjectiveCardLabel personalObjectiveCardLabel;
	private JLabel pointTile1;
	private JLabel pointTile2;
	private JLabel endOfGameTile;
	private JLabel points;
	private JLabel chair;
	
	public PlayerInfo(Dimension windowSize) {
		this.windowSize = windowSize;
		this.tileLength = windowSize.width/12;
		
		//playerInfo
		this.playerInfo = new JPanel();
		this.playerInfo.setLayout(new FlowLayout());
		this.playerInfo.setOpaque(false);

		//playerNameContainer
		this.playerNameAndButton = new JPanel();
		applySettingsToPanel(playerNameAndButton, new BoxLayout(playerNameAndButton, BoxLayout.X_AXIS));
		
		//tiles
		this.tiles = new JPanel();
		applySettingsToPanel(tiles, new BoxLayout(tiles, BoxLayout.X_AXIS));
		
		//bookshelfAndCard
		this.bookshelfAndCard = new JPanel();
		applySettingsToPanel(bookshelfAndCard, new FlowLayout());
		
	}

	//Apply custom settings to the panel
	private void applySettingsToPanel(JPanel panel, LayoutManager layoutManager) {
		panel.setOpaque(false);
		panel.setLayout(layoutManager);
		playerInfo.add(panel);
	}
	
	public void setPlayerName(String name) {
		if(this.playerName == null) {
			this.playerName = new JLabel(name + "'s turn");
			this.playerName.setForeground(Color.white);
			this.playerName.setFont(new Font(Font.DIALOG, Font.BOLD, windowSize.height/22));
			
			this.playerNameAndButton.add(playerName);
			initButton();
		}
		
		playerName.setText(name + "'s turn");
	}
	
	private void initButton() {
		if(this.nextPlayerButton == null) {
			ImageIcon pressedIcon = LoadImageAsIcon("./Assets/ArrowButton/ArrowButtonPressed.png", 4*tileLength, 3*tileLength);
			ImageIcon releasedIcon = LoadImageAsIcon("./Assets/ArrowButton/ArrowButton.png", 4*tileLength, 3*tileLength);
			
			this.nextPlayerButton = new JLabel();
			this.nextPlayerButton.setIcon(releasedIcon);
			this.nextPlayerButton.addMouseListener(new NextPlayerButtonController(nextPlayerButton, pressedIcon, releasedIcon));
			
			this.playerNameAndButton.add(Box.createRigidArea(new Dimension(2*tileLength, tileLength)));
			this.playerNameAndButton.add(nextPlayerButton);
		}
	}
	
	/**
	 * Changes the number of points displayed on the GUI
	 * @param points
	 */
	public void setPoints(int points) {
		if(this.points == null) {
			this.points = new JLabel("Points: 0");
			this.points.setForeground(Color.white);
			this.points.setFont(new Font(Font.DIALOG, Font.BOLD, windowSize.height/36));
			
			tiles.add(this.points);
		}
		this.points.setText("Points: " + Integer.valueOf(points).toString());
	}

	/**
	 * Changes whether the game end tile is displayed or not
	 * @param hasEndOfGameToken
	 */
	public void setEndOfGameTile(boolean hasEndOfGameToken) {
		if(hasEndOfGameToken && this.endOfGameTile == null) {
			endOfGameTile = new JLabel();
			endOfGameTile.setIcon(LoadImageAsIcon("./Assets/Point_tiles/First_to_finish.jpg"));

			tiles.add(endOfGameTile);
			tiles.add(Box.createRigidArea(new Dimension(tileLength/6, tileLength)));
			return;
		}
		
		if(!hasEndOfGameToken && this.endOfGameTile != null)
			tiles.remove(endOfGameTile);
	}
	
	/**
	 * Changes whether the chair (first player token) is displayed or not
	 * @param hasChair
	 */
	public void setChair(boolean hasChair) {
		if(hasChair && this.chair == null) {
			chair = new JLabel();
			chair.setIcon(LoadImageAsIcon("./Assets/Firstplayertoken.png"));
			
			tiles.add(chair);
			tiles.add(Box.createRigidArea(new Dimension(tileLength/6, tileLength)));
		}
		
		if(!hasChair && this.chair != null)
			tiles.remove(chair);
	}
	
	/**
	 * Sets point tile to be displayed
	 * @param pointsFromPointTile
	 */
	public void setPointTile1(int points) {
		if(points == 0)
			return;
		
		if(this.pointTile1 == null) {
			this.pointTile1 = new JLabel();

			this.tiles.add(pointTile1);
			tiles.add(Box.createRigidArea(new Dimension(tileLength/6, tileLength)));
		}
		
		String path = "Assets/Point_tiles/Xp.jpg".replaceAll("X", Integer.valueOf(points).toString());	
		pointTile1.setIcon(LoadImageAsIcon(path));
	}
	
	/**
	 * Sets which point tile should be displayed based on the number of points passed
	 * (0 means no point tile is displayed)
	 * @param pointsFromPointTile
	 */
	public void setPointTile2(int points) {
		if(points == 0)
			return;
		
		if(this.pointTile2 == null) {
			this.pointTile2 = new JLabel();
			
			this.tiles.add(pointTile2);
			tiles.add(Box.createRigidArea(new Dimension(tileLength/6, tileLength)));
		}
		String path = "Assets/Point_tiles/Xp.jpg".replaceAll("X", Integer.valueOf(points).toString());
		pointTile2.setIcon(LoadImageAsIcon(path));
	}

	/**
	 * Sets the bookshelf to be displayed
	 * @param bookshelfLabel
	 */
	public void setBookshelfLabel(BookshelfLabel bookshelfLabel) {
		if(this.bookshelfLabel == null) {

			bookshelfAndCard.add(bookshelfLabel);
		}
		
		this.bookshelfLabel = bookshelfLabel;

	}

	/**
	 * Sets the personal objective card to be displayed
	 * @param personalObjectiveCardLabel
	 */
	public void setPersonalObjectiveCardLabel(PersonalObjectiveCardLabel personalObjectiveCardLabel) {
		if(this.personalObjectiveCardLabel == null) {
			bookshelfAndCard.add(personalObjectiveCardLabel);
		}
		
		this.personalObjectiveCardLabel = personalObjectiveCardLabel;
	}


	public JPanel getPlayerInfoPanel() {
		return playerInfo;
	}
	
	/**
	 * Used to load the game end tile's image from the assets folder
	 * @param image_path
	 * @return
	 */
	private ImageIcon LoadImageAsIcon(String image_path) {
		ImageIcon icon = new ImageIcon(image_path);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(tileLength, tileLength, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
	
	private ImageIcon LoadImageAsIcon(String image_path, int width, int height) {
		ImageIcon icon = new ImageIcon(image_path);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
}
