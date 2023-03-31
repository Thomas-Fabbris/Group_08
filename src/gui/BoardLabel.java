package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import sharedgamearea.Board;

public class BoardLabel extends JLabel {
	private ImageIcon image;
	private static final String BOARD_IMAGE_PATH = "Assets/Board.png";
	
	private static final int gap = BoardWindow.getInstance().getSize().height/11;
//	private static final int vgap =
	
	public BoardLabel(Dimension size, Board board) {
		this.setSize(size.height-38, size.height-38);
		image = LoadImageAsIcon(BOARD_IMAGE_PATH, getSize().width, getSize().height);
		this.setIcon(image);
		this.setLayout(new GridLayout(11, 11, 8, 8));
		BoardWindow.getInstance().add(this);
	}

	private ImageIcon LoadImageAsIcon(String image_path, int width, int height) {
		ImageIcon icon = new ImageIcon(image_path);
		Image image = icon.getImage();
		image = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
}
