package sharedgamearea;

import gui.sharedgamearea.BoardTileLabel;
import main.Tile;
import main.TileType;

public class BoardTile extends Tile{	
	private int column;
	private int row;
	private Board board;
	
	public BoardTile(TileType tile_type, int row, int column, Board board) {
		super(tile_type);
		this.row = row;
		this.column = column;
		this.board = board;
		this.setLabel(new BoardTileLabel(tile_type, row, column, board.getLabel().getSize()));
		this.setVisible(true);
	}

	public BoardTileLabel getLabel() {
		return (BoardTileLabel)label;
	}
	
	public void setType(TileType type) {
		super.setType(type);
		getLabel().setType(type);
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}

	public BoardTile tileUp() {
		return board.getTile(row-1, column);
	}
	
	public BoardTile tileDown() {
		return board.getTile(row+1, column);
	}
	
	public BoardTile tileRigth() {
		return board.getTile(row, column+1);
	}
	
	public BoardTile tileLeft() {
		return board.getTile(row, column-1);
	}
}
