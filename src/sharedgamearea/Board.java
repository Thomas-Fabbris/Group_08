package sharedgamearea;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import gui.BoardLabel;
import gui.SharedGameAreaWindow;
import main.TileType;

public class Board {
	private final static int BOARD_LENGTH = 11;
	
	private BoardTile[][] tiles = new BoardTile[BOARD_LENGTH][BOARD_LENGTH];
	private boolean[][] valid_positions = new boolean[BOARD_LENGTH][BOARD_LENGTH];
	private final String VALID_POSITIONS_FILE_PATH = "./Assets/valid_board_positions.txt";
	private BoardLabel label;
	
	public Board() {
		valid_positions = readValidBoardPositionsFile(new File(VALID_POSITIONS_FILE_PATH));
		this.label = new BoardLabel(SharedGameAreaWindow.getInstance().getBoardSize());
		this.fill();
		SharedGameAreaWindow.getInstance().setVisible(true);
	}
	
	public BoardLabel getLabel() {
		return label;
	}
	
	/***
	 * Adds a tile
	 * @param tile_type type of tile to add
	 * @param row 1 to 9
	 * @param column 1 to 9
	 * @return reference to the new tile
	 */
	public void setTileType(int row, int column, TileType tile_type) {
		
		if(!isValidPosition(row, column))
			throw new InvalidBoardPositionException(row, column);			
		
		tiles[row][column].setType(tile_type);
		tiles[row][column].getLabel().setType(tile_type);
		tiles[row][column].setVisible(true);
	}
	
	/***
	 * Gets the TileType of the tile at the specified position
	 * @param row
	 * @param column
	 * @return the type of the tile at specified row / column
	 */
	public TileType getTileType(int row, int column) {
		if(!isValidPosition(row, column)) {
			throw new InvalidBoardPositionException(row, column);			
		}
		
		return tiles[row][column].getType();
	}
	
	
	/***
	 * Gets the tile at the specified position
	 * @param row
	 * @param column
	 * @return the tile at specified row / column
	 */
	public BoardTile getTile(int row, int column) {
		return tiles[row][column];
	}
	
	/**
	 * @param row
	 * @param column
	 * @return Returns whether the specified row/col position is valid
	 */
	public boolean isValidPosition(int row, int column) {
		return tiles[row][column] != null;
	}
	
	/***
	 * Sets whether the tile at row/col is visible on the board GUI
	 * @param row
	 * @param column
	 * @param isVisible
	 */
	public void setTileVisible(int row, int column, boolean isVisible) {
		
		if(!isValidPosition(row, column))
			throw new InvalidBoardPositionException(row, column);			
		
		tiles[row][column].setVisible(isVisible);
	}
	
	public boolean isTileVisible(int row, int column) {
		if(!isValidPosition(row, column))
			throw new InvalidBoardPositionException(row, column);			
		
		return tiles[row][column].isVisible();
	}
	
	/**
	 * Fills the valid_positions array according to the contents of the file
	 * @param file with positions: 1 for valid, 0 for non valid
	 * @return
	 */
	public boolean[][] readValidBoardPositionsFile(File file) {
		boolean[][] positions = new boolean[BOARD_LENGTH][BOARD_LENGTH];
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String line;
		char c;
		
		for (int i = 0; i < BOARD_LENGTH; i++) {
			
			line = scanner.nextLine().replaceAll("\\s+", "");
			
			for (int j = 0; j < BOARD_LENGTH; j++) {
				c = line.charAt(j);
				if(c == '1') positions[i][j] = true;
				else positions[i][j] = false;
			}
		}
		
		return positions;
	}
	
	/***
	 * Used to initialize the board, will set the tile at row/col without any check beforehand
	 * @param tile_type
	 * @param row
	 * @param column
	 */
	private void initTile(TileType tile_type, int row, int column) {
		BoardTile tile = new BoardTile(tile_type, row, column, label.getSize());
		tiles[row][column] = tile;	
		this.label.add(tile.getLabel());
	}
	
	private void initTiles() {
		Dimension boardlabel_size = label.getSize();
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				if(valid_positions[i][j])
					tiles[i][j] = new BoardTile(TileType.randomType(), i, j, boardlabel_size);
				else {
					tiles[i][j] = new BoardTile(TileType.NULL, i, j, boardlabel_size);
					tiles[i][j].setVisible(false);
				}
				
				this.label.add(tiles[i][j].getLabel());
			}
		}
	}
	
	/***
	 * Generates a random tile on every valid cell of the board
	 */
	private void fill() {
		initTiles();
	}
	
	/***
	 * Regenerates the missing tiles on the board
	 */
	public void refill() {
//		int row = 0;
//		int column = 0;		
//		
//		for (int i = 0; i < valid_positions.length; i+=2) {
//			row = valid_positions[i];
//			column = valid_positions[i+1];
//			
//			if(!tiles[row][column].isVisible()) {
//				setTileType(row, column, TileType.randomType());
//			}
//		}
		throw new UnsupportedOperationException();
	}
	
	public void hideAllTiles() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				tiles[i][j].setVisible(false);
			}
		}
	}
}
