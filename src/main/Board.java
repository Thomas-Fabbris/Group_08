package main;

import java.awt.Dimension;

import gui.BoardLabel;

//TO-DO: add board.removeTile(row, column)

public class Board {
	private Tile[][] tiles = new Tile[11][11];
	private final int[] valid_positions = {1,4, 1,5, 2,4, 2,5, 2,6, 3,3, 3,4, 3,5, 3,6, 3,7, 4,2, 4,3, 4,4, 4,5, 4,6, 4,7, 4,8, 4,9, 5,1, 5,2, 5,3, 5,4, 5,5, 5,6, 5,7, 5,8, 5,9, 6,1, 6,2, 6,3, 6,4, 6,5, 6,6, 6,7, 6,8, 7,3, 7,4, 7,5, 7,6, 7,7, 8,4, 8,5, 8,6, 9,5, 9,6};
	private BoardLabel label;
	
	public Board(Dimension boardwindow_size) {
		this.label = new BoardLabel(boardwindow_size);
		this.fill();
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
					
//		Tile tile = new Tile(tile_type, row, column, label.getSize());
//		tiles[row][column] = tile;
//		this.label.add(tile.getLabel());
		
		tiles[row][column].setType(tile_type);
		tiles[row][column].getLabel().setType(tile_type);
		tiles[row][column].setVisible(true);
	}
	
	/***
	 * Gets the TyleType of the tile at the specified position
	 * @param row
	 * @param column
	 * @return the tile at specified row / column
	 */
	public TileType getTileType(int row, int column) {
		if(!isValidPosition(row, column)) {
			throw new InvalidBoardPositionException(row, column);			
		}
		
		return tiles[row][column].getType();
	}
	
	//Returns whether the specified row/col position is valid
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
	
	/***
	 * Used to initialize the board, will set the tile at row/col without any check beforehand
	 * @param tile_type
	 * @param row
	 * @param column
	 */
	private void initTile(TileType tile_type, int row, int column) {
		Tile tile = new Tile(tile_type, row, column, label.getSize());
		tiles[row][column] = tile;
		this.label.add(tile.getLabel());
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
	
	/***
	 * Regenerates the missing tiles on the board
	 */
	public void refill() {
		int row = 0;
		int column = 0;		
		
		for (int i = 0; i < valid_positions.length; i+=2) {
			row = valid_positions[i];
			column = valid_positions[i+1];
			
			if(!tiles[row][column].isVisible()) {
				setTileType(row, column, TileType.randomType());
			}
		}
	}
	
	public void hideAllTiles() {
		int row = 0;
		int column = 0;		
		
		for (int i = 0; i < valid_positions.length; i+=2) {
			row = valid_positions[i];
			column = valid_positions[i+1];
			tiles[row][column].setVisible(false);
		}
	}
}