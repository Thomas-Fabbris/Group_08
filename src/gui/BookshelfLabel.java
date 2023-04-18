package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BookshelfLabel extends JLabel {
	
	private final String SHELF_IMAGE_PATH = "Assets/Bookshelf.png";
	private ImageIcon image;
	private JPanel TilesContainer = new JPanel();
	
	private int hgap;
	private int vgap;
	
	public BookshelfLabel(Dimension window_size) {
		this.setSize(window_size.height, window_size.height); //TODO: change size computation 
		image = LoadImageAsIcon(SHELF_IMAGE_PATH);
		this.setIcon(image);
		
		this.hgap = 0;//compute based on window size
		this.vgap = 0;
		TilesContainer.setLayout(new GridLayout(5, 4, hgap, vgap));
	}
	
	private ImageIcon LoadImageAsIcon(String image_path) {
		ImageIcon icon = new ImageIcon(image_path);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(this.getSize().width, this.getSize().height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
}
