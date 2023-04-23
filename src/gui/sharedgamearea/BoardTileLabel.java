package gui.sharedgamearea;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.TileType;

public class BoardTileLabel extends JLabel {
	
	private final String folder_path = "Assets/tiles/";
	ImageIcon image;
	
	//informazioni sulla posizione del tile in pixels
	private int x_pos;
	private int y_pos;
	
	//dimensioni del tile in pixels
	public final int tileLength;
	private final int tileCellLength;
	private final int offset;
	
	public BoardTileLabel(TileType tile_type, int row, int column, Dimension board_size) {
		this.tileLength = board_size.height/12;
		this.offset = board_size.height/17;
		this.tileCellLength = board_size.width / 10;
		this.x_pos = convertColumnToXCoords(column);
		this.y_pos = convertRowToYCoords(row);
		
		setType(tile_type);
		this.setBounds(this.x_pos, this.y_pos, tileLength, tileLength);
	}
	
	private String GetImagePath(TileType tile_type) {		
		String image_name = tile_type.toString(); //BOOKS
		String image_substring = image_name.substring(1,image_name.length()); //BOOKS -> OOKS
		return this.folder_path + image_name.charAt(0) + image_substring.toLowerCase() + ".png"; //'Assets/tiles/' + 'B' + 'ooks' + '.png'
	}
	
	private ImageIcon LoadImageAsIcon(String image_path) {
		ImageIcon icon = new ImageIcon(image_path);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(this.tileLength, this.tileLength, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
	
	public void setType(TileType type) {
		if(type == TileType.NULL) return;
		this.image = LoadImageAsIcon(GetImagePath(type));
		setIcon(this.image);
	}
	
	/***
	 * Converts the row number to the actual y coordinate
	 * @param row
	 * @return the location in pixels of the row on the GUI
	 */
	private int convertRowToYCoords(int row) {
		return offset+((row - 1) * tileCellLength);
	}
	
	/***
	 * Converts the column number to the actual x coordinate
	 * @param column
	 * @return the location in pixels of the column on the GUI
	 */
	private int convertColumnToXCoords(int column) {
		return offset+((column - 1) * tileCellLength);
	}
}
