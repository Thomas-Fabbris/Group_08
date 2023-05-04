package view.personalgamearea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.ImageUtils;

public class BookshelfLabel extends JLabel {

	private ImageIcon image;
	public final JPanel tilesContainer = new JPanel(); //The tile labels go inside the JPanel, which also offsets them correctly on the GUI
	
	public BookshelfLabel(Dimension windowSize) {
		this.setSize(windowSize.height*3/5, windowSize.height*3/5);
		image = ImageUtils.loadImageAsIcon(this.getWidth(), this.getHeight(), "Assets/Bookshelf.png");
		this.setIcon(image);
		
		int containerXoffset = (int)(this.getWidth() / 8);
		int containerYoffset = (int)(this.getHeight() / 14);
		int containerWidth = (int)(this.getWidth() - 2 * containerXoffset);
		int containerHeight = (int)(this.getHeight() - 2.7 * containerYoffset);
		
		tilesContainer.setBounds(containerXoffset, containerYoffset, containerWidth, containerHeight);
		tilesContainer.setLayout(null);
		tilesContainer.setOpaque(false);
		
		this.add(tilesContainer);
	}
}
