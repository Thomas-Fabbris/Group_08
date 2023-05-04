package model.personalgamearea;

import model.shared.Tile;
import model.shared.TileType;

public class BookshelfTile extends Tile {
	
	private int column;
	private int row;
	private Bookshelf shelf;
	
	public BookshelfTile(TileType tile_type, int row, int column, Bookshelf shelf) {
		super(tile_type);
		this.row = row;
		this.column = column;
		this.shelf = shelf;
		this.enable();
	}
	
	public void setType(TileType type) {
		super.setType(type);
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
