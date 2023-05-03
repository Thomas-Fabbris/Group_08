package view.commongamearea;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import view.PointTileLabel;

public class CommonObjectiveCardLabel extends JLabel {
	private ImageIcon image;
	private static final String PATH = "Assets/Carte_Obiettivo_Comune/Carta_X.png";

	private int card_height;
	private int card_width;

	private PointTileLabel pointTile;
	
	public CommonObjectiveCardLabel(Dimension frameSize) {
//		this.card_height = SharedGameAreaWindow.getInstance().getSize().height/4;
		this.card_height = frameSize.height / 4;
		this.card_width = card_height*3/2;
		
//		this.image = LoadImageAsIcon(GetImagePath(card_id));
		this.setIcon(image);
		this.setSize(this.card_width, this.card_height);
		
//		points_tile = new PointTileLabel(this.getSize(), starting_points);
//		this.add(pointTile);
		
		this.setVisible(true);
	}
	
	public void setPointTile(PointTileLabel pointTileLabel) {
		this.pointTile = pointTileLabel;
		this.add(pointTileLabel);
	}
	
	//Replaces the X in Carta_X.png with the card's number
	private String GetImagePath(int card_number) {
		if(card_number < 1 || card_number > 12)
			return null;
		
		Integer value = card_number;
		return PATH.replace("X", value.toString());
	}
	
	/**
	 * Updates the tile image to show the new number of points available
	 * @param points
	 */
	public void updatePointsTile(int points) {
//		pointTile.setImage(points);
		throw new UnsupportedOperationException("The method updatePointsTile(int points) has not been implemented yet");
	}
	
	/**
	 * Hides the tile (should be used when there are no points left)
	 */
	public void hidePointsTile() {
		pointTile.setVisible(false);
	}
	
	private ImageIcon LoadImageAsIcon(String image_path) {
		ImageIcon icon = new ImageIcon(image_path);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(this.card_width, this.card_height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
}
