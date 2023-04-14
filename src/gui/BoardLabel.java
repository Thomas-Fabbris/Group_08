package gui;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BoardLabel extends JLabel {
	private ImageIcon image;
	private static final String BOARD_IMAGE_PATH = "Assets/Board.jpg";
	
	public BoardLabel(Dimension size) {
		this.setSize(size.height-38, size.height-38);
		image = LoadImageAsIcon(BOARD_IMAGE_PATH, getSize().width, getSize().height);
		this.setIcon(image);
		this.setLayout(null);
	}

	private ImageIcon LoadImageAsIcon(String image_path, int width, int height) {
		ImageIcon icon = new ImageIcon(image_path);
		Image image = icon.getImage();
		image = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
}
