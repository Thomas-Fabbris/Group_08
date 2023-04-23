package gui.personalgamearea;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BookshelfLabel extends JLabel {
	
	private final String SHELF_IMAGE_PATH = "Assets/Bookshelf.png";
	private ImageIcon image;
	public final JPanel tilesContainer = new JPanel(); //The tile labels go inside the JPanel, which also offsets them correctly on the GUI
	
	private int containerXoffset;
	private int containerYoffset;
	private int containerWidth;
	private int containerHeight;
	
	public BookshelfLabel(Dimension window_size) {
		this.setSize(window_size.height*3/5, window_size.height*3/5);
		image = LoadImageAsIcon(SHELF_IMAGE_PATH);
		this.setIcon(image);
		
		containerXoffset = (int)(window_size.width / 12.5);
		containerYoffset = (int)(window_size.height / 23);
		containerWidth = (int)(this.getWidth() - 2 * containerXoffset);
		containerHeight = (int)(this.getHeight() - 2.7 * containerYoffset);
		
		tilesContainer.setBounds(containerXoffset, containerYoffset, containerWidth, containerHeight);
		tilesContainer.setLayout(null);
		tilesContainer.setOpaque(false);
		
		this.add(tilesContainer);
	}
	
	private ImageIcon LoadImageAsIcon(String image_path) {
		ImageIcon icon = new ImageIcon(image_path);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(this.getSize().width, this.getSize().height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
}
