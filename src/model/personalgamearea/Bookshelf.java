package model.personalgamearea;

import java.util.Iterator;

import model.Player;
import model.commongamearea.BoardTile;
import model.shared.TileType;

public class Bookshelf implements Iterable<Bookshelf> {

	public static final int ROWS = 6;
	public static final int COLUMNS = 5;
	public final BookshelfTile[][] tiles = new BookshelfTile[ROWS][COLUMNS];
	private Player player;
	public boolean stateChanged = false; // stateChanged is a flag determining if the player has updated his bookshelf
											// during the turn

	/**
	 * This is the constructor of the class
	 * 
	 * @param player
	 */
	public Bookshelf(Player player) {
		initTiles();
		this.stateChanged = false;
	}

	/**
	 * 	This method initializes all tiles in the bookshelf, sets each tile to NULL.
	 *	This means that the tile is not displayed graphically
	 *	
	 */
	private void initTiles() {
		for (int row = 0; row < ROWS; row++) {
			for (int column = 0; column < COLUMNS; column++) {
				tiles[row][column] = new BookshelfTile(TileType.NULL, row, column, this);
			}
		}
	}
	
	/**
	 * This method fills the bookshelf with tiles.
	 * This allows to test the good working of the graphic interface on the bookshelf
	 */
	public void fillRandom() {
		for (int row = 0; row < ROWS; row++) {
			for (int column = 0; column < COLUMNS; column++) {
				tiles[row][column].setType(TileType.randomType());
			}
		}
	}
	
	/**
	 * This method allows to throw the UnsupportedOperationException     
	 * 
	 * @param tile
	 * @param column
	 * @throws UnsupportedOperationException
	 */
	public void dropTile(BoardTile tile, int column) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * This method allows to add a tile to the bookshelf      
	 * 
	 * @param tile
	 * @param row
	 * @param column
	 */
	public void addTile(BoardTile tile, int row, int column) {
		tiles[row][column].setType(tile.getType());
	}

	/**
	 * This method returns a boolean that indicates if the selected bookshelf is full or not
	 * 
	 * @return boolean (true if full, else false)
	 */
	public boolean isFull() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLUMNS; col++) {
				if (this.tiles[row][col] != null && this.tiles[row][col].getType() == TileType.NULL) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method returns the tile at specified row / column of the bookshelf       
	 * 
	 * @param row
	 * @param column
	 * @return tiles[][]
	 */
	public BookshelfTile getTile(int row, int column) {
		if (!isValidCell(row, column))
			throw new IllegalArgumentException(
					"row " + row + " and column " + column + " are not valid coordinates on the Bookshelf!");
		return tiles[row][column];
	}
	
	/**
	 * This method returns the type of the tiles on the @param row   
	 * 
	 * @param row
	 * @return res_row
	 */
	public TileType[] getRow(int row) {

		TileType[] res_row = new TileType[5];

		for (int col = 0; col < 5; col++) {
			res_row[col] = tiles[row][col].getType();
		}

		return res_row;

	}
	
	/**
	 * This method returns the type of the tiles on the @param column
	 * 
	 * @param column
	 * @return res_col
	 */
	public TileType[] getColumn(int column) {

		TileType[] res_col = new TileType[6];

		for (int row = 0; row < 6; row++) {
			res_col[row] = tiles[row][column].getType();
		}

		return res_col;

	}
	
	/**
	 * This method sets the type of the tile in the position given by the parameters row and column     
	 * 
	 * @param type
	 * @param row
	 * @param column
	 */
	public void setTileType(TileType type, int row, int column) {
		tiles[row][column].setType(type);
	}
	
	/**
	 * This method returns a boolean that indicates if the position given by the parameters row
	 * and column actually exists on the bookshelf
	 * 
	 * @param row
	 * @param column
	 * @return boolean
	 */
	private boolean isValidCell(int row, int column) {
		return (row >= 0 && row < 6) && (column >= 0 && column < 5); // row 0-5 | column 0-4
	}
	
	/**
	 * This method returns null
	 * 
	 * @return null
	 */
	@Override
	public Iterator<Bookshelf> iterator() {
		return null;
	}

	/**
	 * This method returns a boolean
	 * 
	 * @return the stateChanged
	 */
	public boolean isStateChanged() {
		return stateChanged;
	}

	/**
	 * This method sets the	stateChanged attribute (boolean)
	 * 
	 * @param stateChanged
	 */
	public void setStateChanged(boolean stateChanged) {
		this.stateChanged = stateChanged;
	}

	/**
	 * This method adds a tile to the bookshelf and checks if the selected column actually exists
	 * It changes the state the position of the tile added
	 * 
	 * @param tile
	 * @param col
	 * @throws IllegalArgumentException
	 */
	public void addTile(BookshelfTile tile, int col) {
		if (col > COLUMNS) {
			throw new IllegalArgumentException("Unable to add a tile in a non-existing column in the bookshelf!");
		}
		for (int row = ROWS - 1; row >= 0; row--) {
			if (this.tiles[row][col].getType() == TileType.NULL) {
				this.tiles[row][col] = tile;
				this.stateChanged = true;
				return;
			}
		}

	}

	/**
	 * This method finds the first available (free) row on a specified column. Returns -1 if the
	 * column is full
	 * 
	 * @param column
	 * @return row
	 * @return -1
	 */
	public int getFirstFreeRow(int column) {
		int row = 0;

		// Check if the column is full before looping through it
		if (tiles[0][column].getType() != TileType.NULL) {
			return -1;
		}

		for (; row < Bookshelf.ROWS; row++) {

			// If we get to the end of the column, return the last possible row position
			if (row + 1 >= Bookshelf.ROWS) {
				return Bookshelf.ROWS - 1;
			}

			if (tiles[row + 1][column].getType() != TileType.NULL) {
				return row;
			}
		}

		return -1;
	}

	/**
	 * This method returns whether the bookshelf has enough space to insert a certain number of tiles
	 * 
	 * @param tiles (the number of tiles to add to the bookshelf)
	 * @return boolean
	 */
	public boolean hasAvaibleSpaceFor(int tiles) {

		if (tiles == 0) { // There is always available space to fit zero tiles
			return true;
		}

		// For each column
		for (int col = 0; col < Bookshelf.COLUMNS; col++) {
			int freeCells = 0;

			// Check if the first 3 cells going downwards are free
			for (int row = 0; row < 3; row++) {
				if (this.tiles[row][col].getType() == TileType.NULL) // If a cell is occupied, increment the counter
					freeCells++;
			}
			if (freeCells >= tiles) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method returns whether a single column has enough free cells to insert from 1 to 3 tiles.
	 * It checks the number of free cells from the top of the bookshelf of the column given as parameter.
	 *  
	 * If the number of free cells is at least equal to the number of tiles given as parameter the method
	 * returns true, else it returns false.
	 *  	
	 * @param column (column to be checked) 
	 * @param tiles (the number of tiles to add to the column) 
	 * @return boolean
	 */
	public boolean columnHasSpaceAvailable(int column, int tiles) {
		int freeCells = 0;
		int col = column;

		// Check if the first 3 cells going downwards are free
		for (int row = 0; row < 3; row++) {
			if (this.tiles[row][col].getType() == TileType.NULL) // If a cell is not occupied, increment the counter
				freeCells++;
		}
		if (freeCells >= tiles) {
			return true;
		} else
		return false;
	}
}
