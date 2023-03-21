package gui;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Tile extends JLabel {
	public final TileType type;
	private String folder_path = "Assets/tiles/";
	ImageIcon image;
	
	//informazioni sulla posizione del tile
	private int x_pos;
	private int y_pos;
	private int column;
	private int row;
	
	//dimensioni del tile
	private final int tile_length;
	private final int offset_top;
	private final int offset_left;
	private final int tile_cell_width;
	private final int tile_cell_height;	
	
	public Tile(TileType tile_type, int row, int column, Dimension board_size) {
		this.type = tile_type;
		this.row = row;
		this.column = column;
		this.tile_length = board_size.height/12;
		this.offset_top = board_size.height/18;
		this.offset_left = board_size.width/18;
		this.tile_cell_width = board_size.width / 10;
		this.tile_cell_height = board_size.height / 10;
		this.x_pos = convertColumnToXCoords(column);
		this.y_pos = convertRowToYCoords(row);
		Init();
	}
	
	private void Init() {
		this.folder_path = GetImagePath(this.type); 
		this.image = LoadImageAsIcon(folder_path, tile_length, tile_length);
		this.setIcon(image);
		this.setBounds(this.x_pos, this.y_pos, tile_length, tile_length);
	}
	
	private String GetImagePath(TileType tile_type) {		
		String image_name = tile_type.toString(); //BOOKS
		String image_substring = image_name.substring(1,image_name.length()); //BOOKS -> OOKS
		return this.folder_path + image_name.charAt(0) + image_substring.toLowerCase() + ".png"; //'Assets/tiles/' + 'B' + 'ooks' + '.png'
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	/***
	 * Converts the row number to the actual y coordinate
	 * @param row
	 * @return the location in pixels of the row on the GUI
	 */
	private int convertRowToYCoords(int row) {
		return offset_top+((row - 1) * tile_cell_height);
	}
	
	/***
	 * Converts the column number to the actual x coordinate
	 * @param column
	 * @return the location in pixels of the column on the GUI
	 */
	private int convertColumnToXCoords(int column) {
		return offset_left+((column - 1) * tile_cell_width);
	}
	
	private ImageIcon LoadImageAsIcon(String image_path, int width, int height) {
		ImageIcon icon = new ImageIcon(image_path);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
}
