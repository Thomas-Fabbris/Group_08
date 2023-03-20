package gui;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Tile extends JLabel {
	public final TileType type;
	private int x_pos;
	private int y_pos;
	private int column;
	private int row;
	public static final int delta_x = 92 + 10; //92 is the width of a tile cell when the board is 1000x1000
	public static final int delta_y = 92 + 9;
	private String folder_path = "Assets/tiles/";
	ImageIcon image;
	
	public Tile(TileType tile_type, int row, int column) {
		this.type = tile_type;
		
		this.row = row;
		this.column = column;
		this.x_pos = convertColumnToXCoords(column);
		this.y_pos = convertRowToYCoords(row);
		
		Init();
	}
	
	private void Init() {
		this.folder_path = GetImagePath(this.type); 
		this.image = LoadImageAsIcon(folder_path, 92, 92);
		this.setIcon(image);
		Dimension size = this.getPreferredSize();
		this.setBounds(this.x_pos, this.y_pos, size.width, size.height);
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
		row -= 1;
		return 27+row*Tile.delta_y;
	}
	
	/***
	 * Converts the column number to the actual x coordinate
	 * @param column
	 * @return the location in pixels of the column on the GUI
	 */
	private int convertColumnToXCoords(int column) {
		column -= 1;
		return 40+column*Tile.delta_x;
	}
	
	private ImageIcon LoadImageAsIcon(String image_path, int width, int height) {
		ImageIcon icon = new ImageIcon(image_path);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
}
