package model.personalgamearea;

import model.shared.Tile;
import model.shared.TileType;
import view.personalgamearea.BookshelfTileLabel;
import view.personalgamearea.BookshelfTileListener;

public class BookshelfTile extends Tile {
	
	private int column;
	private int row;
	private Bookshelf shelf;
	
	public BookshelfTile(TileType tile_type, int row, int column, Bookshelf shelf) {
		super(tile_type);
		this.label = new BookshelfTileLabel(tile_type, row, column, shelf);
		this.row = row;
		this.column = column;
		this.shelf = shelf;
		this.setLabel(new BookshelfTileLabel(tile_type, row, column, shelf));
		this.label.addMouseListener(new BookshelfTileListener(this, shelf));
		this.setVisible(true);
	}
	
	public BookshelfTileLabel getLabel() {
		return (BookshelfTileLabel)label;
	}
	
	public void setType(TileType type) {
		super.setType(type);
		this.getLabel().setType(type);
	}
	
	public BookshelfTile tileUp() {
		return shelf.getTile(row-1, column);
	}
	
	public BookshelfTile tileDown() {
		return shelf.getTile(row+1, column);
	}
	
	public BookshelfTile tileRigth() {
		return shelf.getTile(row, column+1);
	}
	
	public BookshelfTile tileLeft() {
		return shelf.getTile(row, column-1);
	}
}
