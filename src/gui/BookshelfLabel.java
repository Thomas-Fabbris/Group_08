package gui;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BookshelfLabel extends JLabel {
	
	private final String SHELF_IMAGE_PATH = "";
	private ImageIcon image;
	
	private int hgap;
	private int vgap;
	
	public BookshelfLabel(Dimension window_size) {
		this.setSize(window_size.height, window_size.height); //TODO: change size computation 
		image = LoadImageAsIcon(SHELF_IMAGE_PATH, getSize().width, getSize().height);
		this.setIcon(image);
		
		this.hgap = //compute based on window size
		this.vgap =
		this.setLayout(new GridLayout(5, 4, hgap, vgap));
	}
}
