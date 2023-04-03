package gui;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CommonObjectiveCardLabel extends JLabel {
	private ImageIcon image;
	private static final String PATH = "Assets/Carte_Obiettivo_Comune/Carta_X.png";

	private int card_height;
	private int card_width;
	
	public CommonObjectiveCardLabel(int card_number, Dimension container_size) {
		this.card_height = container_size.height*2/3;
		this.card_width = card_height+card_height/2;
		
		this.image = LoadImageAsIcon(GetImagePath(card_number));
		this.setIcon(image);
		this.setSize(this.card_width, this.card_height);
		this.setVisible(true);
	}
	
	//Replaces the X in Carta_X.png with the card's number
	private String GetImagePath(int card_number) {
		if(card_number < 1 || card_number > 12)
			return null;
		
		Integer value = card_number;
		return PATH.replace("X", value.toString());
	}
	
	private ImageIcon LoadImageAsIcon(String image_path) {
		ImageIcon icon = new ImageIcon(image_path);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(this.card_width, this.card_height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
}
