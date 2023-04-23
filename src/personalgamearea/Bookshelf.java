package personalgamearea;

import java.awt.Dimension;

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
		label = new BookshelfLabel(new Dimension(200, 200));
	}
	
	//Initializes all tiles in the bookshelf, sets each tile to NULL (which also means it's not displayed on the GUI)
	private void initTiles() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				tiles[i][j] = new BookshelfTile(TileType.NULL, i, j, this);
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
}
