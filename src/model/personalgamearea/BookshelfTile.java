package model.personalgamearea;

import java.util.Iterator;

import model.shared.Tile;
import model.shared.TileType;
/**
 * This Class models the Tiles of the Bookshelves
 * 
 *
 */
public class BookshelfTile extends Tile implements Iterable<BookshelfTile> {
	
	private int column;
	private int row;
	private Bookshelf shelf;
	
	public BookshelfTile(TileType tile_type, int row, int column, Bookshelf shelf) {
		super(tile_type);
		if(shelf == null) {
			throw new NullPointerException("shelf cannot be set to null while creating a BookshelfTile instance!");
		}
		
		this.row = row;
		this.column = column;
		this.shelf = shelf;
		this.enable();
	}
	
	public void setType(TileType type) {
		super.setType(type);
	}
	/**
	 * This method returns the adjacent tile in north position
	 *  
	 * @return
	 */
	public BookshelfTile tileUp() {
		return shelf.getTile(row-1, column);
	}
	/**
	 * This method returns the adjacent tile in south position
	 *  
	 * @return
	 */
	public BookshelfTile tileDown() {
		return shelf.getTile(row+1, column);
	}
	/**
	 * This method returns the adjacent tile in east position
	 *  
	 * @return
	 */
	public BookshelfTile tileRigth() {
		return shelf.getTile(row, column+1);
	}
	/**
	 * This method returns the adjacent tile in west position
	 *  
	 * @return
	 */
	public BookshelfTile tileLeft() {
		return shelf.getTile(row, column-1);
	}

	@Override
	public Iterator<BookshelfTile> iterator() {
		return null;
	}
}
