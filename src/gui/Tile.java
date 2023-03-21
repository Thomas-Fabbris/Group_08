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
	private String folder_path = "Assets/tiles/";
	public static int SIDE_LENGTH = 11;
	public static final int delta = SIDE_LENGTH + SIDE_LENGTH/8; //distanza tra due tiles
	public static final int delta_y = SIDE_LENGTH + SIDE_LENGTH/8;
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
		this.image = LoadImageAsIcon(folder_path, SIDE_LENGTH, SIDE_LENGTH); //92x92
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
		int d = 22;
		return d+row*Tile.delta; //
	}
	
	/***
	 * Converts the column number to the actual x coordinate
	 * @param column
	 * @return the location in pixels of the column on the GUI
	 */
	private int convertColumnToXCoords(int column) {
		column -= 1;
		int d = 38;
		return column*Tile.delta + d;
	}
	
	private ImageIcon LoadImageAsIcon(String image_path, int width, int height) {
		ImageIcon icon = new ImageIcon(image_path);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
}
