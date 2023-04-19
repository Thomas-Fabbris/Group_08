package gui.personalgamearea;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BookshelfLabel extends JLabel {
	
	private final String SHELF_IMAGE_PATH = "Assets/Bookshelf.png";
	private ImageIcon image;
	private JPanel tiles_container = new JPanel(); //The tile labels go inside the JPanel, which also offsets them correctly on the GUI
	
	private int container_xoffset;
	private int container_yoffset;
	private int container_width;
	private int container_height;
	private int container_hgap;
	private int container_vgap;
	
	private int tile_length;
	
	public BookshelfLabel(Dimension window_size) {
		this.setSize(window_size.height, window_size.height); //TODO: change size computation 
		image = LoadImageAsIcon(SHELF_IMAGE_PATH);
		this.setIcon(image);
		
		tile_length = tiles_container.getHeight() / 7;
		
		container_xoffset = (int)(window_size.getWidth() / 21.5);
		container_yoffset = (int)(window_size.getHeight() / 9);
		container_width = (int)(window_size.getWidth() - 2 * container_xoffset);
		container_height = (int)(window_size.getHeight() - 2 * container_yoffset);
		
		tiles_container.setBounds(container_xoffset, container_yoffset, container_width, container_height);
		//compute based on window size
//		this.container_hgap = (int)((container_width / 6) - tile_length);//compute based on window size
//		this.container_vgap = (int)((container_height / 6) - tile_length);

		this.container_hgap = tile_length/5;
		this.container_vgap = (int)(tile_length / 2.6);
		tiles_container.setLayout(new GridLayout(5, 4, container_hgap, container_vgap));
	}
	
	private ImageIcon LoadImageAsIcon(String image_path) {
		ImageIcon icon = new ImageIcon(image_path);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(this.getSize().width, this.getSize().height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
}
