package gui.personalgamearea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.PointTileLabel;

//Adds to a JPanel all the information that need to be displayed about a player in the personal game area
public class PlayerInfo {
	private Dimension windowSize;
	private int tileLength;
	private JPanel playerInfo; //Contains all the info that are displayed on the GUI
	private BookshelfLabel bookshelfLabel;
	private PersonalObjectiveCardLabel personalObjectiveCardLabel;
	private PointTileLabel pointTile1;
	private PointTileLabel pointTile2;
	private JLabel endOfGameTile;
	private JLabel points;
	
	public PlayerInfo(Dimension windowSize) {
		this.windowSize = windowSize;
		this.playerInfo = new JPanel();
//		this.playerInfo.setLayout(new FlowLayout());
		this.playerInfo.setOpaque(false);
		this.tileLength = windowSize.width/12;
	}

	public void setPoints(int points) {
		if(this.points == null) {
			this.points = new JLabel("Points: ");
			this.points.setForeground(Color.white);
			this.points.setFont(new Font(Font.DIALOG, Font.BOLD, windowSize.height/40));
			playerInfo.add(this.points);
		}
		this.points.setText(this.points.getText() + " " + Integer.valueOf(points).toString());
	}

	public void setEndOfGameTile(boolean hasEndOfGameToken) {
		if(hasEndOfGameToken && this.endOfGameTile == null) {
			endOfGameTile = new JLabel();
			endOfGameTile.setIcon(LoadImageAsIcon("./Assets/Point_tiles/First_to_finish.jpg"));
			playerInfo.add(endOfGameTile);
		} else if (this.endOfGameTile != null) {
			playerInfo.remove(endOfGameTile);
		}
	}
	
	public void setPointTile1(int pointsFromPointTile) {
		throw new UnsupportedOperationException();
	}
	
	public void setPointTile2(int pointsFromPointTile) {
		throw new UnsupportedOperationException();
	}

	public BookshelfLabel getBookshelfLabel() {
		return bookshelfLabel;
	}

	public void setBookshelfLabel(BookshelfLabel bookshelfLabel) {
		if(this.bookshelfLabel != null)
			playerInfo.remove(this.bookshelfLabel);
		
		this.bookshelfLabel = bookshelfLabel;
		playerInfo.add(bookshelfLabel);
	}

	public PersonalObjectiveCardLabel getPersonalObjectiveCardLabel() {
		return personalObjectiveCardLabel;
	}

	public void setPersonalObjectiveCardLabel(PersonalObjectiveCardLabel personalObjectiveCardLabel) {
		if(this.personalObjectiveCardLabel != null)
			playerInfo.remove(this.personalObjectiveCardLabel);
		
		this.personalObjectiveCardLabel = personalObjectiveCardLabel;
		playerInfo.add(personalObjectiveCardLabel);
	}


	public JPanel getPlayerInfoPanel() {
		return playerInfo;
	}
	
	private ImageIcon LoadImageAsIcon(String image_path) {
		ImageIcon icon = new ImageIcon(image_path);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(tileLength, tileLength, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
}
