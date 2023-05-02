package sharedgamearea;

import gui.sharedgamearea.BoardTileLabel;
import gui.sharedgamearea.BoardTileListener;
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
		this.label.addMouseListener(new BoardTileListener(board, this)); 
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
	
	public boolean isInteractible () {
		return (!this.getType().equals(TileType.NULL) && this.isVisible());
	} //checks if the tile exists on the board 
	
	public boolean canBePickedUp () {
		int contatore=0;
		if (this.tileUp().isInteractible())
			contatore ++;
		
		if (this.tileDown().isInteractible())
			contatore ++;
		
		if (this.tileLeft().isInteractible())
			contatore ++;
		
		if (this.tileRigth().isInteractible())
			contatore ++;
		
		if (contatore > 0 && contatore < 4) {
			return true;
		}else {
			return false;
		}
			
	}
	
}