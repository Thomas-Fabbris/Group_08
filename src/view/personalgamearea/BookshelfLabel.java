package view.personalgamearea;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.personalgamearea.Bookshelf;
import view.ImageUtils;

public class BookshelfLabel extends JLabel {

	private ImageIcon image;
	public final BookshelfTileLabel[][] tiles;
	public final JPanel tilesContainer = new JPanel(); //This contains the tile labels displayed on the bookshelf
	
	/**
	 * This is the constructor of the BookshelfLabel class
	 * 
	 * @param windowSize
	 */
	public BookshelfLabel(Dimension windowSize) {
		if(windowSize == null) {
			throw new NullPointerException("windowSize cannot be set to null when creating a BookShelfLabel instance!");
		}
		this.setSize(windowSize.height*3/5, windowSize.height*3/5);
		image = ImageUtils.loadImageAsIcon(this.getWidth(), this.getHeight(), "Assets/Bookshelf.png");
		this.setIcon(image);
		
		this.tiles = new BookshelfTileLabel[Bookshelf.ROWS][Bookshelf.COLUMNS];
		
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
