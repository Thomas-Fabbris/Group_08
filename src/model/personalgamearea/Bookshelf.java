package model.personalgamearea;

import java.util.Iterator;

import model.Player;
import model.commongamearea.BoardTile;
import model.shared.TileType;

public class Bookshelf implements Iterable<Bookshelf>{

	public static final int ROWS = 6;
	public static final int COLUMNS = 5;
	public final BookshelfTile[][] tiles = new BookshelfTile[ROWS][COLUMNS];
	private Player player;
	public boolean stateChanged = false; //stateChanged is a flag determining if the player has updated his bookshelf during the turn

	public Bookshelf(Player player) {
		initTiles();
		this.stateChanged = false;
	}

	// Initializes all tiles in the bookshelf, sets each tile to NULL (which also
	// means it's not displayed on the GUI)
	private void initTiles() {
		for (int row = 0; row < ROWS; row++) {
			for (int column = 0; column < COLUMNS; column++) {
				tiles[row][column] = new BookshelfTile(TileType.NULL, row, column, this);
			}
		}
	}

	// TODO: remove this method, used for debug
	public void fillRandom() {
		for (int row = 0; row < ROWS; row++) {
			for (int column = 0; column < COLUMNS; column++) {
				tiles[row][column].setType(TileType.randomType());
			}
		}
	}

	public void dropTile(BoardTile tile, int column) {
		throw new UnsupportedOperationException();
	}

	
	public boolean isFull() {
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLUMNS; col++) {
				if(this.tiles[row][col] != null && this.tiles[row][col].getType() == TileType.NULL) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Returns the tile at specified row / column
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public BookshelfTile getTile(int row, int column) {
		if (!isValidCell(row, column))
			throw new IllegalArgumentException(
					"row " + row + " and column " + column + " are not valid coordinates on the Bookshelf!");
		return tiles[row][column];
	}

	public TileType[] getRow(int row)
	{

		TileType[] res_row = new TileType[5];

		for (int col = 0; col < 5; col++)
		{
			res_row[col] = tiles[row][col].getType();
		}

		return res_row;

	}

	public TileType[] getColumn(int column)
	{

		TileType[] res_col = new TileType[6];

		for (int row = 0; row < 6; row++)
		{
			res_col[row] = tiles[row][column].getType();
		}

		return res_col;

	}

	public void setTileType(int row, int column, TileType tileType) {
		tiles[row][column].setType(tileType);
	}

	private boolean isValidCell(int row, int column) {
		return (row >= 0 && row < 6) && (column >= 0 && column < 5); // row 0-5 | column 0-4
	}

	@Override
	public Iterator<Bookshelf> iterator() {
		return null;
	}

	/**
	 * @return the stateChanged
	 */
	public boolean isStateChanged() {
		return stateChanged;
	}

	/**
	 * @param stateChanged the stateChanged to set
	 */
	public void setStateChanged(boolean stateChanged) {
		this.stateChanged = stateChanged;
	}
	
	public void addTile(BookshelfTile tile, int col) {
		if(col > COLUMNS) {
			throw new IllegalArgumentException("Unable to add a tile in  an non-existing column in the bookshelf!");
		}
		for (int row = ROWS - 1; row >= 0; row--) {
			if(this.tiles[row][col].getType() == TileType.NULL) {
				this.tiles[row][col] = tile;
				this.stateChanged = true;
				return;
			}
		}
		
	}
}
