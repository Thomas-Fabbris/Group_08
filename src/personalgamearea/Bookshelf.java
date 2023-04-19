package personalgamearea;

import gui.BookshelfLabel;
import main.TileType;
import sharedgamearea.BoardTile;

public class Bookshelf {
	
	private BookshelfLabel label;
	private BookshelfTile[][] tiles = new BookshelfTile[5][6];
	
	public Bookshelf() {
		
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
		
	}
}