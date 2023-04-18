package gui;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.TileType;
import personalgamearea.Bookshelf;

public class BookshelfTileLabel extends JLabel {
	
	private final String folder_path = "Assets/tiles/";
	ImageIcon image;
	private int tile_length;
	
	public BookshelfTileLabel(TileType tile_type, Bookshelf shelf) {
		
		//TODO: compute dimensions
		this.tile_length = ;
		
		setType(tile_type);
		this.setSize(tile_length, tile_length);
	}
	
	private String GetImagePath(TileType tile_type) {		
		String image_name = tile_type.toString(); //BOOKS
		String image_substring = image_name.substring(1,image_name.length()); //BOOKS -> OOKS
		return this.folder_path + image_name.charAt(0) + image_substring.toLowerCase() + ".png"; //'Assets/tiles/' + 'B' + 'ooks' + '.png'
	}
	
	private ImageIcon LoadImageAsIcon(String image_path) {
		ImageIcon icon = new ImageIcon(image_path);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(this.tile_length, this.tile_length, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
	
	public void setType(TileType type) {
		if(type == TileType.NULL) return;
		this.image = LoadImageAsIcon(GetImagePath(type));
		setIcon(this.image);
	}
}
