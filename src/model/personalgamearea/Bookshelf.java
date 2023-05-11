package model.personalgamearea;

import model.Player;
import model.commongamearea.BoardTile;
import model.shared.TileType;

public class Bookshelf {

	public static final int ROWS = 6;
	public static final int COLUMNS = 5;
	public final BookshelfTile[][] tiles = new BookshelfTile[ROWS][COLUMNS];
	private Player player;

	public Bookshelf() {
		initTiles();
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

	public Player getPlayer() {
		return player;
	}

	public boolean isFull() {
		throw new UnsupportedOperationException();
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
}
