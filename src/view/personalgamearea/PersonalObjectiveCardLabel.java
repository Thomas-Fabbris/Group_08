package view.personalgamearea;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import view.sharedgamearea.SharedGameAreaWindow;

public class PersonalObjectiveCardLabel extends JLabel {
	private ImageIcon image;
	private static final String PATH = "Assets/Carte_Obiettivo_Personale/Carta_X.png";

	private int card_height;
	private int card_width;
	
	public PersonalObjectiveCardLabel(int card_id) {
		this.card_width = SharedGameAreaWindow.getInstance().getSize().height/4;
		this.card_height = card_width*3/2;
		
		this.image = LoadImageAsIcon(GetImagePath(card_id));
		this.setIcon(image);
		this.setSize(this.card_width, this.card_height);
		
		this.setVisible(true);
	}
	
	//Replaces the X in Carta_X.png with the card's number
	private String GetImagePath(int card_id) {
		if(card_id < 1 || card_id > 12)
			return null;
		
		Integer value = card_id;
		return PATH.replace("X", value.toString());
	}
	
	private ImageIcon LoadImageAsIcon(String image_path) {
		ImageIcon icon = new ImageIcon(image_path);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(this.card_width, this.card_height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
}
