package model.commongamearea;

import model.shared.Tile;
import model.shared.TileType;

public class BoardTile extends Tile{	
	private int column;
	private int row;
	private Board board;
	
	public BoardTile(TileType tile_type, int row, int column, Board board) {
		super(tile_type);
		this.row = row;
		this.column = column;
		this.board = board;
		this.isActive = true;
	}
	
	public void setType(TileType type) {
		super.setType(type);
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
	
		
	
	//checks if the tile exists on the board 
	public boolean isInteractible () {
		return (!this.getType().equals(TileType.NULL) && this.isActive());
	}
	
	//assigns the boolean (isSelectable) to the checked tile
	public boolean canBePickedUp (BoardTile tile) {
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