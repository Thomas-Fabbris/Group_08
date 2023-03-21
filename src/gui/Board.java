package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

//TO-DO: add board.removeTile(row, column)

public class Board extends JLabel {
	private Tile[][] tiles = new Tile[11][11];
	private final int[] valid_positions = {1,4, 1,5, 2,4, 2,5, 2,6, 3,3, 3,4, 3,5, 3,6, 3,7, 4,2, 4,3, 4,4, 4,5, 4,6, 4,7, 4,8, 4,9, 5,1, 5,2, 5,3, 5,4, 5,5, 5,6, 5,7, 5,8, 5,9, 6,1, 6,2, 6,3, 6,4, 6,5, 6,6, 6,7, 6,8, 7,3, 7,4, 7,5, 7,6, 7,7, 8,4, 8,5, 8,6, 9,5, 9,6};
	public static final String BOARD_IMAGE_PATH = "Assets/Board.png";
	private Dimension size;
	private ImageIcon image;
	
	public Board(Dimension size) {
		this.size = new Dimension(size.height-40, size.height-40);
		Init();
	}
	
	private void Init() {
		//change all dimensions based on size
		System.out.println("[Board.java: Init()] Generating board");
		image = LoadImageAsIcon(BOARD_IMAGE_PATH, size.width, size.height);
		this.setIcon(image);
		this.fill();
	}
	
	private ImageIcon LoadImageAsIcon(String image_path, int width, int height) {
		ImageIcon icon = new ImageIcon(image_path);
		Image image = icon.getImage();
		image = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
	
	/***
	 * Adds a tile
	 * @param tile_type type of tile to add
	 * @param row 0 to 8
	 * @param column 0 to 8
	 * @return reference to the new tile
	 */
	public Tile addTile(TileType tile_type, int row, int column) {
		
		if(!isValidTilePosition(row, column)) {
			throw new InvalidBoardPositionException(row, column);			
		}
		
		Tile tile = new Tile(tile_type, row, column);
		tiles[row][column] = tile;
		this.add(tile);
		return tile;
	}
	
	/***
	 * Used to initialize the board, will set the tile at row/col without any check beforehand
	 * @param tile_type
	 * @param row
	 * @param column
	 */
	private void initTile(TileType tile_type, int row, int column) {
		Tile tile = new Tile(tile_type, row, column);
		tiles[row][column] = tile;
		this.add(tile);
	}
	
	/***
	 * Gets the tile at the specified position
	 * @param row
	 * @param column
	 * @return the tile at specified row / column
	 */
	public Tile tileAt(int row, int column) {
		if(!isValidTilePosition(row, column)) {
			throw new InvalidBoardPositionException(row, column);			
		}
		
		return tiles[row][column];
	}
	
	//Returns whether the specified row/col position is valid
	public boolean isValidTilePosition(int row, int column) {
		return tiles[row][column] != null;
	}
	
	/***
	 * Generates a random tile on every cell of the board
	 */
	private void fill() {
		int row = 0;
		int column = 0;
		
		//for each valid position, create a new tile there
		for (int i = 0; i < valid_positions.length; i+=2) {
			row = valid_positions[i];
			column = valid_positions[i+1];
			initTile(TileType.randomType(), row, column);
		}
	}
	
	//TO-DO check if cell is already occupied
	/***
	 * Regenerates the missing tiles on the board
	 */
	public void refill() {
		throw new UnsupportedOperationException();
	}
}
