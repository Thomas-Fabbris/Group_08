package personalgamearea;

import gui.personalgamearea.BookshelfTileLabel;
import gui.sharedgamearea.BoardTileLabel;
import main.Tile;
import main.TileType;
import sharedgamearea.Board;

public class BookshelfTile extends Tile {
	
	private int column;
	private int row;
	private Bookshelf shelf;
	
	public BookshelfTile(TileType tile_type, int row, int column, Bookshelf shelf) {
		super(tile_type);
		this.label = new BookshelfTileLabel(tile_type, shelf);
		this.row = row;
		this.column = column;
		this.shelf = shelf;
		this.setLabel(new BoardTileLabel(tile_type, row, column, shelf.getLabel().getSize()));
		this.setVisible(true);
	}
}
