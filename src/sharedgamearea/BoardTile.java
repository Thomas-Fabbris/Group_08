package sharedgamearea;

import java.awt.Dimension;

import gui.BoardTileLabel;
import main.Tile;
import main.TileType;

public class BoardTile extends Tile {	
	private int column;
	private int row;
	
	public BoardTile(TileType tile_type, int row, int column, Dimension board_size) {
		super(tile_type);
		this.row = row;
		this.column = column;
		this.setLabel(new BoardTileLabel(tile_type, row, column, board_size));
		this.setVisible(true);
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}


}
