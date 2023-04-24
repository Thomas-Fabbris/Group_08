package personalgamearea;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import gui.personalgamearea.BookshelfLabel;
import gui.personalgamearea.PersonalGameAreaWindow;
import main.Player;
import main.TileType;
import sharedgamearea.BoardTile;

public class Bookshelf {
	
	private BookshelfLabel label;
	private BookshelfTile[][] tiles = new BookshelfTile[5][6];
	private Player player;
	
	public Bookshelf() {
		label = new BookshelfLabel(PersonalGameAreaWindow.getInstance().getSize());
		initTiles();
	}
	
	//Initializes all tiles in the bookshelf, sets each tile to NULL (which also means it's not displayed on the GUI)
	private void initTiles() {
		for (int i = 0; i < tiles[0].length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				tiles[j][i] = new BookshelfTile(TileType.randomType(), j, i, this);
				this.label.tilesContainer.add(tiles[j][i].getLabel());
			}
		}
	}
	
	public BookshelfLabel getLabel() {
		return label;
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
	
	public BookshelfTile getTile(int row, int column) {
		if(!isValidCell(row, column))
			throw new IllegalArgumentException("row " + row + " and column " + column + " are not valid coordinates on the Bookshelf!");
		return tiles[row][column];
	}
	
	public void setTileType(int row, int column, TileType tileType) {
		tiles[row][column].setType(tileType);
	}
	
	private boolean isValidCell(int row, int column) {
		return (row > 0 && row < 5) && (column > 0 && column < 4);
	}
}
